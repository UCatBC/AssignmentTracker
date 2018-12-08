package com.example.user1.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditExpiredAssignment extends AppCompatActivity {

    EditText type, module, title, completed, weighting, grade;
    SqliteHelper db;
    Button delete, update;
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
