package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddReviewRating extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    RatingBar r1;
    Button b1;
    String url="", lid;
    int id;
    SharedPreferences sp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_review_rating);

      e1=(EditText)findViewById(R.id.review);
      b1=(Button)findViewById(R.id.review_button);
      r1=(RatingBar)findViewById(R.id.ratingBar);
      sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
      url=sp.getString("url","")+"AddReview";
      lid=sp.getString("lid","");
      id = Integer.parseInt(getIntent().getStringExtra("id"));

      b1.setOnClickListener(this);
  }

    @Override
    public void onClick(View v) {
        if(v==b1)
        {
            String review = e1.getText().toString();
            float rating = r1.getRating();
            JSONObject json=new JSONObject();
            JSONParser jsonParser=new JSONParser();
            ArrayList<NameValuePair> para=new ArrayList<>();
            para.add(new BasicNameValuePair("lid",lid));
            para.add(new BasicNameValuePair("id",id+""));
            para.add(new BasicNameValuePair("review",review+""));
            para.add(new BasicNameValuePair("rating", rating+""));
            json=jsonParser.makeHttpRequest(url,"GET",para);

            try {
                String status = json.getString("status");
                if(status.equalsIgnoreCase("1"))
                {
                    Toast.makeText(this, "Review Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViewReviewRating.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}