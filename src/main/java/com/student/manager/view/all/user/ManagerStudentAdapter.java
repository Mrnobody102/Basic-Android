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
import androidx.recyclerview.widget.RecyclerView;

import com.student.manager.R;
import com.student.manager.model.Student;

import java.util.ArrayList;

public class ManagerStudentAdapter extends RecyclerView.Adapter<ManagerStudentAdapter.ViewHolder> {

    Context context;
    ArrayList<Student> data;
    ClickStudent clickStaff;


    public ManagerStudentAdapter(Context context, ArrayList<Student> data, ClickStudent clickStaff) {
        this.context = context;
        this.data = data;
        this.clickStaff = clickStaff;
    }

    public ManagerStudentAdapter(Context context, ArrayList<Student> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Student> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ManagerStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_manager_staff, parent, false);
        ManagerStudentAdapter.ViewHolder viewHolder = new ManagerStudentAdapter.ViewHolder(view);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ManagerStudentAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student staff = data.get(position);

        holder.stt.setText((position + 1)+"");
        holder.name.setText(staff.getName());
        holder.id.setText(staff.getStudent_id()+"");
        holder.date.setText(staff.getDate_of_birth());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickStaff!=null){
                    clickStaff.clickStudent(staff);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt, name, id, date, view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.txtSTT);
            name = itemView.findViewById(R.id.txtName);
            id = itemView.findViewById(R.id.txtID);
            date = itemView.findViewById(R.id.txtBirthDay);
            view = itemView.findViewById(R.id.txtViewMore);
        }

    }
}

