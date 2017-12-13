package com.ait.android.chess.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ait.android.chess.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button TwoPlayerLocal;
    private Button TwoPlayerRemote;
    private Button OnePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TwoPlayerLocal = (Button) findViewById(R.id.twoPlayLocBtn);
        TwoPlayerLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetails = new Intent();
                intentDetails.setClass(WelcomeActivity.this,
                        TwoPlayerLocalActivity.class);
                startActivity(intentDetails);
            }
        });

        TwoPlayerRemote = (Button) findViewById(R.id.twoPlayRemBtn);
        TwoPlayerRemote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetails = new Intent();
                intentDetails.setClass(WelcomeActivity.this,
                        LoginActivity.class);
                startActivity(intentDetails);
            }
        });

    }
}
