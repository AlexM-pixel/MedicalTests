package com.example.medicaltests;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mStatusTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
ProgressBar ProgressBar;
    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ProgressBar = findViewById(R.id.progressBar);
        mStatusTextView = findViewById(R.id.status);
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);

        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }
       ProgressBar.setVisibility(View.VISIBLE);

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        ProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        ProgressBar.setVisibility(View.VISIBLE);
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            mStatusTextView.setText("не вышло");
                        }
                        ProgressBar.setVisibility(View.INVISIBLE);
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private boolean validateForm() {                                                                //  доделать валидацию
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (email.length()==0) {
            mEmailField.setError("write email");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (password.length()==0) {
            mPasswordField.setError("write password");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {                                                // чтобы опять не заходило в это Активити если пользователь уже залогинелся
            finish();
        }
    }

    void updateUI(FirebaseUser user) {
        ProgressBar.setVisibility(View.INVISIBLE);
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, SpisokActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.emailCreateAccountButton:
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
            case R.id.emailSignInButton:
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
                break;
        }
    }

}
