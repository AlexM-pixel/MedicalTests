package com.example.medicaltests.account;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicaltests.R;
import com.example.medicaltests.all_analysis.SpisokFragment;
import com.example.medicaltests.all_analysis.Test;
import com.example.medicaltests.all_analysis.TestDao;
import com.example.medicaltests.saveAtateSQLite.MyAppDatabase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_CANCELED;

public class AccountFragment extends Fragment {
    private TextView name;
    private TextView email;
    private TextView sex;
    private TextView age;
    private TextView weight;
    private String nameBD;
    private String ageBD;
    private String weightBD;
    private String sexBD;
    private String email_fromFirebase;
    private User user;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account, container, false);
        Button buttonSave = view.findViewById(R.id.button_saveChange);
        Button button_logOut = view.findViewById(R.id.logout);
        button_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().setResult(RESULT_CANCELED);
                getActivity().finish();
            }
        });
        name = view.findViewById(R.id.textView_name);
        email = view.findViewById(R.id.textView_email);
        sex = view.findViewById(R.id.textView_sex);
        age = view.findViewById(R.id.textView_old);
        weight = view.findViewById(R.id.textView_weight);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChangeProfilValues();
            }
        });
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        email_fromFirebase = mAuth.getCurrentUser().getEmail();
        displayProfilValues();
        return view;
    }

    private void displayProfilValues() {
        UserDbAsync userDbAsync = new UserDbAsync(MyAppDatabase.getInstance());
        userDbAsync.execute(email_fromFirebase);
        try {
            user = userDbAsync.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
   //  AsyncTask.execute(() -> MyAppDatabase.getInstance().userDao().findUserByEmail(email_fromFirebase));

        if (user!=null) {
            nameBD = user.getNameUser();
            ageBD = user.getAge();
            weightBD = user.getWeight();
            sexBD = user.getSex();
            String emailBD = user.getEmail();
            //editTexts
            name.setText(nameBD);
            email.setText(emailBD);
            sex.setText(sexBD);
            age.setText(ageBD);
            weight.setText(weightBD);
        }
    }

    private void saveChangeProfilValues() {
        if (!nameBD.equals(name.getText().toString())) {
            user.setNameUser(name.getText().toString());
        }
        if (!sexBD.equals(sex.getText().toString())) {
            user.setSex(sex.getText().toString());
        }
        if (!weightBD.equals(weight.getText().toString())) {
            user.setWeight(weight.getText().toString());
        }
        if (!ageBD.equals(age.getText().toString())) {
            user.setAge(age.getText().toString());
        }
        AsyncTask.execute(() -> MyAppDatabase.getInstance().userDao().update(user));

    }

    private static class UserDbAsync extends AsyncTask<String, Void, User> {
        private final UserDao userDao;

        private UserDbAsync(MyAppDatabase instance) {
            this.userDao = instance.userDao();
        }

        @Override
        protected User doInBackground(String... strings) {
            return userDao.findUserByEmail(strings[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // тут крутелку загрузки можно добавить
        }
    }
}
