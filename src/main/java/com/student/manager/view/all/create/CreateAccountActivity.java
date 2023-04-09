package com.student.manager.view.all.create;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.student.manager.dao.AccountDAO;
import com.student.manager.databinding.ActivityCreateAccountBinding;
import com.student.manager.model.Account;
import com.student.manager.util.Constant;

public class CreateAccountActivity extends AppCompatActivity {

    ActivityCreateAccountBinding binding;

    String roleAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initListener();
    }

    private void initData() {
        roleAccount = getIntent().getStringExtra("type_account");
        if (roleAccount == null) {
            roleAccount = "student";
        }
        switch (roleAccount) {
            case "admin":
                binding.accountType.setText("Admin");
                break;
            case "staff":
                binding.accountType.setText("Staff");
                break;
            case "lecturer":
                binding.accountType.setText("Lecturer");
                break;
            default:
                binding.accountType.setText("Student");
                break;
        }
    }

    private void initListener() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtTaiKhoan.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateAccountActivity.this, "Enter Username!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtMatKhau.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateAccountActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                }else if (binding.edtEmail.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateAccountActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                } else {
                    if ( !checkAccountExit(
                            binding.edtTaiKhoan.getText().toString().trim()
                    )) {
                        binding.prgLoad.setVisibility(View.VISIBLE);
                        AccountDAO.insertAccount(new Account(
                                0,
                                binding.edtTaiKhoan.getText().toString().trim(),
                                binding.edtMatKhau.getText().toString().trim(),
                                roleAccount,
                                binding.edtEmail.getText().toString().trim()
                        ));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Account account = AccountDAO.login(binding.edtTaiKhoan.getText().toString().trim(), binding.edtMatKhau.getText().toString().trim());
                                if (account != null) {
                                    nextToCrateInformation(account);
                                } else {
                                    Toast.makeText(CreateAccountActivity.this, "Create Fail!", Toast.LENGTH_SHORT).show();
                                    binding.prgLoad.setVisibility(View.GONE);
                                }
                            }
                        },200L);

                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Username exit!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void nextToCrateInformation(Account account) {
        Intent intent;
        switch (account.getRole()) {
            case "staff":
                intent = new Intent(CreateAccountActivity.this, CreateStaffActivity.class);
                break;
            case "lecturer":
                intent = new Intent(CreateAccountActivity.this, CreateLecturerActivity.class);
                break;
            default:
                intent = new Intent(CreateAccountActivity.this, CreateStudentActivity.class);
                break;
        }
        intent.putExtra("accountId", account.getAccountId());
        startActivity(intent);
        binding.prgLoad.setVisibility(View.GONE);
        finish();
    }

    private boolean checkAccountExit(String tk) {
        if (Constant.accountAdmin01.getUser_name().equals(tk)) {
            return true;
        }
        Account account = AccountDAO.checkExit(tk);
        return account != null;
    }
}