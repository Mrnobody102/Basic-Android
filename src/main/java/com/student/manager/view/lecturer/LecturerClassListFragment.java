package com.student.manager.view.lecturer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.student.manager.dao.LecturerClassSkillDAO;
import com.student.manager.databinding.FragmentStudentClassListBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Lecturer;
import com.student.manager.view.all.classroom.ClassDetailActivity;
import com.student.manager.view.all.classroom.ClassroomAdapter;
import com.student.manager.view.all.classroom.ClickClassroom;

import java.util.ArrayList;

public class LecturerClassListFragment extends Fragment {
    FragmentStudentClassListBinding binding;
    ClassroomAdapter adapter;

    Lecturer lecturer;

    public LecturerClassListFragment(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public LecturerClassListFragment() {
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
                intent.putExtra("lecturer_id", lecturer.getLecturer_id());
                startActivity(intent);
            }
        });
        ArrayList<Class> listLecturerClass = new ArrayList<>();
        if(lecturer!= null){
            listLecturerClass = LecturerClassSkillDAO.
                    getListClassByLecturer(lecturer.getLecturer_id());
        }
        adapter.setData(listLecturerClass);
        binding.rcyList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
