package com.pangtourPangasinan.pangtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pangtourPangasinan.pangtour.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ImageButton backButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText =(EditText) findViewById(R.id.email);
        resetPasswordButton=(Button) findViewById(R.id.resetPassword);
        resetPasswordButton.setOnClickListener(this);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);

        backButton= (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        auth= FirebaseAuth.getInstance();

    }

    private void resetPassword() {
        String email= emailEditText.getText().toString().trim();

        if(email.isEmpty()) {
            emailEditText.setError("Email is Required! ");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide valid email! ");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ForgotPassword.this);
                    alertDialog.setTitle("Reminder!");
                    alertDialog.setMessage("Please check your email to change your password. ");
                    alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                            onBackPressed();

                        }
                    });
                    alertDialog.show();
                }else{
                    Toast.makeText(ForgotPassword.this, "Try Again! Something wrong happen!  ", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetPassword:
                resetPassword();
                break;

            case R.id.backButton:
                onBackPressed();
                break;
        }
    }
}