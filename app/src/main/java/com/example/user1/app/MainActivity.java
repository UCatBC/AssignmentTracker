package com.example.user1.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button register;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (Button)findViewById(R.id.Login_Register);
        login = (Button)findViewById(R.id.Login);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                startActivity(new Intent(MainActivity.this, NewAssignment.class));
            }
        });
    }



}