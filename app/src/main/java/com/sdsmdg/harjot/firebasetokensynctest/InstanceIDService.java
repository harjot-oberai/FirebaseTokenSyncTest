package com.sdsmdg.harjot.firebasetokensynctest;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
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
     *
     *   The intent is created to send the newToken to the MainActivity to update the toeknText via the BroadcastReceiver.
     */

    private void syncTokenWithServer(String newToken) {

        Intent intent = new Intent();
        intent.setAction(getString(R.string.token_intent_filter));
        intent.putExtra(getString(R.string.token_intent_extra_key), newToken);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

        DatabaseReference tokenReference = FirebaseDatabase.getInstance().getReference();
        tokenReference.child("token").setValue(newToken);
    }
}
