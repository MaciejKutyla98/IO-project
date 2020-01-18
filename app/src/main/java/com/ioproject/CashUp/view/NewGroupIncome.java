package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.FromJSONToString;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewGroupIncome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button saveGroupOutCome;
    private String userId;
    private String username;
    private String chosenGroup;
    private List<String> members = new ArrayList<>();
    private List<String> categoryList = new ArrayList<>();
    private String payer;
    private String price;
    private String category;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group_income);
        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");
        chosenGroup = getIntent().getStringExtra("grupa");

        ((TextView) findViewById(R.id.GroupName)).setText(chosenGroup);

        String result = null;
        try {
            result = Repository.getAllUserInGroup(chosenGroup);
            FromJSONToString fromJSONToString = new FromJSONToString(result);
            System.out.println(result);
            members = fromJSONToString.fromJSONToStringGetAllUserInGroup();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        categoryList.add("planszówki");
        categoryList.add("piwko");
        categoryList.add("winko");
        final Spinner listOfMembers = findViewById(R.id.spinner_choose_person);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, members);
        dataAdapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        listOfMembers.setAdapter(dataAdapter);
        listOfMembers.setOnItemSelectedListener(this);

        final Spinner categorySpinner = findViewById(R.id.KategorieGrup);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
        dataAdapter1.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        categorySpinner.setAdapter(dataAdapter1);
        categorySpinner.setOnItemSelectedListener(this);

        saveGroupOutCome = (Button) findViewById(R.id.AddNewOutComeButton);
        saveGroupOutCome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payer = listOfMembers.getSelectedItem().toString();
                price = ((EditText) findViewById(R.id.KwotaGrupy)).getText().toString();
                category = categorySpinner.getSelectedItem().toString();
                description = ((EditText) findViewById(R.id.opis)).getText().toString();
                if(!price.isEmpty()){
                    try {
                        String result = Repository.addNewGroupOutgo(chosenGroup, price, category, description, payer);
                        if(result.trim().equals("Added")){
                            Toast.makeText(getApplicationContext(), "Dodano wydatek", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
