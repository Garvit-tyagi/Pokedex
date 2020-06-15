package com.example.thepokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
private long backPressedTime;
Toast backToast;
boolean back=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new pokemons_fragment()).commit();
            navigationView.setCheckedItem(R.id.pokemons);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pokemons:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new pokemons_fragment()).commit();

                        break;
                    case R.id.typedex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new typedex_fragment()).commit();
                        break;
                    case R.id.regiondex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new regiondex_fragment()).commit();
                        break;
                    case R.id.itemdex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new itemdex_fragment()).commit();
                        break;
                    case R.id.locationdex:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new locationdex_fragment()).commit();
                        break;
//                    case R.id.favourites:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new favourites_fragment()).commit();
//                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });



    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();
                finish();
            } else {

                backToast = Toast.makeText(getBaseContext(), "Press back again to Exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();

        }

    }}

