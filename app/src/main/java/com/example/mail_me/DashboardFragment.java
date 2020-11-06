package com.example.mail_me;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListItem> listItem;

    private Button fab;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_task, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);


        adapter = new MyAdapter(getContext() ,listItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container,new AddTaskFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listItem = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ListItem item = new ListItem("Item" + (i+1),"Description");
            listItem.add(item);
        }

    }
}