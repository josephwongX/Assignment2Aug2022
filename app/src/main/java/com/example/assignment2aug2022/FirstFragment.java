package com.example.assignment2aug2022;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FirstFragment extends Fragment {
    FloatingActionButton fab;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private RecyclerView eventRV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private ArrayList<EventRVModal> eventRVModalArrayList;
    private EventRVAdapter eventRVAdapter;
    private ConstraintLayout homeCL;
    public FirstFragment(){
        // require a empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first,container,false);
        FirebaseApp.initializeApp(/*context*/getActivity().getApplicationContext());
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());
        // initializing all our variables
        eventRV = (RecyclerView)view.findViewById(R.id.idRV01);
        //homeCL = findViewById(R.id.idRLBSheet);
        //loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance("https://assignment2aug2022-4c55f-default-rtdb.asia-southeast1.firebasedatabase.app/");
        mAuth = FirebaseAuth.getInstance();
        eventRVModalArrayList = new ArrayList<>();
        // on below line we are getting database reference
        databaseReference = firebaseDatabase.getReference("Events");
        //on below line adding a click listener for our floating action button
        fab=(FloatingActionButton) view.findViewById(R.id.fab01);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        fab.show();

        // on below line initializing our adapter class.
        eventRVAdapter = new EventRVAdapter(eventRVModalArrayList, getActivity().getApplicationContext(), this::onEventClick);
        // setting layout malinger to recycler view on below line
        eventRV.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //setting adapter to recycler view on below line
        eventRV.setAdapter(eventRVAdapter);
        // on below line calling a method to fetch food from database
        getEvents();

        return view;
    }

    private void getEvents(){
        // on below line clearing our list
        eventRVModalArrayList.clear();
        // on below line we are calling add child event listener method to read the data
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // on below line we are hiding our progress bar
                loadingPB.setVisibility(View.GONE);
                // adding snapshot to our array list on below line
                eventRVModalArrayList.add(snapshot.getValue(EventRVModal.class));
                // notifying our adapter that data has changed
                eventRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when new child is added
                // we are notifying our adapter and making progress bar
                // visibility as gone.
                loadingPB.setVisibility(View.GONE);
                eventRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // notifying our adapter when child is removed.
                eventRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // notifying our adapter when child is moved.
                eventRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onEventClick(int position) {
        // calling a method to display a bottom sheet on below line.
        displayBottomSheet(eventRVModalArrayList.get(position));
    }

    private void displayBottomSheet(EventRVModal modal) {
        /*
        // on below line we are creating our bottom sheet dialog.
        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(getActivity().getApplicationContext(), R.style.BottomSheetDialogTheme);
        // on below line we are inflating our layout file for our bottom sheet.
        View layout = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.bottom_sheet_layout, homeCL);
        // setting content view for bottom sheet on below line.
        bottomSheetTeachersDialog.setContentView(layout);
        // on below line we are setting a cancelable
        bottomSheetTeachersDialog.setCancelable(false);
        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);
        // calling a method to display our bottom sheet.
        bottomSheetTeachersDialog.show();
        // on below line we are creating variables for
        // our text view and image view inside bottom sheet
        // and initialing them with their ids.
        TextView eventTV = layout.findViewById(R.id.idTVFoodName);
        TextView foodDescTV = layout.findViewById(R.id.idTVFoodDesc);
        TextView priceTV = layout.findViewById(R.id.idTVFoodPrice);
        ImageView foodIV = layout.findViewById(R.id.idIVFood);
        // on below line we are setting data to different views on below line.
        foodNameTV.setText(modal.getFoodName());
        foodDescTV.setText(modal.getFoodDescription());
        priceTV.setText("MYR " + modal.getFoodPrice());
        Picasso.get().load(modal.getFoodImg()).into(foodIV);
        Button viewBtn = layout.findViewById(R.id.idBtnViewDetails);
        Button editBtn = layout.findViewById(R.id.idBtnEditFood);
        // adding on click listener for our edit button.
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are opening our EditFoodActivity on below line.
                Intent i = new Intent(MainActivity.this, EditFoodActivity.class);
                // on below line we are passing our food modal
                i.putExtra("food", modal);
                startActivity(i);
            }
        });
        // adding click listener for our view button on below line.
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are navigating to browser
                // for displaying food details from its url
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(modal.getFoodLink()));
                startActivity(i);
            }
        });
         */
    }

}