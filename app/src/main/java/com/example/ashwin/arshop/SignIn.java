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

public class SignIn extends AppCompatActivity implements View.OnClickListener {
   EditText e1,e2;
   Button b1, b2;
   String url="";
   SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.password);
        b1=(Button)findViewById(R.id.sign_in);
        b2=(Button)findViewById(R.id.sign_up);
        sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sp.getString("url","")+"Login";
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b1)
        {
            String uname=e1.getText().toString();
            String pwd=e2.getText().toString();
            JSONObject json=new JSONObject();
            JSONParser jsonParser=new JSONParser();
            ArrayList<NameValuePair> para=new ArrayList<>();
            para.add(new BasicNameValuePair("username",uname));
            para.add(new BasicNameValuePair("password",pwd));
            json=jsonParser.makeHttpRequest(url,"GET",para);

            if (TextUtils.isEmpty(e1.getText())){
                e1.setError("Email is required");
            }
            else if (TextUtils.isEmpty(e2.getText())){
                e2.setError("You Forget your Password");
            }
            else {
                try {
                    String status = json.getString("status");
                    if(status.equalsIgnoreCase("1"))
                    {
                        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        String lid=json.getString("lid");
                        SharedPreferences.Editor ed=sp.edit();
                        ed.putString("lid",lid);
                        ed.putString("uname",uname);
                        ed.commit();

                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(this, "Invalid User ID", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (v==b2)
        {

            Intent intent = new Intent(SignIn.this, SignUp.class);
            startActivity(intent);

        }
    }

        @Override
        public void onBackPressed() {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
}
