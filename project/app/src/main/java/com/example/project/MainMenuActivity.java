package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainMenuActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec homeSpec = tabHost.newTabSpec("home");
        homeSpec.setIndicator("Home");
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeSpec.setContent(homeIntent);

        TabHost.TabSpec profileSpec = tabHost.newTabSpec("profile");
        profileSpec.setIndicator("Profile");
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        profileSpec.setContent(profileIntent);

        TabHost.TabSpec rankSpec = tabHost.newTabSpec("rank");
        rankSpec.setIndicator("Rank");
        Intent rankIntent = new Intent(this, RankActivity.class);
        rankSpec.setContent(rankIntent);

        tabHost.addTab(homeSpec);
        tabHost.addTab(profileSpec);
        tabHost.addTab(rankSpec);

    }
}