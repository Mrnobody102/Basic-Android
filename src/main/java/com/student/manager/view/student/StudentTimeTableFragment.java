package com.student.manager.view.student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.student.manager.R;
import com.student.manager.dao.StudentClassDAO;
import com.student.manager.dao.StudentCourseSlotDayDAO;
import com.student.manager.databinding.FragmentStudentProfileBinding;
import com.student.manager.databinding.FragmentStudentTimeTableBinding;
import com.student.manager.model.Class;
import com.student.manager.model.Course;
import com.student.manager.model.CourseSlotDay;
import com.student.manager.model.Student;
import com.student.manager.model.StudentClass;
import com.student.manager.model.StudentCourseSlotDay;

import java.util.ArrayList;

public class StudentTimeTableFragment extends Fragment {

    FragmentStudentTimeTableBinding binding;

    Student student;

    public StudentTimeTableFragment(Student student) {
        this.student = student;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStudentTimeTableBinding.inflate(inflater, container, false);
        ArrayList<Class> classes = StudentClassDAO.getListClassByStudent(student.getStudent_id());
        for(Class c:classes) {
            switch (c.getSlot_day_id()) {
                case 1:
                    binding.M2.setText(c.getName());
                    binding.M3.setText(c.getName());
                    binding.M5.setText(c.getName());
                    binding.M6.setText(c.getName());
                    break;
                case 2:
                    binding.E2.setText(c.getName());
                    binding.E3.setText(c.getName());
                    binding.E5.setText(c.getName());
                    binding.E6.setText(c.getName());
                    break;
                case 3:
                    binding.A2.setText(c.getName());
                    binding.A3.setText(c.getName());
                    binding.A5.setText(c.getName());
                    binding.M4.setText(c.getName());
                    break;
                case 4:
                    binding.M7.setText(c.getName());
                    binding.M8.setText(c.getName());
                    binding.E7.setText(c.getName());
                    binding.E8.setText(c.getName());
                    break;
            }
        }
        return binding.getRoot();
    }
}