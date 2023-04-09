package com.student.manager.view.all.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.student.manager.R;
import com.student.manager.model.Course;
import com.student.manager.util.DataUtil;
import com.student.manager.view.all.course.ClickCourse;

import java.util.ArrayList;

public class StudentCourseAdapter extends RecyclerView.Adapter<StudentCourseAdapter.ViewHolder> {

    Context context;
    ArrayList<Course> data;
    ClickCourse click;


    public StudentCourseAdapter(Context context, ArrayList<Course> data, ClickCourse clickCourse) {
        this.context = context;
        this.data = data;
        this.click = clickCourse;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Course> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentCourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_student_course, parent, false);
        StudentCourseAdapter.ViewHolder viewHolder = new StudentCourseAdapter.ViewHolder(view);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentCourseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Course course = data.get(position);

        holder.name.setText(course.getName());
        holder.txtTypeName.setText(DataUtil.listCourseType.get(course.getCourse_type_id()).getName());
        holder.txtLevel.setText(DataUtil.listLevel.get(course.getStart_level()).getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.clickCourse(course);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, txtTypeName, txtLevel;
        AppCompatButton view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            txtTypeName = itemView.findViewById(R.id.txtTypeName);
            txtLevel = itemView.findViewById(R.id.txtLevel);
            view = itemView.findViewById(R.id.btnDetail);
        }

    }
}
