package com.sdsmdg.harjot.firebasetokensynctest;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIDService extends FirebaseInstanceIdService {

    String TAG = "INSTANCE_ID_SERVICE";

    /*
     * This method is called whenever the token is refreshed. This can take place in 3 scenarios.
     *  - The Token is explicitly deleted by the app
     *  - The app is restored on a new device
     *  - App is reinstalled.
     *  - The app data is cleared.
     *  In an of the above case the token needs to be synced with the server.
     */

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String newToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, newToken);

        syncTokenWithServer(newToken);

    }

    /*
     *   Method for synchronising the token with the server.
     *   Since there is no app-server, the Firebase Realtime database is used instead.
     */

    private void syncTokenWithServer(String token){
        DatabaseReference tokenReference = FirebaseDatabase.getInstance().getReference();
        tokenReference.child("token").setValue(token);
    }
}
