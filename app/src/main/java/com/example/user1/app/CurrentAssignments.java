package com.example.user1.app;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CurrentAssignments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_assignments);

        SqliteHelper db = new SqliteHelper(this);
        ArrayList<HashMap<String, String>> assignmentList = db.GetCurrentAssignments();
        ListView lv = (ListView)findViewById(R.id.assignment_list);
        ListAdapter adapter = new SimpleAdapter(CurrentAssignments.this, assignmentList,
                R.layout.list_row,new String[]{"title","deadline"},new int[]{R.id.title,R.id.deadline_date});
        lv.setAdapter(adapter);

    }

    public void viewAssignment (View view) {
        String button_text;
        button_text = ((Button)view).getText().toString();

        Intent assignment = new Intent(this, EditCurrentAssignment.class);
        startActivity(assignment);
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
        else if (button_text.equals("Expired"))
        {
            Intent hello2 = new Intent(this,ExpiredAssignments.class);
            startActivity(hello2);
        }
    }
}
