package com.ioproject.CashUp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ioproject.CashUp.ExampleAdapter;
import com.ioproject.CashUp.R;
import com.ioproject.CashUp.UsersList;
import com.ioproject.CashUp.data.model.server_connection.Repository;

import java.io.IOException;
import java.util.ArrayList;

public class NewGroup extends AppCompatActivity {
    private ArrayList<UsersList> listOfUsers;
    private ArrayList<String> userListToDatabase;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        username = getIntent().getStringExtra("nazwaUzytkownika");
        userId = getIntent().getStringExtra("idUzytkownika");
        description = ((EditText) findViewById(R.id.NewGroupDescription)).getText().toString();
        groupName = ((EditText) findViewById(R.id.NameOfGroup)).getText().toString();

        addGroupButton = (Button) findViewById(R.id.AddNewGroup);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String result = Repository.addNewGroup(groupName, description, userListToDatabase);
                    if(result.trim().equals("Added") && !groupName.isEmpty() && !userListToDatabase.isEmpty()){
                        Toast.makeText(getApplicationContext(), "poprawne dodanie wydatku", Toast.LENGTH_SHORT).show();
                        backToGroupHome(view);

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
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    newUser = ((EditText) findViewById(R.id.PersonNick)).getText().toString();
                    String result = Repository.doesUserExist(newUser);
                    if(result.trim().equals("YES")){
                        listOfUsers.add(new UsersList(R.drawable.ic_child, newUser, ""));
                        userListToDatabase.add(result);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "taki użytkownik nie istnieje", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buildRecyclerView();
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