package com.example.user1.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class NewAssignment extends AppCompatActivity {

    Spinner dropdown, dropdown1;
    EditText editTextModule;
    EditText editTextTitle;
    EditText editTextIssue;
    EditText editTextDeadline;
    EditText editTextWeighting;
    SimpleDateFormat formatter;

    Button buttonCreate;

    TextView textView;

    String selected;

    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assignment);
        //textView = (TextView) findViewById(R.id.text);
        sqliteHelper = new SqliteHelper(this);
        buttonCreate = (Button)findViewById(R.id.buttonDelete);
        editTextModule = (EditText)findViewById(R.id.editTextModule);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);
        editTextIssue = (EditText)findViewById(R.id.editTextCompleted);
        editTextDeadline = (EditText)findViewById(R.id.editTextDeadline);
        editTextWeighting = (EditText)findViewById(R.id.editTextGrade);


        Date today = Calendar.getInstance().getTime();
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(today);

        editTextIssue.setText(date);

        //for type
        dropdown = findViewById(R.id.editTextType);
        String[] items = new String[]{"Assignment", "Task"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        //for resources
        dropdown1 = findViewById(R.id.editTextType1);
        String[] items1 = new String[]{"1", "2"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        //gets string selected within dropdown type
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
                //Code to insert selected string in database
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });//end type selector

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Assignment add = new Assignment();

                    add.setType(selected);
                    add.setModule(editTextModule.getText().toString());
                    add.setTitle(editTextTitle.getText().toString());
                    add.setIssue(editTextIssue.getText().toString());
                    add.setDeadline(editTextDeadline.getText().toString());
                    add.setWeighting(editTextWeighting.getText().toString());



                    String checkTitle = editTextTitle.getText().toString();
                    //Check in the database is there any user associated with  this email
                   if (!sqliteHelper.isAssignmentExists(checkTitle)) {

                        //Assignment does not exist now add new user to database
                        sqliteHelper.addAssignment(add);
                        Snackbar.make(buttonCreate, "Assignment created successfully!", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);



                        String startDate = editTextIssue.getText().toString();
                        String endDate = editTextDeadline.getText().toString();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date editStartDate = null;
                        Date editEndDate = null;
                       try {
                           editStartDate = df.parse(startDate);
                           editEndDate = df.parse(endDate);
                       } catch (ParseException e) {
                           e.printStackTrace();
                       }

                       Long time = new GregorianCalendar().getTimeInMillis()+5*1000;
                       Intent intentAlarm = new Intent(NewAssignment.this, AlarmReciever.class);
                       AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                          alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(NewAssignment.this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
                                      Toast.makeText(NewAssignment.this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();

                     Calendar calStart = new GregorianCalendar();
                       Calendar calEnd = new GregorianCalendar();


                       calStart.setTime(editStartDate);
                       calEnd.setTime(editEndDate);
                       Intent intent1 = new Intent(Intent.ACTION_INSERT)
                               //.setData(CalendarContract.Events.CONTENT_URI)
                               .setType("vnd.android.cursor.item/event")
                               .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calStart.getTimeInMillis())
                               .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calStart.getTimeInMillis())
                               .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                               .putExtra(CalendarContract.Events.TITLE, "Yoga")
                               .putExtra(CalendarContract.Events.DESCRIPTION, "Group class")
                               .putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                               .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                               .putExtra(CalendarContract.Events.STATUS, 1)
                               .putExtra(CalendarContract.Events.VISIBLE, 0)
                               .putExtra(CalendarContract.Events.HAS_ALARM, true)
                               .putExtra(CalendarContract.Events.ALLOWED_REMINDERS, "METHOD_DEFAULT")
                               .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
                               .putExtra(CalendarContract.Reminders.MINUTES, 20);

                       startActivity(intent1);
                       Intent intent=new Intent(NewAssignment.this,CurrentAssignments.class); 
                    }else {

                        //Assignment exists with assignment input provided so show error assignment already exist
                        Snackbar.make(buttonCreate, "Assignment already exists with same title ", Snackbar.LENGTH_LONG).show();
                    }


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