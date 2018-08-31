package com.example.digital.daveloo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    ActionBar actionBar;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c281e2")));

        loginBtn = (Button) findViewById(R.id.btn_signup);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //TODO: login validation
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        setContentView(R.layout.activity_main);
    }
}
