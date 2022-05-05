package com.example.intch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Fragments.ConfigFragment;
import SingletonRequest.SingletonRequest;

public class FormularioCasa extends AppCompatActivity implements View.OnClickListener {
    Button btnCambio;
    EditText NuevoNombre,NuevaDireccion;
    TextView Nombre;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_casa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Modificar Casa");
        btnCambio=findViewById(R.id.btnModCasa);
        Nombre=findViewById(R.id.NomCasa);
        SharedPreferences sharedPreferences = getSharedPreferences("ModCasa", Context.MODE_PRIVATE);
        String nombrecasa = sharedPreferences.getString("nomcasa", "");
        Nombre.setText(nombrecasa);
        NuevoNombre=findViewById(R.id.NuevoNom);
        NuevaDireccion=findViewById(R.id.NuevaDir);
        btnCambio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        url="http://54.90.119.130/api/casas/modificar";
        String nNombre= NuevoNombre.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("ModCasa", Context.MODE_PRIVATE);
        String Nombre = sharedPreferences.getString("nombrecasa", "");
        String Direccion = String.valueOf(NuevaDireccion.getText());
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre_casa",Nombre);
            jsonBody.put("nuevo_nombre_casa",nNombre);
            jsonBody.put("direccion",Direccion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest modicarCasa=new JsonObjectRequest(Request.Method.PUT, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FormularioCasa.this);
                builder.setMessage("Casa modificada con exito")
                        .setTitle("Modificar Casa")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar",   new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(FormularioCasa.this, Inicio.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               AlertDialog  alertDialog = new AlertDialog.Builder(FormularioCasa.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("No se pudo modificar la casa");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                        new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                        });
            }
        }){
            @Override
            public Map<String,String> getHeaders()throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String,String>();
                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token","");
                headers.put("Authorization","Bearer"+token);
                return headers;
            }
        };
        SingletonRequest.getInstance(this).addToRequesQue(modicarCasa);
    }
}