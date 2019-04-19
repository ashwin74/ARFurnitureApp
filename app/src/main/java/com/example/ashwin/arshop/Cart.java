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

import com.squareup.picasso.Picasso;

public class Cart extends AppCompatActivity implements View.OnClickListener {

  TextView itemname,itemprice, itemquantity, categoryname;
  ImageView itemurl;
  Button b1;
  SharedPreferences sh;
  int pos;
  int lid;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cart);
    sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    b1 = (Button)findViewById(R.id.checkout_button);
    b1.setOnClickListener(this);

    itemname = (TextView)findViewById(R.id.item_name);
    itemprice = (TextView)findViewById(R.id.item_price);
    categoryname = (TextView)findViewById(R.id.item_category);
    itemurl=(ImageView)findViewById(R.id.item_image);

//    itemname.setText(Home.itemname.get(pos));
//    itemprice.setText(Home.itemprice.get(pos));
//    categoryname.setText(Home.categoryname.get(pos));
//    String img=Home.itemurl.get(pos);
//    String imgurl="http://"+sh.getString("ip","")+":8084/ARFurnitureWeb/Items/"+img;
//    Picasso.with(getApplicationContext()).load(imgurl).into(itemurl);

  }

  @Override
  public void onClick(View v) {
    if (v==b1) {
      Intent intent = new Intent(Cart.this, Order.class);
      startActivity(intent);
    }
  }
}