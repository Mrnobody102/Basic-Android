package com.student.manager.view.lecturer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.dao.ClassDAO;
import com.student.manager.dao.LecturerClassSkillDAO;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.dao.MarkDAO;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.ActivityLecturerMarkStudentBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Lecturer;
import com.student.manager.model.Mark;
import com.student.manager.model.MarkItem;
import com.student.manager.model.Skill;
import com.student.manager.model.Student;
import com.student.manager.util.DataUtil;
import com.student.manager.view.all.Mark.ClickMark;
import com.student.manager.view.all.Mark.MarkAdapter;
import com.student.manager.view.all.user.ClickStudent;
import com.student.manager.view.student.StudentAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LecturerMarkStudentActivity extends AppCompatActivity implements ClickMark {
    ActivityLecturerMarkStudentBinding binding;
    int classId;
    int lecturerId;
    Lecturer lecturer;
    int skillId = 1;
    List<Student> lStudent = new ArrayList<>();
    List<Mark> lMark = new ArrayList<>();
    List<MarkItem> lMarkItem = new ArrayList<>();
    MarkAdapter adapter;
    Class aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLecturerMarkStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
        initListener();
    }

    private void initView() {
        binding.txtClassName.setText(aClass.getName());
        binding.txtLecturer.setText(lecturer.getName());
    }

    private void initListener() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("333333333333333333333",lMark.size()+"...........");
                Log.d("444444444444444444444",lMarkItem.get(0).getPoint()+"...........");

                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();
                for (int i = 0; i < lMark.size(); i++) {
                    lMark.get(i).setValue(lMarkItem.get(i).getPoint());
                    Log.d("6666666666666666666666",lMark.get(0).getValue()+"...........");
                    lMark.get(i).setModifiedDate(currentDate + "");
                    if (MarkDAO.getMark(classId, lMark.get(i).getStudentId(), skillId) == null) {
                        MarkDAO.insertMark(lMark.get(i));
                    } else {
                        MarkDAO.updateMark(lMark.get(i));
                    }
                }
                changeData();
                Toast.makeText(LecturerMarkStudentActivity.this, "Mark successfully.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        classId = getIntent().getIntExtra("class_id", 0);
        lecturerId = getIntent().getIntExtra("lecturer_id", 0);
        aClass = ClassDAO.getClassById(classId);
        lecturer = LecturerDAO.getLecturerById(lecturerId);
        lStudent = StudentClassDAO.getListStudentByClass(classId);

        List<Integer> lSkill = LecturerClassSkillDAO.getListSkillByLecturerAndClass(
                lecturer.getLecturer_id(), classId);
        List<String> skills = new ArrayList<>();
        for (int i = 0; i < DataUtil.skills.size(); i++) {
            for (int j = 0; j < lSkill.size(); j++) {
                if (lSkill.get(j) == i + 1) {
                    skills.add(new String(DataUtil.skills.get(i)));
                }
            }
        }
        ArrayAdapter listSkill
                = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                skills);
        listSkill.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spSkill.setAdapter(listSkill);

        binding.spSkill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                skillId = lSkill.get(position);
                changeData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                skillId = lSkill.get(0);
                changeData();

            }
        });
    }

    private void changeData() {
        lMark = new ArrayList<>();
        lMarkItem = new ArrayList<>();

        for (int i = 0; i < lStudent.size(); i++) {
            Mark mark = MarkDAO.getMark(classId, lStudent.get(i).getStudent_id(), skillId);
            if (mark == null) {
                mark = new Mark();
                mark.setStudentId(lStudent.get(i).getStudent_id());
                mark.setClassId(classId);
                mark.setLecturerId(lecturerId);
                mark.setValue(0);
                mark.setSkill(skillId);
                Calendar calendar = Calendar.getInstance();
                Date currentDate = calendar.getTime();
                mark.setCreatedDate(currentDate + "");
                mark.setModifiedDate(currentDate + "");
                lMark.add(mark);
            } else {
                lMark.add(mark);
            }
        }
        Log.d("2222222222222222222222",lMark.size()+"...........");
        for (int i = 0; i < lStudent.size(); i++) {
            lMarkItem.add(new MarkItem(i + 1, lStudent.get(i).getName(),
                    lMark.get(i).getValue()));
        }
        Log.d("2222222222222222222222",lMarkItem.size()+"...........");
        adapter = new MarkAdapter(LecturerMarkStudentActivity.this,
                new ArrayList<>());
        adapter.setData((ArrayList<MarkItem>) lMarkItem);
        adapter.setClickMark(LecturerMarkStudentActivity.this);
        binding.rcyList.setAdapter(adapter);
    }

    @Override
    public void clickMark(int position, String s) {
        if(s.length()>0 && !s.startsWith(".") &&
                !s.endsWith(".")){
            Log.d("555555555555555555555", s+"...........");
            lMarkItem.get(position).setPoint(Float.parseFloat(s));
        }else{
            lMarkItem.get(position).setPoint(0);
        }
    }
}


