package com.example.medicaltests.saveAtateSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.medicaltests.R;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "medDB";
    private static final int DB_VERSION = 1;
    // table alltests
    public static final String TABLE_NAME = "alltests";
    private static final String KEY_ID = "_id";
    public static final String TEST_NAME = "test_name";
    public static final String TEST_IMAGE = "image_analys";
    // table userAccount
    public static final String TABLE_NAME_USER = "userAccount";
    private static final String KEY_ID_USER = "_id";
    public static final String NAME_USER = "user_name";
    public static final String SURNAME_USER = "user_surname";
    public static final String AGE_USER = "user_surname";
    public static final String WEIGHT_USER = "user_surname";
    public static final String SEX_USER = "user_surname";
    public static final String EMAIL_USER = "user_surname";
    public static final String PASSWORD_USER = "user_surname";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME
                + "("
                + KEY_ID + " integer PRIMARY KEY AUTOINCREMENT,"
                + TEST_NAME + " TEXT,"
                + TEST_IMAGE + " INTEGER" + ")");
        insertAnalys(db, "Общий анализ крови", R.drawable.true_blood);
        insertAnalys(db, "Гормоны щитовидной железы", R.drawable.butterfly);
        insertAnalys(db, "Биохимический анализ крови", R.drawable.icons_blood48);
        insertAnalys(db, "Общий анализ мочи", R.drawable.mocha);
        insertAnalys(db, "Углеводный обмен", R.drawable.uglevodi);
        insertAnalys(db, "Почечные пробы", R.drawable.pochka);
        insertAnalys(db, "Липидограмма", R.drawable.lipidogramma);
        insertAnalys(db, "Маркеры костной ткани", R.drawable.bone);

        db.execSQL("Create table " + TABLE_NAME_USER
                + "("
                + KEY_ID_USER + " integer PRIMARY KEY AUTOINCREMENT,"
                + NAME_USER + " TEXT,"
                + SURNAME_USER + " TEXT,"
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


    private static void insertAnalys(SQLiteDatabase db, String testName, int imageId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEST_NAME, testName);
        contentValues.put(TEST_IMAGE, imageId);
        db.insert(TABLE_NAME, null, contentValues);
    }
}
