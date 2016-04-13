package com.example.ianshinbro.trackerbat.app;

import android.app.Application;
import android.content.Context;

import com.example.ianshinbro.trackerbat.data.DatabaseHelper;
import com.example.ianshinbro.trackerbat.data.DatabaseManager;


/**
 * Created by ianshinbro on 4/12/2016.
 */
public class App extends Application {

    private static Context context;
    private static DatabaseHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DatabaseHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }

    public static Context getContext(){
        return context;
    }
}
