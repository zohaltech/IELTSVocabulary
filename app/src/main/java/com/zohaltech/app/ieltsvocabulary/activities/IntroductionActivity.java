package com.zohaltech.app.ieltsvocabulary.activities;

import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.zohaltech.app.ieltsvocabulary.R;

public class IntroductionActivity extends EnhancedActivity {

    @Override
    protected void onCreated() {
        setContentView(R.layout.activity_introduction);

        ((TextView) findViewById(R.id.txtWelcome)).setText("Welcome to " + getString(R.string.app_name));
    }

    @Override
    protected void onToolbarCreated() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Introduction");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}