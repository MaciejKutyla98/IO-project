package com.ioproject.CashUp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        String username = getIntent().getStringExtra("nazwaUzytkownika");
        ((TextView) findViewById(R.id.nazwaUzytkownika)).setText(username);

    }

}
