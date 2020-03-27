package com.example.ptsgenap11rpl1absen04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
    }
    public void login(View view) {
        AndroidNetworking.post("http://192.168.43.65/web/ApiPTS_alan_04/login.php")
                .addBodyParameter("u_username", username.getText().toString().trim())
                .addBodyParameter("u_password", password.getText().toString().trim())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("test", String.valueOf(response));
                        Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("anError", error.getLocalizedMessage());
                    }
                });
    }
}
