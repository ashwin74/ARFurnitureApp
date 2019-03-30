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
    Button b1, b2;
    String url="",lid,url1="";
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
        b1=(Button)findViewById(R.id.edit);
        b2=(Button)findViewById(R.id.save);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sp.getString("url","")+"EditUserAccount";
        url1=sp.getString("url","")+"View_user";
        lid=sp.getString("lid","");
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        e6.setEnabled(false);
        e7.setEnabled(false);
        try {
            JSONObject js=new JSONObject();
            JSONParser jsonParser=new JSONParser();
            ArrayList<NameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("lid",lid));
            js=jsonParser.makeHttpRequest(url1,"GET",param);
            String res=js.getString("Status");
            if(res.equalsIgnoreCase("1"))
            {
                e1.setText(js.getString("first_name"));
                e2.setText(js.getString("last_name"));
                e3.setText(js.getString("phone"));
                e4.setText(js.getString("house_name"));
                e5.setText(js.getString("city"));
                e6.setText(js.getString("state"));
                e7.setText(js.getString("zip"));
            }
            else
            {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
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
                    Intent intent = new Intent(getApplicationContext(), EditUserAccount.class);
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


        if(v==b2)
        {
            e1.setEnabled(true);
            e2.setEnabled(true);
            e3.setEnabled(true);
            e4.setEnabled(true);
            e5.setEnabled(true);
            e6.setEnabled(true);
            e7.setEnabled(true);
        }
    }
}
