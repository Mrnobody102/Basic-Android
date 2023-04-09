package com.student.manager.view.staff;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.student.manager.databinding.FragmentStaffManagerBinding;
import com.student.manager.model.Staff;
import com.student.manager.view.all.Notification.ShowListNotificationActivity;
import com.student.manager.view.all.classroom.ShowListClassActivity;
import com.student.manager.view.all.course.ShowListCourseActivity;
import com.student.manager.view.all.user.ShowListUserActivity;

public class StaffManagerFragment extends Fragment {

    Staff staff;

    FragmentStaffManagerBinding binding;

    public StaffManagerFragment(Staff staff) {
        this.staff = staff;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffManagerBinding.inflate(inflater, container, false);

        binding.btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), ShowListUserActivity.class));
                intent.putExtra("type_account", "student");
                startActivity(intent);
            }
        });

        binding.btnLecturer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), ShowListUserActivity.class));
                intent.putExtra("type_account", "lecturer");
                startActivity(intent);
            }
        });

        binding.btnCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), ShowListCourseActivity.class));
                intent.putExtra("role", "staff");
                startActivity(intent);
            }
        });

        binding.btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), ShowListClassActivity.class));
                intent.putExtra("role", "staff");
                startActivity(intent);
            }
        });
        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), ShowListNotificationActivity.class));
                intent.putExtra("role", "staff");
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}