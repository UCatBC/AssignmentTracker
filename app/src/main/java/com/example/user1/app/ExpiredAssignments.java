package com.example.user1.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpiredAssignments extends AppCompatActivity {
    TextView insert;
    Button viewAssignment;
    ArrayAdapter <String> listAdapter;
    ArrayList<String> assignmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expired_assignments);

        SqliteHelper db = new SqliteHelper(this);
        assignmentList = db.getAllAssignments("Yes");

        listAdapter = new ArrayAdapter<String>(this, R.layout.list_row,R.id.label, assignmentList);
        ListView lv = (ListView)findViewById(R.id.assignment_list);
        insert = (TextView)findViewById(R.id.label);
        lv.setAdapter(listAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String insert1 = assignmentList.get(position);

                Intent assignment = new Intent(getApplicationContext(), EditExpiredAssignment.class);
                assignment.putExtra("TITLE", insert1);

                startActivity(assignment);
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
        else if (button_text.equals("Create"))
        {
            Intent hello2 = new Intent(this,NewAssignment.class);
            startActivity(hello2);
        }
    }
}
