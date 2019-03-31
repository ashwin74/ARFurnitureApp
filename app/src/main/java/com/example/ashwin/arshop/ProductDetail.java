package com.example.ashwin.arshop;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

  ArrayList<String> itemid, itemname, categoryid, categoryname, itemprice, itemdescription, itemurl, reviewid, userid, firstname, postdate, review, rating;
  ListView item;
  String url;
  SharedPreferences sp;
  String pid="";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    pid=getIntent().getStringExtra("pid");

  }


  public void productDetail(){
    JSONObject json=new JSONObject();
    JSONParser jsonParser = new JSONParser();
    ArrayList<NameValuePair> para=new ArrayList<>();
    sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    url = sp.getString("url", "") + "ProductDetail";
    json=jsonParser.makeHttpRequest(url,"GET",para);
    try {
      String status=json.getString("status");
      Toast.makeText(this, json.toString(), Toast.LENGTH_SHORT).show();
      if(status.equalsIgnoreCase("1")){
        JSONArray ja=json.getJSONArray("data");
        reviewid=new ArrayList<>();
        userid=new ArrayList<>();
        review=new ArrayList<>();
        postdate=new ArrayList<>();
        rating=new ArrayList<>();
        firstname=new ArrayList<>();
        itemid=new ArrayList<>();

      } else {
        Toast.makeText(this, "No products details...!!", Toast.LENGTH_SHORT).show();
      }
    }catch (Exception e){
      Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }
}