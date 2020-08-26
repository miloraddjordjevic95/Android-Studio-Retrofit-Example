package com.miloraddjordjevic.projekat.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.miloraddjordjevic.projekat.R;
import com.miloraddjordjevic.projekat.api.RetrofitClient;
import com.miloraddjordjevic.projekat.models.User;
import java.util.HashMap;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Context context = LoginActivity.this;
    private Button button_sign_in, button_back;
    private TextInputEditText textInputEditText_email, textInputEditText_password;
    private ImageView imageView_forgot_password;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_sign_in = findViewById(R.id.login_button_sign_in);
        button_back = findViewById(R.id.login_button_back);
        textInputEditText_email = findViewById(R.id.login_textInputEditText_email);
        textInputEditText_password = findViewById(R.id.login_textInputEditText_password);
        imageView_forgot_password = findViewById(R.id.login_imageView_text_forgot_password);
        progressDialog = new ProgressDialog(context);

        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        imageView_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot_password_intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgot_password_intent);
            }
        });
    }

    private void userLogin() {
        final String email = textInputEditText_email.getText().toString().trim();
        final String password = textInputEditText_password.getText().toString().trim();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        Map<String, String> fields = new HashMap<>();
        fields.put("email", user.getEmail());
        fields.put("password", user.getPassword());

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .loginUser(fields);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}