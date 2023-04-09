package com.student.manager.view.lecturer;

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
import com.student.manager.model.Lecturer;
import com.student.manager.view.all.user.ClickLecturer;

import java.util.ArrayList;

public class LecturerAdapter extends RecyclerView.Adapter<LecturerAdapter.ViewHolder> {
    Context context;
    ArrayList<Lecturer> data;
    ClickLecturer clickStaff;


    public LecturerAdapter(Context context, ArrayList<Lecturer> data, ClickLecturer clickStaff) {
        this.context = context;
        this.data = data;
        this.clickStaff = clickStaff;
    }

    public LecturerAdapter(Context context, ArrayList<Lecturer> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Lecturer> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LecturerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_lecturer_view, parent, false);
        LecturerAdapter.ViewHolder viewHolder = new LecturerAdapter.ViewHolder(view);
        return viewHolder;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LecturerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Lecturer staff = data.get(position);

        holder.stt.setText((position + 1)+"");
        holder.name.setText(staff.getName());
        holder.birth.setText(staff.getDate_of_birth()+"");
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(position);
                setData(data);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt, name, birth , remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.txtSTT);
            name = itemView.findViewById(R.id.txtName);
            birth = itemView.findViewById(R.id.txtDateOfBirth);
            remove = itemView.findViewById(R.id.txtRemove);
        }

    }
}
