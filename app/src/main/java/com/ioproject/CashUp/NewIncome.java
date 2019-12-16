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

import com.ioproject.CashUp.ServerConnection.DataBaseRequests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NewIncome extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button zapiszDochod;
    private Button anuluj;
    private Button dodajKategorieDochodu;
    private String kwotaDochodu;
    private String userId;
    private String username;
    private String nowaKategoriaDochodu;
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
        setContentView(R.layout.activity_nowy_dochod);

        List<String> kategorieDochodow = new ArrayList<String>();
        kategorieDochodow.add("Praca");
        kategorieDochodow.add("Praca dorywcza");
        kategorieDochodow.add("Stypendium");
        kategorieDochodow.add("Rodzice");
        kategorieDochodow.add("Dodatkowo");


//        dodajKategorieDochodu = (Button) findViewById(R.id.dodajKatygorieD);
//        dodajKategorieDochodu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText KategoriaDochodu = (EditText) findViewById(R.id.nowaKategoriaDochodu);
//                setNowaKategoriaDochodu(KategoriaDochodu.getText().toString());
//                home(v);
//            }
//        });


        Spinner kategoria = findViewById(R.id.kategorieDochodow);
        ArrayAdapter<String> adapter4 =  new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, kategorieDochodow);
        adapter4.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        kategoria.setAdapter(adapter4);
        kategoria.setOnItemSelectedListener(this);

  //      handleSpinners();

        setUserId(getIntent().getStringExtra("idUzytkownika"));
        setUsername(getIntent().getStringExtra("nazwaUzytkownika"));

        zapiszDochod = (Button) findViewById(R.id.zapiszDochód);
        zapiszDochod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = ((Spinner) findViewById(R.id.kategorieDochodow)).getSelectedItem().toString();
    //            String selectedMonthName = ((Spinner) findViewById(R.id.miesiac)).getSelectedItem().toString();
        //        Integer selectedMonthNumber = Arrays.asList(months).indexOf(selectedMonthName)+1;
  //              String date = ((Spinner) findViewById(R.id.rok)).getSelectedItem().toString() + "-"+selectedMonthNumber.toString()+"-"+((Spinner) findViewById(R.id.dzien)).getSelectedItem().toString();
                String cost = ((EditText) findViewById(R.id.kwotaDochodu)).getText().toString();
                String description = ((EditText) findViewById(R.id.opisDochodu)).getText().toString();

                try {
                    DataBaseRequests.connect(DataBaseRequests.addNewIncome(userId,category,cost,description));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                saveIncome(v);
            }
        });

        anuluj = (Button) findViewById(R.id.powrot2);
        anuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome(v);
            }
        });

    }

//    public void handleSpinners(){
//
//        Spinner days = findViewById(R.id.dzien);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
//        days.setAdapter(adapter);
//        days.setOnItemSelectedListener(this);
//
//        Spinner months = findViewById(R.id.miesiac);
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
//
//    }
    public void backToHome(View view) {
        Intent intent_newBill = new Intent(this, LoggedInUserView.class);
        intent_newBill.putExtra("nazwaUzytkownika", getUsername());
        intent_newBill.putExtra("idUzytkownika", getUserId());
        startActivity(intent_newBill);
    }

    public void saveIncome(View view) {
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

    public String getKwotaDochodu() {
        return kwotaDochodu;
    }

    public void setKwotaDochodu(String kwotaDochodu) {
        this.kwotaDochodu = kwotaDochodu;
    }

    public String getNowaKategoriaDochodu() {
        return nowaKategoriaDochodu;
    }

    public void setNowaKategoriaDochodu(String nowaKategoriaDochodu) {
        this.nowaKategoriaDochodu = nowaKategoriaDochodu;
    }

}
