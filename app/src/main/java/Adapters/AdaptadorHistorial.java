package Adapters;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intch.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import Models.Adafruit;

public class AdaptadorHistorial extends RecyclerView.Adapter<AdaptadorHistorial.HistorialViewHolder> {
    List<Adafruit> historial;

    public AdaptadorHistorial(List<Adafruit> historial) {
        this.historial = historial;
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historial_item, parent, false);
        return new HistorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        try {
            holder.bindAdafruit(historial.get(position));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return historial.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class HistorialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Adafruit adafruit;
        TextView valor, hora;
        String fechahora;

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            valor = itemView.findViewById(R.id.txt_valorfeed);
            hora = itemView.findViewById(R.id.txt_horafeed);
        }

        public void bindAdafruit(Adafruit adafruit) throws ParseException {
            this.adafruit = adafruit;
           /* valor.setText(adafruit.getValue());
            hora.setText(adafruit.getCreated_at());*/
            SharedPreferences prefs = itemView.getContext().getSharedPreferences("detallesensor", itemView.getContext().MODE_PRIVATE);
            String nombre = prefs.getString("nombre_sensor", "");
            switch (nombre) {
                case "temperatura":
                    valor.setText("Valor: " + adafruit.getValue() + "°C");
                    SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                    parser.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date date = parser.parse(adafruit.getCreated_at());
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    fechahora = formatter.format(date);
                    hora.setText("Fecha: " + fechahora);

                    break;
                case "luminosidad":
                    valor.setText("Valor: "+adafruit.getValue() + "%");
                    SimpleDateFormat parser1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                    parser1.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date date1 = parser1.parse(adafruit.getCreated_at());
                    DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    fechahora = formatter1.format(date1);
                    hora.setText("Fecha: " + fechahora);

                    break;
                case "distancia":
                    valor.setText("Valor: " +adafruit.getValue() + "cm");
                    SimpleDateFormat parser2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                    parser2.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date date2 = parser2.parse(adafruit.getCreated_at());
                    DateFormat formatter2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
                    fechahora = formatter2.format(date2);
                    hora.setText("Fecha: " + fechahora);
                    break;
            }
        }
        @Override
        public void onClick(View view) {

        }
    }
}
