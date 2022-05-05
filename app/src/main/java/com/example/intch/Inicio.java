package com.example.intch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Objects;

import Adapters.AdaptadorHabitaciones;
import Fragments.ConfigFragment;
import Fragments.InicioFragment;
import Fragments.PerfilFragment;

public class Inicio extends AppCompatActivity {
    InicioFragment inicio = new InicioFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Objects.requireNonNull(getSupportActionBar()).hide();
        BottomNavigationView nav;
        Toolbar toolbar;
        nav= findViewById(R.id.bottom_nav);
       nav.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);
       loadFragment(inicio);

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener OnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.navigation_inicio:
                    fragment = new InicioFragment();
                    break;
                case R.id.navigation_perfil:
                    fragment = new PerfilFragment();

                    break;
               case R.id.navigation_config:
                    fragment = new ConfigFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}