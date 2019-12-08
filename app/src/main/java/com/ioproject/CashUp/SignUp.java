package com.ioproject.CashUp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Spinner days = findViewById(R.id.spinner_day);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        days.setAdapter(adapter);
        days.setOnItemSelectedListener(this);

        Spinner months = findViewById(R.id.spinner_months);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        months.setAdapter(adapter2);
        months.setOnItemSelectedListener(this);

        Spinner years = findViewById(R.id.spinner_years);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        years.setAdapter(adapter3);
        years.setOnItemSelectedListener(this);
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
