package com.example.ashwin.arshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Custom_driver_review extends BaseAdapter{

    private Context Context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    static int pos;


    public Custom_driver_review(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d) {

        this.Context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;


    }

    @Override
    public int getCount() {

        return b.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.item_review, null);

        }
        else
        {
            gridView=(View)convertview;

        }

        TextView firstname=(TextView)gridView.findViewById(R.id.first_name);
        TextView postdate=(TextView)gridView.findViewById(R.id.post_date);
        TextView review=(TextView)gridView.findViewById(R.id.review);
        TextView rating=(TextView)gridView.findViewById(R.id.rating);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);



        firstname.setTextColor(Color.BLACK);
        postdate.setTextColor(Color.BLACK);
        review.setTextColor(Color.BLACK);
        rating.setTextColor(Color.BLACK);



        firstname.setText(a.get(position));
        postdate.setText(b.get(position));
        review.setText(c.get(position));
        rating.setText(d.get(position));

        return gridView;
    }

}
