package com.example.medicaltests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends Fragment {
    EditText emailFromFragment;
    EditText passwordFromFragment;
    EditText nameFromFragment;

    public AuthFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        emailFromFragment = view.findViewById(R.id.fieldEmail_fragment);
        passwordFromFragment = view.findViewById(R.id.fieldPassword_fragment);
        nameFromFragment = view.findViewById(R.id.name_fragment);

        return view;
    }

    static AuthFragment newInstance() {
        AuthFragment fragmentAuth = new AuthFragment();
        return fragmentAuth;
    }
}
