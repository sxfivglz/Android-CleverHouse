package Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.intch.Login;
import com.example.intch.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import Models.ResPerfil;
import SingletonRequest.SingletonRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        Button btncerrarsesion, btncambiartoken;
        btncerrarsesion = view.findViewById(R.id.btncerrarsesion);
        btncambiartoken = view.findViewById(R.id.btncambiartoken);
        btncerrarsesion.setOnClickListener(this);
        btncambiartoken.setOnClickListener(this);
        TextView nombret, apellidot, correot, telefonot;
        nombret = view.findViewById(R.id.nombreperfil);
        apellidot = view.findViewById(R.id.apellidoperfil);
        correot = view.findViewById(R.id.correoperfil);
        telefonot = view.findViewById(R.id.telefonoperfil);
        String url = "http://54.90.119.130/api/user/get-user";
        JSONObject jsonBody = new JSONObject();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", getActivity().MODE_PRIVATE);
        String token = prefs.getString("token", "");
        try {
            jsonBody.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                ResPerfil res;
                res = gson.fromJson(response.toString(), ResPerfil.class);
                String id = res.getUser().getId();
                String nombre = res.getUser().getNombre_usuario();
                String apellido = res.getUser().getApellido_usuario();
                String correo = res.getUser().getEmail();
                String telefono = res.getUser().getTelefono();
                nombret.setText(nombre);
                apellidot.setText(apellido);
                correot.setText(correo);
                telefonot.setText(telefono);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        SingletonRequest.getInstance(getContext()).addToRequesQue(jsonObjectRequest);
        return view;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncerrarsesion:
                String url2 = "http://54.90.119.130/api/user/logout";
                JSONObject jsonBody = new JSONObject();
                SharedPreferences prefs = getActivity().getSharedPreferences("token", getActivity().MODE_PRIVATE);
                String token = prefs.getString("token", "");
                try {
                    jsonBody.put("token", token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url2, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(), "Sesión cerrada correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), Login.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                SingletonRequest.getInstance(getContext()).addToRequesQue(jsonObjectRequest);
                break;
            case R.id.btncambiartoken:
                break;

        }
    }

    public void ArrayHabs(){

    }
}
