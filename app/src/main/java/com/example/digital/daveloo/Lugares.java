package com.example.digital.daveloo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Lugares extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    GoogleMap gmap;
    JSONArray jarraysote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://daveloo.000webhostapp.com/mapa?format=json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // conseguir la cadena json
                    String cadenaJson = response.body().string();
                    Log.i("====>", cadenaJson);

                    Gson gson = new Gson();
                    Type stringStringMap = new TypeToken<ArrayList<Map<String, Object>>>() { }.getType();

                    final ArrayList<Map<String, Object>> retorno = gson.fromJson(cadenaJson, stringStringMap);

                    Log.i("====>", retorno.toString());

                    try{
                        jarraysote = new JSONArray(retorno.toArray());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nombre();
                            }
                        });

                    }
                    catch(JSONException e){
                        Log.i("====>", "upsidupsi");
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    public void nombre(){
        try {
            Log.i("====>", String.valueOf(jarraysote.get(0)));
        for (int i = 0; i < jarraysote.length(); i++) {
            JSONObject jobinterview = jarraysote.getJSONObject(i);
            Log.i("====>", jobinterview.getString("mapa_nombre"));
            gmap.addMarker(new MarkerOptions()
                    .position(new LatLng(jobinterview.getDouble("mapa_latitud"), jobinterview.getDouble("mapa_longitud")))
                    .title(jobinterview.getString("mapa_nombre")));
        }
        }
        catch(JSONException e){
            Log.i("====>", "upsidupsi2");
            e.printStackTrace();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);
        gmap = googleMap;

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.04592, -77.030565), 15));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.inicio) {

            Intent intent = new Intent(this,MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.redes) {
            Intent intent = new Intent(this,Redes.class);
            this.startActivity(intent);
            return true;
        }

        if (id == R.id.lugares) {
            Intent intent = new Intent(this,Lugares.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
