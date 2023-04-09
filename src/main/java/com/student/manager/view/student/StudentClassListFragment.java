package com.student.manager.view.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.student.manager.R;
import com.student.manager.dao.ClassDAO;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.databinding.ActivityShowListClassBinding;
import com.student.manager.databinding.FragmentStudentClassListBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Student;
import com.student.manager.view.all.classroom.ClassDetailActivity;
import com.student.manager.view.all.classroom.ClassroomAdapter;
import com.student.manager.view.all.classroom.ClickClassroom;
import com.student.manager.view.all.classroom.ShowListClassActivity;

import java.util.ArrayList;


public class StudentClassListFragment extends Fragment {

    FragmentStudentClassListBinding binding;
    ClassroomAdapter adapter;

    Student student;

    public StudentClassListFragment(Student student) {
        this.student = student;
    }

    public StudentClassListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentStudentClassListBinding.inflate(getLayoutInflater());

        adapter = new ClassroomAdapter(getActivity(), new ArrayList<>(), new ClickClassroom() {
            @Override
            public void clickClassroom(Class classroom) {
                Intent intent = new Intent(getActivity(), ClassDetailActivity.class);
                intent.putExtra("class_id", classroom.getClass_id());
                intent.putExtra("course_id", classroom.getCourse_id());
                intent.putExtra("slot_id", classroom.getSlot_day_id());
                startActivity(intent);
            }
        });

        binding.rcyList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (student != null) {
            adapter.setData(StudentClassDAO.getListClassByStudent(student.getStudent_id()));
        }
    }
}