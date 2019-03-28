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

public class EditUserAccount extends AppCompatActivity implements View.OnClickListener {
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1;
    String url="";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_account);

        e1=(EditText)findViewById(R.id.first_name);
        e2=(EditText)findViewById(R.id.last_name);
        e3=(EditText)findViewById(R.id.phone);
        e4=(EditText)findViewById(R.id.house_name);
        e5=(EditText)findViewById(R.id.city);
        e6=(EditText)findViewById(R.id.state);
        e7=(EditText)findViewById(R.id.zip);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sp.getString("url","")+"EditUserAccount";
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b1)
        {
            String fname=e1.getText().toString();
            String lname=e2.getText().toString();
            String phone=e3.getText().toString();
            String house=e4.getText().toString();
            String city=e5.getText().toString();
            String state=e6.getText().toString();
            String zip=e7.getText().toString();
            JSONObject json=new JSONObject();
            JSONParser jsonParser=new JSONParser();
            ArrayList<NameValuePair> para=new ArrayList<>();
            para.add(new BasicNameValuePair("fname",fname));
            para.add(new BasicNameValuePair("lname",lname));
            para.add(new BasicNameValuePair("phone",phone));
            para.add(new BasicNameValuePair("house",house));
            para.add(new BasicNameValuePair("city",city));
            para.add(new BasicNameValuePair("house",state));
            para.add(new BasicNameValuePair("zip",zip));
            json=jsonParser.makeHttpRequest(url,"GET",para);

            try {
                String status = json.getString("status");
                if(status.equalsIgnoreCase("1"))
                {
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserAccount.class);
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
