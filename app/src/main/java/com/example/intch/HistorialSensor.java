package com.example.intch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.AdaptadorHabitaciones;
import Adapters.AdaptadorHistorial;
import Models.Adafruit;
import Models.Habitacion;
import SingletonRequest.SingletonRequest;

public class HistorialSensor extends AppCompatActivity {
    RecyclerView recyclerView;
    String url = "http://54.90.119.130/api/historial";
    String urlconcat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_sensor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Historial");
        recyclerView = findViewById(R.id.recyclerhistorial);
        SharedPreferences sharedPreferences = getSharedPreferences("detallesensor", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre_sensor", "");
        urlconcat = url + "/" + nombre;
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlconcat, null,
                        new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        String json = response.toString();
                        Type type = new TypeToken<List<Adafruit>>() {
                        }.getType();
                        List<Adafruit> adafruitList = gson.fromJson(json, type);
                        AdaptadorHistorial adaptadorHistorial = new AdaptadorHistorial(adafruitList);
                        recyclerView.setAdapter(adaptadorHistorial);
                        recyclerView.setLayoutManager(new LinearLayoutManager(HistorialSensor.this));
                        recyclerView.setHasFixedSize(true);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HistorialSensor.this, error.toString(), Toast.LENGTH_SHORT).show();
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
                SingletonRequest.getInstance(this).addToRequesQue(request);

        }

    }
