package com.cloudcounselage.cloudcounselageapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {


    private EditText etName;
    private EditText etPassword;
    private TextView userRegistration;
    private Button bLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



         etName = (EditText) findViewById(R.id.etName);
         etPassword = (EditText) findViewById(R.id.etPassword);
         userRegistration =  (TextView) findViewById(R.id.tvRegister);
         bLogin = (Button) findViewById(R.id.bLogin);
         forgotPassword = (TextView) findViewById(R.id.tvForgot);




        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainpageActivity.class));
        }


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(etName.getText().toString(), etPassword.getText().toString());
            }
        });



        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, PasswordActivity.class));
            }
        });

    }
    private void validate(String userName, String userPassword ){

        progressDialog.setMessage("You can subscribe Cloud Counselage on Facebook until you verified");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginActivity.this, MainpageActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    }

