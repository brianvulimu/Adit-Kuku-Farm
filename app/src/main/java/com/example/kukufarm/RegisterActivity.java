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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername,editTextIdNo,editTextEmail,editTextPassword,editTextLocation;
    private Button register;
    private TextView login;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextUsername=findViewById(R.id.username);
        editTextIdNo=findViewById(R.id.idNo);
        editTextEmail=findViewById(R.id.email);
        editTextLocation=findViewById(R.id.location);
        editTextPassword=findViewById(R.id.password);
        register=findViewById(R.id.btnRegister);
        login=findViewById(R.id.txtLogin);
        progressBar=findViewById(R.id.progressBar);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String emailAddress=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String fullName=editTextUsername.getText().toString().trim();
        String idNo=editTextIdNo.getText().toString().trim();
        String location=editTextLocation.getText().toString().trim();

        if(emailAddress.isEmpty()){
            editTextEmail.setError("Email Address is empty");
            editTextEmail.requestFocus();
            return;
        }if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            editTextEmail.setError("Email Address is empty");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is empty");
            editTextPassword.requestFocus();
            return;
        }if(password.length()<8){
            editTextPassword.setError("Input password with 6 characters long or more");
            editTextPassword.requestFocus();
            return;
        }if(fullName.isEmpty()){
            editTextUsername.setError("Username is empty");
            editTextUsername.requestFocus();
            return;
        }if(idNo.isEmpty()){
            editTextIdNo.setError("ID No is empty");
            editTextIdNo.requestFocus();
            return;
        }
        if(location.isEmpty()){
            editTextLocation.setError("Location field is empty");
            editTextLocation.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user=new User(fullName,idNo,emailAddress,location,password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();

                                            }else{
                                                Toast.makeText(RegisterActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                                            }
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}