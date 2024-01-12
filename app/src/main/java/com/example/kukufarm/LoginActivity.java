package com.example.kukufarm;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView register,forgot;
    EditText editTextEmail,editTextPassword;
    Button login;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register=findViewById(R.id.txtRegister);
        forgot=findViewById(R.id.txtForgot);
        login=findViewById(R.id.btnLogin);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.txtPassword);
        //login.setOnClickListener(this);
        progressBar=findViewById(R.id.progressBar);
        //register.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtRegister:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.txtForgot:
                resetPassword();
                break;
        }
    }

    private void resetPassword() {
        //reset password function
        startActivity(new Intent(LoginActivity.this,ForgotPassword.class));
    }

    private void userLogin() {

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show();
        }else{
            if(email.isEmpty()){
                editTextEmail.setError("Email Address required");
                editTextEmail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextEmail.setError("Enter Valid Email Address");
                editTextEmail.requestFocus();
                return;
            }
            if(password.isEmpty()){
                editTextPassword.setError("Password required");
                editTextPassword.requestFocus();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified()){
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            user.sendEmailVerification();
                            Toast.makeText(LoginActivity.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Failed to login! Check your credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}