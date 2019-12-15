package com.ioproject.CashUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Home extends AppCompatActivity {

    private Button wybor;
    private Button nowyDochod;
    private Button nowyWydatek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        String username = getIntent().getStringExtra("nazwaUzytkownika");
        ((TextView) findViewById(R.id.nazwaUzytkownika)).setText(username);
        wybor = (Button) findViewById(R.id.wybor);
        nowyWydatek = (Button) findViewById(R.id.nowyWydatek);
        nowyDochod = (Button) findViewById(R.id.nowyDochod);
        wybor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowyDochod.setVisibility(v.VISIBLE);
                nowyWydatek.setVisibility(v.VISIBLE);
            }
        });
    }


    public void newBill(View view) {
        Intent intent_newBill = new Intent(this, nowyWydatek.class);
        startActivity(intent_newBill);
    }
}

