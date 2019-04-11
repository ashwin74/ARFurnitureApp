package com.example.ashwin.arshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<String> itemid, itemname, itemprice, itemdescription, itemurl, categoryid, categoryname, reviewid,userid, postdate, review, rating, firstname;
    ListView item;
    String url;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        item=(ListView)findViewById(R.id.item);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        productDisplay();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.order_status) {
            Intent intent = new Intent(Home.this, OrderStatus.class);
            startActivity(intent);

        }   else if (id == R.id.cart) {
            Intent intent = new Intent(Home.this, Cart.class);
            startActivity(intent);
        }   else if (id == R.id.notification) {
            Intent intent = new Intent(Home.this, Notification.class);
            startActivity(intent);
        }   else if (id == R.id.user_details) {
            Intent intent = new Intent(Home.this, EditUserAccount.class);
            startActivity(intent);
        }   else if (id == R.id.sign_out) {
            Intent intent = new Intent(Home.this, SignIn.class);
            startActivity(intent);
        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

    public void productDisplay(){
        JSONObject json=new JSONObject();
        JSONParser jsonParser = new JSONParser();
        ArrayList<NameValuePair> para=new ArrayList<>();
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url = sp.getString("url", "") + "Products";
        json=jsonParser.makeHttpRequest(url,"GET",para);
        try {
            String status=json.getString("status");
            Toast.makeText(this, json.toString(), Toast.LENGTH_SHORT).show();
            if(status.equalsIgnoreCase("1")){
                JSONArray ja=json.getJSONArray("data");
                itemid=new ArrayList<>();
                itemname=new ArrayList<>();
                itemdescription=new ArrayList<>();
                itemprice=new ArrayList<>();
                itemurl=new ArrayList<>();

                categoryid=new ArrayList<>();
                categoryname=new ArrayList<>();

                reviewid=new ArrayList<>();
                userid=new ArrayList<>();
//                loginid=new ArrayList<>();
                postdate=new ArrayList<>();
                review=new ArrayList<>();
                rating=new ArrayList<>();

                firstname=new ArrayList<>();

                for(int i=0;i<ja.length();i++){
                    JSONObject jo=ja.getJSONObject(i);
                    itemid.add(jo.getString("itemid"));
                    itemname.add(jo.getString("itemname"));
                    itemdescription.add(jo.getString("itemdescription"));
                    itemprice.add(jo.getString("itemprice"));
                    itemurl.add(jo.getString("itemurl"));

                    categoryid.add(jo.getString("categoryid"));
                    categoryname.add(jo.getString("categoryname"));

                    reviewid.add(jo.getString("reviewid"));
                    userid.add(jo.getString("userid"));

                    review.add(jo.getString("review"));
                    rating.add(jo.getString("rating"));
                    postdate.add(jo.getString("postdate"));

                    firstname.add(jo.getString("firstname"));
                }
                    item.setAdapter(new Custom_driver(getApplicationContext(),itemname,itemprice,itemurl,itemid));
            } else {
                Toast.makeText(this, "No products...!!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

