package com.example.intch;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Sensor;
import Models.SensorDetalleHab;
import SingletonRequest.SingletonRequest;

public class RegistroSensor extends AppCompatActivity implements View.OnClickListener {
    String url = "http://54.90.119.130/api/sen/listar";
    Button agregarsensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_sensor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar sensor");
        agregarsensor = findViewById(R.id.btnanadirsensor);
        agregarsensor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnanadirsensor) {
            String nombresens = ((EditText) findViewById(R.id.editTextanadirsensor)).getText().toString();
            String url2 = "http://54.90.119.130/api/det_sen/insertar";
            SharedPreferences sharedPreferences = getSharedPreferences("Detallehab", Context.MODE_PRIVATE);
            String nomhab = sharedPreferences.getString("habitacion", "");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nombre_sensor", nombresens);
                jsonObject.put("nombre_habitacion", nomhab);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest registroRequest = new JsonObjectRequest(Request.Method.POST, url2, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    String json = response.toString();
                    SensorDetalleHab sensorDetalleHab = gson.fromJson(json, SensorDetalleHab.class);
                    Toast.makeText(getApplicationContext(), "Se registro el sensor", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DetalleHabitacion.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
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
            SingletonRequest.getInstance(this).addToRequesQue(registroRequest);
        }
    }

}