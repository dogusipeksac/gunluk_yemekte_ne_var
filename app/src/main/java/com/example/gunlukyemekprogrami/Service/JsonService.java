package com.example.gunlukyemekprogrami.Service;

import android.content.Context;

import com.example.gunlukyemekprogrami.Model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class JsonService {
    public  static  Context context;
    public static Map<Date, Map<String,ArrayList<Model>>> map;

    private static JsonService jsonService;

    /////////////////////////////////////////Singleton
    public static JsonService get(Context context){
        if (jsonService==null){
            jsonService=new JsonService(context);
        }
        return jsonService;
    }
    //////////////////////////////////////////////////

    private JsonService(Context context) {
        this.context = context;
        map =new TreeMap<>();
        getJsonFileFromLocally();
    }
    ///////////////////////////////////////////local jsonu yolunu getirme
    public static String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    //////////////////////////////////////////////////////////////////////

    public void getJsonFileFromLocally() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = obj.getJSONArray("value");
            for(int i=0;i<jsonArray.length();i++){

                ///////////////////////////////////////////get data string from json
                JSONObject object=jsonArray.getJSONObject(i).getJSONObject("fields");
                ////////////////////////////////////////////////////////////////////

                /////////////////////////////////////////////getDate i.için
                String dateString="";
                dateString=jsonArray
                        .getJSONObject(i).getJSONObject("fields")
                        .getString("ItemStartDate");
                Date start=getDate(dateString);
                ////////////////////////////////////////////////////////////////////


                /////////////////////////////getTitle
                String title="";
                title=object.getString("Title");
                ///////////////////////////////////////


                ////////////////////////////////////////////////////getFoodCategory
                String foodCategory="";
                foodCategory=object.getString("FoodCategory");
                ///////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////getCalorie
                String calorie="";
                calorie=object.getString("Calorie");
                ///////////////////////////////////////////////////////////////


                /////////////////////////////////////Nesneleri listeye ekledik
                Model item=new Model();
                item.setCalorie(calorie+"\t\tKl");
                item.setFoodCategory(foodCategory);
                item.setTitle(title);
                item.setItemStartDate(start.toString());
                //dosyadan çekilden verileri hashmap yapısına çevirmek
                createHashMap(item,start);
                ///////////////////////////////////////////////////////////////
            }

            // for
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createHashMap(Model item,Date start){
        //////////////////////////Hashmap Ekleme
        Date  key=start;
        if(map.get(key)==null){
            ArrayList<Model> temp=new ArrayList<>();
            temp.add(item);
            Map<String,ArrayList<Model>> arrayListMap=new HashMap<>();
            arrayListMap.put(item.getFoodCategory(),temp);
            map.put(key, arrayListMap);
        }
        else{
            Map<String ,ArrayList<Model>> categoryMap=map.get(key);
            if(categoryMap.get(item.getFoodCategory())==null){
                ArrayList<Model> temp=new ArrayList<>();
                temp.add(item);
                categoryMap.put(item.getFoodCategory(),temp);
            }
            else {
                ArrayList<Model> temp=categoryMap.get(item.getFoodCategory());
                temp.add(item);
                categoryMap.put(item.getFoodCategory(),temp);
            }
        }

    }
    ////////////////////////hash mapdan size getirme
    public int getSizeOfDate(){
        return map.size();
    }

    ////////////////////////hashmapdan tarihi getirme
    public Date getDateOnIndex(int index){

        int i=0;
        for(Map.Entry<Date,Map<String,ArrayList<Model>>> date : map.entrySet()){
            if(i == index){
                return date.getKey();

            }
            i++;
        }
        return null;
    }
    ////////////////////////7hashmapdan categorinin içindekilerin size getirme
    public Map<String,ArrayList<Model>> getCategoriesOnIndex(int index){
        int i=0;
        for(Map.Entry<Date,Map<String,ArrayList<Model>>> date : map.entrySet()){
            if(i == index){
                return date.getValue();
            }
            i++;
        }
        return null;
    }
    /////////////////////////sıralama için gerekli date tipine dönüştürme
    public Date getDate(String dateString){
        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = formatter.parse(dateString);
            return start;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
