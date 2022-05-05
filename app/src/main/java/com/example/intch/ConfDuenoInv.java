package com.example.intch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import SingletonRequest.SingletonRequest;

public class ConfDuenoInv extends AppCompatActivity implements View.OnClickListener {
    /*Ruta que tiene query para ver si el usuario invitaado/dueño tiene casas registradas
    hacer una condición que compare si tiene casas registradas*/

    String url = "http://54.90.119.130/api/duenos/insertar";
    String url2 = "http://54.90.119.130/api/inv/insertar";
    String urlbus = "http://54.90.119.130/api/casas/casasDueno";
    String urlbus2 = "http://54.90.119.130/api/casas/casasInvitado";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_dueno_inv);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button dueno, invitado;
        dueno = findViewById(R.id.btndueno);
        invitado = findViewById(R.id.btninvitado);
        dueno.setOnClickListener(this);
        invitado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btndueno) {
            SharedPreferences sp = getSharedPreferences("token", Context.MODE_PRIVATE);
            String correo = sp.getString("correo", "");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", correo);
            } catch (Exception e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
            try {
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObject);
                JsonArrayRequest cartabus = new JsonArrayRequest(Request.Method.POST, urlbus, jsonArray, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.toString().equals("[]")) {
                            InsertarDueno();
                        } else {
                            Intent intent = new Intent(ConfDuenoInv.this, Inicio.class);
                            startActivity(intent);
                        }
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
                SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(cartabus);
            } catch (Exception e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btninvitado) {

            SharedPreferences sp = getSharedPreferences("token", Context.MODE_PRIVATE);
            String correo = sp.getString("correo", "");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("email", correo);
            } catch (Exception e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
            try {
                //HACER LAS VIST
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(jsonObject);
                JsonArrayRequest cartabus2 = new JsonArrayRequest(Request.Method.POST, urlbus2, jsonArray, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.toString().equals("[]")) {
                            InsertarInvitado();

                        } else {
                            Intent intent = new Intent(ConfDuenoInv.this, Inicio.class);
                            startActivity(intent);
                        }
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
                SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(cartabus2);
            } catch (Exception e) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

       public void InsertarDueno() {
            JSONObject jsonBody = new JSONObject();
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                String correo = sharedPreferences.getString("correo", "");
                jsonBody.put("email", correo);
                jsonBody.put("nombre_dueno", "dueño");
                jsonBody.put("columna_1", "0000");
            } catch (Exception e) {
                e.printStackTrace();
            }
            JsonObjectRequest carta = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ConfDuenoInv.this, RegistroCasa.class);
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

        public void InsertarInvitado(){
            JSONObject jsonBody2 = new JSONObject();
            try {
                SharedPreferences sp = getSharedPreferences("token", Context.MODE_PRIVATE);
                String email = sp.getString("correo", "");
                jsonBody2.put("email", email);
                jsonBody2.put("nombre_invitado", "invitado");
            } catch (Exception e) {
                e.printStackTrace();
            }
            JsonObjectRequest carta2 = new JsonObjectRequest(Request.Method.POST, url2, jsonBody2, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ConfDuenoInv.this, EsperaInvitado.class);
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
            SingletonRequest.getInstance(getApplicationContext()).addToRequesQue(carta2);
        }
}


































  /*  @Override
    public void onClick(View view) {
        String url = "http://54.90.119.130/api/duenos/insertar";
        String url2 = "http://54.90.119.130/api/inv/insertar";
        JSONObject json = new JSONObject();
        switch (view.getId()) {
            case R.id.btndueno:
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                    String correo = sharedPreferences.getString("correo", "");
                    json.put("email", correo);
                    json.put("nombre_dueno", "dueño");
                    json.put("columna_1", generarNumeroAleatorio());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JsonObjectRequest carta = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ConfDuenoInv.this, RegistroCasa.class);
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
                break;

            case R.id.btninvitado:
                try {
                    SharedPreferences sp = getSharedPreferences("token", Context.MODE_PRIVATE);
                    String email = sp.getString("correo", "");
                    json.put("email", email);
                    json.put("nombre_invitado", "invitado");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JsonObjectRequest carta2 = new JsonObjectRequest(Request.Method.POST, url2, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ConfDuenoInv.this, RegistroCasa.class);
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
        }
    }
    public int generarNumeroAleatorio() {
        int numero = (int) (Math.random() * 9999) + 1;
        return numero;
    }
}
*/