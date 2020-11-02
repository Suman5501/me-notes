package com.example.mail_me;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        updatebutton = (Button) findViewById(R.id.updatebutton);
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

            }
        });


    }
    public void updateProfile(View view){

    }
}