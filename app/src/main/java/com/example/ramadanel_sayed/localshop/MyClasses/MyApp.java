package com.example.ramadanel_sayed.localshop.MyClasses;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by Ramadan El-Sayed on 12/26/2016.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
                Configuration dbConfiguration=new Configuration.Builder(this).setDatabaseName("dbdbdb.db").
                addModelClasses(ProfileData.class,ProductData.class,Orders.class).create();
                ActiveAndroid.initialize(dbConfiguration);
    }
}
