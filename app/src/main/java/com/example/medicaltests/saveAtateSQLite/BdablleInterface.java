package com.example.medicaltests.saveAtateSQLite;

import com.example.medicaltests.account.User;

public interface BdablleInterface {
    public User select(String email);
    public void insert(User user);
    public void update(User user);
}
