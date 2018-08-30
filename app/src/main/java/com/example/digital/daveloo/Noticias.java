package com.example.digital.daveloo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Noticias extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_noticias, container, false);

        String[] from = new String[] { "titulo" };
        int[] to = new int[] { R.id.titulo };

        ArrayList<String[]> lista = new ArrayList<String[]>();

        String[] evento1 = {"Transferencia de gestión administrativa", "1" };
        String[] evento2 = {"A comer sano en Miraflores", "2" };
        String[] evento3 = {"Sistema de bicicleta pública", "3" };
        String[] evento4 = {"Subasta pública de siete módulos comerciales", "3" };
        String[] evento5 = {"Contribuyentes no habidos", "3" };
        String[] evento6 = {"Asignación de ubicaciones - propaganda electoral para elecciones municipales", "3" };
        String[] evento7 = {"Fraccionamiento virtual - web", "3" };
        String[] evento8 = {"Actualización de datos del vecino", "3" };


        lista.add(evento1);
        lista.add(evento2);
        lista.add(evento3);
        lista.add(evento4);
        lista.add(evento5);
        lista.add(evento6);
        lista.add(evento7);
        lista.add(evento8);

        ArrayList<HashMap<String, String>> eventos = new ArrayList<HashMap<String, String>>();

        for (String[] evento : lista) {
            HashMap<String, String> datosEvento = new HashMap<String, String>();

            datosEvento.put("titulo", evento[0]);
            datosEvento.put("id", evento[1]);

            eventos.add(datosEvento);
        }

        SimpleAdapter listadoAdapter = new SimpleAdapter(getContext(), eventos, R.layout.fragment_noticias_fila, from, to);

        ListView lv = (ListView)rootView.findViewById(R.id.lista1);
        lv.setAdapter(listadoAdapter);

        // Inflate the layout for this fragment
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}