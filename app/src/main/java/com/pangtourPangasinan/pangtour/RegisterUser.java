package com.pangtourPangasinan.pangtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pangtourPangasinan.pangtour.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView registerUser, registerLogin;
    private EditText editTextFullName, editTextEmail, editTextPassword, editTextPassword2;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth= FirebaseAuth.getInstance();

        registerLogin = (TextView) findViewById(R.id.registerLogin);
        registerLogin.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);



        editTextFullName= (EditText) findViewById(R.id.fullName);
        editTextEmail= (EditText) findViewById(R.id.email);
        editTextPassword= (EditText) findViewById(R.id.password);
        editTextPassword2= (EditText) findViewById(R.id.password2);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {

        switch  (v.getId()) {
            case R.id.registerLogin:
                onBackPressed();
                break;

            case R.id.registerUser:
                registerUser();
                break;

        }

    }

    private void registerUser() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String password2=editTextPassword2.getText().toString().trim();
        String fullName=editTextFullName.getText().toString().trim();

        if(fullName.isEmpty()) {
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
            return;
        }

        if(email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(password2.isEmpty()) {
            editTextPassword2.setError("Password is required");
            editTextPassword2.requestFocus();
            return;
        }

        if(password.length() < 6 ) {
            editTextPassword.setError("Min password should be 6 characters! ");
            editTextPassword.requestFocus();
            return;
        }

        if(!password.equals(password2)) {
            editTextPassword2.setError("Password does not match! ");
            editTextPassword2.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName, email);

                            mAuth= FirebaseAuth.getInstance();

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()) {

                                                            progressBar.setVisibility(View.GONE);
                                                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterUser.this);
                                                            alertDialog.setTitle("User has been registered successfully!");
                                                            alertDialog.setMessage("Please check your email for verification. ");
                                                            alertDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    FirebaseAuth.getInstance().signOut();

                                                                    dialog.dismiss();
                                                                    onBackPressed();

                                                                }
                                                            });
                                                            alertDialog.show();
                                                        }else {
                                                            Toast.makeText(RegisterUser.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });

                                            } else {
                                                Toast.makeText(RegisterUser.this, "Failed to register! Try Again!", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });

                        }else {
                            Toast.makeText(RegisterUser.this, "Failed to register", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}