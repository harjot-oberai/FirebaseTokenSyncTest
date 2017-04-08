package com.sdsmdg.harjot.firebasetokensynctest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    TextView tokenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenText = (TextView) findViewById(R.id.token);

        //Getting the current token. Will return blank if the token needs to be refreshed.
        String token  = FirebaseInstanceId.getInstance().getToken();

        tokenText.setText(token);

    }
}
