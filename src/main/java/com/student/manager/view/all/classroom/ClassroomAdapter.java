package com.student.manager.view.all.classroom;

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
import com.student.manager.dao.CourseDAO;
import com.student.manager.model.Class;
import com.student.manager.util.DataUtil;

import java.util.ArrayList;

public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ViewHolder> {

    Context context;
    ArrayList<Class> data;
    ClickClassroom click;


    public ClassroomAdapter(Context context, ArrayList<Class> data, ClickClassroom clickCourse) {
        this.context = context;
        this.data = data;
        this.click = clickCourse;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Class> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassroomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_class_room, parent, false);
        ClassroomAdapter.ViewHolder viewHolder = new ClassroomAdapter.ViewHolder(view);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ClassroomAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Class aClass = data.get(position);

        holder.name.setText(aClass.getName());
        holder.txtSlotDay.setText(DataUtil.listSlotDay.get(aClass.getSlot_day_id()));
        holder.txtCourse.setText(
                CourseDAO.getCourseById(aClass.getCourse_id()).getName()
        );



        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.clickClassroom(aClass);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, txtCourse, txtSlotDay;
        AppCompatButton view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            txtCourse = itemView.findViewById(R.id.txtCourse);
            txtSlotDay = itemView.findViewById(R.id.txtSlotDay);
            view = itemView.findViewById(R.id.btnDetail);
        }
    }
}

