package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ioproject.CashUp.R;
import com.ioproject.CashUp.data.model.LoggedInUser;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import java.io.IOException;

public class LogInView extends AppCompatActivity {
    private String login;
    private String password;
    private Button LogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LogInButton = (Button) findViewById(R.id.sign_in);
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = ((EditText) findViewById(R.id.login)).getText().toString();
                password = ((EditText) findViewById(R.id.password)).getText().toString();
                String result = null;
                try {
                    result = Repository.loginUser(login, password);
                    result = result.trim();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                checkLoginPassword(v, result);
            }
        });
    }
    public void registration(View view) {
        Intent intent_registration = new Intent(this, RegistationView.class);
        startActivity(intent_registration);
    }

    public void home(View view, LoggedInUser loggedInUser) {
        Intent intent_home= new Intent(this, IndividualHomeView.class);
        intent_home.putExtra("nazwaUzytkownika", loggedInUser.getDisplayName());
        intent_home.putExtra("idUzytkownika", loggedInUser.getUserId());
        startActivity(intent_home);
    }

    private void checkLoginPassword(View v, String result){
        if(login.trim().isEmpty() || password.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "nie podano loginu lub hasla", Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("0")){
            Toast.makeText(getApplicationContext(), "błędne dane logowania", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), login + " logowanie powiodło się", Toast.LENGTH_SHORT).show();
            LoggedInUser loggedInUser =  new LoggedInUser(result, login);
            home(v, loggedInUser);
        }
    }

}
