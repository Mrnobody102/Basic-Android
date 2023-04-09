package com.student.manager.view.all.Notification;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.dao.NotificationDAO;
import com.student.manager.databinding.ActivityEditNotificationBinding;
import com.student.manager.model.Notification;

public class EditNotificationActivity extends AppCompatActivity {

    ActivityEditNotificationBinding binding;
    int notification_id;
    Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initListener();
    }

    private void initListener() {
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationDAO.deleteNotification(notification_id);
                finish();
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtTitle.getText().toString().trim().equals("")) {
                    Toast.makeText(EditNotificationActivity.this, "Enter Title!", Toast.LENGTH_SHORT).show();
                } else if (binding.edtContent.getText().toString().trim().equals("")) {
                    Toast.makeText(EditNotificationActivity.this, "Enter Content!", Toast.LENGTH_SHORT).show();
                } else {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        notification.setTitle(binding.edtTitle.getText().toString());
                        notification.setContent(binding.edtContent.getText().toString());
                        notification.setNotificationId(notification_id);
                        NotificationDAO.updateNotification(notification);
                        Toast.makeText(EditNotificationActivity.this, "Update Successfully.", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });
    }

    private void initData() {
        notification_id = getIntent().getIntExtra("notification_id", 0);
        notification = NotificationDAO.getNotificationById(notification_id);

        binding.txtDate.setText(notification.getDate()+"");
        binding.edtTitle.setText(notification.getTitle());
        binding.edtContent.setText(notification.getContent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}