package com.example.ashwin.arshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Order extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        b1 = (Button)findViewById(R.id.change_delivery_button);
        b2 = (Button)findViewById(R.id.pay_button);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==b1) {
            Intent intent = new Intent(Order.this, EditUserAccount.class);
            startActivity(intent);
        }
        if (v==b2) {
            Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
        }
    }
}