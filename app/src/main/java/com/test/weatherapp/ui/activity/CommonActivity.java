package com.test.weatherapp.ui.activity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Contains functionality that can be shared across activities; avoid writing boilerplate code.
 *
 * @author Usman
 */
public class CommonActivity extends AppCompatActivity {


    private ProgressDialog mProcessingDialog;

    public void showProcessingDialog(@NonNull String message) {

        mProcessingDialog = new ProgressDialog(this);
        mProcessingDialog.setMessage(message);
        mProcessingDialog.setCancelable(true);
        mProcessingDialog.show();
    }

    public void dismissProcessingDialog() {
        if (mProcessingDialog != null && mProcessingDialog.isShowing()) {
            try {
                mProcessingDialog.dismiss();
                mProcessingDialog = null;
            } catch (Exception ignored) {
            }
        }
    }


}
