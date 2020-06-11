package com.example.medicaltests.saveAtateSQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.example.medicaltests.account.User;
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

    public void insertUser(User user) {
        DatabaseHelper BdHelper = DatabaseHelper.getInstance();
        Cursor cursor = null;
        SQLiteDatabase db = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PASSWORD_USER, user.getPassword());
        contentValues.put(DatabaseHelper.EMAIL_USER, user.getEmail());
        contentValues.put(DatabaseHelper.NAME_USER, user.getNameUser());
        contentValues.put(DatabaseHelper.SEX_USER, user.getSex());
        contentValues.put(DatabaseHelper.WEIGHT_USER, user.getWeight());
        contentValues.put(DatabaseHelper.AGE_USER, user.getAge());
        try {
            db = BdHelper.getWritableDatabase();
            cursor = db.query(DatabaseHelper.TABLE_NAME_USER,
                    null,
                    null, null, null, null, null);
            db.insert(DatabaseHelper.TABLE_NAME_USER, null, contentValues);
        } catch (SQLException e) {
            Toast.makeText(MyApp.context, "ОЙ!", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
                db.close();
            }
        }
    }

    public void updateUser(User user) {
        DatabaseHelper BdHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME_USER, user.getNameUser());
        contentValues.put(DatabaseHelper.EMAIL_USER, user.getEmail());
        contentValues.put(DatabaseHelper.SEX_USER, user.getSex());
        contentValues.put(DatabaseHelper.WEIGHT_USER, user.getWeight());
        contentValues.put(DatabaseHelper.AGE_USER, user.getAge());
        try {
            db = BdHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_NAME_USER, contentValues, DatabaseHelper.EMAIL_USER +"=?", new String[]{user.getEmail()});

        } catch (SQLException e) {
            Toast.makeText(MyApp.context, "ОЙ!", Toast.LENGTH_SHORT).show();
        } finally {
           if (db!=null) {
               db.close();
           }
        }
    }

    public User findUserByEmail(String email) {
        User user = null;
        DatabaseHelper BdHelper = DatabaseHelper.getInstance();
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = BdHelper.getReadableDatabase();
            cursor = db.query(DatabaseHelper.TABLE_NAME_USER,
                    new String[]{DatabaseHelper.NAME_USER, DatabaseHelper.AGE_USER, DatabaseHelper.WEIGHT_USER, DatabaseHelper.SEX_USER, DatabaseHelper.EMAIL_USER},
                    DatabaseHelper.EMAIL_USER + " = ?", new String[]{email}, null, null, null);
            if (cursor.moveToFirst()) {
                String nameBD = cursor.getString(0);
                String ageBD = cursor.getString(1);
                String weightBD = cursor.getString(2);
                String sexBD = cursor.getString(3);
                String emailBD = cursor.getString(4);
                user = new User(null, nameBD, ageBD, emailBD, null, weightBD, sexBD);

            }
        } catch (SQLException e) {
            Toast.makeText(MyApp.context, "ОЙ!", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
                db.close();
            }
        }
        return user;
    }

}