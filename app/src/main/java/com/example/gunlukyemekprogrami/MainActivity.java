package com.example.gunlukyemekprogrami;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.gunlukyemekprogrami.Adapter.MyAdapterCategory;

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
        nextButton=findViewById(R.id.nextImage);
        prevButton=findViewById(R.id.prevImage);

        service=new JsonService(this);
        paginator=new Paginator(this);
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
        titleTime.setText(paginator.getPageTitle(currentPage));


        //butonlarla ile sayfa değişimi
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage+=1;
                recyclerView.setAdapter(new MyAdapterCategory(getApplicationContext(),
                        paginator.generatePage(currentPage)));
                titleTime.setText(paginator.getPageTitle(currentPage));
                toggleButtons();
            }
        });
        //butonlarla ile sayfa değişimi
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage-=1;
                recyclerView.setAdapter(new MyAdapterCategory(getApplicationContext(),
                        paginator.generatePage(currentPage)));
                titleTime.setText(paginator.getPageTitle(currentPage));
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

}