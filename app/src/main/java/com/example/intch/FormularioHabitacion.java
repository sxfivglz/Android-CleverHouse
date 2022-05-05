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

public class FormularioHabitacion extends AppCompatActivity implements View.OnClickListener {
    Button btnModHab;
    EditText NuevoNomHab;
    TextView NombreAnt;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_habitacion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Modificar habitación");
        btnModHab=findViewById(R.id.btnModHab);
        NombreAnt=findViewById(R.id.NomHab);
        SharedPreferences sharedPreferences = getSharedPreferences("ModHab", Context.MODE_PRIVATE);
        String nombreAnt = sharedPreferences.getString("nombrehab", "");
        NombreAnt.setText(nombreAnt);
        NuevoNomHab=findViewById(R.id.NuevoNomHab);
        btnModHab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnModHab) {
            url = "http://54.90.119.130/api/hab/modificar";
            SharedPreferences sharedPreferences = getSharedPreferences("ModHab", Context.MODE_PRIVATE);
            String nombreanterior = sharedPreferences.getString("nombrehab", "");
            String nNombre = NuevoNomHab.getText().toString();
            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("nombre_anterior", nombreanterior);
                jsonBody.put("nuevo_nombre", nNombre);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest modicarhab = new JsonObjectRequest(Request.Method.PUT, url, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormularioHabitacion.this);
                    builder.setMessage("Habitación modificada correctamente")
                            .setTitle("Modificación de habitación")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(FormularioHabitacion.this, Inicio.class);
                                    startActivity(intent);
                                }
                                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Modificación incorrecta");
                    builder.setMessage("Por favor, verifique que la habitacion exista");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(FormularioHabitacion.this, ModHab.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", "");
                    headers.put("Authorization", "Bearer" + token);
                    return headers;
                }
            };
            SingletonRequest.getInstance(this).addToRequesQue(modicarhab);
        }
    }
}
