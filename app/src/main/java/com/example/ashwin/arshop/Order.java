package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

public class Order extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button b1, b2;
    EditText e1, card, cvv;
    TextView t1, t2, t3, t4, t5, t6;
    String url="", url1="", lid;
    int pos;
    String id;
    Spinner snr;
    SharedPreferences sp;
    String[] qty={"1","2","3","4","5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        b1 = (Button)findViewById(R.id.change_delivery_button);
        b2 = (Button)findViewById(R.id.pay_button);

        t1 = (TextView)findViewById(R.id.first_name);
        t2 = (TextView)findViewById(R.id.house_name);
        t3 = (TextView)findViewById(R.id.city);
        t4 = (TextView)findViewById(R.id.state);
        t5 = (TextView)findViewById(R.id.zip);
        t6 = (TextView)findViewById(R.id.total_price);

        card = (EditText)findViewById(R.id.card_number);
        cvv = (EditText)findViewById(R.id.cvv);
        snr=(Spinner)findViewById(R.id.quantity);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,qty);
        snr.setAdapter(adapter);
        snr.setOnItemSelectedListener(this);
        pos = Integer.parseInt(getIntent().getStringExtra("pos"));
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url=sp.getString("url","")+"Order";
        url1=sp.getString("url","")+"Pay";
        lid=sp.getString("lid","");
        id = Home.itemid.get(pos);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        try {
            JSONObject js=new JSONObject();
            JSONParser jsonParser=new JSONParser();
            ArrayList<NameValuePair> param=new ArrayList<>();
            param.add(new BasicNameValuePair("lid",lid));
            js=jsonParser.makeHttpRequest(url,"GET",param);
            String res=js.getString("Status");
            if(res.equalsIgnoreCase("1"))
            {
                t1.setText(js.getString("firstname"));
                t2.setText(js.getString("housename"));
                t3.setText(js.getString("city"));
                t4.setText(js.getString("state"));
                t5.setText(js.getString("zipcode"));
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
    public void onClick(View v) {
        if (v==b1) {
            Intent intent = new Intent(Order.this, EditUserAccount.class);
            startActivity(intent);
        }
        if (v==b2) {

            String quantity=snr.getSelectedItem().toString();

            String cardnumber=card.getText().toString();
            String cvvnumber=cvv.getText().toString();
            JSONObject json=new JSONObject();
            JSONParser jsonParser=new JSONParser();
            ArrayList<NameValuePair> para=new ArrayList<>();
            para.add(new BasicNameValuePair("lid",lid));
            para.add(new BasicNameValuePair("id",id+""));
            para.add(new BasicNameValuePair("cardnumber",cardnumber+""));
            para.add(new BasicNameValuePair("cvvnumber", cvvnumber+""));
            para.add(new BasicNameValuePair("quantity",quantity+""));
            json=jsonParser.makeHttpRequest(url1,"POST",para);

            try {
                String status = json.getString("status");
                if(status.equalsIgnoreCase("1"))
                {
                    Toast.makeText(this, "Payment Successfull and Order Placed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Order.this, OrderStatus.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e) {
                Toast.makeText(this, e.toString()+"", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(getApplicationContext(), OrderStatus.class);
            startActivity(intent);



        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Order.this, ProductDetail.class);
        intent.putExtra("pid",pos+"");
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int price=Integer.parseInt(Home.itemprice.get(pos));
        int qnty=Integer.parseInt(snr.getSelectedItem().toString());
        int total=price*qnty;
        t6.setText(total+"");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}