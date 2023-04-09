package com.student.manager.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.student.manager.R;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.ActivityStudentBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Student;
import com.student.manager.view.lecturer.LecturerProfileFragment;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    ActivityStudentBinding binding;


    Student student ;
    int accountId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        accountId = getIntent().getIntExtra("accountId", 0);
        student = StudentDAO.getStudent(accountId);
        ArrayList<Class> classes = StudentClassDAO.getListClassByStudent(student.getStudent_id());

        if(getIntent().getStringExtra("tag")!=null
                && getIntent().getStringExtra("tag").equals("Profile")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new StudentProfileFragment(student), "Profile")
                    .commit();
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new StudentTimeTableFragment(student), "StatisticalFragment")
                    .commit();
        }
        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_time_table:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new StudentTimeTableFragment(student), "TimeTable")
                                .commit();
                        break;
                    case R.id.navigation_class_list:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new StudentClassListFragment(student), "ClassList")
                                .commit();
                        break;
                    case R.id.navigation_noti:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new StudentNotificationFragment(), "Notification")
                                .commit();
                        break;
                    case R.id.navigation_profile:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new StudentProfileFragment(student), "Profile")
                                .commit();
                        break;
                }
                return true;
            }
        });


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