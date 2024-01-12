package com.example.kukufarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    CardView inventory,transactions,health,feeds,settings;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inventory=findViewById(R.id.inventory);
        transactions=findViewById(R.id.transaction);
        health=findViewById(R.id.health);
        feeds=findViewById(R.id.feeds);
        settings=findViewById(R.id.settings);
        navigationView=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(MainActivity.this,"Home Page",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.profile:
                        //startActivity
                        Toast.makeText(MainActivity.this,"Profile Page",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        //startActivity
                        Toast.makeText(MainActivity.this,"About Us Page",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_logout:
                        Toast.makeText(MainActivity.this,"User",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("Confirm Exit...");
                        alertDialogBuilder.setMessage("Are you sure you want to exit?");
                        alertDialogBuilder.setCancelable(false);
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,"Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog alertDialog=alertDialogBuilder.create();
                        alertDialog.show();
                        break;
                    case R.id.nav_share:
                        Toast.makeText(MainActivity.this,"Share",Toast.LENGTH_SHORT).show();
                        ApplicationInfo api=getApplicationContext().getApplicationInfo();
                        String path=api.sourceDir;
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("application/vnd.android.package-archive");
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(path)));
                        startActivity(Intent.createChooser(intent,"Share Via"));
                        break;
                    case R.id.nav_contact:
                        Toast.makeText(MainActivity.this,"Dialing...",Toast.LENGTH_SHORT).show();
                        Intent call=new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:0728853098"));
                        startActivity(call);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InventoryActivity.class));
            }
        });
        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        feeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}