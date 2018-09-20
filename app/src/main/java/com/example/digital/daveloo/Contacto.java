package com.example.digital.daveloo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Contacto extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacto, container, false);

        // onclick event for the camera button
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
                startActivity(abrirCamara);

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

}