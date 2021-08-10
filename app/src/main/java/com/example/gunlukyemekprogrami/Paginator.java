package com.example.gunlukyemekprogrami;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Paginator {
    private int total_page;
    private int items_per_page;
    private   int items_remaning;
    private   int last_page;
    private JsonService service;
    private Context context;

    public Paginator(Context context) {
        this.context = context;
        service=new JsonService(context);
        start(service.getSizeOfDate());

    }
    public void start(int total_pages){
        total_page=total_pages;
        items_per_page=1;

    }

    public String getPageTitle(int currentPage){
        return service.getDateOnIndex(currentPage);
    }

    public Map<String,ArrayList<Model>> generatePage(int currentPage){

        return service.getCategoriesOnIndex(currentPage);
    }

    public int getTotal_page() {
        return total_page;
    }

    public int getItems_per_page() {
        return items_per_page;
    }


}
