package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setip extends AppCompatActivity {

    EditText ed;
    Button save;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setip);

        ed=(EditText)findViewById(R.id.ipaddr);
        save=(Button)findViewById(R.id.setip);

        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ed.setText(sh.getString("ip",""));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ip=ed.getText().toString();
                String url="http://"+ip+"/augmented%20reality%20web%20forms/android_connections/";
                SharedPreferences.Editor edt=sh.edit();
                edt.putString("ip",ip);
                edt.putString("url",url);
                edt.commit();

                Intent in=new Intent(getApplicationContext(),SignIn.class);
                startActivity(in);
            }
        });

    }
}
