package com.student.manager.view.all.course;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.student.manager.R;
import com.student.manager.dao.CourseDAO;
import com.student.manager.databinding.ActivityShowListCourseBinding;
import com.student.manager.model.Course;

import java.util.ArrayList;

public class ShowListCourseActivity extends AppCompatActivity {

    ActivityShowListCourseBinding binding;
    CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowListCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new CourseAdapter(ShowListCourseActivity.this, new ArrayList<>(), new ClickCourse() {
            @Override
            public void clickCourse(Course course) {
                Intent intent = new Intent(ShowListCourseActivity.this, EditCourseActivity.class);
                intent.putExtra("course_id", course.getCourse_id());
                startActivity(intent);
            }
        });
        binding.rcyList.setAdapter(adapter);

        initListener();
    }

    private void initListener() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowListCourseActivity.this, CreateCoureActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setData(CourseDAO.getListCourse());
    }
}