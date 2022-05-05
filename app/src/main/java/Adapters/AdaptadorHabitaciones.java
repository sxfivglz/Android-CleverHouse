package Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intch.DetalleHabitacion;
import com.example.intch.R;

import Fragments.InicioFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Models.Habitacion;
import Models.HabitacionesCasa;

public class AdaptadorHabitaciones extends RecyclerView.Adapter<AdaptadorHabitaciones.HabitacionViewHolder> {
    List<HabitacionesCasa> habitaciones;

    public AdaptadorHabitaciones(List<HabitacionesCasa> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @NonNull
    @Override
    public HabitacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habitacion_item, parent, false);
        return new HabitacionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitacionViewHolder holder, int position) {
        holder.bindHabitacion(habitaciones.get(position));
    }
    @Override
    public long getItemId(int position) {
        return habitaciones.get(position).getId();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return habitaciones.size();
    }

    public class HabitacionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        HabitacionesCasa habitacion;
        TextView nomHabitacion;
        Intent intent;

        public HabitacionViewHolder( View itemView) {
            super(itemView);
            nomHabitacion = itemView.findViewById(R.id.txt_habitacion_item_nombre);
            itemView.setOnClickListener(this);
        }

        public void bindHabitacion(HabitacionesCasa habitacion) {
            this.habitacion = habitacion;
            nomHabitacion.setText(habitacion.getNombre_habitacion().toString());
        }

        @Override
        public void onClick(View v) {
            /*Toast.makeText(v.getContext(), "Habitacion: " + habitacion.getNombre_habitacion(), Toast.LENGTH_SHORT).show();*/
            Intent intent = new Intent(v.getContext(), DetalleHabitacion.class);
            intent.putExtra("habitacion", habitacion.getNombre_habitacion());
            intent.putExtra("idhabitacion", habitacion.getId());
            v.getContext().startActivity(intent);
            SharedPreferences prefs = v.getContext().getSharedPreferences("Detallehab", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("habitacion", habitacion.getNombre_habitacion());
            editor.putInt("idhabitacion", habitacion.getId());
            editor.apply();

        }
    }
}
