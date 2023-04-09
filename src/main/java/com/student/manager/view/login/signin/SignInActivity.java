package com.student.manager.view.login.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.student.manager.dao.AccountDAO;
import com.student.manager.databinding.ActivitySignInBinding;
import com.student.manager.model.Account;
import com.student.manager.util.Constant;
import com.student.manager.util.SaveUtil;
import com.student.manager.view.admin.AdminActivity;
import com.student.manager.view.all.create.CreateAccountActivity;
import com.student.manager.view.lecturer.LecturerActivity;
import com.student.manager.view.staff.StaffActivity;
import com.student.manager.view.student.StudentActivity;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initView();
        initListener();
    }

    private void initData() {

        if (SaveUtil.isRememberAcc()) {
            binding.edtTaiKhoan.setText(SaveUtil.getRememberUserName());
            binding.edtMatKhau.setText(SaveUtil.getRememberPassword());
            binding.cbRemember.setChecked(true);
        }
    }

    private void initView() {

    }

    private void initListener() {
        binding.cbRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveUtil.setRememberAcc(binding.cbRemember.isChecked());
            }
        });
        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, CreateAccountActivity.class);
                intent.putExtra("type_account", "student");
                startActivity(intent);
            }
        });
        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtTaiKhoan.getText().toString().trim().equals("")) {
                    Toast.makeText(SignInActivity.this, "Enter account!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtMatKhau.getText().toString().trim().equals("")) {
                    Toast.makeText(SignInActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                } else {
                    checkLogIn(
                            binding.edtTaiKhoan.getText().toString().trim(),
                            binding.edtMatKhau.getText().toString().trim()
                    );
                }
            }
        });
    }

    private void checkLogIn(String tk, String mk) {

        if (Constant.accountAdmin01.getUser_name().equals(tk)
                && Constant.accountAdmin01.getPassword().equals(mk)) {
            login(Constant.accountAdmin01);
            return;
        }

        Account account = AccountDAO.login(tk, mk);
        if ( account != null) {
            login(account);
        } else {
            Toast.makeText(this, "Login information is incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    private void login(Account account) {
        Intent intent;
        switch (account.getRole()) {
            case "admin":
                intent = new Intent(SignInActivity.this, AdminActivity.class);
                break;
            case "staff":
                intent = new Intent(SignInActivity.this, StaffActivity.class);
                break;
            case "lecturer":
                intent = new Intent(SignInActivity.this, LecturerActivity.class);
                break;
            default:
                intent = new Intent(SignInActivity.this, StudentActivity.class);
                break;
        }
        SaveUtil.setRememberUserName(account.getUser_name());
        SaveUtil.setRememberPassword(account.getPassword());
        Toast.makeText(this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
        intent.putExtra("accountId", account.getAccountId());
        startActivity(intent);
        finish();
    }


}