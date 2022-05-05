package com.example.intch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import Models.Adafruit;
import SingletonRequest.SingletonRequest;

public class DetalleSensor extends AppCompatActivity implements View.OnClickListener {
    TextView nomsens, datosens;
    Button btn_historial, btn_grafica;
    String url = "http://54.90.119.130/api/UltimoRegistro";
    String urlconcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_sensor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sensor");
        Intent intent = getIntent();
        nomsens = findViewById(R.id.textView_nombre_sensor_detalle);
        datosens = findViewById(R.id.textView_datosexternos_detalle);
        btn_historial = findViewById(R.id.btn_historial);
        btn_grafica = findViewById(R.id.btn_grafica);
        btn_historial.setOnClickListener(this);
        btn_grafica.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("detallesensor", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre_sensor", "");
        nomsens.setText(nombre);
        urlconcat = url + "/" + nombre;
        switch (nombre){
            case "temperatura":
                JsonObjectRequest jsonBody = new JsonObjectRequest(Request.Method.GET, urlconcat, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Gson gson = new Gson();
                            Adafruit sensor = gson.fromJson(response.toString(), Adafruit.class);
                            String valor = sensor.getValue() + "°C";
                            datosens.setText(valor);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                SingletonRequest.getInstance(this).addToRequesQue(jsonBody);
                break;
                case "luminosidad":
                    JsonObjectRequest jsonBody1 = new JsonObjectRequest(Request.Method.GET, urlconcat, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Gson gson = new Gson();
                                Adafruit sensor = gson.fromJson(response.toString(), Adafruit.class);
                                String valor = sensor.getValue() + " %";
                                datosens.setText(valor);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    SingletonRequest.getInstance(this).addToRequesQue(jsonBody1);
                    break;
                    case "distancia":
                        JsonObjectRequest jsonBody2 = new JsonObjectRequest(Request.Method.GET, urlconcat, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Gson gson = new Gson();
                                    Adafruit sensor = gson.fromJson(response.toString(), Adafruit.class);
                                    String valor = sensor.getValue() + " cm";
                                    datosens.setText(valor);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        SingletonRequest.getInstance(this).addToRequesQue(jsonBody2);
                        break;
        }
    }
    @Override
    public void onClick(View view) {
           if(view.getId() == R.id.btn_historial) {
               Intent intent = new Intent(this, HistorialSensor.class);
               startActivity(intent);
           }
           if (view.getId() == R.id.btn_grafica) {
               Intent intent = new Intent(this, GraficaSensor.class);
               startActivity(intent);

            }
    }
}