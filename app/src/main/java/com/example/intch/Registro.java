package com.example.intch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import Models.ResReg;
import SingletonRequest.SingletonRequest;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button registrado;
        registrado = findViewById(R.id.btnregistro);
        registrado.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregistro:
                String nombre = ((EditText) findViewById(R.id.nombre)).getText().toString();
                String apellido = ((EditText) findViewById(R.id.apellidos)).getText().toString();
                String correo = ((EditText) findViewById(R.id.correo)).getText().toString();
                String pass = ((EditText) findViewById(R.id.pass)).getText().toString();
                String telefono = ((EditText) findViewById(R.id.telefono)).getText().toString();
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || pass.isEmpty() || telefono.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Registro incorrecto");
                    builder.setMessage("Por favor, verifique que todos los campos esten llenos");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Registro.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    RegistroV(nombre,apellido,correo,pass,telefono);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setTitle("Registro correcto");
                    builder1.setMessage("Por favor, inicia sesión");
                    builder1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Registro.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog1 = builder1.create();
                    dialog1.show();
                }
                break;
        }
    }

    public void RegistroV(String nombre, String apellido, String correo, String pass, String telefono) {

        String url;
        url = "http://54.90.119.130/api/user/register";
        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("nombre_usuario", nombre);
            jsonBody.put("apellido_usuario", apellido);
            jsonBody.put("email", correo);
            jsonBody.put("password", pass);
            jsonBody.put("telefono", telefono);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest registroRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                String data = response.toString();
                ResReg msj_res = gson.fromJson(data, ResReg.class);
                Intent intentR = new Intent(Registro.this, Login.class);
                SharedPreferences sharedPreferences = getSharedPreferences("registro", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nombre", nombre);
                editor.putString("apellido", apellido);
                editor.putString("correo", correo);
                editor.putString("pass", pass);
                editor.putString("telefono", telefono);
                editor.apply();
                startActivity(intentR);
                // Toast.makeText(getApplicationContext(), msj_res.getToken(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                Log.d("test", error.toString());
            }
        });
        SingletonRequest.getInstance(this).addToRequesQue(registroRequest);
    }

}