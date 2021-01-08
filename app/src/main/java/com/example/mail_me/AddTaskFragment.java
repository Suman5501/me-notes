package com.example.mail_me;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Models.ListItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListItem> listItem;
    ProgressDialog progressDialog;
    FirebaseFirestore db;
    private Button add;
    private TextView title;
    private TextView description;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_add_task, container, false);
        add = v.findViewById(R.id.addTask);
        title = (TextView) v.findViewById(R.id.addTitle);
        description =(TextView) v.findViewById(R.id.addDescription);

        final String title1 = title.getText().toString();
        final String description1 = description.getText().toString();

        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this.getContext());


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listItem = new ArrayList<>();
                //ListItem item = new ListItem("title1","description1");
                //listItem.add(item);

                uploadData(title,description);
            }
        });
        return v;
    }

    private void uploadData(TextView title, TextView description) {

        progressDialog.setTitle("Uploading data...");
        progressDialog.show();
        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id);
        doc.put("title",title);
        doc.put("description",description);

        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Uploaded successfully..", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}