package com.example.medicaltests.contextApp;

import android.app.Application;
import android.content.Context;

import com.example.medicaltests.DaoMaster;
import com.example.medicaltests.DaoSession;
import com.example.medicaltests.db.DbOpenHelper;


public class GreenDaoApp extends Application {
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = new DaoMaster(new DbOpenHelper(this, "greenDaoDb").getWritableDb()).newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }

}
