package com.example.proj;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
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
        if (k.isAttendance())
        {
            holder.attendance.setText("נוכח");
            holder.attendance.setBackgroundColor(Color.BLUE);
        }
        else
        {
            holder.attendance.setText("לא-נוכח");
            holder.attendance.setBackgroundColor(Color.RED);
        }
        Inventory i = k.getInventory();

        int counter = 0;

        holder.s0.setText("");
        holder.s0.setBackgroundColor(Color.BLUE);
        holder.s1.setText("");
        holder.s1.setBackgroundColor(Color.BLUE);
        holder.s2.setText("");
        holder.s2.setBackgroundColor(Color.BLUE);
        holder.s3.setText("");
        holder.s3.setBackgroundColor(Color.BLUE);

        if(!i.isClothes())
        {
            holder.s0.setText("בגדים");
            holder.s0.setBackgroundColor(Color.RED);
            counter++;

        }
        if(!i.isDiapers())
        {
            switch(counter)
            {
                case 0:
                    holder.s0.setText("טיטולים");
                    holder.s0.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    holder.s1.setText("טיטולים");
                    holder.s1.setBackgroundColor(Color.RED);
                    break;

            }
            counter++;
        }
        if(!i.isFood())
        {
            switch(counter)
            {
                case 0:
                    holder.s0.setText("אוכל");
                    holder.s0.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    holder.s1.setText("אוכל");
                    holder.s1.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    holder.s2.setText("אוכל");
                    holder.s2.setBackgroundColor(Color.RED);
                    break;
            }
            counter++;

        }
        if(!i.isWipes())
        {
            switch(counter)
            {
                case 0:
                    holder.s0.setText("מגבונים");
                    holder.s0.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    holder.s1.setText("מגבונים");
                    holder.s1.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    holder.s2.setText("מגבונים");
                    holder.s2.setBackgroundColor(Color.RED);
                case 3:
                    holder.s3.setText("מגבונים");
                    holder.s3.setBackgroundColor(Color.RED);
            }
        }
    }

    @Override
    public int getItemCount() {
        return kids.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, attendance, s0, s1, s2, s3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView5);
            attendance = itemView.findViewById(R.id.button6);
            s0 = itemView.findViewById(R.id.button20);
            s1 = itemView.findViewById(R.id.button21);
            s2 = itemView.findViewById(R.id.button22);
            s3 = itemView.findViewById(R.id.button23);


        }
    }

}
