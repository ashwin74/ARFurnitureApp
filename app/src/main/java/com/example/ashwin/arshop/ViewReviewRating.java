package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewReviewRating extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2;
    ListView l1;
    int pos;
    String id;
    SharedPreferences sp;
    String url;
    ArrayList<String> firstname,postdate,review,rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review_rating);

        pos = Integer.parseInt(getIntent().getStringExtra("pos"));
        l1=(ListView)findViewById(R.id.review_rate);
        b1=(Button)findViewById(R.id.add_review_button);
        b1.setOnClickListener(this);

        try{
            JSONObject json=new JSONObject();
            JSONParser jsonParser = new JSONParser();
            ArrayList<NameValuePair> para=new ArrayList<>();
            para.add(new BasicNameValuePair("id",Home.itemid.get(pos)));
            sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            url = sp.getString("url", "") + "ViewReview";
            json=jsonParser.makeHttpRequest(url,"GET",para);

            String status=json.getString("status");

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
                l1.setAdapter(new Custom_driver_review(getApplicationContext(),firstname,postdate,review,rating));
            } else {
                Toast.makeText(this, "No Reviews Found", Toast.LENGTH_SHORT).show();
            }
        }  catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
        id = Home.itemid.get(pos);
    }

    @Override
    public void onClick(View v) {
        if(v==b1){
            Intent intent = new Intent(ViewReviewRating.this, AddReviewRating.class);
            intent.putExtra("id",id);
            intent.putExtra("pos",pos+"");
            startActivity(intent);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewReviewRating.this, ProductDetail.class);
        intent.putExtra("pid",pos+"");
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
