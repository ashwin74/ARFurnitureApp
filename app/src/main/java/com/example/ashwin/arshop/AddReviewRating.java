package com.example.ashwin.arshop;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddReviewRating extends AppCompatActivity implements View.OnClickListener {

  RadioButton r1,r2,r3,r4,r5;
  EditText e1;
  Button b1;
  String url;
  SharedPreferences sp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_review_rating);

    e1=(EditText)findViewById(R.id.review);
    b1=(Button)findViewById(R.id.register_button);
    sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    url = sp.getString("url", "") + "AddReviewRating";
    b1.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {

  }
}