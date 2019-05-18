package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderStatus extends AppCompatActivity {

    ListView l1;
    TextView t1;
    String id;
    SharedPreferences sp;
    String url;
    ArrayList<String> itemname, itemquantity, itemprice,orderstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        t1=(TextView)findViewById(R.id.total_price);
        l1=(ListView)findViewById(R.id.pending);
        try{
            JSONObject json=new JSONObject();
            JSONParser jsonParser = new JSONParser();
            ArrayList<NameValuePair> para=new ArrayList<>();
            sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            url = sp.getString("url", "") + "OrderStatus";
            para.add(new BasicNameValuePair("lid",sp.getString("lid","")));
            json=jsonParser.makeHttpRequest(url,"GET",para);

            Log.d("======",json.toString());
            String status=json.getString("status");

            if(status.equalsIgnoreCase("1")) {
                JSONArray ja = json.getJSONArray("data");
                itemname = new ArrayList<>();
                itemquantity = new ArrayList<>();
                itemprice = new ArrayList<>();
                orderstatus = new ArrayList<>();
                int total=0;
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = ja.getJSONObject(i);
                    itemname.add(jo.getString("itemname"));
                    itemquantity.add(jo.getString("itemquantity"));
                    itemprice.add(jo.getString("itemprice"));
                    orderstatus.add(jo.getString("orderstatus"));
                    total=total+((Integer.parseInt(jo.getString("itemquantity"))*(Integer.parseInt(jo.getString("itemprice")))));
                }
                t1.setText(total+"");
                l1.setAdapter(new Custom_order(getApplicationContext(),itemname,itemquantity,itemprice, orderstatus));
            } else {
                Toast.makeText(this, "No Orders Found", Toast.LENGTH_SHORT).show();
            }
        }  catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(OrderStatus.this, Home.class);
        startActivity(a);
    }
}