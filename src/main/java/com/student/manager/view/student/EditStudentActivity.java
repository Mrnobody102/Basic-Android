package com.student.manager.view.student;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.R;
import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.CourseDAO;
import com.student.manager.dao.StaffDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.ActivityEditStudentBinding;
import com.student.manager.model.Student;
import com.student.manager.model.StudentCourseSlotDay;
import com.student.manager.util.DataUtil;
import com.student.manager.view.all.create.CreateLecturerActivity;
import com.student.manager.view.all.create.CreateStudentActivity;
import com.student.manager.view.lecturer.LecturerActivity;

import java.util.Calendar;

public class EditStudentActivity extends AppCompatActivity {

    ActivityEditStudentBinding binding;
    Student student;
    int studentId;
    int gender;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initView();
        initListener();

    }

    private void initListener() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        binding.edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditStudentActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        binding.btnUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.edtName.getText().toString().trim().equals("")) {
                    Toast.makeText(EditStudentActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtDateOfBirth.getText().toString().trim().equals("")) {
                    Toast.makeText(EditStudentActivity.this, "Enter Date Of Birth!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtAddress.getText().toString().trim().equals("")) {
                    Toast.makeText(EditStudentActivity.this, "Enter Address!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(EditStudentActivity.this, "Enter Phone!", Toast.LENGTH_SHORT).show();
                } else {
                    Student newStudent = new Student(
                            student.getStudent_id(),
                            student.getAccountId(),
                            binding.edtName.getText().toString().trim(),
                            gender,
                            binding.edtDateOfBirth.getText().toString().trim(),
                            binding.edtAddress.getText().toString().trim(),
                            binding.edtPhone.getText().toString().trim(),
                            1,
                            DataUtil.getDate(),
                            1
                    );
                    StudentDAO.updateStudent(newStudent);
                    if(role !=null && role.equals("student")){
                        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                        intent.putExtra("tag", "Profile");
                        intent.putExtra("accountId", student.getAccountId());
                        startActivity(intent);
                    }else{
                        student = newStudent;
                        finish();
                    }

                }
            }
        });
    }

    private void initView() {

            if (gender == 1)
                binding.rdMale.setChecked(true);
            else binding.rdFemale.setChecked(true);
            binding.edtName.setText(student.getName());
            binding.edtDateOfBirth.setText(student.getDate_of_birth());
            binding.edtAddress.setText(student.getAddress());
            binding.edtPhone.setText(student.getPhone_number());
    }

    private void initData() {
        studentId = getIntent().getIntExtra("student_id", 0);
        student = StudentDAO.getStudentById(studentId);
        if (student != null) {
            studentId = getIntent().getIntExtra("student_id", 0);
//            student = StudentDAO.getStudent(studentId);
            gender = student.getGender();
        }
        role = getIntent().getStringExtra("role");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}