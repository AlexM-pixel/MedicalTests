package com.example.medicaltests;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Calendar;


public class AuthFragment extends Fragment {
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TextView displayDate;
    private TextView displayWeight;
    private NumberPicker weightPicker;
    String sex = "мужской";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_auth, container, false);
        displayWeight = view.findViewById(R.id.weightUser);
        displayDate = view.findViewById(R.id.date_picker);
        insertWeight();
        insertDate();
        final Switch switchSex = view.findViewById(R.id.switch_sex);
        switchSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sex = (isChecked ? "женский" : "мужской");
                switchSex.setText(sex);
            }
        });
        return view;
    }

    private void insertWeight() {
        displayWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightPicker = new NumberPicker(getContext());
                weightPicker.setMinValue(2);
                weightPicker.setMaxValue(210);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(weightPicker);
                builder.setCancelable(false);
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @SuppressLint("SetTextI18n")
                            public void onClick(DialogInterface dialog, int id) {
                                displayWeight.setText(weightPicker.getValue() + " кг");
                            }
                        });
                builder.setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });

    }

    private void insertDate() {
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog,
                        onDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                displayDate.setText(date);
            }
        };
    }
    static AuthFragment newInstance() {
        AuthFragment fragmentAuth = new AuthFragment();
        return fragmentAuth;
    }
}
