package com.student.manager.view.all.classroom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.student.manager.dao.ClassDAO;
import com.student.manager.dao.CourseDAO;
import com.student.manager.dao.LecturerClassSkillDAO;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.databinding.ActivityCreateClassBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Lecturer;
import com.student.manager.model.LecturerClassSkill;
import com.student.manager.model.Student;
import com.student.manager.model.StudentClass;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class CreateClassActivity extends AppCompatActivity {

    ActivityCreateClassBinding binding;

    int course_id = 1;
    int slotday_id = 1;
    int lisLec, speLec, reaLec, wriLec;

    List<Student> studentList = new ArrayList<>();
    List<Lecturer> lecturerList = new ArrayList<>();
    int numberOfClassCreate = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateClassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initListener();
    }

    private void initListener() {

        binding.spCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                course_id = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                course_id = 1;
            }
        });

        binding.spSlotDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                slotday_id = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                slotday_id = 1;
            }
        });

        binding.spLisLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lisLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                lisLec = 1;
            }
        });

        binding.spSpeLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                speLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                speLec = 1;
            }
        });

        binding.spReaLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                reaLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                reaLec = 1;
            }
        });

        binding.spWriLec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                wriLec = i+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                wriLec = 1;
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isExistCourseSlotDay = true;
                for(Class c:ClassDAO.getListClassRoom())
                {
                    if (course_id == c.getCourse_id() && slotday_id == c.getSlot_day_id()){
                        isExistCourseSlotDay = false;
                    }
                }

                int studentCourseSlotDay = StudentCourseSlotDayDAO.getListStudentByCourseSlotDay(course_id, slotday_id).size();
                if (binding.edtClassName.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateClassActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (isExistCourseSlotDay == false) {
                    Toast.makeText(CreateClassActivity.this, "Exist Course and Slot Day!", Toast.LENGTH_SHORT).show();
                } else if (studentCourseSlotDay <= 20 || studentCourseSlotDay >= 40) {
                    Toast.makeText(CreateClassActivity.this, "A class must be 20 - 40 students!", Toast.LENGTH_SHORT).show();
                } else {
                        int classId = ClassDAO.insertClassRoom(new Class(course_id, slotday_id,
                                binding.edtClassName.getText().toString().trim(), 1,
                                studentList, lecturerList));
                        LecturerClassSkillDAO.insertLecturerClassSkill(
                                new LecturerClassSkill(lisLec, classId, 1));
                        LecturerClassSkillDAO.insertLecturerClassSkill(
                                new LecturerClassSkill(speLec, classId, 2));
                        LecturerClassSkillDAO.insertLecturerClassSkill(
                                new LecturerClassSkill(reaLec, classId, 3));
                        LecturerClassSkillDAO.insertLecturerClassSkill(
                            new LecturerClassSkill(wriLec, classId, 4));
                    List<Student> students = StudentCourseSlotDayDAO.getListStudentByCourseSlotDay(course_id, slotday_id);
                    for (Student student:students) {
                        StudentClassDAO.insertStudentClass(
                                new StudentClass(student.getStudent_id(), classId));
                    }
                    finish();
                }
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
        ArrayAdapter adCourse
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                CourseDAO.getListCourse());
        adCourse.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spCourse.setAdapter(adCourse);


        ArrayAdapter adSlotDay
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                DataUtil.listSlotDay);
        adCourse.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spSlotDay.setAdapter(adSlotDay);

        ArrayAdapter listeningLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        adCourse.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spLisLec.setAdapter(listeningLecture);

        ArrayAdapter speakingLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        adCourse.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spSpeLec.setAdapter(speakingLecture);

        ArrayAdapter readingLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        adCourse.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spReaLec.setAdapter(readingLecture);

        ArrayAdapter writingLecture
                = new ArrayAdapter( this, android.R.layout.simple_spinner_item,
                LecturerDAO.getListLecturer());
        adCourse.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        binding.spWriLec.setAdapter(writingLecture);


    }
//    private void changeData(){
//        StudentAdapter adapter = new StudentAdapter(this,
//                StudentCourseDAO.getListStudentByCourse(course_id,0));
//        LinearLayoutManager manager = new LinearLayoutManager(this,
//                RecyclerView.VERTICAL,false);
//        binding.rcListStudent.setLayoutManager(manager);
//        binding.rcListStudent.setAdapter(adapter);
//    }
}