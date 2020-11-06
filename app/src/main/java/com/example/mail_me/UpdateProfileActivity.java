package com.example.mail_me;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class UpdateProfileActivity extends AppCompatActivity {

    private TextView displayName;
    private Button updatebutton;
    private ImageView profileImageView;
    String DISPLAY_NAME;
    String PROFILE_IMAGE_URL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        updatebutton = (Button) findViewById(R.id.updateProfilebutton);
        displayName = (TextView) findViewById(R.id.displayname);
        profileImageView = (ImageView) findViewById(R.id.profileimageView);

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
            }
        }
    }

    public void updateProfile(View view){

    }
}