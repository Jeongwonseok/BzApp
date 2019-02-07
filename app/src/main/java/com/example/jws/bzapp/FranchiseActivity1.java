package com.example.jws.bzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class FranchiseActivity1 extends AppCompatActivity {
    ImageButton btnBack,btnFast,btnleisure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchise1);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FranchiseActivity1.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnFast = (ImageButton) findViewById(R.id.btnfast);
        btnFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FranchiseActivity1.this, Franchisefastfood.class);
                startActivity(intent);
            }
        });
        btnleisure=(ImageButton)findViewById(R.id.btnleisure);
        btnleisure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(FranchiseActivity1,FranchisePlay.class);
//                startActivity(intent);
            }
        });
    }
}
