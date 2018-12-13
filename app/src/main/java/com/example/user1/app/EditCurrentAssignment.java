package com.example.user1.app;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditCurrentAssignment extends AppCompatActivity {

    Spinner dropdown, dropdown1;
    EditText title, module, deadline, weighting, issue, deadlineTime;
    CheckBox completed;
    SqliteHelper db;
    Button delete, update, view;
    String selected;
    SimpleDateFormat formatter;
    ImageView infoType, infoTitle, infoModule, infoIssue, infoDeadline, infoWeighting, infoResources, infoNotes, infoCompleted;

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
        completed = (CheckBox)findViewById(R.id.checkBoxCompleted);
        infoTitle = (ImageView)findViewById(R.id.imageViewInfo9);
        infoType = (ImageView)findViewById(R.id.imageViewInfo7);
        infoModule = (ImageView)findViewById(R.id.imageViewInfo8);
        infoIssue = (ImageView)findViewById(R.id.imageViewInfo10);
        infoDeadline = (ImageView)findViewById(R.id.imageViewInfo11);
        infoWeighting = (ImageView)findViewById(R.id.imageViewInfo12);
        infoResources = (ImageView)findViewById(R.id.imageViewInfo13);
        infoNotes = (ImageView)findViewById(R.id.imageViewInfo14);
        infoCompleted = (ImageView)findViewById(R.id.imageViewInfo15);
        view = (Button)findViewById(R.id.button2);

        db = new SqliteHelper(this);

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
                view.setTime(deadlineTime.getText().toString());
                view.setWeighting(weighting.getText().toString());

                if(completed.isChecked()){
                    view.setCompleted("Yes");
                    Date today = Calendar.getInstance().getTime();
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String date = formatter.format(today);
                    view.setDeadline(date);
                }
                else {
                    view.setDeadline(deadline.getText().toString());
                }
                db.updateCurrentAssignment(view);

                Intent hello2 = new Intent(getApplicationContext(),CurrentAssignments.class);
                startActivity(hello2);
            }
        });

        infoType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Choose type of work", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 400);
                toast.show();

            }
        });

        infoModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Enter which module the assignment/task is for", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 550);
                toast.show();

            }
        });

        infoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Enter the title of the assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 700);
                toast.show();

            }
        });

        infoIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "This is the issue date of the assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 850);
                toast.show();

            }
        });
        infoDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Enter deadline date and time in this format (2018-12-12) (17-00)", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1000);
                toast.show();

            }
        });

        infoWeighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Enter weight of assignment/task if applicable in numbers", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1150);
                toast.show();

            }
        });

        infoResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Choose which resources needed for the assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1300);
                toast.show();

            }
        });
        infoNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Click to view notes related to assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1450);
                toast.show();

            }
        });
        infoCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Click to mark as completed - WARNING IRREVERSIBLE", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 50, 1600);
                toast.show();

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

    public void viewChange(View View) {
        String button_text;
        button_text = ((Button) View).getText().toString();
        String extra = title.getText().toString();

        if (button_text.equals("View")) {
            Intent hello2 = new Intent(getApplicationContext(), Notes.class);
            hello2.putExtra("NOTES", extra);
            startActivity(hello2);
        }

    }
}
