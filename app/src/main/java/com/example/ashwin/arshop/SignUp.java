package com.example.ashwin.arshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    Button b;
    EditText ed_fname,ed_lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        b=(Button)findViewById(R.id.register_id);
        ed_fname=(EditText)findViewById(R.id.first_name_id);


        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String fname=ed_fname.getText().toString();
        String lname=ed_lname.getText().toString();

    }
}
