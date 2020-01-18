package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.FromJSONToString;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GroupHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private String username;
    private String userId;
    private ArrayList<String> groups;
    private String chosenGroup;
    float x1,x2,y1,y2;
    private Button goToGroups;
    private Button newGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");

        try {
            FromJSONToString fromJSONToString = new FromJSONToString(Repository.getAllUserGroups(userId));
            groups = fromJSONToString.fromJSONToStringGetAllUserGroups();
            Spinner listOfGroups = findViewById(R.id.spinner_groups);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
            dataAdapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
            listOfGroups.setAdapter(dataAdapter);
            listOfGroups.setOnItemSelectedListener(this);

            chosenGroup = listOfGroups.getSelectedItem().toString();
            goToGroups = (Button) findViewById(R.id.goToGroup);
            goToGroups.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    goToOtherGroup(v);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        newGroup = (Button) findViewById(R.id.newGroup);
        newGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewGroup(view);
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
                    i.putExtra("nazwaUzytkownika", username);
                    i.putExtra("idUzytkownika", userId);
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
        new_groups.putExtra("nazwaUzytkownika", username);
        new_groups.putExtra("grupa", chosenGroup);
        new_groups.putExtra("idUzytkownika", userId);
        startActivity(new_groups);
    }

    public void createNewGroup(View view) {
        Intent new_group = new Intent(this, NewGroup.class);
        new_group.putExtra("nazwaUzytkownika", username);
        new_group.putExtra("idUzytkownika", userId);
        startActivity(new_group);
    }
}
