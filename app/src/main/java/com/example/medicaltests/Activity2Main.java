package com.example.medicaltests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medicaltests.account.AccountFragment;
import com.example.medicaltests.all_analysis.SpisokFragment;
import com.example.medicaltests.myTests.MyResultTests;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity2Main extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager fr;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spisok);

        if (savedInstanceState == null) {
            fr = getSupportFragmentManager();
            ft = fr.beginTransaction();
            ft.replace(R.id.frame_fragment, SpisokFragment.newInstance());
            ft.commit();
        }
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = SpisokFragment.newInstance();
                        break;
                    case R.id.favorits:
                        fragment = MyResultTests.newInstance();
                        break;
                    case R.id.account:
                        fragment = AccountFragment.newInstance();
                        break;
                }
                fr = getSupportFragmentManager();
                ft = fr.beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.replace(R.id.frame_fragment, fragment).commit();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
