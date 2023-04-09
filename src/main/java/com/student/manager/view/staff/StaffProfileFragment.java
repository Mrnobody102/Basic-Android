package com.student.manager.view.staff;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.student.manager.dao.StaffDAO;
import com.student.manager.databinding.FragmentStaffProfileBinding;
import com.student.manager.model.Staff;
import com.student.manager.view.admin.AdminEditStaffActivity;
import com.student.manager.view.login.signin.SignInActivity;

public class StaffProfileFragment extends Fragment {

    FragmentStaffProfileBinding binding;

    Staff staff;

    public StaffProfileFragment(Staff staff) {
        this.staff = staff;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffProfileBinding.inflate(inflater, container, false);

        Staff staffView = StaffDAO.getStaff(staff.getAccountId());
        binding.edtName.setText(staffView.getName());
        binding.edtDateOfBirth.setText(staffView.getDate_of_birth());
        binding.edtAddress.setText(staffView.getAddress());
        binding.edtPhone.setText(staffView.getPhone_number());

        if (staffView.getGender() == 1) {
            binding.rdgGender.setText("Male");
        } else {
            binding.rdgGender.setText("Female");
        }
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), EditStaffActivity.class));
                intent.putExtra("staff_id", staff.getStaff_id());
                intent.putExtra("role", "staff");
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