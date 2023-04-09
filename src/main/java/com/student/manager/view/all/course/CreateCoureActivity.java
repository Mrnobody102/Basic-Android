package com.student.manager.view.all.course;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.student.manager.dao.CourseDAO;
import com.student.manager.databinding.ActivityCreateCoureBinding;
import com.student.manager.model.Course;
import com.student.manager.util.DataUtil;
import com.student.manager.view.login.signin.SignInActivity;

public class CreateCoureActivity extends AppCompatActivity {

    private ActivityCreateCoureBinding binding;

    private int level_id = 1;
    private int course_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateCoureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initListener();

    }

    private void initData() {
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                DataUtil.listLevel);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        binding.spStartLevel.setAdapter(ad);

        ArrayAdapter ad2
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                DataUtil.listCourseType);

        // set simple layout resource file
        // for each item of spinner
        ad2.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        binding.spCourseType.setAdapter(ad2);
    }

    private void initListener() {
        binding.spStartLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level_id = DataUtil.listLevel.get(i).getLevel_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spCourseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course_id = DataUtil.listCourseType.get(i).getCourse_type_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtName.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateCoureActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else {
                    CourseDAO.insertCourse(new Course(
                            0,
                            binding.edtName.getText().toString().trim(),
                            binding.edtDescription.getText().toString().trim(),
                            level_id,
                            course_id
                    ));
                    finish();
                }
            }
        });
    }
}