package com.example.medicaltests.dialogues;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.medicaltests.validation.Validation;

public class FragmentDialogAlert extends DialogFragment {


    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String text = "";
        Bundle arguments = getArguments();
        if (arguments != null) {
            text = arguments.getString(Validation.ALERT_PASSW);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(text)
                .setCancelable(true)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }

}
