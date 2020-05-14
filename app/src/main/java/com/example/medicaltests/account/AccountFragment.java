package com.example.medicaltests.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.medicaltests.MainActivity;
import com.example.medicaltests.R;
import com.google.firebase.auth.FirebaseAuth;

import static android.app.Activity.RESULT_CANCELED;

public class AccountFragment extends Fragment {

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.account,container,false);
        Button button_logOut=view.findViewById(R.id.logout);
        button_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                FirebaseAuth.getInstance().signOut();
                            getActivity().setResult(RESULT_CANCELED);
                             getActivity().finish();
            }
        });
           return view;
    }
}
