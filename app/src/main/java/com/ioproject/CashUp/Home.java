package com.ioproject.CashUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Home extends AppCompatActivity {

    private Button dalej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        String username = getIntent().getStringExtra("nazwaUzytkownika");
        ((TextView) findViewById(R.id.nazwaUzytkownika)).setText(username);
//        dalej = (Button) findViewById(R.id.dalej);
//        dalej.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                home(v);
//            }

//    });
    }
    public void home(View view) {
        Intent intent_home= new Intent(this, home2.class);
        startActivity(intent_home);
    }
}
