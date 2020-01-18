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

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import java.io.IOException;

/**
 * Class allows to add new personal outcome
 */
public class NewOutgoView extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button saveOutgo;
    private Button cancel;
    private String userId;
    private String username;

    /**
     * This metod sets userId
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method gets userId
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method gets username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method is responsible for user contact mechanism
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowy_wydatek);

        Spinner category = findViewById(R.id.kategorieWydatkow);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.kategorieWydatkow, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        category.setAdapter(adapter4);
        category.setOnItemSelectedListener(this);

        setUserId(getIntent().getStringExtra("idUzytkownika"));
        setUsername(getIntent().getStringExtra("nazwaUzytkownika"));

        saveOutgo = (Button) findViewById(R.id.zapiszWydatek);
        saveOutgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = ((Spinner) findViewById(R.id.kategorieWydatkow)).getSelectedItem().toString();
                String cost = ((EditText) findViewById(R.id.kwotaWydatku)).getText().toString();
                String description = ((EditText) findViewById(R.id.opisWydatku)).getText().toString();
                try {
                    Repository.addNewOutgo(getUserId(),category,cost, description);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveBill(v);
            }
        });
        cancel = (Button) findViewById(R.id.powrot);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome(v);
            }
        });
    }


    /**
     * This method takes us to a Individual Home view
     * @param view Creates a new object of the View class
     */
    public void backToHome(View view) {
        Intent intent_newBill = new Intent(this, IndividualHomeView.class);
        intent_newBill.putExtra("nazwaUzytkownika", username);
        intent_newBill.putExtra("idUzytkownika", userId);
        startActivity(intent_newBill);
    }

    /**
     * This method saves new outcome
     * @param view Creates a new object of the View class
     */
    public void saveBill(View view) {
        Intent intent_newBill = new Intent(this, IndividualHomeView.class);
        intent_newBill.putExtra("nazwaUzytkownika", username);
        intent_newBill.putExtra("idUzytkownika", userId);
        startActivity(intent_newBill);
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
}
