package com.student.manager.view.all.classroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.ClassDAO;
import com.student.manager.dao.LecturerClassSkillDAO;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.dao.StaffDAO;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.databinding.ActivityAddToClassBinding;
import com.student.manager.model.Lecturer;
import com.student.manager.model.LecturerClassSkill;
import com.student.manager.model.Student;
import com.student.manager.view.all.Mark.ViewClassMarkActivity;
import com.student.manager.view.all.user.ClickStudent;
import com.student.manager.view.lecturer.LecturerAdapter;
import com.student.manager.view.lecturer.LecturerMarkStudentActivity;
import com.student.manager.view.student.StudentAdapter;

public class ClassDetailActivity extends AppCompatActivity implements ClickStudent {

    ActivityAddToClassBinding binding;
    int class_id, course_id, slot_id, lecturer_id;
    int lisLec, speLec, reaLec, wriLec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddToClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initListener();
    }

    private void initListener() {

        binding.spLisLec.setSelection(LecturerClassSkillDAO.getLecturerByClassSkill(1, class_id).getLecturer_id()-1);

        binding.spSpeLec.setSelection(LecturerClassSkillDAO.getLecturerByClassSkill(2, class_id).getLecturer_id()-1);

        binding.spReaLec.setSelection(LecturerClassSkillDAO.getLecturerByClassSkill(3, class_id).getLecturer_id()-1);

        binding.spWriLec.setSelection(LecturerClassSkillDAO.getLecturerByClassSkill(4, class_id).getLecturer_id()-1);

        binding.spLisLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lisLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        binding.spSpeLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                speLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        binding.spReaLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reaLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        binding.spWriLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                wriLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LecturerClassSkillDAO.updateLecturer(new LecturerClassSkill(lisLec,class_id, 1 ));
                LecturerClassSkillDAO.updateLecturer(new LecturerClassSkill(speLec,class_id, 2));
                LecturerClassSkillDAO.updateLecturer(new LecturerClassSkill(reaLec,class_id,3));
                LecturerClassSkillDAO.updateLecturer(new LecturerClassSkill(wriLec,class_id,4));
                Toast.makeText(ClassDetailActivity.this, "Change successfully!", Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassDetailActivity.this,
                        LecturerMarkStudentActivity.class);
                intent.putExtra("class_id", class_id);
                intent.putExtra("lecturer_id", lecturer_id);
                startActivity(intent);
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        class_id = getIntent().getIntExtra("class_id", 0);
        course_id = getIntent().getIntExtra("course_id", 0);
        slot_id = getIntent().getIntExtra("slot_id", 0);
        lecturer_id = getIntent().getIntExtra("lecturer_id", 0);

        binding.addToClass.setText(ClassDAO.getClassById(class_id).getName());

        ///
        StudentAdapter adapter = new StudentAdapter(this,
                StudentClassDAO.getListStudentByClass(class_id));
        adapter.setClickStudent(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,
        RecyclerView.VERTICAL,false);
        binding.rcListStudent.setLayoutManager(manager);
        binding.rcListStudent.setAdapter(adapter);
        ///

        ArrayAdapter listeningLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        listeningLecture.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spLisLec.setAdapter(listeningLecture);

        ArrayAdapter speakingLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        speakingLecture.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spSpeLec.setAdapter(speakingLecture);

        ArrayAdapter readingLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        readingLecture.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spReaLec.setAdapter(readingLecture);

        ArrayAdapter writingLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        writingLecture.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spWriLec.setAdapter(writingLecture);
    }

    @Override
    public void clickStudent(Student student) {
        delete(student.getStudent_id());
    }

    void delete(int student_id){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Remove!!!");
        alertDialogBuilder.setMessage("Remove this student?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                StudentClassDAO.deleteStudentClass(student_id, class_id);

                StudentAdapter adapter = new StudentAdapter(ClassDetailActivity.this,
                        StudentClassDAO.getListStudentByClass(class_id));
                adapter.setClickStudent(ClassDetailActivity.this);
                LinearLayoutManager manager = new LinearLayoutManager(ClassDetailActivity.this,
                        RecyclerView.VERTICAL,false);
                binding.rcListStudent.setLayoutManager(manager);
                binding.rcListStudent.setAdapter(adapter);

            }
        });
        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}