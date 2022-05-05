package com.example.intch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Objects;

import Models.ResLogin;
import Models.Usuario;
import SingletonRequest.SingletonRequest;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        TextView Registro;
        Button RegistroCasa;
        Registro= findViewById(R.id.linkregistro);
        RegistroCasa=findViewById(R.id.btnlogin);
        Registro.setOnClickListener(this);
        RegistroCasa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linkregistro:
                Intent intentR = new Intent(Login.this, Registro.class);
                startActivity(intentR);
                break;
            case R.id.btnlogin:
                String correo = ((EditText) findViewById(R.id.correologin)).getText().toString();
                String pass = ((EditText) findViewById(R.id.contralogin)).getText().toString();
                String url= "http://54.90.119.130/api/user/login";
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("email", correo);
                    jsonBody.put("password", pass);
                }catch (Exception e){
                    e.printStackTrace();
                }
                JsonObjectRequest RequestLog = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        String data = response.toString();
                        Usuario usuario = gson.fromJson(data, Usuario.class);
                        ResLogin msj_res = gson.fromJson(data, ResLogin.class);
                       // Toast.makeText(getApplicationContext(), msj_res.getToken(), Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", msj_res.getToken());
                        editor.putString("correo", correo);
                        editor.apply();
                        Intent intent = new Intent(Login.this, ConfDuenoInv.class);
                        startActivity(intent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("test", error.toString());
                    }
                });
                SingletonRequest.getInstance(this).addToRequesQue(RequestLog);
                break;
        }
    }

}