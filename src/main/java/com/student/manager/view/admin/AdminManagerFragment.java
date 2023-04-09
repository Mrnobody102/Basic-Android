package com.student.manager.view.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.student.manager.dao.StaffDAO;
import com.student.manager.databinding.FragmentAdminManagerBinding;
import com.student.manager.model.Staff;
import com.student.manager.view.all.create.CreateAccountActivity;

import java.util.ArrayList;

public class AdminManagerFragment extends Fragment implements ClickStaff {


    FragmentAdminManagerBinding binding;

    ManagerStaffAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ManagerStaffAdapter(requireContext(), new ArrayList<>() , this );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminManagerBinding.inflate(inflater, container, false);

        binding.addStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(requireContext(), CreateAccountActivity.class));
                intent.putExtra("type_account", "staff");
                startActivity(intent);
            }
        });

        binding.rcyList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setData(StaffDAO.getListStaff());
    }

    @Override
    public void clickStaff(Staff staff) {
        Intent intent = new Intent(requireContext(), AdminEditStaffActivity.class);
        intent.putExtra("staff_id", staff.getStaff_id());
        startActivity(intent);
    }
}