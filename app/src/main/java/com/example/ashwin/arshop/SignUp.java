package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText e1,e2,e3,e4,e5;
    Button b1;
    String url="";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        e1=(EditText)findViewById(R.id.first_name);
        e2=(EditText)findViewById(R.id.last_name);
        e3=(EditText)findViewById(R.id.email);
        e4=(EditText)findViewById(R.id.password);
        e5=(EditText)findViewById(R.id.phone);
        b1=(Button)findViewById(R.id.register_button);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sp.getString("url","")+"SignUp";
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b1)
        {
            if (TextUtils.isEmpty(e1.getText())){
                e1.setError("First Name is required");
            }
            else if (TextUtils.isEmpty(e2.getText())){
                e2.setError("Last Name is required");
            } else if (TextUtils.isEmpty(e3.getText())){
                e3.setError("Email is required");
            } else if (TextUtils.isEmpty(e4.getText())){
                e4.setError("Password is required");
            } else if (TextUtils.isEmpty(e5.getText())){
                e5.setError("Phone Number is required");
            } else {
                String fname=e1.getText().toString();
                String lname=e2.getText().toString();
                String email=e3.getText().toString();
                String password=e4.getText().toString();
                String phone=e5.getText().toString();
                JSONObject json=new JSONObject();
                JSONParser jsonParser=new JSONParser();
                ArrayList<NameValuePair> para=new ArrayList<>();
                para.add(new BasicNameValuePair("fname",fname));
                para.add(new BasicNameValuePair("lname",lname));
                para.add(new BasicNameValuePair("email",email));
                para.add(new BasicNameValuePair("password",password));
                para.add(new BasicNameValuePair("phone",phone));
                json=jsonParser.makeHttpRequest(url,"GET",para);

                try {
                    String status = json.getString("status");
                    if(status.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignIn.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(this, "Registration Unsuccessfull", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(SignUp.this, SignIn.class);
        startActivity(a);
    }
}