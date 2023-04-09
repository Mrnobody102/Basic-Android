package com.student.manager.view.all.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.student.manager.R;
import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.ClassDAO;
import com.student.manager.dao.CourseDAO;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.ActivityCreateStudentBinding;
import com.student.manager.databinding.ActivityCreateStudentCourseBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Student;
import com.student.manager.model.StudentClass;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.util.DataUtil;
import com.student.manager.view.all.classroom.CreateClassActivity;
import com.student.manager.view.all.create.CreateStudentActivity;


public class CreateStudentCourseActivity extends AppCompatActivity {

    ActivityCreateStudentCourseBinding binding;

    int accountId;

    int course_id = 1;
    int slotday_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateStudentCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initListener();

    }

    private void initListener() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                boolean isExistCourseSlotDay = true;
                for(Class c: StudentClassDAO.getListClassByStudent(accountId))
                {
                    if (course_id == c.getCourse_id() && slotday_id == c.getSlot_day_id()){
                        isExistCourseSlotDay = false;
                    }
                }
                int studentCourseSlotDay = StudentCourseSlotDayDAO.getListStudentByCourseSlotDay(course_id, slotday_id).size();

                if (isExistCourseSlotDay == false) {
                    Toast.makeText(CreateStudentCourseActivity.this, "Exist Course and Slot Day!", Toast.LENGTH_SHORT).show();
                } else if (studentCourseSlotDay <= 18 || studentCourseSlotDay >= 40){
                    Toast.makeText(CreateStudentCourseActivity.this, "This Course and Slot Day too few or too many students!!", Toast.LENGTH_SHORT).show();
                } else {
                    StudentCourseSlotDayDAO.insertStudentCourseSlotDay(new StudentCourseSlotDay(accountId, course_id, slotday_id));
                    finish();
                }
            }
        });
    }

    private void initData() {

        accountId = getIntent().getIntExtra("student_id", 0);

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AccountDAO.deleteAccount(accountId);
    }
}