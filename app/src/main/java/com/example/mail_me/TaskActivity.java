package com.example.mail_me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;

import ListAdapter.MyAdapter;
import Models.ListItem;

public class TaskActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_task);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItem = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ListItem item = new ListItem("Item" + (i+1),"Description");
            listItem.add(item);
        }

        adapter = new MyAdapter(this,listItem);
        recyclerView.setAdapter(adapter);

    }
}