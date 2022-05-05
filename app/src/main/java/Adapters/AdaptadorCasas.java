package Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intch.R;

import java.util.List;

import Models.CasasDueno;
import Models.ResCasa;

public class AdaptadorCasas  extends ArrayAdapter<CasasDueno> {
    List<CasasDueno> casas;
    Context context;
    LayoutInflater inflater;

    public AdaptadorCasas(Context context, List<CasasDueno> casas) {
        super(context, R.layout.casas_item, casas);
        this.casas = casas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.simple_spinner_dropdown_item, parent, false);
        TextView label = row.findViewById(R.id.item_nom_casa_dropdown);
         label.setText(casas.get(position).getNombre_casa());
        return row;
    }
}

    /*
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.simple_spinner_dropdown_item, null, true);
        CasasDueno casa = casas.get(position);
        TextView textView = view.findViewById(R.id.item_nom_casa);
        textView.setText(casa.getNombre_casa());

        return view;
    }
    public long getItemId(int position) {

        return casas.get(position).getId();
    }
    @Override
    public int getCount() {
        return casas.size();
    }
   public List<CasasDueno> getList(){
        return casas;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        v = inflater.inflate(R.layout.casas_item, null, true);
        CasasDueno casa = casas.get(position);
        TextView textView = convertView.findViewById(R.id.item_nom_casa);
        textView.setText(casa.getNombre_casa());
        return v;
    }

}*/
