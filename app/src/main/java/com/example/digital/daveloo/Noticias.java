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

        String[] from = new String[] { "time", "name", "desc" };
        int[] to = new int[] { R.id.hora, R.id.nombre, R.id.desc1 };

        ArrayList<String[]> lista = new ArrayList<String[]>();

        String[] evento1 = { "11:30", "Reunión de coordinación", "Lugar: Auditorio", "1" };
        String[] evento2 = { "12:00", "Almuerzo", "Lugar: Comedor central", "2" };
        String[] evento3 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento4 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento5 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento6 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento7 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento8 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento9 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };
        String[] evento10 = { "14:00", "Tiro al dardo", "Lugar: Oficina", "3" };

        lista.add(evento1);
        lista.add(evento2);
        lista.add(evento3);
        lista.add(evento4);
        lista.add(evento5);
        lista.add(evento6);
        lista.add(evento7);
        lista.add(evento8);
        lista.add(evento9);
        lista.add(evento10);

        ArrayList<HashMap<String, String>> eventos = new ArrayList<HashMap<String, String>>();

        for (String[] evento : lista) {
            HashMap<String, String> datosEvento = new HashMap<String, String>();

            datosEvento.put("time", evento[0]);
            datosEvento.put("name", evento[1]);
            datosEvento.put("desc", evento[2]);
            datosEvento.put("id", evento[3]);

            eventos.add(datosEvento);
        }

        SimpleAdapter listadoAdapter = new SimpleAdapter(getContext(), eventos, R.layout.fragment_noticias_fila, from, to);

        ListView lv = (ListView)rootView.findViewById(R.id.lista1);
        lv.setAdapter(listadoAdapter);

        // Inflate the layout for this fragment
        return rootView;

    }
}