package com.example.user1.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "database.db";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USERS = "users";

    public static final String TABLE_ASSIGNMENTS = "assignments";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";
    //COLUMN user name
    public static final String KEY_USER_NAME = "username";
    //COLUMN email
    public static final String KEY_EMAIL = "email";
    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_TYPE = "type";
    public static final String KEY_MODULE = "module";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ISSUE_DATE = "issue_date";
    public static final String KEY_DEADLINE_DATE = "deadline_date";
    public static final String KEY_DEADLINE_TIME = "deadline_time";
    public static final String KEY_WEIGHTING = "weighting";
    public static final String KEY_RESOURCES = "resources";
    public static final String KEY_COMPLETED = "completed";
    public static final String KEY_GRADE = "grade";
    public static final String KEY_NOTES_IMAGE = "notes_text";
    public static final String KEY_NOTES_TEXT = "notes_image";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";

    public static final String SQL_TABLE_ASSIGNMENTS = " CREATE TABLE " + TABLE_ASSIGNMENTS
            + "("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TYPE + " TEXT, "
            + KEY_MODULE + " TEXT, "
            + KEY_TITLE + " TEXT, "
            + KEY_ISSUE_DATE + " TEXT, "
            + KEY_DEADLINE_DATE + " TEXT, "
            + KEY_DEADLINE_TIME + " TEXT,"
            + KEY_WEIGHTING + " TEXT, "
            + KEY_RESOURCES + " TEXT, "
            + KEY_GRADE + " TEXT, "
            + KEY_COMPLETED + " TEXT, "
            + KEY_NOTES_TEXT + " TEXT, "
            + KEY_NOTES_IMAGE + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_ASSIGNMENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);
        this.onCreate(sqLiteDatabase);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    //using this method we can add assignments to user table
    public long addAssignment(Assignment assignment) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();
        //Put email in  @values
        values.put(KEY_TYPE, assignment.getType());
        values.put(KEY_MODULE, assignment.getModule());
        //Put password in  @values
        values.put(KEY_TITLE, assignment.getTitle());
        values.put(KEY_ISSUE_DATE, assignment.getIssue());
        values.put(KEY_DEADLINE_DATE, assignment.getDeadline());
        values.put(KEY_DEADLINE_TIME, assignment.getTime());
        values.put(KEY_WEIGHTING, assignment.getWeighting());
        values.put(KEY_COMPLETED, "No");

        // insert row
        long result = db.insert(TABLE_ASSIGNMENTS, null, values);
        db.close();
        return result;
    }

    public boolean isAssignmentExists(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ASSIGNMENTS,// Selecting Table
                new String[]{KEY_ID, KEY_TYPE, KEY_MODULE, KEY_TITLE, KEY_ISSUE_DATE, KEY_DEADLINE_DATE, KEY_DEADLINE_TIME, KEY_WEIGHTING}, //KEY_RESOURCES, KEY_GRADE, KEY_COMPLETED},//Selecting columns want to query
                KEY_TITLE + "=?",
                new String[]{title},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given assignment so return true
            return true;
        }

        //if assignment does not exist return false
        return false;
    }

    public ArrayList<String> getAllAssignments() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> listItems = new ArrayList<String>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ASSIGNMENTS, null);//new String[]{});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            listItems.add(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            cursor.moveToNext();
        }

        return listItems;
    }

     public Assignment getOneAssignment(String title) {
        Assignment listItems = new Assignment();
        SQLiteDatabase db = this.getReadableDatabase();

        String [] columns = {KEY_TYPE, KEY_MODULE, KEY_TITLE, KEY_ISSUE_DATE, KEY_DEADLINE_DATE, KEY_DEADLINE_TIME, KEY_WEIGHTING, KEY_GRADE};
        String selection = KEY_TITLE + " = ?";
        String [] selectionArgs = { title };

        Cursor cursor = db.query(TABLE_ASSIGNMENTS, columns, selection, selectionArgs, null, null, null);

        if(null != cursor) {

            cursor.moveToFirst();

            listItems.setType(cursor.getString(0));
            listItems.setModule(cursor.getString(1));
            listItems.setTitle(cursor.getString(2));
            listItems.setIssue(cursor.getString(3));
            listItems.setDeadline(cursor.getString(4));
            listItems.setTime(cursor.getString(5));
            listItems.setWeighting(cursor.getString(6));
            //listItems.setResources(cursor.getString(7));
            listItems.setGrade(cursor.getString(7));
            //listItems.setCompleted(cursor.getString(9));
        }
        db.close();

        return listItems;
    }



    public void deleteAssignment(String title) {
        String assignment [] = { title };
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSIGNMENTS, KEY_TITLE + " = ?", assignment);
        db.close();
    }

    public long updateCurrentAssignment(Assignment assignment) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();
        //Put email in  @values
        values.put(KEY_MODULE, assignment.getModule());
        //Put password in  @values
        values.put(KEY_TITLE, assignment.getTitle());
        //values.put(KEY_ISSUE_DATE, assignment.getIssue());
        values.put(KEY_DEADLINE_DATE, assignment.getDeadline());
        values.put(KEY_DEADLINE_TIME, assignment.getTime());
        values.put(KEY_WEIGHTING, assignment.getWeighting());
        //values.put(KEY_GRADE, assignment.getGrade());
        //values.put(KEY_COMPLETED, "No");

        // insert row
       return db.update(TABLE_ASSIGNMENTS, values, KEY_TITLE +
        " = ?", new String[] { String.valueOf(assignment.getTitle()) });
    }

    public long updateExpiredAssignment(Assignment assignment) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();
        values.put(KEY_GRADE, assignment.getGrade());

        // insert row
        return db.update(TABLE_ASSIGNMENTS, values, KEY_TITLE +
                " = ?", new String[] { String.valueOf(assignment.getTitle()) });
    }
}