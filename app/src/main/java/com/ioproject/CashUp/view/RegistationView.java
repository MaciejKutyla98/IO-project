package com.ioproject.CashUp.view;

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

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Class allows new users to register
 */
public class RegistationView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private String confirmPassword;
    private Integer selectedMonthNumber;
    private Button signUpButton;
    private String [] months = new String[] {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesien", "Październik", "Listopad", "Grudzień"};

    /**
     * This method is responsible for user contact mechanism
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        handleSpinners();
        final CheckBox termsAndCond = (CheckBox) findViewById(R.id.regulamin);
        signUpButton = (Button) findViewById(R.id.registratrion_button);
        signUpButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               if(termsAndCond.isChecked()){
                   name = ((EditText) findViewById(R.id.name)).getText().toString();
                   surname = ((EditText) findViewById(R.id.surname)).getText().toString();
                   email = ((EditText) findViewById(R.id.email)).getText().toString();
                   login = ((EditText) findViewById(R.id.login_reg)).getText().toString();
                   password = ((EditText) findViewById(R.id.password_registration)).getText().toString();
                   confirmPassword = ((EditText) findViewById(R.id.password_registration2)).getText().toString();
                   String selectedMonthName = ((Spinner) findViewById(R.id.spinner_months)).getSelectedItem().toString();
                   selectedMonthNumber = Arrays.asList(months).indexOf(selectedMonthName)+1;

                   String birthday = ((Spinner) findViewById(R.id.spinner_years)).getSelectedItem().toString() + "-"+selectedMonthNumber.toString()+"-"+((Spinner) findViewById(R.id.spinner_day)).getSelectedItem().toString();
                   try {
                       if(checkNameSurname()){
                           if(checkEmail()){
                               if(checkPassword()) {
                                   if(password.equals(confirmPassword)) {
                                       String add = Repository.addNewUser(name, surname, email, login,
                                               password, birthday);
                                       if (!add.equals("User with this login already exists".trim())) {
                                           Toast.makeText(getApplicationContext(), "poprawna rejestracja", Toast.LENGTH_SHORT).show();
                                           logIn(v);
                                       } else {
                                           Toast.makeText(getApplicationContext(), "użytkownik o takim loginie lub mailu już istnieje", Toast.LENGTH_SHORT).show();
                                       }
                                   }else{
                                       Toast.makeText(getApplicationContext(), "podane hasła się od siebie różnią", Toast.LENGTH_SHORT).show();
                                   }
                               }
                               else{
                                   Toast.makeText(getApplicationContext(), "hasło powinno być dłuższe niż 5 znaków i zawierać cyfrę", Toast.LENGTH_SHORT).show();
                               }
                           }
                           else{
                               Toast.makeText(getApplicationContext(), "niepoprawny format email", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else {
                           Toast.makeText(getApplicationContext(), "niepoprawne imię lub nazwisko", Toast.LENGTH_SHORT).show();
                       }
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

    /**
     * This method allows to register
     * @param view Creates a new object of the View class
     */
    public void registration(View view) {
        Intent intent_registration = new Intent(this, RegistationView.class);
        startActivity(intent_registration);
    }

    /**
     * This method allows to log in
     * @param view Creates a new object of the View class
     */
    public void logIn(View view) {
        Intent intent_login = new Intent(this, LogInView.class);
        startActivity(intent_login);
    }

    /**
     * This method creates necessary  spinners
     */
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
        adapter3.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item);
        years.setAdapter(adapter3);
        years.setOnItemSelectedListener(this);
    }

    /**
     * This method validates the name field
     * @return true if it is okey, otherwise false
     */
    private Boolean checkNameSurname(){
        if(name.trim().isEmpty() || name.trim().isEmpty()){
            return false;
        }
        int flag = 0;
        for(int i = 0; i< name.length(); i++){
            int ascii = (int) name.charAt(i);
            if(!((ascii >= 97 && ascii <= 122) || (ascii >= 65 && ascii <= 90))){
                flag = 1;
            }
        }
        for(int i = 0; i< surname.length(); i++){
            int ascii = (int) surname.charAt(i);
            if(!((ascii >= 97 && ascii <= 122) || (ascii >= 65 && ascii <= 90) || ascii == 45)){
                flag = 1;
            }
        }
        if(flag == 1)
            return false;
        return true;
    }

    /**
     * This method validates the password field
     * @return true if it is okey, otherwise false
     */
    private Boolean checkPassword(){
        if(password.length() < 6)
            return false;
        int flag = 0;
        for(int i = 0; i< password.length(); i++) {
            int ascii = (int) password.charAt(i);
            if (ascii > 47 && ascii < 58) {
                flag = 1;
            }
        }
        if(flag == 0)
            return false;
        return true;
    }

    /**
     * This method validates the email field
     * @return true if it is okey, otherwise false
     */
    private Boolean checkEmail(){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
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
       // String text = parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method allows a specific action at the time of none of the items are selected
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
