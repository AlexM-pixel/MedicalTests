package com.example.medicaltests.saveAtateSQLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.medicaltests.contextApp.MyApp;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "medDB";
    private static final int DB_VERSION = 2;
    // table alltests
    public static final String TABLE_NAME = "alltests";
    private static final String KEY_ID = "_id";
    public static final String TEST_NAME = "test_name";
    // table userAccount
    public static final String TABLE_NAME_USER = "user_profil";
    private static final String KEY_ID_USER = "_id";
    public static final String NAME_USER = "user_name";
    public static final String AGE_USER = "user_age";
    public static final String WEIGHT_USER = "user_weight";
    public static final String SEX_USER = "user_sex";
    public static final String EMAIL_USER = "user_email";
    public static final String PASSWORD_USER = "user_pasword";

    private static DatabaseHelper databaseHelper;

    private DatabaseHelper() {
        super(MyApp.context, DB_NAME, null, DB_VERSION);
    }

    public static DatabaseHelper getInstance() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME
                + "("
                + KEY_ID + " integer PRIMARY KEY AUTOINCREMENT,"
                + TEST_NAME + " TEXT" + ")");

        insertAnalys(db, "Общий анализ крови");
        insertAnalys(db, "Гормоны щитовидной железы");
        insertAnalys(db, "Биохимический анализ крови");
        insertAnalys(db, "Общий анализ мочи");
        insertAnalys(db, "Углеводный обмен");
        insertAnalys(db, "Почечные пробы");
        insertAnalys(db, "Липидограмма");
        insertAnalys(db, "Маркеры костной ткани");

        db.execSQL("Create table " + TABLE_NAME_USER
                + "("
                + KEY_ID_USER + " integer PRIMARY KEY AUTOINCREMENT,"
                + NAME_USER + " TEXT,"
                + AGE_USER + " INTEGER,"
                + WEIGHT_USER + " INTEGER,"
                + SEX_USER + " TEXT,"
                + EMAIL_USER + " TEXT,"
                + PASSWORD_USER + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }


    private static void insertAnalys(SQLiteDatabase db, String testName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEST_NAME, testName);
        db.insert(TABLE_NAME, null, contentValues);
    }
}
