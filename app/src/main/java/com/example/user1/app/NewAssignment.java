package com.example.user1.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class NewAssignment extends AppCompatActivity {

    Spinner dropdown;
    EditText editTextModule;
    EditText editTextTitle;
    EditText editTextIssue;
    EditText editTextDeadline;
    EditText editTextWeighting;

    Button buttonCreate;

    TextView textView;

    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assignment);
        //textView = (TextView) findViewById(R.id.text);
        sqliteHelper = new SqliteHelper(this);
        buttonCreate = (Button)findViewById(R.id.delete);
        editTextModule = (EditText)findViewById(R.id.editTextModule);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextIssue = (EditText)findViewById(R.id.editTextIssue);
        editTextDeadline = (EditText)findViewById(R.id.editTextDeadline);
        editTextWeighting = (EditText)findViewById(R.id.editTextGrade);

        //for type
        dropdown = findViewById(R.id.editTextType);
        String[] items = new String[]{"Assignment", "Task"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        //for resources
        dropdown = findViewById(R.id.editTextType1);
        String[] items1 = new String[]{"1", "2"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown.setAdapter(adapter1);



        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String Module = editTextModule.getText().toString();
                    String Title = editTextTitle.getText().toString();
                    String Issue = editTextIssue.getText().toString();
                    String Deadline = editTextDeadline.getText().toString();
                    String Weighting = editTextWeighting.getText().toString();

                    //Check in the database is there any user associated with  this email
                   // if (!sqliteHelper.isAssignmentExists(Title)) {

                        //Assignment does not exist now add new user to database
                        sqliteHelper.addAssignment(new Assignment(Module, Title, Issue, Deadline, Weighting));
                        Snackbar.make(buttonCreate, "Assignment created successfully!", Snackbar.LENGTH_LONG).show();
                     /*   new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);*/
                        Intent intent=new Intent(NewAssignment.this,CurrentAssignments.class);
                        startActivity(intent);
                   /* }else {

                        //Assignment exists with assignment input provided so show error assignment already exist
                        Snackbar.make(buttonCreate, "Assignment already exists with same title ", Snackbar.LENGTH_LONG).show();
                    }*/


                }

        });


    }

    public void page1change(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();

        if (button_text.equals("Current"))
        {
            Intent hello2 = new Intent(this,CurrentAssignments.class);
            startActivity(hello2);
        }
        else if (button_text.equals("Expired"))
        {
            Intent hello2 = new Intent(this,ExpiredAssignments.class);
            startActivity(hello2);
        }
    }

    }