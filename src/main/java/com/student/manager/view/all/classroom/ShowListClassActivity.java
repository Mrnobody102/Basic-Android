package com.student.manager.view.all.classroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.student.manager.dao.ClassDAO;
import com.student.manager.databinding.ActivityShowListClassBinding;
import com.student.manager.model.Class;

import java.util.ArrayList;

public class ShowListClassActivity extends AppCompatActivity {

    ActivityShowListClassBinding binding;

    ClassroomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowListClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ClassroomAdapter(ShowListClassActivity.this, new ArrayList<>(), new ClickClassroom() {
            @Override
            public void clickClassroom(Class classroom) {
                Intent intent = new Intent(ShowListClassActivity.this, ClassDetailActivity.class);
                intent.putExtra("class_id", classroom.getClass_id());
                intent.putExtra("course_id", classroom.getCourse_id());
                intent.putExtra("slot_id", classroom.getSlot_day_id());
                startActivity(intent);
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
                startActivity(new Intent(ShowListClassActivity.this, CreateClassActivity.class));
            }
        });

    }

    private void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setData(ClassDAO.getListClassRoom());
    }
}