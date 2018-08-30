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

public class Telefonos extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_telefono, container, false);

        String[] from = new String[] { "name", "desc" };
        int[] to = new int[] { R.id.contacto, R.id.numero };

        ArrayList<String[]> lista_telf = new ArrayList<String[]>();

        String[] evento1 = {"Policía", "105", "1" };
        String[] evento2 = {"Bomberos", "116", "2" };
        String[] evento3 = {"SAMU", "106", "3" };
        String[] evento4 = {"Cruz Roja", "115", "3" };
        String[] evento5 = {"Defensa Civil", "110", "3" };
        String[] evento6 = {"Emergencia ESSALUD", "117", "3" };

        lista_telf.add(evento1);
        lista_telf.add(evento2);
        lista_telf.add(evento3);
        lista_telf.add(evento4);
        lista_telf.add(evento5);
        lista_telf.add(evento6);

        ArrayList<HashMap<String, String>> eventos = new ArrayList<HashMap<String, String>>();

        for (String[] evento : lista_telf) {
            HashMap<String, String> datosEvento = new HashMap<String, String>();

            datosEvento.put("name", evento[0]);
            datosEvento.put("desc", evento[1]);
            datosEvento.put("id", evento[2]);

            eventos.add(datosEvento);
        }

        SimpleAdapter listadoAdapter = new SimpleAdapter(getContext(), eventos, R.layout.fragment_telefono_fila, from, to);

        ListView lv = (ListView)rootView.findViewById(R.id.lista2);
        lv.setAdapter(listadoAdapter);
        return rootView;
    }
}