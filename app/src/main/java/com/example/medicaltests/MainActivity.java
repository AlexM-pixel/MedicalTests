package com.example.medicaltests;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicaltests.dialogues.RecoveryDialog;
import com.example.medicaltests.validation.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String MAIL= "email";
    int count = 0;
    private TextView mStatusTextView;
    private EditText emailField;
    private EditText mPasswordField;
    ProgressBar ProgressBar;
    FirebaseAuth mAuth;
    FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ProgressBar = findViewById(R.id.progressBar);
        mStatusTextView = findViewById(R.id.status);
        emailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);

        findViewById(R.id.emailSignInButton).setOnClickListener(this);
        findViewById(R.id.emailCreateAccountButton).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        updateUI();
        fm = getSupportFragmentManager();

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    private void createAccount(String email, String password) {
        if (!Validation.getInstance().validEmail(email, emailField)) {
            return;
        }
        if (!Validation.getInstance().validPassword(password, mPasswordField, fm)) {
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
                            updateUI();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Ошибка Авторизации",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }
                        ProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
        // [END create_user_with_email]
    }


    private void signIn(final String email, String password) {

        if (email.equals("")) {
            emailField.setHintTextColor(Color.RED);
            emailField.setHint("write email !");
        }
        if (password.equals("")) {
            mPasswordField.setHintTextColor(Color.RED);
            mPasswordField.setHint("write password !");
        } else {
            ProgressBar.setVisibility(View.VISIBLE);
            // [START sign_in_with_email]
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                updateUI();
                                count = 0;
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Ошибка Авторизации",
                                        Toast.LENGTH_SHORT).show();
                                count++;
                                if (count == 3) {       //если пароль или почта введены не правильно 3 раза подряд
                                    RecoveryDialog recoveryDialog = new RecoveryDialog();
                                    Bundle agruments = new Bundle();
                                    agruments.putString(MAIL,email);                   // передал адрес почты через аргументы
                                    recoveryDialog.setArguments(agruments);
                                    recoveryDialog.show(fm,"passw");
                                }
                            }
                            ProgressBar.setVisibility(View.INVISIBLE);
                        }
                    });
            // [END sign_in_with_email]
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {                                                // чтобы опять не заходило в это Активити если пользователь уже залогинелся
            finish();
        }
    }

    void updateUI() {
        ProgressBar.setVisibility(View.INVISIBLE);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(this, SpisokActivity.class);
            startActivityForResult(intent, 1);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.emailCreateAccountButton:
                createAccount(emailField.getText().toString(), mPasswordField.getText().toString());
                break;
            case R.id.emailSignInButton:
                signIn(emailField.getText().toString(), mPasswordField.getText().toString());
                break;
        }
    }
}
