package com.example.studentmanagement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.studentmanagement.model.Subject;

public class Database extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "studentmanagement";
    //Bảng môn học
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //Bảng sinh viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String SEX = "sex";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";


    //Tạo bảng môn học
    private String SQLQuery = "CREATE TABLE "+ TABLE_SUBJECTS +" ( "+ID_SUBJECTS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_TITLE+" TEXT, "
            +CREDITS+" INTEGER, "
            +TIME+" TEXT, "
            + PLACE+" TEXT) ";

    //Tạo bảng sinh viên
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +SEX+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +DATE_OF_BIRTH+" TEXT, "
            +ID_SUBJECTS+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECTS +" ) REFERENCES "+
            TABLE_SUBJECTS+"("+ID_SUBJECTS+"))";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addSubject(Subject subject) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SUBJECT_TITLE, subject.getTitle());
        contentValues.put(TIME, subject.getTime());
        contentValues.put(CREDITS, subject.getCredit());
        contentValues.put(PLACE, subject.getPlace());

        database.insert(TABLE_SUBJECTS, null, contentValues);
        database.close();
    }

    public boolean updateSubject(int id, Subject subject) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SUBJECT_TITLE, subject.getTitle());
        contentValues.put(TIME, subject.getTime());
        contentValues.put(CREDITS, subject.getCredit());
        contentValues.put(PLACE, subject.getPlace());

        sqLiteDatabase.update(TABLE_SUBJECTS, contentValues,ID_SUBJECTS + " = " + id, null );
        return true;
    }

    public Cursor getDataSubject() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_SUBJECTS, null);
        return cursor;
    }

    public int deleteSubject(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int res = sqLiteDatabase.delete(TABLE_SUBJECTS, ID_SUBJECTS + " = " + id, null);
        return res;
    }

}
