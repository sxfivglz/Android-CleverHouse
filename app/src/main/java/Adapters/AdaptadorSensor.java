package Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intch.DetalleHabitacion;
import com.example.intch.DetalleSensor;
import com.example.intch.R;

import java.util.List;

import Models.Sensor;
import Models.SensorDetalleHab;

public class AdaptadorSensor extends RecyclerView.Adapter<AdaptadorSensor.SensorViewHolder> {
    public List<SensorDetalleHab> sensores;

    public AdaptadorSensor(List<SensorDetalleHab> sensores) {
        this.sensores = sensores;
    }


    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensor_item, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
       holder.bindSensor(sensores.get(position));
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return sensores.size();
    }

    public class SensorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombreSensor;
        SensorDetalleHab sensordetalle;
        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreSensor = itemView.findViewById(R.id.nombre_sensor);
            itemView.setOnClickListener(this);
        }

        public void bindSensor(SensorDetalleHab sensordetalle) {
            this.sensordetalle = sensordetalle;
            nombreSensor.setText(sensordetalle.getNombre_sensor().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Sensor: " + sensordetalle.getNombre_sensor(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext().getApplicationContext(), DetalleSensor.class);
            intent.putExtra("sensordetalle", sensordetalle.getNombre_sensor());
            intent.putExtra("id_sensor", sensordetalle.getId());
            view.getContext().startActivity(intent);
            SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("detallesensor", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombre_sensor", sensordetalle.getNombre_sensor());
            editor.apply();
           /* Intent intent = new Intent(v.getContext(), DetalleHabitacion.class);
            intent.putExtra("habitacion", habitacion.getNombre_habitacion());
            intent.putExtra("idhabitacion", habitacion.getId());
            v.getContext().startActivity(intent);
            SharedPreferences prefs = v.getContext().getSharedPreferences("Detallehab", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("habitacion", habitacion.getNombre_habitacion());
            editor.putInt("idhabitacion", habitacion.getId());
            editor.apply();*/
        }
    }
}