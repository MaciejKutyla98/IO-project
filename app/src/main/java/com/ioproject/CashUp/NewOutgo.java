package com.ioproject.CashUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ioproject.CashUp.ServerConnection.Repository;

import java.io.IOException;

public class NewOutgo extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button zapiszWydatek;
    private Button anuluj;
    private String userId;
    private String username;
    private String [] months = new String[] {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesien", "Październik", "Listopad", "Grudzień"};


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowy_wydatek);

        String [] options={"ODD NUMBER","EVEN NUMBER","PRIME NUMBER","MULTIPLE OF 3","EXACT NUMBER" };

        Spinner kategoria = findViewById(R.id.kategorieWydatkow);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.kategorieWydatkow, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        kategoria.setAdapter(adapter4);
        kategoria.setOnItemSelectedListener(this);

//        Spinner days = findViewById(R.id.dzien);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
//        days.setAdapter(adapter);
//        days.setOnItemSelectedListener(this);
//
//        final Spinner months = findViewById(R.id.miesiac);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
//        months.setAdapter(adapter2);
//        months.setOnItemSelectedListener(this);
//
//        Spinner years = findViewById(R.id.rok);
//        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
//        adapter3.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
//        years.setAdapter(adapter3);
//        years.setOnItemSelectedListener(this);
        setUserId(getIntent().getStringExtra("idUzytkownika"));
        setUsername(getIntent().getStringExtra("nazwaUzytkownika"));

        zapiszWydatek = (Button) findViewById(R.id.zapiszWydatek);
        zapiszWydatek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = ((Spinner) findViewById(R.id.kategorieWydatkow)).getSelectedItem().toString();
  //              String selectedMonthName = ((Spinner) findViewById(R.id.miesiac)).getSelectedItem().toString();
//                Integer selectedMonthNumber = Arrays.asList(months).indexOf(selectedMonthName)+1;
//                String date = ((Spinner) findViewById(R.id.rok)).getSelectedItem().toString() + "-"+selectedMonthNumber.toString()+"-"+((Spinner) findViewById(R.id.dzien)).getSelectedItem().toString();
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

        anuluj = (Button) findViewById(R.id.powrot);
        anuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome(v);
            }
        });

    }

    public void backToHome(View view) {
        Intent intent_newBill = new Intent(this, LoggedInUserView.class);
        intent_newBill.putExtra("nazwaUzytkownika", getUsername());
        intent_newBill.putExtra("idUzytkownika", getUserId());
        startActivity(intent_newBill);
    }

    public void saveBill(View view) {
        Intent intent_newBill = new Intent(this, LoggedInUserView.class);
        intent_newBill.putExtra("nazwaUzytkownika", getUsername());
        intent_newBill.putExtra("idUzytkownika", getUserId());
        startActivity(intent_newBill);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        // String text = parent.getItemAtPosition(position).toString();
        // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
