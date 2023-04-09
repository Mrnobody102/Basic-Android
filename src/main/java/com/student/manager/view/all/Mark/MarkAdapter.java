package com.student.manager.view.all.Mark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.student.manager.R;
import com.student.manager.model.MarkItem;


import java.util.ArrayList;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.ViewHolder> {
    Context context;
    ArrayList<MarkItem> data;
    ClickMark clickMark;

    public MarkAdapter(Context context, ArrayList<MarkItem> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
    }

    public void setClickMark(ClickMark clickMark) {
        this.clickMark = clickMark;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<MarkItem> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_mark, parent, false);
        MarkAdapter.ViewHolder viewHolder = new MarkAdapter.ViewHolder(view);
        return viewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MarkAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MarkItem markItem = data.get(position);

        holder.stt.setText(markItem.getStt()+"");
        holder.txtName.setText(markItem.getStudentName());
        holder.edtMark.setText(markItem.getPoint()+"");
        holder.edtMark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(clickMark!=null){
//                    clickMark.clickMark(position, holder.edtMark.getText().toString());
                    clickMark.clickMark(position, charSequence.toString());

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stt, txtName;
        EditText edtMark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.txtSTT);
            txtName = itemView.findViewById(R.id.txtName);
            edtMark = itemView.findViewById(R.id.edtMark);

        }

    }
}
