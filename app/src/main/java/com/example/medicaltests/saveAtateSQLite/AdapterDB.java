package com.example.medicaltests.saveAtateSQLite;

import com.example.medicaltests.account.User;

public class AdapterDB implements BdablleInterface {
    private MyAppDatabase myAppDatabase;
    private DatabaseHelper databaseHelper;

    public AdapterDB(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public AdapterDB(MyAppDatabase myAppDatabase) {
        this.myAppDatabase = myAppDatabase;
    }

    @Override
    public User select(String email) {
        //return   myAppDatabase.userDao().findUserByEmail(email);
        return databaseHelper.findUserByEmail(email);
    }

    @Override
    public void insert(User user) {
        // myAppDatabase.userDao().insert(user);
        databaseHelper.insertUser(user);
    }

    @Override
    public void update(User user) {
        //  myAppDatabase.userDao().update(user);
        databaseHelper.updateUser(user);
    }
}
