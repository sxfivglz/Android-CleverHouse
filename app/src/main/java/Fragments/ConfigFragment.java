package Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.intch.AgregarInv;
import com.example.intch.BorrarInv;
import com.example.intch.ElimCasa;
import com.example.intch.ElimHab;
import com.example.intch.ModHab;
import com.example.intch.MostrarInv;
import com.example.intch.R;
import com.example.intch.Registro;
import com.example.intch.RegistroCasa;
import com.example.intch.RegistroHabitacion;

import Models.ModCasa;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button MCasa,ECasa,MHab,EHab;
    public ConfigFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfigFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=inflater.inflate(R.layout.fragment_config, container, false);
        view.findViewById(R.id.ModCasa).setOnClickListener(this);
        view.findViewById(R.id.ElimCasa).setOnClickListener(this);
        view.findViewById(R.id.ModHab).setOnClickListener(this);
        view.findViewById(R.id.ElimHab).setOnClickListener(this);
        view.findViewById(R.id.agregarcasaconfig).setOnClickListener(this);
        view.findViewById(R.id.agregarhabconfig).setOnClickListener(this);
        view.findViewById(R.id.AgregarInv).setOnClickListener(this);
        view.findViewById(R.id.MostrarInv).setOnClickListener(this);
        view.findViewById(R.id.BorrarInv).setOnClickListener(this);
      return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agregarcasaconfig:
                Intent intent = new Intent(getActivity(), RegistroCasa.class);
                startActivity(intent);
                break;
                case R.id.agregarhabconfig:
                Intent intent2 = new Intent(getActivity(), RegistroHabitacion.class);
                startActivity(intent2);
                break;
            case (R.id.ModCasa):
                Intent intent3 = new Intent(getActivity(), ModCasa.class);
                startActivity(intent3);
                break;
            case (R.id.ElimCasa):
               Intent intent4 = new Intent(getActivity(), ElimCasa.class);
                startActivity(intent4);
                break;
            case (R.id.ModHab):
               Intent intent5 = new Intent(getActivity(), ModHab.class);
                startActivity(intent5);
                break;
            case (R.id.ElimHab):
              Intent  intent6 = new Intent(getActivity(), ElimHab.class);
                startActivity(intent6);
                break;
          /*  case (R.id.AgregarInv):
                Intent intent7 = new Intent(getActivity(), AgregarInv.class);
                startActivity(intent7);
                break;
            case (R.id.MostrarInv):
                Intent intent8 = new Intent(getActivity(), MostrarInv.class);
                startActivity(intent8);
                break;
            case (R.id.BorrarInv):
                Intent intent9 = new Intent(getActivity(), BorrarInv.class);
                startActivity(intent9);
                break;*/
        }
    }
}