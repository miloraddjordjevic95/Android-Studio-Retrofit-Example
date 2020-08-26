package com.miloraddjordjevic.projekat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.miloraddjordjevic.projekat.R;
import com.miloraddjordjevic.projekat.api.RetrofitClient;
import com.miloraddjordjevic.projekat.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    private Context context = ForgotPasswordActivity.this;
    private Button button_send;
    private TextInputEditText textInputEditText_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        button_send = findViewById(R.id.forgot_password_button_send);
        textInputEditText_email = findViewById(R.id.forgot_password_textInputEditText_email);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String email = textInputEditText_email.getText().toString().trim();

        User user = new User();
        user.setEmail(email);

        Map<String, String> fields = new HashMap<>();
        fields.put("email", user.getEmail());

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .sendEmail(fields);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 202) {
                        String s = response.body().string();
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 401) {
                        String s = response.errorBody().string();
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(context, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}