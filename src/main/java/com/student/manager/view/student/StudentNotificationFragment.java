package com.student.manager.view.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.student.manager.dao.NotificationDAO;
import com.student.manager.databinding.FragmentStudentNotificationBinding;
import com.student.manager.model.Notification;
import com.student.manager.model.Student;
import com.student.manager.view.all.Notification.ClickNotification;
import com.student.manager.view.all.Notification.NotificationAdapter;
import com.student.manager.view.all.Notification.ViewNotificationActivity;

import java.util.ArrayList;

public class StudentNotificationFragment extends Fragment {

    FragmentStudentNotificationBinding binding;
    NotificationAdapter adapter;
    Student student;
    public StudentNotificationFragment() {
        // Required empty public constructor
    }
    public StudentNotificationFragment(Student student) {
        this.student = student;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentNotificationBinding.inflate(inflater, container, false);
        adapter = new NotificationAdapter(requireContext(), new ArrayList<>(),
                    new ClickNotification() {
                        @Override
                        public void clickNotification(Notification notification) {
                            Intent intent = new Intent(requireContext(), ViewNotificationActivity.class);
                            intent.putExtra("notification_id", notification.getNotificationId());
                            startActivity(intent);
                        }
                    });
        adapter.setData(NotificationDAO.getListNotificationByStaff());
        binding.rcyList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setData(NotificationDAO.getListNotificationByStaff());
    }
}