package com.test.weatherapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.test.weatherapp.R;
import com.test.weatherapp.comm.ApiEndpoints;
import com.test.weatherapp.comm.RestAdapter;
import com.test.weatherapp.comm.data.ForecastData;
import com.test.weatherapp.comm.data.GetForecastResponse;
import com.test.weatherapp.ui.UiUtils;
import com.test.weatherapp.ui.fragment.ForecastInfoFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Landing page of the app. It displays the viewpager including 5 pages (days) of weather forecast data
 * fetched from OpenWeatherMap api. An async call is made to api while this activity loads.
 *
 * @author Usman
 */
public class HomeActivity extends CommonActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String TAG = "HomeActivity";
    private Map<String, List<ForecastData>> mDataMap;
    private String[] mSortedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.weather_forecast);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        getWeatherForecast();

    }

    private void getWeatherForecast() {
        Retrofit retrofit = RestAdapter.createRetrofit(this);
        ApiEndpoints apiEndpoints = retrofit.create(ApiEndpoints.class);
        showProcessingDialog(getString(R.string.fetching_weather_info));

        Call<GetForecastResponse> call = apiEndpoints.getForecast(getString(R.string.london_id));

        call.enqueue(new Callback<GetForecastResponse>() {
            @Override
            public void onResponse(Call<GetForecastResponse> call, Response<GetForecastResponse> response) {

                if (!response.isSuccessful()) {
                    dismissProcessingDialog();
                    UiUtils.showUnknownErrDialog(HomeActivity.this);
                    return;
                }

                processForecastData(response.body());

            }

            @Override
            public void onFailure(Call<GetForecastResponse> call, Throwable t) {

                dismissProcessingDialog();
                UiUtils.showConnectionAlertErrDialog(HomeActivity.this);
            }
        });


    }

    /**
     * Converts the single list of forecast data, fetched from OpenWeatherMap api, into a day-wise map.
     * This will allow each day's forecast to display on a separate page of ViewPager.
     *
     * @param forecastDataResponse
     */
    private void processForecastData(GetForecastResponse forecastDataResponse) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        mDataMap = new HashMap<>();

        for (ForecastData data : forecastDataResponse.forecastDataList) {
            // Converting unix timestamp from secs to millisecs
            String day = dateFormat.format(data.timestamp * 1000L);
            List<ForecastData> dayList = mDataMap.get(day);
            if (dayList == null) {
                dayList = new ArrayList<>(8);
                mDataMap.put(day, dayList);
            }
            dayList.add(data);
        }

        mSortedList = new String[mDataMap.keySet().size()];
        mSortedList = mDataMap.keySet().toArray(mSortedList);
        Arrays.sort(mSortedList);
        dismissProcessingDialog();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String key = mSortedList[position];
            boolean isFirstItem = position == 0 ? true : false;
            boolean isLastItem = position == mSortedList.length - 1 ? true : false;
            return ForecastInfoFragment.newInstance(mDataMap.get(key), isFirstItem, isLastItem);
        }

        @Override
        public int getCount() {
            return mSortedList.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
