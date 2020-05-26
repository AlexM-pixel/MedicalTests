package com.example.medicaltests.saveAtateSQLite;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.medicaltests.account.User;
import com.example.medicaltests.account.UserDao;
import com.example.medicaltests.all_analysis.Test;
import com.example.medicaltests.all_analysis.TestDao;

import static com.example.medicaltests.contextApp.MyApp.context;

@Database(entities = {Test.class, User.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    private static MyAppDatabase INSTANCE;
    private static final String DB_NAME = "medical.db";

    public static MyAppDatabase getInstance() {
        if (INSTANCE == null) {
            synchronized (MyAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyAppDatabase.class, DB_NAME)
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("MedicalDatabase", "populating with data...");
                                    new UserDbAsync(INSTANCE).execute();
                                }
                            })
                      //      .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TestDao testDao();

    public abstract UserDao userDao();

    private static class UserDbAsync extends AsyncTask<Void, Void, Void> {
        private final UserDao userDaoDao;
        private final TestDao testDao;

        private UserDbAsync(MyAppDatabase instance) {
            this.userDaoDao = instance.userDao();
            this.testDao = instance.testDao();
        }


        @Override
        protected Void doInBackground(Void... v) {
            INSTANCE.testDao().insert(new Test(null, "Общий анализ крови"));
            INSTANCE.testDao().insert(new Test(null, "Гормоны щитовидной железы"));
            INSTANCE.testDao().insert(new Test(null, "Биохимический анализ крови"));
            INSTANCE.testDao().insert(new Test(null, "Общий анализ мочи"));
            INSTANCE.testDao().insert(new Test(null, "Углеводный обмен"));
            INSTANCE.testDao().insert(new Test(null, "Почечные пробы"));
            INSTANCE.testDao().insert(new Test(null, "Липидограмма"));
            INSTANCE.testDao().insert(new Test(null, "Маркеры костной ткани"));
            return null;
        }
    }
}
