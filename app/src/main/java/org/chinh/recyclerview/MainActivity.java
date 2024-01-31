package org.chinh.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        List<Student> studentList = new DatabaseHandler2(this).getAllStudents();

        studentAdapter = new StudentAdapter(studentList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);
    }

    @Override
    public void onItemClick(int position, Student student) {
        showUserInfoDialog(student);
    }

    private void showUserInfoDialog(Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_user_item, null);

        TextView textViewId = dialogView.findViewById(R.id.idTextView);
        TextView textViewEmail = dialogView.findViewById(R.id.emailTextView);
        TextView textViewPassword = dialogView.findViewById(R.id.passwordTextView);
        TextView textViewRole = dialogView.findViewById(R.id.roleTextView);

        textViewId.setText("ID: " + student.getId());
        textViewEmail.setText("Địa chỉ: " + student.getDiaChi());
        textViewPassword.setText("Họ tên: " + student.getHoTen());
        textViewRole.setText("Số điện thoại: " + student.getSoDienThoai());

        builder.setView(dialogView);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}