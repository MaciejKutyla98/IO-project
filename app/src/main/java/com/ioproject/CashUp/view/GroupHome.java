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

/**
 * Class responsible for choosing the right group
 */
public class GroupHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private String username;
    private String userId;
    private ArrayList<String> groups;
    private String chosenGroup;
    float x1,x2,y1,y2;
    private Button goToGroups;
    private Button newGroup;

    /**
     * This method is responsible for user contact mechanism
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");

        try {
            String result = Repository.getAllUserGroups(username);
            FromJSONToString fromJSONToString = new FromJSONToString(result);
            System.out.println(result);
            groups = fromJSONToString.fromJSONToStringGetAllUserGroups();
            final Spinner listOfGroups = findViewById(R.id.spinner_groups);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groups);
            dataAdapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
            listOfGroups.setAdapter(dataAdapter);
            listOfGroups.setOnItemSelectedListener(this);

            goToGroups = (Button) findViewById(R.id.goToGroup);
            goToGroups.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    chosenGroup = listOfGroups.getSelectedItem().toString();
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

    /**
     * This method is responsible for swipe action which leads to the Individual view
     * @param touchevent Retrieves click positions from the screen
     * @return True if user swaps correctly and moves to Group view
     */
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

    /**
     * This method allows a specific action at the time of selection items
     * @param parent
     * @param view Creates a new object of the View class
     * @param position Specifies item position
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
    }

    /**
     * This method allows a specific action at the time of none of the items are selected
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /**
     * This method takes us to a given group
     * @param view Creates a new object of the View class
     */
    public void goToOtherGroup(View view) {
        Intent new_groups = new Intent(this, GroupBilans.class);
        new_groups.putExtra("nazwaUzytkownika", username);
        new_groups.putExtra("grupa", chosenGroup);
        new_groups.putExtra("idUzytkownika", userId);
        startActivity(new_groups);
    }

    /**
     * This method takes us to a new group creator
     * @param view Creates a new object of the View class
     */
    public void createNewGroup(View view) {
        Intent new_group = new Intent(this, NewGroup.class);
        new_group.putExtra("nazwaUzytkownika", username);
        new_group.putExtra("idUzytkownika", userId);
        startActivity(new_group);
    }
}
