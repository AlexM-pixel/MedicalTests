package com.example.medicaltests.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.medicaltests.DaoMaster;
import com.example.medicaltests.DaoSession;
import com.example.medicaltests.all_analysis.Test;
import com.example.medicaltests.contextApp.GreenDaoApp;

import org.greenrobot.greendao.database.Database;


public class DbOpenHelper extends DaoMaster.OpenHelper {

    private Context context;

    public DbOpenHelper(Context context, String name) {
        super(context, name);
        this.context = context;
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
        DaoSession daoSession = new DaoMaster(new DbOpenHelper(context, "greenDaoDb").getWritableDb()).newSession();
        daoSession.getTestDao().insert(new Test(null,"Общий анализ крови"));
        daoSession.getTestDao().insert(new Test(null,"Гормоны щитовидной железы"));
        daoSession.getTestDao().insert(new Test(null,"Биохимический анализ крови"));
        daoSession.getTestDao().insert(new Test(null,"Общий анализ мочи"));
        daoSession.getTestDao().insert(new Test(null,"Углеводный обмен"));
        daoSession.getTestDao().insert(new Test(null,"Почечные пробы"));
        daoSession.getTestDao().insert(new Test(null,"Липидограмма"));
        daoSession.getTestDao().insert(new Test(null,"Маркеры костной ткани"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        switch (oldVersion) {
//            case 1:
//            case 2:
//        }
        onCreate(db);
    }
}
