package com.example.medicaltests.account;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicaltests.R;
import com.example.medicaltests.saveAtateSQLite.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;

import static android.app.Activity.RESULT_CANCELED;

public class AccountFragment extends Fragment {
    private TextView name;
    private TextView email;
    private TextView sex;
    private TextView age;
    private TextView weight;
    private DatabaseHelper BdHelper = DatabaseHelper.getInstance();
    private String nameBD;
    private String ageBD;
    private String emailBD;
    private String weightBD;
    private String sexBD;
    private String email_fromFirebase;

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
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            db = BdHelper.getReadableDatabase();
            cursor = db.query(DatabaseHelper.TABLE_NAME_USER,
                    new String[]{DatabaseHelper.NAME_USER, DatabaseHelper.AGE_USER, DatabaseHelper.WEIGHT_USER, DatabaseHelper.SEX_USER, DatabaseHelper.EMAIL_USER},
                    DatabaseHelper.EMAIL_USER+" = ?",new String[]{email_fromFirebase}, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    nameBD = cursor.getString(0);
                    ageBD = cursor.getString(1);
                    weightBD = cursor.getString(2);
                    sexBD = cursor.getString(3);
                    emailBD = cursor.getString(4);
                    name.setText(nameBD);
                    email.setText(emailBD);
                    sex.setText(sexBD);
                    age.setText(ageBD);
                    weight.setText(weightBD);
                } while (cursor.moveToNext());
            }
        } catch (SQLException e) {
            Toast.makeText(getContext(), "ОЙ!", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
                db.close();
            }
        }
    }

    private void saveChangeProfilValues() {
        SQLiteDatabase db = null;
        ContentValues contentValues = new ContentValues();
        if (!nameBD.equals(name.getText().toString())) {
            contentValues.put(DatabaseHelper.NAME_USER, name.getText().toString());
        }
        if (!emailBD.equals(email.getText().toString())) {
            contentValues.put(DatabaseHelper.EMAIL_USER, email.getText().toString());
        }
        if (!sexBD.equals(sex.getText().toString())) {
            contentValues.put(DatabaseHelper.SEX_USER, sex.getText().toString());
        }
        if (!weightBD.equals(weight.getText().toString())) {
            contentValues.put(DatabaseHelper.WEIGHT_USER, weight.getText().toString());
        }
        if (!ageBD.equals(age.getText().toString())) {
            contentValues.put(DatabaseHelper.AGE_USER, age.getText().toString());
        }
        try {
            db = BdHelper.getWritableDatabase();
            db.update(DatabaseHelper.TABLE_NAME_USER, contentValues, DatabaseHelper.EMAIL_USER +"=?", new String[]{email_fromFirebase});

        } catch (SQLException e) {
            Toast.makeText(getContext(), "ОЙ!", Toast.LENGTH_SHORT).show();
        } finally {
            assert db != null;
            db.close();
        }
    }
}
