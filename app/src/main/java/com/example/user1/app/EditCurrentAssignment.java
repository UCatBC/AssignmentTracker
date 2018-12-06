package com.example.user1.app;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditCurrentAssignment extends AppCompatActivity {

    Spinner dropdown, dropdown1;
    EditText title, module, deadline, weighting, issue, deadlineTime;
    SqliteHelper db;
    Button delete, update, export;
    String selected;
    Date formatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_current_assignment);
        title = (EditText)findViewById(R.id.editTextTitle);
        module = (EditText)findViewById(R.id.editTextModule);
        issue = (EditText)findViewById(R.id.editTextCompleted);
        deadline = (EditText)findViewById(R.id.editTextDeadline);
        deadlineTime = (EditText)findViewById(R.id.editTextDeadlineTime2);
        weighting = (EditText)findViewById(R.id.editTextGrade);
        delete = (Button)findViewById(R.id.buttonDelete);
        update = (Button)findViewById(R.id.update);

        db = new SqliteHelper(this);

        //for type
        /*dropdown = findViewById(R.id.editTextType);
        String[] items = new String[]{"Assignment", "Task"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);*/


        //for resources
        dropdown1 = findViewById(R.id.editTextType1);

        String[] items1 = new String[]{"1", "2"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        Intent intent = getIntent();
        String title1 = intent.getStringExtra("TITLE");


        Assignment view = db.getOneAssignment(title1);

        String choice = view.getType();
        dropdown = findViewById(R.id.editTextType);
        String [] items;
        if(choice == "Assignment") {
            items = new String[]{choice, "Task"};
        }
        else{
            items = new String[]{choice, "Assignment"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        title.setText(view.getTitle());
        issue.setText(view.getIssue());
        module.setText(view.getModule());
        deadline.setText(view.getDeadline());
        deadlineTime.setText(view.getTime());
        weighting.setText(view.getWeighting());

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
                //Code to insert selected string in database
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteTitle = title.getText().toString();
                db.deleteAssignment(deleteTitle);

                Intent hello2 = new Intent(getApplicationContext(),CurrentAssignments.class);
                startActivity(hello2);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assignment view = new Assignment();
                view.setType(selected);
                view.setModule(module.getText().toString());
                view.setTitle(title.getText().toString());
                view.setDeadline(deadline.getText().toString());
                view.setTime(deadlineTime.getText().toString());
                view.setWeighting(weighting.getText().toString());
                db.updateCurrentAssignment(view);
            }
        });

    }


    public void page1change(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();

        if (button_text.equals("Create"))
        {
            Intent hello2 = new Intent(this,NewAssignment.class);
            startActivity(hello2);
        }
        else if (button_text.equals("Current"))
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
