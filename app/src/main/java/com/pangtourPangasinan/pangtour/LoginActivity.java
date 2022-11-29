package com.pangtourPangasinan.pangtour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn= (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail= (EditText) findViewById(R.id.email);
        editTextPassword= (EditText) findViewById(R.id.password);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        mAuth= FirebaseAuth.getInstance();
        forgotPassword= (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.signIn:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;

        }
    }

    private void userLogin() {
        LoadingAlert loadingAlert= new LoadingAlert(LoginActivity.this);
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is Required! ");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email! ");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextEmail.setError("Password is Required! ");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6) {
            editTextPassword.setError("Min password length is 6 characters! ");
            editTextPassword.requestFocus();
            return;
        }

        loadingAlert.startAlertDialog();
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        //User profile
                        progressBar.setVisibility(View.GONE);
                        loadingAlert.closeAlertDialog();
                        startActivity(new Intent(LoginActivity.this, MainPage.class));
                    }else {
                        loadingAlert.closeAlertDialog();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this,"Check your Email to Verify your Account! ",Toast.LENGTH_LONG).show();

                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    loadingAlert.closeAlertDialog();
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}