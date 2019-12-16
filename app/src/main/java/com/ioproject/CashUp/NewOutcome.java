package com.ioproject.CashUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class NewOutcome extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    private Button saveOutcome;
    private Button cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowy_wydatek);

        String [] options={"ODD NUMBER","EVEN NUMBER","PRIME NUMBER","MULTIPLE OF 3","EXACT NUMBER" };

        Spinner kategoria = findViewById(R.id.kategorieWydatkow);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,options);
        adapter4.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        kategoria.setAdapter(adapter4);
        kategoria.setOnItemSelectedListener(this);

        dateChandler();

        saveOutcome = (Button) findViewById(R.id.zapiszWydatek);
        saveOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome(v);
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

    public void dateChandler(){
        Spinner days = findViewById(R.id.dzien);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        days.setAdapter(adapter);
        days.setOnItemSelectedListener(this);

        Spinner months = findViewById(R.id.miesiac);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        months.setAdapter(adapter2);
        months.setOnItemSelectedListener(this);

        Spinner years = findViewById(R.id.rok);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        years.setAdapter(adapter3);
        years.setOnItemSelectedListener(this);
    }
    public void backToHome(View view) {
        Intent intent_newBill = new Intent(this, LoggedInUserView.class);
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
