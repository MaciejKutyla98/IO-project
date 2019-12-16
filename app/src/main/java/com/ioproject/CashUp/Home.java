package com.ioproject.CashUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Home extends AppCompatActivity {

    private Button wybor;
    private Button nowyDochod;
    private Button nowyWydatek;
    private int flag;
    ListView listViewWydatki;
    ListView listViewDochody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        listViewWydatki = (ListView)findViewById(R.id.listviewWydatki);
        ArrayList<String> wydatki = new ArrayList<>();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,wydatki);
        listViewWydatki.setAdapter(arrayAdapter);
        //tutaj musimy w formie Stringa najlepiej Kategoria + ":    " + 'suma' i dodawać po porstu addem do bazy
        wydatki.add("elo");
        wydatki.add("my");
        wydatki.add("tego");
        wydatki.add("kurwa");
        wydatki.add("nie");
        wydatki.add("zdamy");
        wydatki.add("elo");
        wydatki.add("elo");

        listViewDochody = (ListView)findViewById(R.id.listviewDochody);
        ArrayList<String> dochody = new ArrayList<>();

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dochody);
        listViewDochody.setAdapter(arrayAdapter2);
        dochody.add("elo");
        dochody.add("my");
        dochody.add("tego");
        dochody.add("kurwa");
        dochody.add("nie");
        dochody.add("zdamy");
        dochody.add("elo");
        dochody.add("elo");
        /// tutaj wstawić funkcję która liczy sumę wydatków i dochodów i w poostaci stringa przypisujemy to do podliczenieZBazy
        String podliczenieZBazy = "51,72 zł";
        ((TextView) findViewById(R.id.bilansBazy)).setText(podliczenieZBazy);
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

