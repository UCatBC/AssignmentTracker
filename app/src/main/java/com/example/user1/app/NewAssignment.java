package com.example.user1.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewAssignment extends AppCompatActivity {

    Spinner dropdown;
    EditText editTextModule;
    EditText editTextTitle;
    EditText editTextIssue;
    EditText editTextDeadline;
    EditText editTextWeighting;

    Button buttonCreate;

    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assignment);
        buttonCreate = (Button)findViewById(R.id.button);
        editTextModule = (EditText)findViewById(R.id.editTextModule);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextIssue = (EditText)findViewById(R.id.editTextIssue);
        editTextDeadline = (EditText)findViewById(R.id.editTextDeadline);
        editTextWeighting = (EditText)findViewById(R.id.editTextWeighting);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.editTextType);
        //create a list of items for the spinner.
        String[] items = new String[]{"Assignment", "Task"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        /*LEAVE BELOW FUNCTION HERE, STRUGGLING GETTING IT TO CREATE THE DATABASE,WILL SORT THIS WEEKEND AT SOMEPOINT*/
        /*buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String Module = editTextModule.getText().toString();
                    String Title = editTextTitle.getText().toString();
                    String Issue = editTextIssue.getText().toString();
                    String Deadline = editTextDeadline.getText().toString();
                    String Weighting = editTextWeighting.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isAssignmentExists(Title)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addAssignment(new Assignment(Module, Title, Issue, Deadline, Weighting));
                        Snackbar.make(buttonCreate, "Assignment created successfully!", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(buttonCreate, "Assignment already exists with same title ", Snackbar.LENGTH_LONG).show();
                    }


                }

        });*/


    }


    }
