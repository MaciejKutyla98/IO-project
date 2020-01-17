package com.ioproject.CashUp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.ioproject.CashUp.R;

public class GroupHome extends AppCompatActivity {
    private String username;
    private String userId;
    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);

        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");
    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1=touchevent.getX();
                y1=touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=touchevent.getX();
                y2=touchevent.getY();
                if(x1 < x2){
                    Intent i = new Intent(this, IndividualHomeView.class);
                    i.putExtra("nazwaUzytkownika", username);
                    i.putExtra("idUzytkownika", userId);
                    startActivity(i);
                }
                break;

        }
        return false;
    }

}
