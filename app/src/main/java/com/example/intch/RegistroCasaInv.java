package com.example.intch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import SingletonRequest.SingletonRequest;

public class RegistroCasaInv extends AppCompatActivity implements View.OnClickListener {
    String url = "http://54.90.119.130/api/casas/casasInvitado";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_casa_inv);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button btncasa;
        btncasa= findViewById(R.id.anadircasainv);
        btncasa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String correodueno = ((EditText) findViewById(R.id.correoduenoinv)).getText().toString();
        String codigodueno = ((EditText) findViewById(R.id.codigoduenoinv)).getText().toString();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre_casa", correodueno);
            jsonBody.put("direccion", codigodueno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest carta = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("datosinvitado", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", response.optString("id"));
                editor.putString("nombrecasa", correodueno);
                editor.putString("direccioncasa", codigodueno);
                editor.apply();
                Intent intent = new Intent(RegistroCasaInv.this, RegistroHabitaciones.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(carta);
    }
    }











/*  String url = "http://54.90.119.130/api/casas/casasDueno";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_casa);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button btncasa;
        btncasa= findViewById(R.id.anadircasa);
        btncasa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nombrecasa = ((EditText) findViewById(R.id.nombrecasa)).getText().toString();
        String direccioncasa = ((EditText) findViewById(R.id.direccioncasa)).getText().toString();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id",null);
            jsonBody.put("nombre_casa", nombrecasa);
            jsonBody.put("direccion", direccioncasa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest carta = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("datoscasa", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("id", response.optString("id"));
                editor.putString("nombrecasa", nombrecasa);
                editor.putString("direccioncasa", direccioncasa);
                editor.apply();
                Intent intent = new Intent(RegistroCasa.this, RegistroHabitaciones.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(carta);
    }
}*/