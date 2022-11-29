package com.pangtourPangasinan.pangtour;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.pangtourPangasinan.pangtour.R;

public class LoadingAlert {

    Activity activity;
    AlertDialog dialog;

    LoadingAlert(LoginActivity myActivity) {
        activity= myActivity;
    }

    void startAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_layout, null));

        builder.setCancelable(true);

        dialog =builder.create();
        dialog.show();
    }

    void closeAlertDialog() {
        dialog.dismiss();
    }
}
