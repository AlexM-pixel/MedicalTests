package com.example.medicaltests.validation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;


import androidx.fragment.app.FragmentManager;

import com.example.medicaltests.dialogues.FragmentDialogAlert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static final String ALERT_PASSW = "1";
    private static Validation sInstance;

    private Validation() {
    }

    public static Validation getInstance() {
        if (sInstance == null) {
            sInstance = new Validation();
        }
        return sInstance;
    }


    public boolean validEmail(String mail, EditText editMail) {
        String valid = "^[a-zA-Z0-9.]{1,}[@]{1}[a-z]{2,7}[.]{1}+[a-z]{2,6}";
        if (mail.equals("")) {
            editMail.setHintTextColor(Color.RED);
            editMail.setHint("write email !");
            return false;
        }
        if (!mail.matches(valid)) {
            editMail.setText("");
            editMail.setHintTextColor(Color.RED);
            editMail.setHint("incorrect email !");
            return false;
        }
        return true;
    }


    @SuppressLint("ResourceType")
    public boolean validPassword(String pass, EditText textPassword, FragmentManager fm) {
        FragmentDialogAlert alertDialogFragment = new FragmentDialogAlert();
        Bundle bundle = new Bundle();
        if (pass.equals("")) {                                       //если ничего не написано, делаю подсказку красным
            textPassword.setHintTextColor(Color.RED);
            textPassword.setHint("write password !");
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(pass);
        if (!matcher.find()) {                                                   //строка должна содержать хотя бы одно число;
            bundle.putString(ALERT_PASSW, "пароль должен содержать минимум одно число");
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(fm, "key1");
            return false;
        }
        pattern = Pattern.compile("[a-z]");
        matcher = pattern.matcher(pass);
        if (!matcher.find()) {                           // строка должна содержать минимум одну латинскую букву в нижнем регистре
            // Toast.makeText(context, "пароль должен содержать минимум одну латинскую букву в нижнем регистре", Toast.LENGTH_SHORT).show();
            bundle.putString(ALERT_PASSW, "пароль должен содержать минимум одну латинскую букву в нижнем регистре");
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(fm, "key2");
            return false;
        }
        pattern = Pattern.compile("[A-Z]");
        matcher = pattern.matcher(pass);
        if (!matcher.find()) {                           //строка должна содержать минимум одну латинскую букву в вверхнем регистре
            // Toast.makeText(context, "пароль должен содержать минимум одну латинскую букву в вверхнем регистре", Toast.LENGTH_SHORT).show();
            bundle.putString(ALERT_PASSW, "пароль должен содержать минимум одну латинскую букву в ВВЕРХНЕМ регистре");
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(fm, "key3");
            return false;
        }
        if (pass.length() < 6) {                        // строка состоит не менее чем из 6 символов.
            // Toast.makeText(context, "пароль должен содержать минимум 6 символов", Toast.LENGTH_SHORT).show();
            bundle.putString(ALERT_PASSW, "пароль должен содержать минимум 6 символов");
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(fm, "key4");
            return false;
        }
        return true;
    }


    public boolean dateBorning(int date) {                //может не понадобиться
        return false;
    }


}
