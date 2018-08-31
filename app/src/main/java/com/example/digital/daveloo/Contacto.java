package com.example.digital.daveloo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class Contacto extends Fragment {
    private Spinner spinner;
    private Button btnEnviar;
    private Button camara;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_contacto, container, false);
    }

    public void addListenerOnButton() {

        spinner = getView().findViewById(R.id.spinner);
        camara = getView().findViewById(R.id.camara);
        btnEnviar = getView().findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCamara = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(abrirCamara);
            }
        });

    }
}