package com.example.gunlukyemekprogrami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.gunlukyemekprogrami.Adapter.MyAdapterCategory;
import com.example.gunlukyemekprogrami.Paginator.Paginator;
import com.example.gunlukyemekprogrami.Service.JsonService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Paginator paginator;
    private int totalPages;
    private int currentPage;
    private Button nextButton,prevButton;
    private JsonService service;
    TextView titleTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //arayüz elemanlarının tanımlamaları
        recyclerView=findViewById(R.id.mainRecycleView);
        titleTime=findViewById(R.id.timeTextId);
        nextButton=findViewById(R.id.nextButton);
        prevButton=findViewById(R.id.prevButton);

        service=JsonService.get(this);
        paginator=Paginator.get(this);
        currentPage=0;
        prevButton.setEnabled(false);
        //toplam listenin eleman sayısı
        totalPages=paginator.getTotal_page() / paginator.getItems_per_page()-1;

        //reclerview adaptore baglama
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MyAdapterCategory(this,
                paginator.generatePage(currentPage));
        recyclerView.setAdapter(adapter);

        //şu anki sayfayı yazdırma
        titleTime.setText(""+converterDate());

        //butonlarla ile sayfa değişimi
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage+=1;
                recyclerView.setAdapter(new MyAdapterCategory(getApplicationContext(),
                        paginator.generatePage(currentPage)));
                titleTime.setText(""+converterDate());
                toggleButtons();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage-=1;
                recyclerView.setAdapter(new MyAdapterCategory(getApplicationContext(),
                        paginator.generatePage(currentPage)));
                titleTime.setText(""+converterDate());
                toggleButtons();
            }
        });

    }
    //ileri geri durumuna göre görünürlüğünü ayarlama
    private void toggleButtons() {
        if(currentPage==totalPages){
            nextButton.setEnabled(false);
            prevButton.setEnabled(true);
        }
        else if(currentPage==0){
            prevButton.setEnabled(false);
            nextButton.setEnabled(true);
        }else if(currentPage>=1 && currentPage<=totalPages){
            nextButton.setEnabled(true);
            prevButton.setEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    public String converterDate(){
        String string = paginator.getPageTitle(currentPage).toString();
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            Date date = format.parse(string);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
             formatter.format(date);
            return formatter.format(date);
        }
        catch(ParseException pe) {

            return null;
        }
    }

}