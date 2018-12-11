package com.example.user1.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditExpiredAssignment extends AppCompatActivity {

    EditText type, module, title, completed, weighting, grade;
    SqliteHelper db;
    Button delete, update;
    ImageView infoType, infoTitle, infoModule, infoWeighting, infoGrade, infoNotes, infoCompleted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expired_assignment);

        type = (EditText)findViewById(R.id.editTextType);
        module = (EditText)findViewById(R.id.editTextModule);
        title = (EditText)findViewById(R.id.editTextTitle);
        completed = (EditText)findViewById(R.id.editTextCompleted);
        weighting = (EditText)findViewById(R.id.editTextWeighting);
        grade = (EditText)findViewById(R.id.editTextGrade);
        delete = (Button)findViewById(R.id.buttonDelete);
        update = (Button)findViewById(R.id.buttonUpdate);
        infoType = (ImageView)findViewById(R.id.imageViewInfo15);
        infoModule = (ImageView)findViewById(R.id.imageViewInfo16);
        infoTitle = (ImageView)findViewById(R.id.imageViewInfo17);
        infoCompleted = (ImageView)findViewById(R.id.imageViewInfo18);
        infoWeighting = (ImageView)findViewById(R.id.imageViewInfo19);
        infoGrade = (ImageView)findViewById(R.id.imageViewInfo20);
        infoNotes = (ImageView)findViewById(R.id.imageViewInfo21);




        db = new SqliteHelper(this);

        Intent intent = getIntent();
        String title1 = intent.getStringExtra("TITLE");

        Assignment view = db.getOneAssignment(title1);

        type.setText(view.getType());
        module.setText(view.getModule());
        title.setText(view.getTitle());
        completed.setText(view.getDeadline()); //not correct getter
        weighting.setText(view.getWeighting());
        grade.setText(view.getGrade());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteTitle = title.getText().toString();
                db.deleteAssignment(deleteTitle);

                Intent hello2 = new Intent(getApplicationContext(),ExpiredAssignments.class);
                startActivity(hello2);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assignment view = new Assignment();
                view.setGrade(grade.getText().toString());
                db.updateExpiredAssignment(view);

                Intent hello2 = new Intent(getApplicationContext(),ExpiredAssignments.class);
                startActivity(hello2);
            }
        });

        infoType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "This is the type of the work completed", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 400);
                toast.show();

            }
        });

        infoModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "This is the module of the assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 550);
                toast.show();

            }
        });

        infoTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "This is the title of the assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 700);
                toast.show();

            }
        });

        infoCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "This is the date the assignment/task was completed", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 850);
                toast.show();

            }
        });
        infoWeighting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "This is the weighting of the assignment/grade", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1000);
                toast.show();

            }
        });

        infoGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Enter grade you received for this assignment", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1150);
                toast.show();

            }
        });

        infoNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(getApplicationContext(),
                        "Click to view notes that were related to this assignment/task", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.LEFT, 50, 1300);
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
}
