package com.ioproject.CashUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginFields extends AppCompatActivity {
    private String login;
    private String password;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);
        signInButton = (Button) findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputLogin = (EditText) findViewById(R.id.login);
                String login = inputLogin.getText().toString();
                EditText inputPassword = (EditText) findViewById(R.id.password);
                String password = inputPassword.getText().toString();
                setLogin(login);
                setPassword(password);
                printLoginAndPassword();
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
}
