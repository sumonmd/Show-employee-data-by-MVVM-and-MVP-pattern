package com.example.user.internproject.view;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.user.internproject.mydatabase.MyDatabase;
import com.example.user.internproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    // for database//
    public static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         myDatabase = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"MyDatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        // for fragment//
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        bottomNav.setSelectedItemId(R.id.local_id);
    }
       private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()){
                    case R.id.local_id:
                        selectedFragment = new LocalFragment();
                        break;
                    case R.id.server_id:
                        selectedFragment = new ServerFragment();
                        break;
                    case R.id.more_id:
                        selectedFragment = new MoreFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectedFragment).commit();
                return true;
            }
        };
    }
