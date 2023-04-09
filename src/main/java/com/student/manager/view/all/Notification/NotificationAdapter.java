package com.student.manager.view.all.Notification;

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
import com.student.manager.model.Notification;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    Context context;
    ArrayList<Notification> data;
    ClickNotification click;

    public NotificationAdapter(Context context, ArrayList<Notification> data, ClickNotification click) {
        this.context = context;
        this.data = data;
        this.click = click;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Notification> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_notification, parent, false);
        NotificationViewHolder viewHolder = new NotificationViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = data.get(position);

        holder.txtNotificationTitle.setText(notification.getTitle());
        holder.txtDate.setText(notification.getDate()+"");
        holder.txtContent.setText(notification.getContent());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click!=null){
                    click.clickNotification(notification);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        TextView txtNotificationTitle, txtDate, txtContent;
        AppCompatButton view;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNotificationTitle = itemView.findViewById(R.id.txtNotificationTitle);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtContent = itemView.findViewById(R.id.txtContent);
            view = itemView.findViewById(R.id.btnDetail);
        }
    }
}
