package com.example.medicaltests;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SpisokActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisok);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setResult(RESULT_OK);
        Button button= findViewById(R.id.button_out);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
