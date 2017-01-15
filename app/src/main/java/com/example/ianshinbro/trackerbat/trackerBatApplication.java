package com.example.ianshinbro.trackerbat;


import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;


/**
 * Created by iansh on 1/14/2017.
 */


public class trackerBatApplication extends Application {

    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}
