package com.ioproject.CashUp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewGroup extends AppCompatActivity {
    private ArrayList<UsersList> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter  mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        createExampleList();
        buildRecyclerView();
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 1", "Nazwisko 2"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 3", "Nazwisko 4"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 5", "Nazwisko 6"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 1", "Nazwisko 2"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 3", "Nazwisko 4"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 5", "Nazwisko 6"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 1", "Nazwisko 2"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 3", "Nazwisko 4"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 5", "Nazwisko 6"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 1", "Nazwisko 2"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 3", "Nazwisko 4"));
        mExampleList.add(new UsersList(R.drawable.ic_child, "Osoba 5", "Nazwisko 6"));
    }

    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

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
}