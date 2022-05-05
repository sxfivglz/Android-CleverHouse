package Models;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.intch.FormularioCasa;
import com.example.intch.R;

public class ModCasa extends AppCompatActivity implements View.OnClickListener {

    Button btn_modificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_casa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Modificar Casa");
        btn_modificar = findViewById(R.id.btncambio);
        btn_modificar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btncambio){
            String nombrecasa = ((EditText) findViewById(R.id.nomCasa_mod)).getText().toString();
            SharedPreferences prefs = getSharedPreferences("ModCasa", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("nombrecasa", nombrecasa);
            editor.apply();
            Intent intent = new Intent(this, FormularioCasa.class);
            startActivity(intent);

        }
    }
}