package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ioproject.CashUp.R;

import java.util.ArrayList;
import java.util.List;

public class NewGroupIncome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button saveGroupOutCome;
    private String userId;
    private String username;
    private String chosenGroup;
    private List<String> members = new ArrayList<>();
    private String payer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_income);
        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");
        chosenGroup = getIntent().getStringExtra("grupa");

        members.add("Hubert Kompanowski");
        members.add("Alicja Brajner");
        members.add("Maciej Kutyłą");
        Spinner listOfMembers = findViewById(R.id.spinner_choose_person);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, members);
        dataAdapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        listOfMembers.setAdapter(dataAdapter);
        listOfMembers.setOnItemSelectedListener(this);
        payer = listOfMembers.getSelectedItem().toString();

        saveGroupOutCome = (Button) findViewById(R.id.AddNewOutComeButton);
        saveGroupOutCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backToGroup(v);
                }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    public void backToGroup(View view) {
        Intent intent_back = new Intent(this, GroupHome.class);
        intent_back.putExtra("nazwaUzytkownika", username);
        intent_back.putExtra("idUzytkownika", userId);
        startActivity(intent_back);
    }
}
