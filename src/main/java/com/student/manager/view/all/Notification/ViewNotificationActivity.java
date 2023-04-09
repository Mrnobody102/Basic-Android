package com.student.manager.view.all.Notification;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.dao.NotificationDAO;
import com.student.manager.databinding.ActivityViewNotificationBinding;
import com.student.manager.model.Notification;

public class ViewNotificationActivity extends AppCompatActivity {

    ActivityViewNotificationBinding binding;
    int notification_id;
    Notification notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
    }

    private void initData() {
        notification_id = getIntent().getIntExtra("notification_id", 0);
        notification = NotificationDAO.getNotificationById(notification_id);

        binding.txtDate.setText(notification.getDate()+"");
        binding.edtTitle.setText(notification.getTitle());
        binding.edtContent.setText(notification.getContent());
    }
}