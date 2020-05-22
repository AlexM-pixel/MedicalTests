package com.example.medicaltests.all_analysis;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "test")
public class Test {
    @Id(autoincrement = true)
    private Long id= 0L;
    @Property(nameInDb = "test_name")
   private String nameTest;

    @Generated(hash = 407115561)
    public Test(Long id, String nameTest) {
        this.id = id;
        this.nameTest = nameTest;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

    public Long getId() {
        return id;
    }

    String getNameTest() {
        return nameTest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    void setNameTest(String nameTest) {
        this.nameTest = nameTest;
    }

}
