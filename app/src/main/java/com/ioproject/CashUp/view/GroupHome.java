package com.ioproject.CashUp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ioproject.CashUp.NewGroup;
import com.ioproject.CashUp.R;

import java.util.ArrayList;
import java.util.List;

public class GroupHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    float x1,x2,y1,y2;
    private Button goToGroups;
    private Button newGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        List<String> groups = new ArrayList<>();
        groups.add("Znajomi ze studiow");
        groups.add("Rodzina");
        groups.add("Ziomki z pracy");
        Spinner listOfGroups = findViewById(R.id.spinner_groups);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
        dataAdapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        listOfGroups.setAdapter(dataAdapter);
        listOfGroups.setOnItemSelectedListener(this);

        goToGroups = (Button) findViewById(R.id.registratrion_button);
        goToGroups.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToOtherGroup(v);
            }
        });

        newGroup = (Button) findViewById(R.id.newGroup);
        newGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createNewGroup(v);
            }
        });
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
                if(x1 < x2){
                    Intent i = new Intent(this, IndividualHomeView.class);
                    startActivity(i);
                }
                break;

        }
        return false;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void goToOtherGroup(View view) {
        Intent new_groups = new Intent(this, GroupBilans.class);
        startActivity(new_groups);
    }

    public void createNewGroup(View view) {
        Intent new_group = new Intent(this, NewGroup.class);
        startActivity(new_group);
    }
}
