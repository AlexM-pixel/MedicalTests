package com.example.medicaltests.all_analysis;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TestDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Test test);

    @Query("SELECT * FROM test")
    List<Test> getAll();

}
