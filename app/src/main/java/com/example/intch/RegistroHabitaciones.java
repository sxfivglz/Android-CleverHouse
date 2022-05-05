package com.example.intch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Fragments.InicioFragment;
import Models.Habitacion;
import Models.ResInsertarArray;
import SingletonRequest.SingletonRequest;

public class  RegistroHabitaciones extends AppCompatActivity implements View.OnClickListener {
    TextView linkhab;
    Button agregarhab;
    CheckBox cocina, bano, habprin, hab, sala, comedor;
    String url = "http://54.90.119.130/api/hab/insertarArray";
    String url2= "http://54.90.119.130/api/det/insertar";
    String url3 = "http://54.90.119.130/api/det_hab/insertar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_habitaciones);
        Objects.requireNonNull(getSupportActionBar()).hide();
        cocina = findViewById(R.id.checkcocina);
        bano = findViewById(R.id.checkbano);
        habprin = findViewById(R.id.checkprincipal);
        hab = findViewById(R.id.checkhab);
        sala = findViewById(R.id.checksala);
        comedor = findViewById(R.id.checkcomedor);
        agregarhab = findViewById(R.id.btnanadirhab);
        agregarhab.setOnClickListener(this);
        linkhab = findViewById(R.id.linkagregarhabs);
        linkhab.setOnClickListener(this);
        cocina.setOnClickListener(this);
        bano.setOnClickListener(this);
        habprin.setOnClickListener(this);
        hab.setOnClickListener(this);
        sala.setOnClickListener(this);
        comedor.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnanadirhab) {
            InsertarDetCasaDno();

            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            JSONObject jsonObject3 = new JSONObject();
            JSONObject jsonObject4 = new JSONObject();
            JSONObject jsonObject5 = new JSONObject();

            if (cocina.isChecked() || bano.isChecked() || habprin.isChecked() || hab.isChecked() || sala.isChecked() || comedor.isChecked()) {
                if (cocina.isChecked()) {
                    try {
                        jsonObject.put("nombre_habitacion", "Cocina");
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al crear el JSON" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (bano.isChecked()) {
                    try {
                        jsonObject1.put("nombre_habitacion", "Baño");
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al crear el JSON" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (habprin.isChecked()) {
                    try {
                        jsonObject2.put("nombre_habitacion", "Habitación Principal");;
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al crear el JSON" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (hab.isChecked()) {
                    try {
                        jsonObject3.put("nombre_habitacion", "Habitación");
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al crear el JSON" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (sala.isChecked()) {
                    try {
                        jsonObject4.put("nombre_habitacion", "Sala");
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al crear el JSON" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                if (comedor.isChecked()) {
                    try {
                        jsonObject5.put("nombre_habitacion", "Comedor");
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al crear el JSON" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Debe seleccionar al menos una habitación")
                        .setTitle("Error")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();

                            }
                        });
            }
            JSONArray jsonArray = new JSONArray();

            if (jsonObject.length() > 0) {
                jsonArray.put(jsonObject);

            }
            if (jsonObject1.length() > 0) {
                jsonArray.put(jsonObject1);
            }
            if (jsonObject2.length() > 0) {
                jsonArray.put(jsonObject2);
            }
            if (jsonObject3.length() > 0) {
                jsonArray.put(jsonObject3);
            }
            if (jsonObject4.length() > 0) {
                jsonArray.put(jsonObject4);
            }
            if (jsonObject5.length() > 0) {
                jsonArray.put(jsonObject5);
            }
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Toast.makeText(getApplicationContext(), "Habitaciones agregadas" + response.toString(), Toast.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    String json = response.toString();
                    Type type = new TypeToken<List<ResInsertarArray>>() {}.getType();
                    List<ResInsertarArray> resInsertarArrays = gson.fromJson(json, type);
                    SharedPreferences sharedPreferences = getSharedPreferences("Array", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Array", json);
                    editor.apply();
                    InsertarHabADetallehabs();
                    Intent intent = new Intent(RegistroHabitaciones.this, Inicio.class);
                    startActivity(intent);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Intent intent = new Intent(RegistroHabitaciones.this, Inicio.class);
                    startActivity(intent);
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
            SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(jsonArrayRequest);

        } else if (view.getId() == R.id.linkagregarhabs) {
            Intent intent = new Intent(RegistroHabitaciones.this, RegistroHabitacion.class);
            startActivity(intent);
        }
    }
public void InsertarDetCasaDno(){
        SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        String correo = sharedPreferences.getString("correo", "");
        SharedPreferences sharedPreferences1 = getSharedPreferences("casa", Context.MODE_PRIVATE);
        String idcasa = sharedPreferences1.getString("idcasa", "");

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", correo);
            jsonObject.put("casa_fk", idcasa);
        }catch (JSONException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Detalle de casa insertado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
        SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(jsonObjectRequest);
}
    public void InsertarHabADetallehabs() {
        SharedPreferences sharedPreferences2 = getSharedPreferences("casa", Context.MODE_PRIVATE);
        String nomcasa = sharedPreferences2.getString("nombrecasa", "");
        SharedPreferences sharedPreferences = getSharedPreferences("Array", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("Array", "");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ResInsertarArray>>() {
        }.getType();
        List<ResInsertarArray> resInsertarArrays = gson.fromJson(json, listType);
        for (int i = 0; i < resInsertarArrays.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nombre_habitacion", resInsertarArrays.get(i).getNombre_habitacion());
                jsonObject.put("nombre_casa", nomcasa);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url3, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Se inserto", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
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
                SingletonRequest.getInstance(this).addToRequesQue(jsonObjectRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}







