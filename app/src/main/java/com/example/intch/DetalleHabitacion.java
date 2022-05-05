package com.example.intch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.AdaptadorHabitaciones;
import Adapters.AdaptadorSensor;
import Models.Sensor;
import Models.SensorDetalleHab;
import SingletonRequest.SingletonRequest;

public class DetalleHabitacion extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    TextView textohab;
    String url ="http://54.90.119.130/api/det_sen/SensorHabitacion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_habitacion);
        textohab = findViewById(R.id.txtTituloHab);
        FloatingActionButton fab;
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sensores");
        recyclerView = findViewById(R.id.recyclersensor);
        ListarSensoresHab();
        Intent intent = getIntent();
        SharedPreferences sharedPreferences = getSharedPreferences("Detallehab", Context.MODE_PRIVATE);
        String nombrehab = sharedPreferences.getString("habitacion", "");
        textohab.setText(nombrehab);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }
    public void ListarSensoresHab() {
    JSONArray jsonArray = new JSONArray();
    try {
        SharedPreferences sharedPreferences = getSharedPreferences("Detallehab", Context.MODE_PRIVATE);
        String nomhab = sharedPreferences.getString("habitacion", "");
        jsonArray.put(new JSONObject().put("nombre_habitacion", nomhab));
    } catch (Exception e) {
        e.printStackTrace();
    }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url,jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                String json = response.toString();
                Type listType = new TypeToken<List<SensorDetalleHab>>() {
                }.getType();
                List<SensorDetalleHab> sensores = gson.fromJson(json, listType);
                AdaptadorSensor adaptersensor = new AdaptadorSensor(sensores);;
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adaptersensor);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetalleHabitacion.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }

        };
        SingletonRequest.getInstance(this).addToRequesQue(jsonArrayRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Intent intent = new Intent(this, RegistroSensor.class);
                startActivity(intent);
                break;
        }
    }
}