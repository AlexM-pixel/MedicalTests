package com.example.medicaltests.account;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    User findUserByEmail(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(User user);
}
