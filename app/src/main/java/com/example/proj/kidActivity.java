package com.example.proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class kidActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid);

        bnv = findViewById(R.id.bottomNavigationView);
        bnv.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                Fragment selected = null;

                switch(item.getItemId())
                {
                    case R.id.mainn:
                        selected = new mainn();
                        break;
                    case R.id.request:
                        selected = new request();
                        break;
                    case R.id.info2:
                        selected = new info();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,selected).commit();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new mainn()).commit();

    }
}