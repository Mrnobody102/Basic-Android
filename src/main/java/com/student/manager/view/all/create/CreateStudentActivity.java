package com.student.manager.view.all.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.student.manager.model.Student;
import com.student.manager.model.StudentClass;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.util.DataUtil;
import com.student.manager.view.student.EditStudentActivity;

import java.util.Calendar;

public class CreateStudentActivity extends AppCompatActivity {

    ActivityCreateStudentBinding binding;

    int accountId;
    int gender = 1;

    int course_id = 1;
    int slotday_id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        initData();
        initView();
        initListener();
        
    }

    private void initListener() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountDAO.deleteAccount(accountId);
                finish();
            }
        });
        binding.edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateStudentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        if(d<10 && m<10){
                            binding.edtDateOfBirth.setText("0"+d+"/0"+ (m+1)+"/"+y);
                        } else if(m<10 && m>=10){
                            binding.edtDateOfBirth.setText("0"+d+"/"+(m+1)+"/"+y);
                        }else if(m>=10 && m<10){
                            binding.edtDateOfBirth.setText(d+"/0"+(m+1)+"/"+y);
                        }else{
                            binding.edtDateOfBirth.setText(d+"/"+(m+1)+"/"+y);
                        }
                    }
                },2000,month,day);
                datePickerDialog.show();
            }
        });
        binding.rdgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdMale:
                        gender = 1;
                        break;
                    case R.id.rdFemale:
                        gender = 2;
                        break;
                }
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

                if (binding.edtName.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateStudentActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtDateOfBirth.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateStudentActivity.this, "Enter Date Of Birth!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtAddress.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateStudentActivity.this, "Enter Address!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateStudentActivity.this, "Enter Phone!", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = new Student(
                            accountId,
                            binding.edtName.getText().toString().trim(),
                            gender,
                            binding.edtDateOfBirth.getText().toString().trim(),
                            binding.edtAddress.getText().toString().trim(),
                            binding.edtPhone.getText().toString().trim(),
                            1,
                            DataUtil.getDate(),
                            1
                    );
                    int sid = StudentDAO.insertStudent(student);
                    StudentCourseSlotDayDAO.insertStudentCourseSlotDay(
                            new StudentCourseSlotDay(sid, course_id, slotday_id));
//                    StudentClassDAO.insertStudentClass(
//                            new StudentClass(sid, ClassDAO.getClassByCourseIdAndSlotDayId(course_id, slotday_id).getClass_id()));
                    finish();
                }
            }
        });
    }

    private void initView() {
        gender = 1;
        binding.rdMale.setChecked(true);
    }

    private void initData() {

        accountId = getIntent().getIntExtra("accountId", 0);

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