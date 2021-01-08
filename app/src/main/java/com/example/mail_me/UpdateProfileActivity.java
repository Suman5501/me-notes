package com.example.mail_me;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateProfileActivity extends AppCompatActivity {

    private TextView displayName;
    private Button updatebutton;
    private ImageView profileImageView;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        updatebutton = (Button) findViewById(R.id.updateProfilebutton);
        displayName = (TextView) findViewById(R.id.displayname);
        profileImageView = (ImageView) findViewById(R.id.profileimage);
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            displayName.setText(user.getDisplayName());
        }
        DatabaseReference reference;


        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, 1000);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(requestCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                profileImageView.setImageURI(imageUri);
                updateProfileToFirebase(imageUri);
            }
        }
    }

    private void updateProfileToFirebase(Uri imageUri) {

        final StorageReference fref = storageReference.child("profile.jpg");
        fref.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Picasso.get().load
                    }
                });
                Toast.makeText(UpdateProfileActivity.this,"image uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateProfileActivity.this,"error in uploading",Toast.LENGTH_SHORT).show();
            }
        });


    }


}