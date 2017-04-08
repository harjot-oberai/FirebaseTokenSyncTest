package com.sdsmdg.harjot.firebasetokensynctest;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class InstanceIDService extends FirebaseInstanceIdService {

    String TAG = "INSTANCE_ID_SERVICE";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String newToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, newToken);

        syncTokenWithSerevr(newToken);

    }

    private void syncTokenWithSerevr(String token){
        DatabaseReference tokenReference = FirebaseDatabase.getInstance().getReference();
        tokenReference.child("token").setValue(token);
    }
}
