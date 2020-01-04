package com.ioproject.CashUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ioproject.CashUp.ServerConnection.Repository;
import com.ioproject.CashUp.data.model.FromJSONToString;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class LoggedInUserView extends AppCompatActivity {

    private Button choice;
    private Button newIncome;
    private Button newOutcome;
    private int flag;
    private String userId;
    private String username;
    ListView listViewWydatki;
    ListView listViewDochody;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        setUsername(getIntent().getStringExtra("nazwaUzytkownika"));
        String userId = getIntent().getStringExtra("idUzytkownika");
        setUserId(userId);
        ((TextView) findViewById(R.id.nazwaUzytkownika)).setText(username);

        listViewWydatki = (ListView)findViewById(R.id.listviewWydatki);

        ArrayList<String> wydatki = new ArrayList<>();
        ArrayList<String> dochody = new ArrayList<>();
        String podliczenieZBazy = null;
        String transactions = null;
        try {
            transactions = Repository.showAllTransactions(userId);
            FromJSONToString fromJSONToString = new FromJSONToString(transactions);
            dochody = fromJSONToString.fromJSONTOStringIncome();
            wydatki = fromJSONToString.fromJSONTOStringOutgo();
            podliczenieZBazy = String.valueOf(fromJSONToString.fromJSONBalanceSheet()) + ".00";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
        e.printStackTrace();
    }
        
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,wydatki);
        listViewWydatki.setAdapter(arrayAdapter);
        //tutaj musimy w formie Stringa najlepiej Kategoria + ":    " + 'suma' i dodawać po porstu addem do bazy
//        wydatki.add("elo");
//        wydatki.add("my");
//        wydatki.add("tego");
//        wydatki.add("kurwa");
//        wydatki.add("nie");
//        wydatki.add("zdamy");
//        wydatki.add("elo");
//        wydatki.add("elo");

        listViewDochody = (ListView)findViewById(R.id.listviewDochody);


        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dochody);
        listViewDochody.setAdapter(arrayAdapter2);
//        dochody.add("elo");
//        dochody.add("my");
//        dochody.add("tego");
//        dochody.add("kurwa");
//        dochody.add("nie");
//        dochody.add("zdamy");
//        dochody.add("elo");
//        dochody.add("elo");
        /// tutaj wstawić funkcję która liczy sumę wydatków i dochodów i w poostaci stringa przypisujemy to do podliczenieZBazy
//        String podliczenieZBazy = "51,72 zł";
        ((TextView) findViewById(R.id.bilansBazy)).setText(podliczenieZBazy);

        choice = (Button) findViewById(R.id.wybor);
        newOutcome = (Button) findViewById(R.id.nowyWydatek);
        newIncome = (Button) findViewById(R.id.nowyDochod);
        flag = 0;
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0) {
                    newIncome.setVisibility(v.VISIBLE);
                    newOutcome.setVisibility(v.VISIBLE);
                    flag +=1;
                }
                else if(flag == 1){
                    newIncome.setVisibility(v.INVISIBLE);
                    newOutcome.setVisibility(v.INVISIBLE);
                    flag = 0;
                }
            }
        });

        newOutcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newBill(view);
            }
        });

        newIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIncome(view);
            }
        });
    }

    public void newBill(View view) {
        Intent intent_newBill = new Intent(this, NewOutgo.class);
        intent_newBill.putExtra("nazwaUzytkownika", getUsername());
        intent_newBill.putExtra("idUzytkownika", getUserId());
        startActivity(intent_newBill);
    }

    public void newIncome(View view) {
        Intent intent_newIncome = new Intent(this, NewIncome.class);
        intent_newIncome.putExtra("nazwaUzytkownika", getUsername());
        intent_newIncome.putExtra("idUzytkownika", getUserId());
        startActivity(intent_newIncome);
    }
}

