package com.example.digital.daveloo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Lugares extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.04592, -77.030565))
                .title("Centro de Lima")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.044956, -77.029831))
                .title("Palacio de Gobierno"));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.046661, -77.029544))
                .title("Catedral"));

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
