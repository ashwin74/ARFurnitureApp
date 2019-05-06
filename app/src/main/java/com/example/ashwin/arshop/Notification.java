package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

  TextView t1, t2;
  Button b1;
  String url="",lid;
  SharedPreferences sp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_notification);

    t1 = (TextView)findViewById(R.id.date);
    t2 = (TextView)findViewById(R.id.notification);

    sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    url=sp.getString("url","")+"Notification";
    lid=sp.getString("lid","");

    try {
      JSONObject js=new JSONObject();
      JSONParser jsonParser=new JSONParser();
      ArrayList<NameValuePair> param=new ArrayList<>();
      param.add(new BasicNameValuePair("lid",lid));
      js=jsonParser.makeHttpRequest(url,"POST",param);
      String res=js.getString("Status");
      if(res.equalsIgnoreCase("1"))
      {
        t1.setText(js.getString("postdate"));
        t2.setText(js.getString("notification"));
      }
      else
      {
        Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
      }
    }catch (Exception e)
    {
      Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }
  @Override
  public void onBackPressed() {
    Intent a = new Intent(Notification.this, Home.class);
    startActivity(a);
  }
}