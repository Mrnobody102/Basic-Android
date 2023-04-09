package com.student.manager.view.all.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.student.manager.R;
import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.databinding.ActivityCreateLecturerBinding;
import com.student.manager.model.Lecturer;
import com.student.manager.util.DataUtil;
import com.student.manager.view.student.EditStudentActivity;

import java.util.Calendar;

public class CreateLecturerActivity extends AppCompatActivity {

    ActivityCreateLecturerBinding binding;

    int accountId;
    int gender = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateLecturerBinding.inflate(getLayoutInflater());
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateLecturerActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                if (binding.edtName.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateLecturerActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtDateOfBirth.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateLecturerActivity.this, "Enter Date Of Birth!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtAddress.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateLecturerActivity.this, "Enter Address!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateLecturerActivity.this, "Enter Phone!", Toast.LENGTH_SHORT).show();
                } else {
                    Lecturer lecturer = new Lecturer(
                            0,
                            accountId,
                            binding.edtName.getText().toString().trim(),
                            gender,
                            binding.edtDateOfBirth.getText().toString().trim(),
                            binding.edtAddress.getText().toString().trim(),
                            binding.edtPhone.getText().toString().trim(),
                            DataUtil.getDate(),
                            1
                    );
                    LecturerDAO.insertLecturer(lecturer);
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AccountDAO.deleteAccount(accountId);
    }
}