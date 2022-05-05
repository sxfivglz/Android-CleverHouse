package com.example.intch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EsperaInvitado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espera_invitado);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Invitado");
    }
}