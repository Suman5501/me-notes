package com.example.mail_me;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.

 */
public class UpdateProfileFragment extends Fragment {


    private TextView displayName;
    private Button updatebutton;
    private ImageView profileImageView;
    String DISPLAY_NAME;
    String PROFILE_IMAGE_URL = null;

    public UpdateProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View item = inflater.inflate(R.layout.activity_update_profile, container, false);
        updatebutton = (Button) item.findViewById(R.id.updatebutton);
        displayName = (TextView) item.findViewById(R.id.displayname);
        profileImageView = (ImageView) item.findViewById(R.id.profileimageView);

        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            displayName.setText(user.getDisplayName());
        }
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Users");
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile(view);
            }
        });

        return item;
    }
    public void updateProfile(View view){

    }
}