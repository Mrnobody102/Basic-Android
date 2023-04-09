package com.student.manager.view.all.Notification;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.dao.NotificationDAO;
import com.student.manager.databinding.ActivityCreateNotificationBinding;
import com.student.manager.model.Notification;

import java.time.LocalDate;

public class CreateNotificationActivity extends AppCompatActivity {

    ActivityCreateNotificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
                if (binding.edtTitle.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateNotificationActivity.this, "Enter Title!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtContent.getText().toString().trim().equals("")) {
                    Toast.makeText(CreateNotificationActivity.this, "Enter Content!", Toast.LENGTH_SHORT).show();
                } else {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Notification notification = new Notification(
                                binding.edtTitle.getText().toString(),
                                binding.edtContent.getText().toString(),
                                1,0,0, LocalDate.now());
                        NotificationDAO.insertNotification(notification);
                        Toast.makeText(CreateNotificationActivity.this, "Create Successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }

}