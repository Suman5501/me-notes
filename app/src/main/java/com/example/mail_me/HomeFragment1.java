package com.example.mail_me;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment1 extends Fragment {


    public HomeFragment1() {
        // Required empty public constructor
    }
    private static String getDate() {

        String currentMonth= new SimpleDateFormat("MMMM").format(new Date());
        String currentDate= new SimpleDateFormat("dd").format(new Date());
        int dateInt = Integer.parseInt(currentDate);
        switch (dateInt%10)
        {
            case(1):
                currentDate+="st";
                break;
            case(2):
                currentDate+="nd";
                break;
            case(3):
                currentDate+="rd";
                break;
            default:
                currentDate+="th";
                break;
        }

        return currentMonth+" "+currentDate;


    }
    private static String getTime() {
        return DateFormat.getTimeInstance().format(new Date());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View item = inflater.inflate(R.layout.activity_home_nav, container, false);
        final TextView mUserName = item.findViewById(R.id.user_name);

        TextView mDateTime = item.findViewById(R.id.date_time);
        mDateTime.setText(getDate() + " " + getTime());

        FirebaseUser user;
        DatabaseReference reference;
        String userID;

        user = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.fullName;
                    mUserName.setText("Welcome " + fullName + "!");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return item;
    }
}