package com.example.intch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class RegistroHabitacion extends AppCompatActivity implements View.OnClickListener {
    String url = "http://54.90.119.130/api/hab/insertar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_habitacion);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button btnagregahab = findViewById(R.id.anadirhab);
        btnagregahab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String nomhab = ((EditText) findViewById(R.id.nombrehab)).getText().toString();
        AnadirHab(nomhab);

    }

    public void AnadirHab(String nomhab) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre_habitacion", nomhab);

        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest carta = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegistroHabitacion.this, Inicio.class);
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
    public void InsertarHabDetalleHab(){
        SharedPreferences sharedPreferences = getSharedPreferences("casa", Context.MODE_PRIVATE);
        String nomcasa = sharedPreferences.getString("nombrecasa", "");
        SharedPreferences sharedPreferences1 = getSharedPreferences("habitacion", Context.MODE_PRIVATE);
    }
}