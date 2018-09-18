package com.example.digital.daveloo;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyAndroidFirebaseInstanceIdService extends FirebaseMessagingService  {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("Refreshed token:",token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}
