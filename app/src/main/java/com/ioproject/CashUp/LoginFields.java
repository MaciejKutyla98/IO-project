package com.ioproject.CashUp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ioproject.CashUp.ServerConnection.DataBaseRequests;

import java.io.IOException;

public class LoginFields extends AppCompatActivity {
    private String login;
    private String password;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        signInButton = (Button) findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.login)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                String result = null;
                try {
                    result = DataBaseRequests.connect(DataBaseRequests.loginUser(username, password));
                    System.out.println(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(result.equals("0")){
                    Toast.makeText(getApplicationContext(), "błędne dane logowania", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), username + " logowanie powiodło się", Toast.LENGTH_SHORT).show();
                    home(v);
                }
            }
        });
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void printLoginAndPassword(){
        System.out.println(this.login + " " + this.password);
    }

    public void registration(View view) {
        Intent intent_registration = new Intent(this, SignUp.class);
        startActivity(intent_registration);
    }

    public void home(View view) {
        Intent intent_home= new Intent(this, Home.class);
        intent_home.putExtra("nazwaUzytkownika", this.login);
        startActivity(intent_home);
    }
}
