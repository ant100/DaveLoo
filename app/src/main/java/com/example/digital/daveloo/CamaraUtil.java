package com.example.digital.daveloo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


class CameraUtil {

    public File createImageFile(File storageDir) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "Foto_" + timeStamp + "_";
        File image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        );
        return image;
    }
}