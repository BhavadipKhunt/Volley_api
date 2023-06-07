package com.example.volley_api;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle_adapter extends RecyclerView.Adapter<recycle_adapter.Userholder> {
    MainActivity mainActivity;
     ArrayList<model_class> datalist;
    public recycle_adapter(MainActivity mainActivity, ArrayList<model_class> datalist) {
        this.mainActivity=mainActivity;
        this.datalist=datalist;
    }

    @NonNull
    @Override
    public recycle_adapter.Userholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.recycle_iteam,parent,false);
        Userholder userholder=new Userholder(view);
        return userholder;
    }

    @Override
    public void onBindViewHolder(@NonNull recycle_adapter.Userholder holder, int position) {
        holder.namet.setText("Name: "+datalist.get(position).getName());
        holder.emailt.setText("email: "+datalist.get(position).getEmail());
        holder.bodyt.setText("Body: "+datalist.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class Userholder extends RecyclerView.ViewHolder {
        TextView namet,emailt,bodyt;
        public Userholder(@NonNull View itemView) {
            super(itemView);
            namet=itemView.findViewById(R.id.name);
            emailt=itemView.findViewById(R.id.emial);
            bodyt=itemView.findViewById(R.id.body);
        }
    }
}
