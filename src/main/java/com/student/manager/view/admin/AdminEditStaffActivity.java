package com.student.manager.view.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.student.manager.R;
import com.student.manager.dao.AccountDAO;
import com.student.manager.dao.StaffDAO;
import com.student.manager.databinding.ActivityEditStaffBinding;
import com.student.manager.model.Staff;

public class AdminEditStaffActivity extends AppCompatActivity {

    ActivityEditStaffBinding binding;
    int staff_id ;
    Staff staff;
    int gender = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initListener();
    }

    private void initListener() {
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
                    Toast.makeText(AdminEditStaffActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtDateOfBirth.getText().toString().trim().equals("")) {
                    Toast.makeText(AdminEditStaffActivity.this, "Enter Date Of Birth!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtAddress.getText().toString().trim().equals("")) {
                    Toast.makeText(AdminEditStaffActivity.this, "Enter Address!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(AdminEditStaffActivity.this, "Enter Phone!", Toast.LENGTH_SHORT).show();
                } else {
                    Staff staffNew = new Staff(
                            staff.getStaff_id(),
                            staff.getAccountId(),
                            binding.edtName.getText().toString().trim(),
                            gender,
                            binding.edtDateOfBirth.getText().toString().trim(),
                            binding.edtAddress.getText().toString().trim(),
                            binding.edtPhone.getText().toString().trim(),
                            staff.getDate_of_join(),
                            staff.getStatus()
                    );
                    StaffDAO.updateStaff(staffNew);
                    staff = staffNew;
                    finish();
                }
            }
        });
    }

    private void initData() {
        staff_id = getIntent().getIntExtra("staff_id", 0);
        staff = StaffDAO.getStaffbyId(staff_id);
        binding.edtName.setText(staff.getName());
        binding.edtDateOfBirth.setText(staff.getDate_of_birth());
        binding.edtAddress.setText(staff.getAddress());
        binding.edtPhone.setText(staff.getPhone_number());
        gender = staff.getGender();
        if (gender == 1) {
            binding.rdMale.setChecked(true);
        } else  {
            binding.rdFemale.setChecked(true);
        }
    }

    void delete(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete!!!");
        alertDialogBuilder.setMessage("Delete this Staff?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                AccountDAO.deleteAccount(staff.getAccountId());
                StaffDAO.deleteStaff(staff_id);
                finish();
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