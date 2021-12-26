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
    ArrayList<KidInfo> kids;

    public myAdapter(Context context, ArrayList<KidInfo> kids) {
        this.context = context;
        this.kids = kids;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        KidInfo k = kids.get(position);
        holder.name.setText(k.getName());

    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, inventory, attendance;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.kidd);
            inventory = itemView.findViewById(R.id.invent);
            attendance = itemView.findViewById(R.id.attend);

        }
    }

}
