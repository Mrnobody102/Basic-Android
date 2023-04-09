package com.student.manager.view.lecturer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.student.manager.R;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.databinding.ActivityLecturerBinding;
import com.student.manager.model.Lecturer;
import com.student.manager.view.student.StudentNotificationFragment;

public class LecturerActivity extends AppCompatActivity {

    ActivityLecturerBinding binding;

    Lecturer lecturer;
    int accountId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLecturerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        accountId = getIntent().getIntExtra("accountId", 0);
        lecturer = LecturerDAO.getLecturer(accountId);
        if(getIntent().getStringExtra("tag")!=null
        && getIntent().getStringExtra("tag").equals("Profile")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new LecturerProfileFragment(lecturer), "Profile")
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new LecturerClassListFragment(lecturer), "ClassList")
                    .commit();

        }

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_class_list:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new LecturerClassListFragment(lecturer), "ClassList")
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
                                .replace(R.id.frame_container, new LecturerProfileFragment(lecturer), "Profile")
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