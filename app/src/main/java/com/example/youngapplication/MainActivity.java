package com.example.youngapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, confirmpassword;
    Button signUp, signIn;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.confirmpassword);
        signUp = (Button) findViewById(R.id.btnSignup);
        signIn = (Button) findViewById(R.id.btnSignin);
        dataBase = new DataBase(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass= password.getText().toString();
                String confipass=confirmpassword.getText().toString();

                if(user.equals("")||pass.equals("")||confipass.equals(""))
                    Toast.makeText(MainActivity.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(confipass)) {
                        Boolean checkuser=dataBase.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = dataBase.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(MainActivity.this,"Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(MainActivity.this,"Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Users already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}