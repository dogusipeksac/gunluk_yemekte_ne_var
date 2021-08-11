package com.example.gunlukyemekprogrami.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gunlukyemekprogrami.Model.Model;
import com.example.gunlukyemekprogrami.R;

import java.util.ArrayList;
import java.util.Map;


public class MyAdapterCategory extends RecyclerView.Adapter<MyAdapterCategory.ViewHolder> {


    private Context context;
    private Map<String,ArrayList<Model>> categotryMap;
    private RecyclerView.Adapter adapter;


    public MyAdapterCategory(Context context, Map<String,ArrayList<Model>> categotryMap) {

        this.context = context;
        this.categotryMap = categotryMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int i= 0;
        System.out.println("-------------> "+position);
        for(Map.Entry<String,ArrayList<Model>> category : categotryMap.entrySet()){
            if(i==position){
                adapter = new MyAdapterFood(context,category.getValue());
                holder.foodCategoryText.setText(category.getKey());
                holder.foodRec.setAdapter(adapter);
                holder.foodRec.setLayoutManager(new LinearLayoutManager(context));
            }
            i++;
        }
    }

    @Override
    public int getItemCount() {
        System.out.println("------------>size"+categotryMap.size());
        return categotryMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodCategoryText;
        RecyclerView foodRec;


        public ViewHolder(View itemView) {
            super(itemView);
            foodRec=itemView.findViewById(R.id.foodRecycle);
            foodCategoryText=itemView.findViewById(R.id.foodCategory);
        }
    }
}