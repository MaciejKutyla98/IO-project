package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ioproject.CashUp.ExampleAdapter;
import com.ioproject.CashUp.R;
import com.ioproject.CashUp.UsersList;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class NewGroup extends AppCompatActivity {
    private ArrayList<UsersList> listOfUsers = new ArrayList<>();
    private ArrayList<String> userListToDatabase = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String groupName;
    private String username;
    private String userId;
    private String newUser;
    private String description;
    private Button addGroupButton;
    private Button addPersonButton;
    ListView listViewOutgo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");

        userListToDatabase.add(username);
        addGroupButton = (Button) findViewById(R.id.AddNewGroup);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    description = ((EditText) findViewById(R.id.NewGroupDescription)).getText().toString();
                    groupName = ((EditText) findViewById(R.id.NameOfGroup)).getText().toString();
                    String result = Repository.addNewGroup(groupName, description, userListToDatabase);
                    System.out.println(result);
                    if(result.trim().equals("Added") && !groupName.isEmpty() && !userListToDatabase.isEmpty()){
                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                        backToGroupHome(view);
                    }
                    else if(result.trim().equals("empty")){
                        Toast.makeText(getApplicationContext(), "Nie masz jeszcze żadnych grup", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "coś poszło nie tak", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        addPersonButton = (Button) findViewById(R.id.AddNewPerson);
        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    newUser = ((EditText) findViewById(R.id.PersonNick)).getText().toString();
                    String result = Repository.doesUserExist(newUser);
                    System.out.println(result);
                    if(result.trim().equals("YES")){
                        if(!userListToDatabase.contains(newUser)){
                            listOfUsers.add(new UsersList(R.drawable.ic_android, newUser, ""));
                            userListToDatabase.add(newUser);
                            buildRecyclerView();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "użytkownik już dodany", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "taki użytkownik nie istnieje", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void removeItem(int position) {
        listOfUsers.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        listOfUsers.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(listOfUsers);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void backToGroupHome(View view){
        Intent i = new Intent(this, GroupHome.class);
        i.putExtra("nazwaUzytkownika", username);
        i.putExtra("idUzytkownika", userId);
        startActivity(i);
    }
}