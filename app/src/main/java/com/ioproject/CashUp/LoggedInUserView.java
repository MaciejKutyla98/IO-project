package com.ioproject.CashUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoggedInUserView extends AppCompatActivity {

    private Button choice;
    private Button newIncome;
    private Button newOutcome;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        String username = getIntent().getStringExtra("nazwaUzytkownika");
        ((TextView) findViewById(R.id.nazwaUzytkownika)).setText(username);
        choice = (Button) findViewById(R.id.wybor);
        newOutcome = (Button) findViewById(R.id.nowyWydatek);
        newIncome = (Button) findViewById(R.id.nowyDochod);
        flag = 0;
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0) {
                    newIncome.setVisibility(v.VISIBLE);
                    newOutcome.setVisibility(v.VISIBLE);
                    flag +=1;
                }
                else if(flag == 1){
                    newIncome.setVisibility(v.INVISIBLE);
                    newOutcome.setVisibility(v.INVISIBLE);
                    flag = 0;
                }
            }
        });

        newOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBill(view);
            }
        });

        newIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIncome(view);
            }
        });
    }

    public void newBill(View view) {
        Intent intent_newBill = new Intent(this, NewOutgo.class);
        startActivity(intent_newBill);
    }

    public void newIncome(View view) {
        Intent intent_newIncome = new Intent(this, NewIncome.class);
        startActivity(intent_newIncome);
    }
}

