package org.chinh.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        recyclerView = findViewById(R.id.studentRecyclerView);

        List<Student> studentList = new DatabaseHandler2(this).getAllStudents();

        studentAdapter = new StudentAdapter(studentList, (position, student) -> showUserInfoDialog(student));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);
    }

    private void showUserInfoDialog(Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_student_item, null);

        TextView textViewId = dialogView.findViewById(R.id.idTextView);
        TextView textViewEmail = dialogView.findViewById(R.id.emailTextView);
        TextView textViewPassword = dialogView.findViewById(R.id.passwordTextView);
        TextView textViewRole = dialogView.findViewById(R.id.roleTextView);

        textViewId.setText(String.valueOf(student.getId()));
        //  textViewEmail.setText(student.getEmail());
        // textViewPassword.setText(student.getPassword());
        // textViewRole.setText(student.getRole());
        builder.setView(dialogView);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}