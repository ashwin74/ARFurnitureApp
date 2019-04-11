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

public class Custom_driver_detail extends BaseAdapter{

    private Context Context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> e;
    ArrayList<String> f;
    ArrayList<String> g;
    ArrayList<String> h;
    ArrayList<String> i;
    ArrayList<String> j;
    ArrayList<String> k;
    ArrayList<String> l;
    ArrayList<String> m;



    public Custom_driver_detail(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e, ArrayList<String> f, ArrayList<String> g, ArrayList<String> h, ArrayList<String> i, ArrayList<String> j, ArrayList<String> k, ArrayList<String> l, ArrayList<String> m) {

        this.Context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.g=g;
        this.h=h;
        this.i=i;
        this.j=j;
        this.k=k;
        this.l=l;
        this.m=m;

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
            gridView=inflator.inflate(R.layout.item, null);

        }
        else
        {
            gridView=(View)convertview;

        }

        TextView itemname=(TextView)gridView.findViewById(R.id.item_name);
        TextView itemcategory=(TextView)gridView.findViewById(R.id.item_category);
        TextView itemprice=(TextView)gridView.findViewById(R.id.item_price);
        TextView itemdescription=(TextView)gridView.findViewById(R.id.item_description);
        ImageView itemimage=(ImageView)gridView.findViewById(R.id.item_image);

        TextView firstname=(TextView)gridView.findViewById(R.id.first_name);
        TextView postdate=(TextView)gridView.findViewById(R.id.post_date);
        TextView review=(TextView)gridView.findViewById(R.id.review);
        TextView rating=(TextView)gridView.findViewById(R.id.rating);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);





        itemname.setText(a.get(position));
        itemcategory.setText(b.get(position));
        itemprice.setText(c.get(position));
        itemdescription.setText(d.get(position));
        firstname.setText(e.get(position));
        postdate.setText(f.get(position));
        review.setText(g.get(position));
        rating.setText(h.get(position));

        try
        {
            String imgurl="http://"+sh.getString("ip","")+":8084/ARFurnitureWeb/Items/"+h.get(position);
            Picasso.with(Context).load(imgurl).transform(new CircleTransform()).into(itemimage);}
        catch (Exception e)
        {
            Toast.makeText(Context, e.toString(), Toast.LENGTH_SHORT).show();

        }



        return gridView;
    }

}
