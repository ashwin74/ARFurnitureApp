package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class SetIp extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    Button b1;
    String url="";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ip);
        e1=(EditText)findViewById(R.id.set_ip);
        b1=(Button)findViewById(R.id.set_ip_button);
        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1.setText(sp.getString("ip", ""));
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b1)
        {
            String ip=e1.getText().toString();
            url="http://"+ip+":8084/ARFurnitureWeb/";
            SharedPreferences.Editor ed=sp.edit();
            ed.putString("ip",ip);
            ed.putString("url",url);
            ed.commit();
            Intent intent = new Intent(getApplicationContext(), SignIn.class);
            startActivity(intent);


        }
    }
}
