package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity implements View.OnClickListener {

  TextView itemname, itemprice, itemdescription, categoryname;
  ImageView itemurl;
  Button b1, b2;
  SharedPreferences sh;
  int pos;
  String id, lid;
  SharedPreferences sp;
  String url;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    pos = Integer.parseInt(getIntent().getStringExtra("pid"));
    sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    b1=(Button)findViewById(R.id.add_review_button);
    b2=(Button)findViewById(R.id.buy_item);
    b1.setOnClickListener(this);
    b2.setOnClickListener(this);

    //GET ID
    itemname = (TextView)findViewById(R.id.item_name);
    itemprice = (TextView)findViewById(R.id.item_price);
    itemdescription = (TextView)findViewById(R.id.item_description);
    categoryname = (TextView)findViewById(R.id.item_category);
    itemurl=(ImageView)findViewById(R.id.item_image);


    //SET

    itemname.setText(Home.itemname.get(pos));
    itemprice.setText(Home.itemprice.get(pos));
    itemdescription.setText(Home.itemdescription.get(pos));
    categoryname.setText(Home.categoryname.get(pos));
    String img=Home.itemurl.get(pos);
    String imgurl="http://"+sh.getString("ip","")+":8084/ARFurnitureWeb/Items/"+img;
    Picasso.with(getApplicationContext()).load(imgurl).into(itemurl);



    id = Home.itemid.get(pos);
  }

    @Override
    public void onClick(View v) {
        if (v==b1) {
          Intent intent = new Intent(ProductDetail.this, ViewReviewRating.class);
          intent.putExtra("id",id);
          intent.putExtra("lid",lid);
          startActivity(intent);
        }
        if (v==b2) {
          Intent intent = new Intent(ProductDetail.this, Cart.class);
          intent.putExtra("id",id);
          intent.putExtra("lid", lid);
          startActivity(intent);
        }
    }
}