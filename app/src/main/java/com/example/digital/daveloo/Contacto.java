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
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class Contacto extends Fragment{

    private ImageView imageView;
    File fotoFile = null;
    Uri fotoUri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacto, container, false);

        // onclick event for the camera button
        imageView = rootView.findViewById(R.id.imageView2);
        rotar(90);
        Button btnCamara = rootView.findViewById(R.id.camara);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCamera();
            }
        });

        return rootView;
    }

    private void rotar(float degree)
    {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        imageView.startAnimation(rotateAnim);
    }

    public void callCamera(){

        CameraUtil util = new CameraUtil();
        Intent tomarfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (tomarfoto.resolveActivity(getActivity().getPackageManager()) != null){
                try
                {
                    fotoFile = util.createImageFile(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                }
                catch(IOException e)
                {
                    Log.e("upsidupsi",e.getMessage());
                }

                fotoUri = FileProvider.getUriForFile(getContext(),"com.example.android.fileprovider", fotoFile);
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
}