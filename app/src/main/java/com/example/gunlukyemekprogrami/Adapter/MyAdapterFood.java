package com.example.gunlukyemekprogrami.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gunlukyemekprogrami.Model;
import com.example.gunlukyemekprogrami.R;

import java.util.ArrayList;


public class MyAdapterFood extends RecyclerView.Adapter<MyAdapterFood.ViewHolder> {
    ////////////////////////////////////////Main adapter
    private Context context;
    private ArrayList<Model> modelItems;


    public MyAdapterFood(Context context, ArrayList<Model> modelItems) {

        this.context = context;
        this.modelItems = modelItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleText.setText(modelItems.get(position).getTitle());
        holder.caloryText.setText(modelItems.get(position).getCalorie());
        System.out.println("-------------> "+position);
    }

    @Override
    public int getItemCount() {
        return modelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView caloryText;


        public ViewHolder(View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleTxt);
            caloryText=itemView.findViewById(R.id.caloryTxt);
        }
    }
}