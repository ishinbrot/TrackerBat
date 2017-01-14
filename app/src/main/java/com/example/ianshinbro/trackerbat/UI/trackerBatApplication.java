package com.example.ianshinbro.trackerbat.UI;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by iansh on 1/14/2017.
 */

public class trackerBatApplication extends com.activeandroid.app.Application {

    public void onCreate() {
        super.onCreate();
        Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("trackerBat.db").create();
        ActiveAndroid.initialize(dbConfiguration);
    }
}
