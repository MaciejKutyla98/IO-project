package com.ioproject.CashUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ioproject.CashUp.ServerConnection.DataBaseRequests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class Registation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String [] months = new String[] {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesien", "Październik", "Listopad", "Grudzień"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        handleSpinners();
        final CheckBox termsAndCond = (CheckBox) findViewById(R.id.regulamin);
        Button signUpButton = (Button) findViewById(R.id.registratrion_button);
        signUpButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               if(termsAndCond.isChecked()){
                   String name = ((EditText) findViewById(R.id.name)).getText().toString();
                   String surname = ((EditText) findViewById(R.id.surname)).getText().toString();
                   String email = ((EditText) findViewById(R.id.email)).getText().toString();
                   String login = ((EditText) findViewById(R.id.login_reg)).getText().toString();
                   String password = ((EditText) findViewById(R.id.password_registration)).getText().toString();
                   String selectedMonthName = ((Spinner) findViewById(R.id.spinner_months)).getSelectedItem().toString();
                   Integer selectedMonthNumber = Arrays.asList(months).indexOf(selectedMonthName)+1;

                   String birthday = ((Spinner) findViewById(R.id.spinner_years)).getSelectedItem().toString() + "-"+selectedMonthNumber.toString()+"-"+((Spinner) findViewById(R.id.spinner_day)).getSelectedItem().toString();
                   try {

                       String add = DataBaseRequests.connect(DataBaseRequests.addNewUser(name, surname, email, login,
                               password, birthday));
                       if(!add.equals("User with this login already exists\n")){
                           logIn(v);
                       }
                       System.out.println(add);

                   } catch (UnsupportedEncodingException ex){
                       System.out.println(ex);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
               else {
                   Toast.makeText(getApplicationContext(), "Nie zaakceptowałeś regulaminu", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    public void registration(View view) {
        Intent intent_registration = new Intent(this, Registation.class);
        startActivity(intent_registration);
    }

    public void logIn(View view) {
        Intent intent_login = new Intent(this, LogInView.class);
        startActivity(intent_login);
    }

    public void handleSpinners(){
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
