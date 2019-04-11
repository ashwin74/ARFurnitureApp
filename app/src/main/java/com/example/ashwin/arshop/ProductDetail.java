package com.example.ashwin.arshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.AbstractQueue;
import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {

  TextView itemid, itemname, itemprice, itemdescription, categoryid, categoryname, reviewid, userid, postdate, review, rating, firstname;
  ImageView itemurl;
  private android.content.Context Context;
  SharedPreferences sh;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    int pos = Integer.parseInt(getIntent().getStringExtra("pid"));
    sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    //GET ID
    itemname = (TextView)findViewById(R.id.item_name);
    itemprice = (TextView)findViewById(R.id.item_price);
    itemdescription = (TextView)findViewById(R.id.item_description);
    categoryname = (TextView)findViewById(R.id.item_category);
    itemurl=(ImageView)findViewById(R.id.item_image);

    postdate = (TextView)findViewById(R.id.post_date);
    review = (TextView)findViewById(R.id.review);
    rating = (TextView)findViewById(R.id.rating);
    firstname = (TextView)findViewById(R.id.first_name);

    //SET

    itemname.setText(Home.itemname.get(pos));
    itemprice.setText(Home.itemprice.get(pos));
    itemdescription.setText(Home.itemdescription.get(pos));
    categoryname.setText(Home.categoryname.get(pos));
    String img=Home.itemurl.get(pos);
    String imgurl="http://"+sh.getString("ip","")+":8084/ARFurnitureWeb/Items/"+img;
    Picasso.with(Context).load(imgurl).into(itemurl);

    postdate.setText(Home.postdate.get(pos));
    review.setText(Home.review.get(pos));
    rating.setText(Home.rating.get(pos));
    firstname.setText(Home.firstname.get(pos));

  }
}