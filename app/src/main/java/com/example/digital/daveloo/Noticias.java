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

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Noticias extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_noticias, container, false);

        ListView lv = (ListView)rootView.findViewById(R.id.lista1);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://daveloo.000webhostapp.com/notas")
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
                    final ListView listView = (ListView)getView().findViewById(R.id.lista1);

                    final String[] from = new String[] { "noticia_id", "noticia_titular" };
                    final int[] to = new int[] { R.id.noticia_id, R.id.titulo };

                    final ArrayList<HashMap<String, String>> eventos = new ArrayList<HashMap<String, String>>();

                    // pasar del Objeto retorno al arraylist Lista
                    ArrayList<String[]> lista = new ArrayList<String[]>();
                    int i = 0;
                    for (Map<String, Object> x : retorno) {
                        String[] eventoX = {"\""+x.get("noticia_id")+"\"", "\""+x.get("noticia_titular")+"\"" };
                        lista.add(eventoX);
                    }

                    for (String[] evento : lista) {
                        HashMap<String, String> datosEvento = new HashMap<String, String>();
                        datosEvento.put("noticia_id", evento[0]);
                        datosEvento.put("noticia_titular", evento[1]);
                        eventos.add(datosEvento);
                    }


                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {

                            SimpleAdapter listadoAdapter = new SimpleAdapter(getContext(), eventos, R.layout.fragment_noticias_fila, from, to);

                            ListView lv = (ListView)getView().findViewById(R.id.lista1);
                            lv.setAdapter(listadoAdapter);

                        }
                    });


                }
            }
        });


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