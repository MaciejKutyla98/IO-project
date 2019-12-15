package com.ioproject.CashUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String name;
    private String surname;
    private String day;
    private String month;
    private String year;
    private String email;
    private String login;
    private String password;
    private Button signUpButton;
    private CheckBox termsAndCond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
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

       termsAndCond = (CheckBox) findViewById(R.id.regulamin);
       signUpButton = (Button) findViewById(R.id.registratrion_button);
       signUpButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               if(termsAndCond.isChecked()){
                   System.out.println("elo");;
               }
               else {
                   Toast.makeText(getApplicationContext(), "Nie zaakceptowałeś regulaminu", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    public void registration(View view) {
        Intent intent_registration = new Intent(this, SignUp.class);
        startActivity(intent_registration);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
       // String text = parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
