package com.example.mail_me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import Models.ListItem;

public class AddTaskActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ListItem> listItem;
    ProgressDialog progressDialog;
    FirebaseFirestore db;
    private Button add;
    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        add = (Button)findViewById(R.id.addTask);
        title = (TextView) findViewById(R.id.addTitle);
        description =(TextView) findViewById(R.id.addDescription);

        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });
    }

    private void uploadData() {
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Uploading Data...");
        progressDialog.show();

        String id = UUID.randomUUID().toString();
        Map<String, Object> doc = new HashMap<>();

        String titleText = title.getText().toString();
        String descriptionText = description.getText().toString();
        doc.put("id",id);
        doc.put("title",titleText);
        doc.put("description",descriptionText);

        db.collection("Documents").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        Toast.makeText(AddTaskActivity.this, "Uploaded successfully..", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(AddTaskActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}