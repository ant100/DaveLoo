package com.example.digital.daveloo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Noticias extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_noticias, container, false);

        String[] from = new String[] { "titulo", "id" };
        int[] to = new int[] { R.id.titulo, R.id.noticia_id };

        ArrayList<String[]> lista = new ArrayList<String[]>();

        String[] evento1 = {"Transferencia de gestión administrativa", "1" };
        String[] evento2 = {"A comer sano en Miraflores", "2" };
        String[] evento3 = {"Sistema de bicicleta pública", "3" };
        String[] evento4 = {"Subasta pública de siete módulos comerciales", "3" };
        String[] evento5 = {"Contribuyentes no habidos", "3" };

        lista.add(evento1);
        lista.add(evento2);
        lista.add(evento3);
        lista.add(evento4);
        lista.add(evento5);

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.noticia_id);
                String noticia_id = tv.getText().toString();

                Intent i = new Intent(getActivity(), DetalleNoticia.class);

                // send the id to the activity
                Bundle b = new Bundle();
                b.putString("noticia_id", noticia_id);
                i.putExtras(b);

                // start the activity
                getActivity().startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}