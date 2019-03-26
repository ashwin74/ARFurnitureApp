package com.example.ashwin.arshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        b=(Button)findViewById(R.id.register_id);

        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
