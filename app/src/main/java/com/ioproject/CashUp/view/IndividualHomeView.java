package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.server_connection.Repository;
import com.ioproject.CashUp.data.model.FromJSONToString;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class IndividualHomeView extends AppCompatActivity {

    private Button choice;
    private Button newIncome;
    private Button newOutcome;
    private int flag;
    private String userId;
    private String username;
    ListView listViewOutgo;
    ListView listViewIncome;
    private ArrayList<String> outgo = new ArrayList<>();
    private ArrayList<String> income = new ArrayList<>();
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");
        ((TextView) findViewById(R.id.nazwaUzytkownika)).setText(username);
        listViewOutgo = (ListView)findViewById(R.id.listviewWydatki);
        String summary = null;
        try {
            FromJSONToString fromJSONToString = new FromJSONToString(Repository.showAllTransactions(userId));
            income = fromJSONToString.fromJSONTOStringIncome();
            outgo = fromJSONToString.fromJSONTOStringOutgo();
            summary = fromJSONToString.fromJSONBalanceSheet() + ".00";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
        e.printStackTrace();
    }
        
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, outgo);
        listViewOutgo.setAdapter(arrayAdapter);
        listViewIncome = (ListView)findViewById(R.id.listviewDochody);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, income);
        listViewIncome.setAdapter(arrayAdapter2);
        ((TextView) findViewById(R.id.bilansBazy)).setText(summary);

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
                newOutgo(view);
            }
        });
        newIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIncome(view);
            }
        });
    }

    public void newOutgo(View view) {
        Intent intent_newBill = new Intent(this, NewOutgoView.class);
        intent_newBill.putExtra("nazwaUzytkownika", username);
        intent_newBill.putExtra("idUzytkownika", userId);
        startActivity(intent_newBill);
    }

    public void newIncome(View view) {
        Intent intent_newIncome = new Intent(this, NewIncomeView.class);
        intent_newIncome.putExtra("nazwaUzytkownika", username);
        intent_newIncome.putExtra("idUzytkownika", userId);
        startActivity(intent_newIncome);
    }
    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=touchevent.getX();
                y1=touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=touchevent.getX();
                y2=touchevent.getY();
                if(x2 < x1){
                    Intent i = new Intent(this, GroupHome.class);
                    i.putExtra("nazwaUzytkownika", username);
                    i.putExtra("idUzytkownika", userId);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

}