package com.student.manager.view.lecturer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.student.manager.R;
import com.student.manager.dao.LecturerDAO;
import com.student.manager.databinding.FragmentLecturerProfileBinding;
import com.student.manager.databinding.FragmentStudentProfileBinding;
import com.student.manager.model.Lecturer;
import com.student.manager.view.login.signin.SignInActivity;

public class LecturerProfileFragment extends Fragment {

    FragmentLecturerProfileBinding binding;

    Lecturer lecturer;

    public LecturerProfileFragment(Lecturer lecturer) {
        this.lecturer = lecturer;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLecturerProfileBinding.inflate(inflater, container, false);

        Lecturer lecturerView = LecturerDAO.getLecturer(lecturer.getAccountId());
        binding.edtName.setText(lecturerView.getName());
        binding.edtDateOfBirth.setText(lecturerView.getDate_of_birth());
        binding.edtAddress.setText(lecturerView.getAddress());
        binding.edtPhone.setText(lecturerView.getPhone_number());

        if (lecturer.getGender() == 1) {
            binding.rdgGender.setText("Male");
        } else {
            binding.rdgGender.setText("Female");
        }

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), EditLecturerActivity.class));
                intent.putExtra("lecturer_id", lecturer.getLecturer_id());
                intent.putExtra("role", "lecturer");
                startActivity(intent);
            }
        });
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), SignInActivity.class));
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}