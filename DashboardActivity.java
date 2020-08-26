package com.miloraddjordjevic.projekat.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import com.miloraddjordjevic.projekat.R;

public class DashboardActivity extends AppCompatActivity {
    private Context context = DashboardActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}