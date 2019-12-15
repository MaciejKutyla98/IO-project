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
    private int flag;

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
        flag = 0;
        wybor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0) {
                    nowyDochod.setVisibility(v.VISIBLE);
                    nowyWydatek.setVisibility(v.VISIBLE);
                    flag +=1;
                }
                else if(flag == 1){
                    nowyDochod.setVisibility(v.INVISIBLE);
                    nowyWydatek.setVisibility(v.INVISIBLE);
                    flag = 0;
                }
            }
        });

        nowyWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBill(view);
            }
        });

        nowyDochod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIncome(view);
            }
        });
    }


    public void newBill(View view) {
        Intent intent_newBill = new Intent(this, nowyWydatek.class);
        startActivity(intent_newBill);
    }

    public void newIncome(View view) {
        Intent intent_newIncome = new Intent(this, nowyDochod.class);
        startActivity(intent_newIncome);
    }
}

