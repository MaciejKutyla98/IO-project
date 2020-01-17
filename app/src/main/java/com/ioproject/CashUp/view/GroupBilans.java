package com.ioproject.CashUp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ioproject.CashUp.NewGroupIncome;
import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.FromJSONToString;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GroupBilans extends AppCompatActivity {

    private Button NewGroupIncome;
    ListView listViewOutgo;
    ListView listViewIncome;
    private ArrayList<String> outgo = new ArrayList<>();
    private ArrayList<String> income = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_bilans);


        listViewOutgo = (ListView) findViewById(R.id.listviewWydatkiGrupy);
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
        listViewIncome = (ListView) findViewById(R.id.listviewDochodyGrupy);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, income);
        listViewIncome.setAdapter(arrayAdapter2);
        ((TextView) findViewById(R.id.bilansBazy)).setText(summary);

        NewGroupIncome = (Button) findViewById(R.id.wyborGrupy);
        NewGroupIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGroupIncome(view);
            }
            });
    }

    public void newGroupIncome(View view) {
        Intent intent_newGroupIncomee = new Intent(this, com.ioproject.CashUp.NewGroupIncome.class);
        startActivity(intent_newGroupIncomee);
    }

}