package com.example.kukufarm.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.kukufarm.AdditionEggs;
import com.example.kukufarm.R;
import com.example.kukufarm.ReductionEggs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BatchReductionFragment extends Fragment {

    FloatingActionButton addBatchButton;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_batch_reduction, container, false);
        addBatchButton= view.findViewById(R.id.add_reduce);
        toolbar=view.findViewById(R.id.toolbar);
        addBatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ReductionEggs.class);
                startActivity(intent);
            }
        });
        return view;
    }
}