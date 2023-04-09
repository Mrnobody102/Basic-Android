package com.student.manager.view.all.Notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.student.manager.dao.NotificationDAO;
import com.student.manager.databinding.ActivityShowListNotificationBinding;
import com.student.manager.model.Notification;

import java.util.ArrayList;

public class ShowListNotificationActivity extends AppCompatActivity {

    ActivityShowListNotificationBinding binding;
    NotificationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityShowListNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
            adapter = new NotificationAdapter(ShowListNotificationActivity.this, new ArrayList<>(),
                    new ClickNotification() {
                        @Override
                        public void clickNotification(Notification notification) {
                            Intent intent = new Intent(ShowListNotificationActivity.this, EditNotificationActivity.class);
                            intent.putExtra("notification_id", notification.getNotificationId());
                            startActivity(intent);
                        }
                    });

        binding.rcyList.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ShowListNotificationActivity.this, CreateNotificationActivity.class);
                    intent.putExtra("role", "staff");
                    startActivity(intent);
                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setData(NotificationDAO.getListNotificationByStaff());
    }

}