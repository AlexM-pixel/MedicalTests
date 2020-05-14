package com.example.medicaltests.myTests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.medicaltests.R;

public class MyResultTests extends Fragment {
    public static MyResultTests newInstance() {
        return new MyResultTests();
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.spisok_myresult_tests,container,false);
    }
}
