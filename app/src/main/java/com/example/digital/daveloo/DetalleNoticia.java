package com.example.digital.daveloo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetalleNoticia extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_detalle_noticia);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        // get the id
        Bundle b = getIntent().getExtras();
        String value = "";
        if (b != null){
            value = b.getString("noticia_id");
        }
        int id = Integer.parseInt(value.replaceAll("\"",""));
        String url = "https://daveloo.000webhostapp.com/notas/"+id+"?format=json";
        // call the service
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
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
                    String cadenaJson = response.body().string();

                    Gson gson = new Gson();
                    Type stringStringMap = new TypeToken<Object>() { }.getType();

                    final Map<String, Object> retorno = gson.fromJson(cadenaJson, stringStringMap);
                    final TextView titular = (TextView) findViewById(R.id.titular);
                    final TextView contenido = (TextView) findViewById(R.id.contenido);
                    final ImageView imagen = (ImageView) findViewById(R.id.imagen);

                    runOnUiThread(new Runnable() {
                        public void run() {
                            titular.setText(retorno.get("noticia_titular").toString());
                            contenido.setText(Html.fromHtml(retorno.get("noticia_contenido").toString()));
                            new DownloadImageTask(imagen)
                                    .execute("https://daveloo.000webhostapp.com/imagen/portrait/"+retorno.get("noticia_imagen").toString());

                        }
                    });

               }
            }
         });


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

