package com.samy.familychat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class register extends AppCompatActivity {
 EditText  name,email,password;
 TextView gotologin;
 Button register;
 ProgressDialog progressDialog;
 FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.namer);
        progressDialog=new ProgressDialog(this);
        email=findViewById(R.id.emailr);
        password=findViewById(R.id.passwordr);
        gotologin=findViewById(R.id.gotologin);
        register=findViewById(R.id.registerr);
        firebaseAuth=firebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              registeruser();


            }
        });





        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,Login.class));
            }
        });


    }

    private void registeruser() {
        progressDialog.setMessage("Please wait......");
        progressDialog.show();
        String nameofuser=name.getText().toString();
        String useremail=email.getText().toString();
        String  userpassword=password.getText().toString();
        if(!nameofuser.isEmpty()){
            if(!useremail.isEmpty()){
                if(!userpassword.isEmpty()){


                    registerProcess(nameofuser,useremail,userpassword);

                }
                else{
                    password.setError("Please enter a  password");
                }
            }
            else {
                email.setError("Email is empty");
            }

        }
        else{
            name.setError("Name is empty");
        }

        }

    private void registerProcess(String name, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(register.this, "User is Registered Sucessfully", Toast.LENGTH_SHORT).show();

                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(register.this, "Sorry! could not register Sucessfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}



