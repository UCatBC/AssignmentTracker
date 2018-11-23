package com.example.user1.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText EditUname, EditPass, EditCPass;
    Button ButtonReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        EditUname = (EditText) findViewById(R.id.Uname);
        EditPass = (EditText) findViewById(R.id.Password);
        EditCPass = (EditText) findViewById(R.id.CPassword);


        ButtonReg = (Button) findViewById(R.id.Login);

        ButtonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Text1 = EditUname.getText().toString();
                String Text2 = EditPass.getText().toString();
                String Text3 = EditCPass.getText().toString();

                if (Text1.equals("") || Text2.equals("") || Text3.equals("")) {
                    Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (Text2.equals(Text3)) {
                        Boolean checkUname = db.checkUname(Text1);

                        if (checkUname == true) {
                            Boolean insert = db.insert(Text1, Text2);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
