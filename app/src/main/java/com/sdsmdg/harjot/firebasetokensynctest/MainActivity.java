package com.sdsmdg.harjot.firebasetokensynctest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    TextView tokenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenText = (TextView) findViewById(R.id.token);

        // Getting the current token. Will return blank if the token needs to be refreshed
        // or token cannot be generated due to connection issues and is not cached.
        String token = FirebaseInstanceId.getInstance().getToken();

        tokenText.setText(token);
    }

    /*
     * Broadcast receiver to update tokenText whenever the token is refreshed and synchronization is started.
     */

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Synchronization started", Toast.LENGTH_SHORT).show();

            String newToken = intent.getStringExtra(getString(R.string.token_intent_extra_key));
            tokenText.setText(newToken);

        }
    };

    /*
     * Registering the receiver with the LocalBroadcastManger to send data between InstaceIDService and MainActivity.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter(getString(R.string.token_intent_filter)));
    }
}
