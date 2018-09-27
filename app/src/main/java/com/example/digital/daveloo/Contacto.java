package com.example.digital.daveloo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

public class Contacto extends Fragment {

    private ImageView imageView;
    File fotoFile = null;
    Uri fotoUri;
    private DbHelper _dbHelper;
    String tipoSeleccionado;
    String texto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacto, container, false);

        // onclick event for the camera button
        imageView = rootView.findViewById(R.id.imageView2);
        rotar(90);
        Spinner tipoSpinner = rootView.findViewById(R.id.spinner);
        tipoSeleccionado = tipoSpinner.getSelectedItem().toString();
        final EditText textoView = rootView.findViewById(R.id.texto);
        Button btnCamara = rootView.findViewById(R.id.camara);
        Button btnEnviar = rootView.findViewById(R.id.btnEnviar);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto = textoView.getText().toString();
                enviarJson();
            }
        });

        return rootView;
    }

    private void rotar(float degree) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        imageView.startAnimation(rotateAnim);
    }

    public void callCamera() {

        CameraUtil util = new CameraUtil();
        Intent tomarfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (tomarfoto.resolveActivity(getActivity().getPackageManager()) != null) {
            try {
                fotoFile = util.createImageFile(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            } catch (IOException e) {
                Log.e("upsidupsi", e.getMessage());
            }

            fotoUri = FileProvider.getUriForFile(getContext(), "com.example.android.fileprovider", fotoFile);
            tomarfoto.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(tomarfoto, 786);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 786) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(fotoUri);
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    /*public String getStringFile(File f) {
        InputStream inputStream = null;
        String encodedFile= "", lastVal;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());

            byte[] buffer = new byte[10240];//specify the size to allow
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }
            output64.close();
            encodedFile =  output.toString();
        }
        catch (FileNotFoundException e1 ) {
            e1.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        lastVal = encodedFile;
        return lastVal;
    }*/

    public void enviarJson(){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this.getActivity());
        int usuario_id = sharedPreferences.getInt("usuario_id", -1);
        Bitmap bitmap = BitmapFactory.decodeFile(fotoFile.getAbsolutePath());
        String image = getStringImage(bitmap);
            //String image = getStringFile(fotoFile);
            /*List<Formulario> list = new ArrayList<Formulario>();
            list.add(new Formulario(usuario_id, tipoSeleccionado, texto, image));
            Gson gson = new Gson();
            Type type = new TypeToken<List<Formulario>>() {
            }.getType();
            String json = gson.toJson(list, type);*/
            Gson gson = new Gson();
            String json = gson.toJson(new Formulario(usuario_id, tipoSeleccionado, texto, image));
            //System.out.println(json);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
            try {
                post("http://daveloo.000webhostapp.com/form/get_post", json);
                Toast toast = Toast.makeText(this.getActivity().getApplicationContext(), "Se envió correctamente", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //List<Formulario> fromJson = gson.fromJson(json, type);
            //System.out.println(fromJson);
            /*for (Formulario formulario : fromJson) {
                //System.out.println(formulario);
                //System.out.println(json);
                Log.i("==========>", "hola");
                try {
                    post("http://daveloo.000webhostapp.com/form/get_post", formulario.toJsonString());
                    Toast toast = Toast.makeText(this.getActivity().getApplicationContext(), "Se envió correctamente", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }*/
        }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        System.out.println(body.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    }




/*
    public static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Fotos");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
*/