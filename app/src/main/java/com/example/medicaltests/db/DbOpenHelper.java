package com.example.medicaltests.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.medicaltests.DaoMaster;


public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
        }
    }
}
