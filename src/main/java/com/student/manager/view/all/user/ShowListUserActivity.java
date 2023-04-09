package com.student.manager.view.all.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.student.manager.dao.LecturerDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.ActivityShowListUserBinding;
import com.student.manager.model.Lecturer;
import com.student.manager.model.Student;
import com.student.manager.view.all.create.CreateAccountActivity;
import com.student.manager.view.lecturer.EditLecturerActivity;
import com.student.manager.view.student.EditStudentActivity;

import java.util.ArrayList;
import java.util.Objects;

public class ShowListUserActivity extends AppCompatActivity {

    ActivityShowListUserBinding binding;

    ManagerStudentAdapter adapterStudent;
    ManagerLecturerAdapter adapterLecture;

    String type_account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowListUserBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        type_account = getIntent().getStringExtra("type_account");
        if (Objects.equals(type_account, "student")) {
            initStudent();
        }
        if (Objects.equals(type_account, "lecturer")) {
            initLecturer();
        }

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(ShowListUserActivity.this, CreateAccountActivity.class));
                intent.putExtra("type_account", type_account);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Objects.equals(type_account, "student")) {
            adapterStudent.setData(StudentDAO.getListStudent());
        }
        if (Objects.equals(type_account, "lecturer")) {
            adapterLecture.setData(LecturerDAO.getListLecturer());
        }
    }

    private void initStudent() {
        binding.txtTitle.setText("List Student");
        adapterStudent = new ManagerStudentAdapter(ShowListUserActivity.this, new ArrayList<>(), new ClickStudent() {
            @Override
            public void clickStudent(Student student) {
                Intent intent = new Intent(new Intent(ShowListUserActivity.this, EditStudentActivity.class));
                intent.putExtra("type_account", type_account);
                intent.putExtra("student_id", student.getStudent_id());

                startActivity(intent);
            }
        });
        binding.rcyList.setAdapter(adapterStudent);
    }

    private void initLecturer() {
        binding.txtTitle.setText("List Lecturer");
        adapterLecture = new ManagerLecturerAdapter(ShowListUserActivity.this, new ArrayList<>(), new ClickLecturer() {
            @Override
            public void clickLecturer(Lecturer lecturer) {
                Intent intent = new Intent(new Intent(ShowListUserActivity.this, EditLecturerActivity.class));
                intent.putExtra("type_account", type_account);
                intent.putExtra("lecturer_id", lecturer.getLecturer_id());
                startActivity(intent);
            }
        });
        binding.rcyList.setAdapter(adapterLecture);
    }
}