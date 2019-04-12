package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
  ListView l1;
  Button b1;
  SharedPreferences sh;
  int pos;
  String id;
  SharedPreferences sp;
  String url;
  ArrayList<String> firstname,postdate,review,rating;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    pos = Integer.parseInt(getIntent().getStringExtra("pid"));
    sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    b1=(Button)findViewById(R.id.add_review_button);
    l1=(ListView)findViewById(R.id.review_rate);
    b1.setOnClickListener(this);

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

    try{
        JSONObject json=new JSONObject();
        JSONParser jsonParser = new JSONParser();
        ArrayList<NameValuePair> para=new ArrayList<>();
        para.add(new BasicNameValuePair("id",Home.itemid.get(pos)));
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = sp.getString("url", "") + "ProductDetail";
        json=jsonParser.makeHttpRequest(url,"GET",para);


        String status=json.getString("status");
       Toast.makeText(getApplicationContext(), para+"", Toast.LENGTH_SHORT).show();

        if(status.equalsIgnoreCase("1")) {
            JSONArray ja = json.getJSONArray("data");
            firstname = new ArrayList<>();
            postdate = new ArrayList<>();
            review = new ArrayList<>();
            rating = new ArrayList<>();

            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                firstname.add(jo.getString("firstname"));
                postdate.add(jo.getString("postdate"));
                review.add(jo.getString("review"));
                rating.add(jo.getString("rating"));
            }
            ArrayAdapter<String> ad=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,firstname);
            l1.setAdapter(ad);
        }
        }   catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
  }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ProductDetail.this, AddReviewRating.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}