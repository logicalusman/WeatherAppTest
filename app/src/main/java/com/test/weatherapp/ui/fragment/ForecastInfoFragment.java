package com.test.weatherapp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.weatherapp.R;
import com.test.weatherapp.comm.data.ForecastData;
import com.test.weatherapp.util.Utils;

import java.util.List;

/**
 * Displays the weather forecast information fo the given day.
 *
 * @author Usman
 */
public class ForecastInfoFragment extends Fragment {

    private List<ForecastData> mForecastData;
    private Boolean mIsFirstItem = false;
    private Boolean mIsLastItem = false;
    private RecyclerView mForecastRv;
    private TextView mDateTv;
    private ForecastListAdapter mForecastListAdapter;
    private ImageView mScrollLeftIv, mScrollRightIv;


    public ForecastInfoFragment() {
        // Required empty public constructor
    }

    public static ForecastInfoFragment newInstance(@NonNull List<ForecastData> forecastData, @Nullable Boolean isFirstItem, @Nullable Boolean isLastItem) {
        ForecastInfoFragment fragment = new ForecastInfoFragment();
        fragment.mForecastData = forecastData;
        if (isFirstItem != null) {
            fragment.mIsFirstItem = isFirstItem;
        }
        if (isLastItem != null) {
            fragment.mIsLastItem = isLastItem;
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_forecast_info, container, false);
        mDateTv = (TextView) v.findViewById(R.id.date_tv);
        // set date of the day. All items in the list belongs to same day
        if (mForecastData != null && !mForecastData.isEmpty()) {
            mDateTv.setText(Utils.getDate(mForecastData.get(0).timestamp * 1000L));
        }
        mScrollLeftIv = (ImageView) v.findViewById(R.id.scroll_left_iv);
        mScrollRightIv = (ImageView) v.findViewById(R.id.scroll_right_iv);
        mForecastRv = (RecyclerView) v.findViewById(R.id.forecast_list_rv);
        // add layout manager to rv
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mForecastRv.setLayoutManager(llm);
        // add divider to rv
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mForecastRv.getContext(),
                llm.getOrientation());
        mForecastRv.addItemDecoration(dividerItemDecoration);
        showScrollingViews();
        setAdapterToList();
        return v;
    }

    private void showScrollingViews() {
        if (mIsFirstItem) {
            mScrollLeftIv.setVisibility(View.INVISIBLE);
        }
        if (mIsLastItem) {
            mScrollRightIv.setVisibility(View.INVISIBLE);
        }
    }

    private void setAdapterToList() {

        mForecastListAdapter = new ForecastListAdapter();
        mForecastRv.setAdapter(mForecastListAdapter);

    }


    private class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.VH> {


        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.forecast_row, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {

            ForecastData data = mForecastData.get(position);
            String time = String.format("@ %s", Utils.getTime(data.timestamp * 1000L));
            String temperature = String.format("%.02f C", data.temperatureInfo.temperature);

            holder.mTimeTv.setText(time);
            holder.mTemperatureTv.setText(temperature);
            holder.mDescTv.setText(data.weatherInfo.get(0).description);

        }

        @Override
        public int getItemCount() {
            if (mForecastData != null) {
                return mForecastData.size();
            }
            return 0;
        }


        class VH extends RecyclerView.ViewHolder {

            TextView mTimeTv, mTemperatureTv, mDescTv;

            public VH(View v) {
                super(v);
                mTimeTv = (TextView) v.findViewById(R.id.time_tv);
                mTemperatureTv = (TextView) v.findViewById(R.id.temperature_tv);
                mDescTv = (TextView) v.findViewById(R.id.desc_tv);
            }
        }

    }

}
