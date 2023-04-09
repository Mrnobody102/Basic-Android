package com.student.manager.view.all.user;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.CourseDAO;
import com.student.manager.dao.StaffDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.ActivityShowListCourseBinding;
import com.student.manager.databinding.ActivityShowStudentCourseBinding;
import com.student.manager.model.Course;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.view.all.course.ClickCourse;
import com.student.manager.view.all.user.StudentCourseAdapter;
import com.student.manager.view.all.course.CreateCoureActivity;
import com.student.manager.view.all.course.EditCourseActivity;
import com.student.manager.view.all.course.ShowListCourseActivity;

import java.util.ArrayList;

public class ManageStudentCourseActivity extends AppCompatActivity {

    ActivityShowStudentCourseBinding binding;
    StudentCourseAdapter adapter;
    int student_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowStudentCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new StudentCourseAdapter(ManageStudentCourseActivity.this, new ArrayList<>(), new ClickCourse() {
            @Override
            public void clickCourse(Course course) {
                delete(course);

            }
        });
        binding.rcyList.setAdapter(adapter);
        initData();
        initListener();
    }

    private void initListener() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageStudentCourseActivity.this, CreateStudentCourseActivity.class);
                intent.putExtra("student_id", student_id);
                startActivity(intent);
            }
        });

    }

    void delete(Course course){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Un-register!");
        alertDialogBuilder.setMessage("Un-register this course for this student?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                StudentCourseSlotDayDAO.deleteStudentCourseSlotDay(student_id, course.getCourse_id());
            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setData(CourseDAO.getCoursesByStudentId(student_id));
    }


    private void initData() {
        student_id = getIntent().getIntExtra("student_id", 0);
    }
}
