package com.ioproject.CashUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ioproject.CashUp.view.GroupBilans;
import com.ioproject.CashUp.view.GroupHome;
import com.ioproject.CashUp.view.NewIncomeView;

public class NewGroupIncome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button saveGroupOutCome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_income);
        List<String> members = new ArrayList<>();
        members.add("Hubert Kompanowski");
        members.add("Alicja Brajner");
        members.add("Maciej Kutyłą");
        Spinner listOfMembers = findViewById(R.id.spinner_choose_person);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, members);
        dataAdapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        listOfMembers.setAdapter(dataAdapter);
        listOfMembers.setOnItemSelectedListener(this);

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
        startActivity(intent_back);
    }
}
