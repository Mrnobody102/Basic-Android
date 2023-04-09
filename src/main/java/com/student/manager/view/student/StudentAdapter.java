package com.student.manager.view.student;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.student.manager.R;
import com.student.manager.model.Student;
import com.student.manager.view.all.user.ClickStudent;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    Context context;
    ArrayList<Student> data;
    ClickStudent clickStudent;


    public StudentAdapter(Context context, ArrayList<Student> data, ClickStudent clickStudent) {
        this.context = context;
        this.data = data;
        this.clickStudent = clickStudent;
    }

    public StudentAdapter(Context context, ArrayList<Student> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
    }

    public void setClickStudent(ClickStudent clickStudent) {
        this.clickStudent = clickStudent;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Student> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_student_view, parent, false);
        StudentAdapter.ViewHolder viewHolder = new StudentAdapter.ViewHolder(view);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student student = data.get(position);

        holder.stt.setText((position + 1) + "");
        holder.name.setText(student.getName());
        holder.birth.setText(student.getDate_of_birth() + "");
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickStudent != null) {
                    clickStudent.clickStudent(student);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt, name, birth, remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.txtSTT);
            name = itemView.findViewById(R.id.txtName);
            birth = itemView.findViewById(R.id.txtDateOfBirth);
            remove = itemView.findViewById(R.id.txtRemove);
        }

    }
}
