package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.FromJSONToString;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class responsible for groups balance
 */
public class GroupBilans extends AppCompatActivity {

    private String userId;
    private String username;
    private String groupId;
    private Button NewGroupIncome;
    ListView listViewOutgo;
    ListView listViewIncome;
    private ArrayList<String> balance = new ArrayList<>();
    private ArrayList<String> history = new ArrayList<>();

    /**
     * This method is responsible for user contact mechanism
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_bilans);

        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");
        groupId = getIntent().getStringExtra("grupa");

        listViewOutgo = (ListView) findViewById(R.id.listviewWydatkiGrupy);
        try {
            String result = Repository.showUserHistory(username, groupId);
            String result2 = Repository.showUserBalance(username, groupId);
            if(!result.trim().equals("empty")){
                System.out.println(result);
                FromJSONToString fromJSONToStringHistory = new FromJSONToString(result);
                history = fromJSONToStringHistory.fromJSONToStringGroupHistory();
            }
            else if(!result2.trim().equals("empty")){
                System.out.println(result2);
                FromJSONToString fromJSONToStringBalance = new FromJSONToString(result2);
                balance = fromJSONToStringBalance.fromJSONToStringGroupBalance();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, balance);
        listViewOutgo.setAdapter(arrayAdapter);
        listViewIncome = (ListView) findViewById(R.id.listviewDochodyGrupy);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, history);
        listViewIncome.setAdapter(arrayAdapter2);

        NewGroupIncome = (Button) findViewById(R.id.wyborGrupy);
        NewGroupIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGroupIncome(view);
            }
            });
    }

    /**
     * This method allows to add new group income
     * @param view Creates a new object of the View class
     */
    public void newGroupIncome(View view) {
        Intent intent_newGroupIncomee = new Intent(this, com.ioproject.CashUp.view.NewGroupIncome.class);
        intent_newGroupIncomee.putExtra("nazwaUzytkownika", username);
        intent_newGroupIncomee.putExtra("grupa", groupId);
        intent_newGroupIncomee.putExtra("idUzytkownika", userId);
        startActivity(intent_newGroupIncomee);
    }
}