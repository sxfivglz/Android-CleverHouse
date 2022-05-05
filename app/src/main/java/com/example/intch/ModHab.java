package com.example.intch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModHab extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnmod;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_hab);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Modificar habitación");
        btnmod = findViewById(R.id.btnModHab);
        btnmod.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnModHab) {
            String nombrehab = ((EditText) findViewById(R.id.nomHab_mod)).getText().toString();
            SharedPreferences prefs = getSharedPreferences("ModHab", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombrehab", nombrehab);
            editor.apply();
            Intent intent = new Intent(this, FormularioHabitacion.class);
            startActivity(intent);
        }
    }

   /* public void ModHab(View view) {
        String nom_hab=((EditText)findViewById(R.id.nomHab_mod)).getText().toString();
        Intent intent = new Intent(this, FormularioHabitacion.class);
        intent.putExtra("nombre",nom_hab);
        startActivity(intent);
    }*/
}