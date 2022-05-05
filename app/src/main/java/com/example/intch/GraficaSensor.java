package com.example.intch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SingletonRequest.SingletonRequest;

public class GraficaSensor extends AppCompatActivity implements OnChartValueSelectedListener {
   /* ArrayList<Entry> x;
    ArrayList<String> y;*/
    ArrayList<Entry> y;
    ArrayList<String> x;
    String url = "http://54.90.119.130/api/historial";
    String urlconcat;
    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_sensor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Grafica del sensor");
       /* x = new ArrayList<Entry>();
        y = new ArrayList<String>();*/
        y = new ArrayList<Entry>();
        x = new ArrayList<String>();
        mChart = (LineChart)findViewById(R.id.line);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.getXAxis().setTextSize(15f);
        mChart.getAxisLeft().setTextSize(15f);
        XAxis xl = mChart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setInverted(true);
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        Graph_List();
    }
    private void Graph_List() {
        SharedPreferences sharedPreferences = getSharedPreferences("detallesensor", Context.MODE_PRIVATE);
        String nombre = sharedPreferences.getString("nombre_sensor", "");
        urlconcat = url + "/" + nombre;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlconcat, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String score = jsonObject.getString("created_at");
                                String date = jsonObject.getString("value");
                                x.add(score);
                                y.add(new Entry(Float.parseFloat(date), i));
                              /*  x.add(new Entry(Integer.parseInt(date), i));
                                y.add(score);*/

                            }
                            SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                            String token = sharedPreferences.getString("token", "");
                           /* LineDataSet set1 = new LineDataSet(x, "Sensor");*/
                            LineDataSet set1 = new LineDataSet(y, "Sensor");
                            set1.setColors(ColorTemplate.COLORFUL_COLORS);
                            set1.setLineWidth(1.5f);
                            set1.setCircleRadius(4f);
                          /*  LineData data = new LineData(y, set1);*/
                            LineData data = new LineData(x, set1);
                            mChart.setData(data);
                            mChart.invalidate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(GraficaSensor.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                HashMap<String, String> headers = new HashMap<String, String>();
                                SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                                String token = sharedPreferences.getString("token", "");
                                headers.put("Authorization", "Bearer " + token);
                                return headers;
                            }
                        };
                        SingletonRequest.getInstance(this).addToRequesQue(stringRequest);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}