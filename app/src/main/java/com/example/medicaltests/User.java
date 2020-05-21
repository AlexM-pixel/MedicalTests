package com.example.medicaltests;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "user")
public class User {
    @Id(autoincrement = true)
    private Long id= 0L;
    @Property(nameInDb ="name")
    private String nameUser;
    @Property(nameInDb ="age")
    private String age;
    @Property(nameInDb = "email")
    private String email;
    @Property(nameInDb ="password")
    private String password;
    @Property(nameInDb = "weight")
    private String weight;
    @Property(nameInDb = "sex")
    private String sex;

//    User(long id, String name, String age, String email, String password, String weight, String sex) {
//      this.id=id;
//        this.nameUser = name;
//        this.age = age;
//        this.email = email;
//        this.password = password;
//        this.weight = weight;
//        this.sex = sex;
//    }

 

    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 1835802666)
    public User(Long id, String nameUser, String age, String email, String password, String weight,
            String sex) {
        this.id = id;
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
    public void setId(Long id) {
        this.id=id;
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

    public String getPassword() {
        return password;
    }

    public String getWeight() {
        return weight;
    }

    public String getSex() {
        return sex;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
