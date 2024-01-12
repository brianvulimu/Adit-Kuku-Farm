package com.example.kukufarm.Fragment;

import static android.content.Intent.getIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.kukufarm.Adapter.AdditionEggsAdapter;
import com.example.kukufarm.AdditionEggs;
import com.example.kukufarm.Helper.AdditionEggsHelper;
import com.example.kukufarm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BatchAdditionFragment extends Fragment {

    FloatingActionButton addBatchButton;
    Toolbar toolbar;
    RecyclerView recyclerView;
    AdditionEggsAdapter additionEggsAdapter;
    ArrayList<AdditionEggsHelper> arrayList;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_batch_addition, container, false);
        addBatchButton= view.findViewById(R.id.add_batch);
        toolbar=view.findViewById(R.id.toolbar);
        recyclerView=view.findViewById(R.id.batch_view);
        databaseReference= FirebaseDatabase.getInstance().getReference("AddEggs");
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this);

        arrayList=new ArrayList<>();
        additionEggsAdapter=new AdditionEggsAdapter(this);
        recyclerView.setAdapter(additionEggsAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    AdditionEggsHelper additionEggsHelper=dataSnapshot.getValue(AdditionEggsHelper.class);
                    arrayList.add(additionEggsHelper);
                }
                additionEggsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addBatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AdditionEggs.class);
                startActivity(intent);
            }
        });
        return view;
    }

}