package Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.intch.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.AdaptadorCasas;
import Adapters.AdaptadorHabitacionesGet;
import Models.AdafruitPuerta;
import Models.CasasDueno;
import Models.Habitacion;
import Models.ResInsertarArray;
import SingletonRequest.SingletonRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    Spinner spinner;
    Button abrir, cerrar;
     String url = "http://54.90.119.130/api/det_hab/habitacionesCasa";
   /* String url = "http://54.90.119.130/api/hab/listar";*/
    String url2 = "http://54.90.119.130/api/casas/casasDueno";
    String urlabrir = "http://54.90.119.130/api/Abrir/acceso";
    String urlcerrar = "http://54.90.119.130/api/Cerrar/acceso";
String nomhab;
    TextView textodepuerta;
    String stringcasa;
    int idcasa;

    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        abrir = view.findViewById(R.id.btnabrir);
        cerrar = view.findViewById(R.id.btncerrar);
        textodepuerta = view.findViewById(R.id.txtpuertastatus);
        abrir.setOnClickListener(this);
        cerrar.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerhab);
        spinner = view.findViewById(R.id.spinner);
        ListarCasas();


        return view;
    }

    public void ListarCasas() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("correo", "");
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put(new JSONObject().put("email", email));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url2, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                String json = response.toString();
                Type listType = new TypeToken<List<CasasDueno>>() {
                }.getType();
                List<CasasDueno> casas = gson.fromJson(json, listType);
                AdaptadorCasas adaptadorCasas = new AdaptadorCasas(getActivity(), casas);
                adaptadorCasas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adaptadorCasas);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         nomhab = casas.get(i).getNombre_casa();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("casaselec", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nombre", nomhab);
                        Toast.makeText(getActivity(), nomhab, Toast.LENGTH_SHORT).show();
                        ListarHabitacion();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        SingletonRequest.getInstance(getContext()).addToRequesQue(jsonArrayRequest);
    }


    public void ListarHabitacion(){

       JSONObject jsonObject = new JSONObject();
       try {
           jsonObject.put("nombre_casa", nomhab);
       }catch (JSONException e) {
           e.printStackTrace();
       }
        JSONArray body = new JSONArray();
            body.put(jsonObject);
            JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.POST, url, body, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Gson gson = new Gson();
                    String json = response.toString();
                    Type listType = new TypeToken<List<Habitacion>>() {
                    }.getType();
                    List<Habitacion> habitaciones = gson.fromJson(json, listType);
                    AdaptadorHabitacionesGet adaptadorHabitaciones = new AdaptadorHabitacionesGet(habitaciones);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adaptadorHabitaciones);
                    recyclerView.setHasFixedSize(true);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", "");
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            };
            SingletonRequest.getInstance(getContext()).addToRequesQue(jsonArrayRequest2);
        }


    /*----------------------------------------------------------------------------------------------------------------------*/
/*
    public void ListarHabitacion() {
        String nomhab = spinner.getSelectedItem().toString();
        try {
            JSONObject ola = new JSONObject();
            ola.put();
            JsonObjectRequest jsonArrayRequest2 = new JsonObjectRequest(Request.Method.POST, url, ola, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    String json = response.toString();
                    Type listType = new TypeToken<List<HabitacionesCasa>>() {
                    }.getType();
                    List<HabitacionesCasa> habitaciones = gson.fromJson(json, listType);
                    AdaptadorHabitaciones adaptadorHabitaciones = new AdaptadorHabitaciones(habitaciones);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adaptadorHabitaciones);
                    recyclerView.setHasFixedSize(true);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                    String token = sharedPreferences.getString("token", "");
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            };
            SingletonRequest.getInstance(getContext()).addToRequesQue(jsonArrayRequest2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
/*----------------------------------------------------------------------------------------------------------------*/
  /*  public void ListarHabitacion() {
        String nomcasa = spinner.getSelectedItem().toString();
        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                String json = response.toString();
                Type listType = new TypeToken<List<Habitacion>>() {
                }.getType();
                List<Habitacion> habitaciones = gson.fromJson(json, listType);
                AdaptadorHabitacionesGet adaptadorHabitaciones = new AdaptadorHabitacionesGet(habitaciones);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adaptadorHabitaciones);
                recyclerView.setHasFixedSize(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        SingletonRequest.getInstance(getContext()).addToRequesQue(jsonArrayRequest2);
    }
*/
    /*----------------------------------------------------------------------------------------------------------------*/
    /*BOTONEEEEEES*/
        @Override
        public void onClick(View view) {
                if (view.getId() == R.id.btnabrir) {
                    JsonObjectRequest jsonBody1 = new JsonObjectRequest(Request.Method.POST, urlabrir, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Gson gson = new Gson();
                                AdafruitPuerta sensor = gson.fromJson(response.toString(), AdafruitPuerta.class);
                                String valor = sensor.getValue();
                                if (valor.equals("1")) {
                                   textodepuerta.setText("Abierta");
                                }
                                else {
                                    textodepuerta.setText("Cerrada");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                            String token = sharedPreferences.getString("token", "");
                            headers.put("Authorization", "Bearer " + token);
                            return headers;
                        }

                    };
                    SingletonRequest.getInstance(getContext()).addToRequesQue(jsonBody1);
                }  else if (view.getId() == R.id.btncerrar){
                    JsonObjectRequest jsonBody2 = new JsonObjectRequest(Request.Method.POST, urlcerrar, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Gson gson = new Gson();
                                AdafruitPuerta sensor = gson.fromJson(response.toString(), AdafruitPuerta.class);
                                String valor = sensor.getValue();
                                if (valor.equals("0")) {
                                    textodepuerta.setText("Cerrada");
                                }
                                else {
                                    textodepuerta.setText("Abierta");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
                            String token = sharedPreferences.getString("token", "");
                            headers.put("Authorization", "Bearer " + token);
                            return headers;
                        }

                    };
                    SingletonRequest.getInstance(getContext()).addToRequesQue(jsonBody2);

            }
        }

}