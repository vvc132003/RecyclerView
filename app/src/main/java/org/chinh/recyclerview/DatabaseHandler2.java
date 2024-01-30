package org.chinh.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler2 extends SQLiteOpenHelper {

    private static final String TABLE_NAME_STUDENT = "student";
    private static final String KEY_STUDENT_ID = "id";
    private static final String KEY_STUDENT_NAME = "hoTen";
    private static final String KEY_STUDENT_ADDRESS = "diaChi";
    private static final String KEY_STUDENT_PHONE = "soDienThoai";

    public DatabaseHandler2(Context context) {
        super(context, "your_database_name", null, 1); // Thay "your_database_name" bằng tên cơ sở dữ liệu của bạn
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStudentTable = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_STUDENT, KEY_STUDENT_ID, KEY_STUDENT_NAME, KEY_STUDENT_ADDRESS, KEY_STUDENT_PHONE);
        db.execSQL(createStudentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropStudentTable = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_STUDENT);
        db.execSQL(dropStudentTable);
        onCreate(db);
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentList;
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME, student.getHoTen());
        values.put(KEY_STUDENT_ADDRESS, student.getDiaChi());
        values.put(KEY_STUDENT_PHONE, student.getSoDienThoai());
        db.insert(TABLE_NAME_STUDENT, null, values);
        db.close();
    }
}