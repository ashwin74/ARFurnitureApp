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

public class Custom_order extends BaseAdapter{

    private Context Context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;


    public Custom_order(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d) {

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
            gridView=inflator.inflate(R.layout.pending, null);
        }
        else
        {
            gridView=(View)convertview;
        }

        TextView itemname=(TextView)gridView.findViewById(R.id.item_name);
        TextView itemquantity=(TextView)gridView.findViewById(R.id.item_quantity);
        TextView itemprice=(TextView)gridView.findViewById(R.id.item_price);
        TextView orderstatus=(TextView)gridView.findViewById(R.id.order_status);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);

        itemname.setText(a.get(position));
        itemquantity.setText(b.get(position));
        itemprice.setText(c.get(position));
        orderstatus.setText(d.get(position));

        return gridView;
    }

}
