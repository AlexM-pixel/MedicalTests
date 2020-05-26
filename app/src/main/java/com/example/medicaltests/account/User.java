package com.example.medicaltests.account;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private Long id= 0L;
    private String nameUser;
    private String age;
    private String email;
    private String password;
    private String weight;
    private String sex;

    public User(Long id, String nameUser, String age, String email, String password, String weight,
                String sex) {
        this.nameUser = nameUser;
        this.age = age;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNameUser() {
        return nameUser;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getWeight() {
        return weight;
    }

    public String getSex() {
        return sex;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
