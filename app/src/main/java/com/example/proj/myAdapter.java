package com.example.proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    Context context;
    ArrayList<Kid> list;

    public myAdapter(Context context, ArrayList<Kid> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Kid k = list.get(position);
        holder.name.setText(k.getName());
        holder.remark.setText(k.getRemark());
        if(k.isAttendance())
        {
            holder.attendance.setText("   v   ");
        }
        else
        {
            holder.attendance.setText("   x   ");
        }
        if(k.isFood())
        {
            holder.food.setText("   v   |   ");
        }
        else
        {
            holder.food.setText("   x   |   ");
        }
        if(k.isClothes())
        {
            holder.clothes.setText("   v   ");
        }
        else
        {
            holder.clothes.setText("   x   ");
        }
        if(k.isDiapers())
        {
            holder.diapers.setText("   v   |   ");
        }
        else
        {
            holder.diapers.setText("   x   |   ");
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, remark, attendance, diapers, food, clothes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.kid_name);
            remark = itemView.findViewById(R.id.kid_remark);
            attendance = itemView.findViewById(R.id.attendance);
            diapers = itemView.findViewById(R.id.diaper);
            food = itemView.findViewById(R.id.food);
            clothes = itemView.findViewById(R.id.clothes);

        }
    }

}
