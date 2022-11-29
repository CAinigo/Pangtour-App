package com.pangtourPangasinan.pangtour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pangtourPangasinan.pangtour.R;

public class GuestLogin extends AppCompatActivity implements View.OnClickListener{

    Button login, guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guest_login);

        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(this);

        guest= (Button) findViewById(R.id.guest);
        guest.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.guest:
                startActivity(new Intent(this, MainPage.class));
                break;


        }

    }

    @Override
    public void onBackPressed() {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(GuestLogin.this);
            alertDialog.setTitle("Exit app");
            alertDialog.setMessage("Do you want to exit app? ");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
    }
}