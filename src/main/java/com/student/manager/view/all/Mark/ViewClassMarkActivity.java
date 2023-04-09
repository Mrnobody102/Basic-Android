package com.student.manager.view.all.Mark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.student.manager.dao.LecturerClassSkillDAO;
import com.student.manager.databinding.ActivityViewClassMarkBinding;
import com.student.manager.model.Lecturer;
import com.student.manager.util.DataUtil;
import com.student.manager.view.lecturer.LecturerMarkStudentActivity;

import java.util.List;

public class ViewClassMarkActivity extends AppCompatActivity {
    ActivityViewClassMarkBinding binding;

    int skill = 0;
    int classId  = 1;
    Lecturer lecturer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewClassMarkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initData();
        initListener();
    }

    private void initListener() {
        binding.spSkill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                skill = i;
                lecturer = LecturerClassSkillDAO.getLecturerByClassSkill(skill,classId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                skill = 1;
            }
        });
        binding.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewClassMarkActivity.this,
                        LecturerMarkStudentActivity.class);
                lecturer = LecturerClassSkillDAO.getLecturerByClassSkill(classId,skill);
                intent.putExtra("lecturer_id", lecturer.getLecturer_id());
                intent.putExtra("class_id", classId);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        ArrayAdapter skills
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                DataUtil.skills);
        skills.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spSkill.setAdapter(skills);
        binding.spSkill.setSelection(0);

    }
}