package com.example.mail_me;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ListAdapter.MyAdapter;
import Models.ListItem;

/**
 * A simple {@link Fragment} subclass.

 */
public class DashboardFragment extends Fragment {


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;
        List<ListItem> listItem;
        Button fab;
        View view = inflater.inflate(R.layout.activity_task, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.class));
        fab = view.findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskActivity.this,AddTaskActivity.class));
            }
        });*/

        listItem = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ListItem item = new ListItem("Item" + (i+1),"Description");
            listItem.add(item);
        }

        adapter = new MyAdapter(this,listItem);
        recyclerView.setAdapter(adapter);

        return view;
    }
}