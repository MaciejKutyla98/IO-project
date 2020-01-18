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
import java.util.ArrayList;
import java.util.List;

/**
 * Class allows to add new personal income
 */
public class NewIncomeView extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button saveIncome;
    private Button cancel;
    private Button dodajKategorieDochodu;
    private String kwotaDochodu;
    private String userId;
    private String username;
    private String nowaKategoriaDochodu;
    private ArrayList<String> categories = new ArrayList<>();
    private String [] months = new String[] {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesien", "Październik", "Listopad", "Grudzień"};

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
     * This method add new element to the list
     * @param category Name of the list
     * @param element  Element to be inserted
     */
    public void addNewElementToList(List<String> category, String element){
        category.add(element);
    }

    /**
     * This method is responsible for user contact mechanism
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowy_dochod);

        categories.add("Praca");
        categories.add("Praca dorywcza");
        categories.add("Stypendium");
        categories.add("Rodzice");
        categories.add("Dodatkowo");

        Spinner category = findViewById(R.id.kategorieDochodow);
        ArrayAdapter<String> adapter4 =  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, categories);
        adapter4.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        category.setAdapter(adapter4);
        category.setOnItemSelectedListener(this);

        userId = getIntent().getStringExtra("idUzytkownika");
        username = getIntent().getStringExtra("nazwaUzytkownika");

        saveIncome = (Button) findViewById(R.id.zapiszDochód);
        saveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = ((Spinner) findViewById(R.id.kategorieDochodow)).getSelectedItem().toString();
                String cost = ((EditText) findViewById(R.id.kwotaDochodu)).getText().toString();
                String description = ((EditText) findViewById(R.id.opisDochodu)).getText().toString();
                try {
                    Repository.addNewIncome(userId,category,cost,description);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveIncome(v);
            }
        });

        cancel = (Button) findViewById(R.id.powrot2);
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
     * This method saves new income
     * @param view Creates a new object of the View class
     */
    public void saveIncome(View view) {
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
