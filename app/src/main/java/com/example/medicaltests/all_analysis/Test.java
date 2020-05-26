package com.example.medicaltests.all_analysis;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test")
public class Test {
    @PrimaryKey(autoGenerate = true)
    private Long id= 0L;
    @ColumnInfo(name = "name")
    String testName;
public Test() {}
    public Test(Long id, String nameTest) {
        this.id = id;
        this.testName = nameTest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
