package com.student.manager.view.staff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.student.manager.R;
import com.student.manager.dao.StaffDAO;
import com.student.manager.databinding.ActivityStaffBinding;
import com.student.manager.model.Staff;
import com.student.manager.view.lecturer.LecturerProfileFragment;

public class StaffActivity extends AppCompatActivity {

    ActivityStaffBinding binding;

    Staff staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStaffBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        if(getIntent().getStringExtra("tag")!=null
                && getIntent().getStringExtra("tag").equals("Profile")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new StaffProfileFragment(staff), "StaffProfile")
                    .commit();
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new StaffManagerFragment(staff), "StaffManager")
                    .commit();
        }
        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_manager:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new StaffManagerFragment(staff), "StaffManager")
                                .commit();
                        break;
                    case R.id.navigation_profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new StaffProfileFragment(staff), "StaffProfile")
                                .commit();
                        break;
                }
                return true;
            }
        });


    }

    private void initData() {
        int accountId = getIntent().getIntExtra("accountId", 0);
        staff = StaffDAO.getStaff(accountId);
    }


    private boolean clickBack = false;
    @Override
    public void onBackPressed() {
        if (clickBack) {
            finish();
        } else {
            clickBack = true;
            Toast.makeText(this, getString(R.string.click_again_back), Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    clickBack = false;
                }
            }, 1000L);
        }
    }
}