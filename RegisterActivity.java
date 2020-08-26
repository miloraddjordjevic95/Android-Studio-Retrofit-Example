package com.miloraddjordjevic.projekat.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.miloraddjordjevic.projekat.R;
import com.miloraddjordjevic.projekat.api.API;
import com.miloraddjordjevic.projekat.api.RetrofitClient;
import com.miloraddjordjevic.projekat.models.User;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private Context context = RegisterActivity.this;
    private Button button_create_account, button_back;
    private TextInputEditText textInputEditText_first_name, textInputEditText_last_name, textInputEditText_email, textInputEditText_password, textInputEditText_confirm_password;
    private ProgressDialog progressDialog;
    private API API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button_create_account = findViewById(R.id.register_button_create_account);
        button_back = findViewById(R.id.register_button_back);
        textInputEditText_first_name = findViewById(R.id.login_textInputEditText_email);
        textInputEditText_last_name = findViewById(R.id.register_textInputEditText_last_name);
        textInputEditText_email = findViewById(R.id.register_textInputEditText_email);
        textInputEditText_password = findViewById(R.id.register_textInputEditText_password);
        textInputEditText_confirm_password = findViewById(R.id.register_textInputEditText_confirm_password);
        progressDialog = new ProgressDialog(context);

        button_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcome_intent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                startActivity(welcome_intent);
            }
        });
    }

    private void registerUser() {
        final String first_name = textInputEditText_first_name.getText().toString().trim();
        final String last_name = textInputEditText_last_name.getText().toString().trim();
        final String email = textInputEditText_email.getText().toString().trim();
        final String password = textInputEditText_password.getText().toString().trim();
        final String password_confirmation = textInputEditText_confirm_password.getText().toString().trim();

        User user = new User();
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPassword_confirmation(password_confirmation);

        Map<String, String> fields = new HashMap<>();
        fields.put("first_name", user.getFirst_name());
        fields.put("last_name", user.getLast_name());
        fields.put("email", user.getEmail());
        fields.put("password", user.getPassword());
        fields.put("password_confirmation", user.getPassword_confirmation());

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .registerUser(fields);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    Toast.makeText(context, "Successfully registered.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}