package com.miloraddjordjevic.projekat.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.miloraddjordjevic.projekat.R;

public class WelcomeActivity extends AppCompatActivity {
    private Context context = WelcomeActivity.this;
    private Button button_sign_in, button_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        button_sign_in = findViewById(R.id.welcome_button_sign_in);
        button_sign_up = findViewById(R.id.welcome_button_sign_up);

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(login_intent);
            }
        });

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(register_intent);
            }
        });
    }
}