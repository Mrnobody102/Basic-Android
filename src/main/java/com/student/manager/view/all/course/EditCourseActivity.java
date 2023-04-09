package com.student.manager.view.all.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.student.manager.dao.CourseDAO;
import com.student.manager.databinding.ActivityEditCourseBinding;
import com.student.manager.model.Course;
import com.student.manager.util.DataUtil;

public class EditCourseActivity extends AppCompatActivity {

    ActivityEditCourseBinding binding;
    int course_id;
    int level_id;
    int course_type;
    Course course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initListener();
    }

    private void initListener() {
        binding.spStartLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level_id = DataUtil.listLevel.get(i).getLevel_id();
//                level_id = i +1 ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                level_id = course.getStart_level()-1;
            }
        });

        binding.spCourseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course_type = DataUtil.listCourseType.get(i).getCourse_type_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                course_type = course.getCourse_type_id()-1;
            }
        });
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtName.getText().toString().trim().equals("")) {
                    Toast.makeText(EditCourseActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else {
                    CourseDAO.updateCourse(new Course(
                            course_id,
                            binding.edtName.getText().toString().trim(),
                            binding.edtDescription.getText().toString().trim(),
                            level_id,
                            course_type
                    ));
                    finish();
                }
            }
        });
    }

    private void initData() {
        course_id = getIntent().getIntExtra("course_id", 0);
        Course course = CourseDAO.getCourseById(course_id);
        level_id = course.getStart_level();
        course_type = course.getCourse_type_id();
        binding.edtName.setText(course.getName());
        binding.edtDescription.setText(course.getDescription());
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
        binding.spStartLevel.setSelection(course.getStart_level()-1);

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
        binding.spCourseType.setSelection(course.getCourse_type_id()-1);
    }
}