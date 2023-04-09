package com.student.manager.view.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.student.manager.R;
import com.student.manager.dao.StudentDAO;
import com.student.manager.databinding.FragmentStaffManagerBinding;
import com.student.manager.databinding.FragmentStudentProfileBinding;
import com.student.manager.model.Student;
import com.student.manager.view.lecturer.EditLecturerActivity;
import com.student.manager.view.login.signin.SignInActivity;

public class StudentProfileFragment extends Fragment {

    FragmentStudentProfileBinding binding;

    Student student;

    public StudentProfileFragment(Student student) {
        this.student = student;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentProfileBinding.inflate(inflater, container, false);

        Student studentView = StudentDAO.getStudent(student.getAccountId());
        binding.edtName.setText(studentView.getName());
        binding.edtDateOfBirth.setText(studentView.getDate_of_birth());
        binding.edtAddress.setText(studentView.getAddress());
        binding.edtPhone.setText(studentView.getPhone_number());

        if (studentView.getGender() == 1) {
            binding.rdgGender.setText("Male");
        } else {
            binding.rdgGender.setText("Female");
        }

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), EditStudentActivity.class));
                intent.putExtra("student_id", student.getStudent_id());
                intent.putExtra("role", "student");
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
}