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

public class Custom_driver extends BaseAdapter{

    private Context Context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;





    public Custom_driver(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d) {

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
            gridView=inflator.inflate(R.layout.item, null);

        }
        else
        {
            gridView=(View)convertview;

        }

        TextView item_name=(TextView)gridView.findViewById(R.id.item_name);
        TextView item_price=(TextView)gridView.findViewById(R.id.item_price);
        ImageView item_image=(ImageView)gridView.findViewById(R.id.item_image);
        final Button add_cart = (Button)gridView.findViewById(R.id.add_cart);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);



        item_name.setTextColor(Color.BLACK);
        item_price.setTextColor(Color.BLACK);



        item_name.setText(a.get(position));
        item_price.setText(b.get(position));

        try
        {
            String imgurl="http://"+sh.getString("ip","")+":8084/ARFurnitureWeb/Items/"+c.get(position);
            Picasso.with(Context).load(imgurl).transform(new CircleTransform()).into(item_image);}
        catch (Exception e)
        {
            Toast.makeText(Context, e.toString(), Toast.LENGTH_SHORT).show();

        }

        add_cart.setTag(d.get(position));


        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid=add_cart.getTag().toString();
                Toast.makeText(Context, pid, Toast.LENGTH_SHORT).show();
                Intent in=new Intent(Context,ProductDetail.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("pid",pid);
                Context.startActivity(in);
            }
        });

        return gridView;
    }

}
