package com.example.gunlukyemekprogrami.Paginator;

import android.content.Context;

import com.example.gunlukyemekprogrami.Model.Model;
import com.example.gunlukyemekprogrami.Service.JsonService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Paginator {
    private int total_page;
    private int items_per_page;
    private   int items_remaning;
    private   int last_page;
    private JsonService service;
    private Context context;

    private static Paginator paginator;

    /////////////////////////////////////////Singleton
    public static Paginator get(Context context){
        if (paginator==null){
            paginator=new Paginator(context);
        }
        return paginator;
    }

    private Paginator(Context context) {
        this.context = context;
        service=JsonService.get(context);
        start(service.getSizeOfDate());

    }
    public void start(int total_pages){
        total_page=total_pages;
        items_per_page=1;

    }

    public Date getPageTitle(int currentPage){

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
