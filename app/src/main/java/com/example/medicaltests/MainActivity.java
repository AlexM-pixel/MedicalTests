package com.example.medicaltests;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.medicaltests.dialogues.RecoveryDialog;
import com.example.medicaltests.validation.Validation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String MAIL = "email";
    int count = 0;
    float x1, x2, y1, y2;
    private EditText emailField;
    private EditText mPasswordField;
    ProgressBar ProgressBar;
    FirebaseAuth mAuth;
    FragmentManager fm;
    LinearLayout liner_signIn;
    LinearLayout show_menu;
    EditText fragmentEmail;
    EditText fragmentPassword;
    EditText fragmentName;
    boolean swipeSignIn;
    FragmentManager fragmentManager;
    Switch aSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ProgressBar = findViewById(R.id.progressBar);
        emailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);
        show_menu = findViewById(R.id.show_menu);
        liner_signIn = findViewById(R.id.liner_for_signIn);
        findViewById(R.id.signIn_Button).setOnClickListener(this);
        findViewById(R.id.signUp_Button).setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("fragment") != null) {
            liner_signIn.setVisibility(View.GONE);
            methodforswipe();
        }
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
        if (!Validation.getInstance().validEmail(email, fragmentEmail)) {
            return;
        }
        if (!Validation.getInstance().validPassword(password, fragmentPassword, fm)) {
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
                                    agruments.putString(MAIL, email);                   // передал адрес почты через аргументы
                                    recoveryDialog.setArguments(agruments);
                                    recoveryDialog.show(fm, "passw");
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
            Intent intent = new Intent(this, Activity2Main.class);
            startActivityForResult(intent, 1);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp_Button:
                if (liner_signIn.getVisibility() == View.VISIBLE) {                                       //проверка , что кнопка уже была нажата
                    //    liner_signIn.setVisibility(View.GONE);
                    startAnimationMethod();
                } else {
                    fragmentEmail = findViewById(R.id.fieldEmail_fragment);
                    fragmentPassword = findViewById(R.id.fieldPassword_fragment);
                    fragmentName = findViewById(R.id.name_fragment);
                    aSwitch= findViewById(R.id.switch_sex);                                                 // возможно не сюда
                    createAccount(fragmentEmail.getText().toString(), fragmentPassword.getText().toString());
                }
                break;
            case R.id.signIn_Button:
                signIn(emailField.getText().toString(), mPasswordField.getText().toString());
                break;
        }
    }

    private void startAnimationMethod() {
        swipeSignIn = false;               // пока переменная false свайп не работает
        final Animation hide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.menu_sign_in_close);
        hide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                liner_signIn.setVisibility(View.GONE);
                if (fragmentManager.findFragmentByTag("fragment") == null) {
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.add(R.id.frame_signUp, AuthFragment.newInstance(), "fragment");
                    //  ft.addToBackStack(null);                     если добавлять в BackStack то после удаления фрагмента вновь он создаваться не будет
                    ft.commit();
                }
                methodforswipe();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        show_menu.startAnimation(hide);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (swipeSignIn) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //первое касание
                    x1 = event.getX();
                    y1 = event.getY();
                    break;
                case MotionEvent.ACTION_UP: //отпускание
                    x2 = event.getX();
                    y2 = event.getY();
                    if (y1 > y2 && y1 - y2 > Math.abs(x1 - x2)) {        // вверх должно быть больше чем вправо или влево
                        liner_signIn.setVisibility(View.VISIBLE);
                        //delete fragment
                        if (fragmentManager.findFragmentByTag("fragment") != null) {
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.remove(fragmentManager.findFragmentByTag("fragment")).commit();
                        }
                    }
                    break;
            }
        }

        return false;
    }


    @SuppressLint("ClickableViewAccessibility")
    public void methodforswipe() {                                // добавил чтобы только в этой активити срабатывал свайп
        show_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    swipeSignIn = true;
                }
                return false;
            }
        });
    }
}
