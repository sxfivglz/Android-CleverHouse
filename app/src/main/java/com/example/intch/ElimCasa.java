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
import android.widget.EditText;
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

public class ElimCasa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elim_casa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Eliminar casa");
    }
    public void ElimCasa(View view) {
        Intent intent = new Intent(this, ConfigFragment.class);
        startActivity(intent);
        String url="http://54.90.119.130/api/casas/borrar";
        String nombre=((EditText)findViewById(R.id.nomCasa_elim)).getText().toString();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("nombre_casa",nombre);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest modicarCasa=new JsonObjectRequest(Request.Method.DELETE, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ElimCasa.this);
                builder.setMessage("Habitación modificada correctamente")
                        .setTitle("Modificación de habitación")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(ElimCasa.this, Inicio.class);
                                startActivity(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ElimCasa.this, error.toString(), Toast.LENGTH_SHORT).show();
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