package com.example.digital.daveloo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class Contacto extends Fragment{

    private ImageView imageView;
    private Uri file;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacto, container, false);

        // onclick event for the camera button
        imageView = rootView.findViewById(R.id.imageView2);
        Button btnCamara = rootView.findViewById(R.id.camara);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });

        return rootView;
    }


    public void callCamera(){
        try
        {
            if(Build.VERSION.SDK_INT > 22)
            {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions( new String[]{Manifest.permission.CAMERA}, 101);
                    return;
                }

                Intent abrirCamara = new Intent("android.media.action.IMAGE_CAPTURE");
                //file = Uri.fromFile(getOutputMediaFile());
                //abrirCamara.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(abrirCamara,786);
                Log.i("cam", "what?");

            }
            else {
                Intent abrirCamara = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(abrirCamara);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 786) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            }
        }
    }

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

}