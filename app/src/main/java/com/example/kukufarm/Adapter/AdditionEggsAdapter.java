package com.example.kukufarm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kukufarm.Fragment.BatchAdditionFragment;
import com.example.kukufarm.Helper.AdditionEggsHelper;
import com.example.kukufarm.R;

import java.util.ArrayList;
import java.util.List;

public class AdditionEggsAdapter extends RecyclerView.Adapter<AdditionEggsAdapter.MyViewHolder> {
    Context context;
    ArrayList<AdditionEggsHelper> dataList;

    public AdditionEggsAdapter(Context context, ArrayList<AdditionEggsHelper> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public AdditionEggsAdapter(BatchAdditionFragment batchAdditionFragment) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_addition_batch,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AdditionEggsHelper additionEggsHelper=dataList.get(position);
        holder.goodEggs.setText(additionEggsHelper.getGoodEggs());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView goodEggs, badEggs, eggsNotInTray,dateCollection;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            goodEggs=(TextView)itemView.findViewById(R.id.editTextGoodEggs);
        }
    }
}
