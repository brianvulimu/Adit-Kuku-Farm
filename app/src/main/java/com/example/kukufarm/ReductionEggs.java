package com.example.kukufarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class ReductionEggs extends AppCompatActivity {

    String [] flocks={"Whole Farm","Specific Flock"};

    String [] reduction={"Cull","Death","Sold","Lost/Stolen","Others"};

    AutoCompleteTextView autoCompleteTextView, autoCompleteTextViewReduction;

    ArrayAdapter<String> arrayAdapter,arrayAdapter2;

    DatePickerDialog datePickerDialog;
    TextInputEditText reduction_date, customerName,quantity_of_eggs,amount_earned;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button btnSave;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduction_eggs);
        autoCompleteTextView=findViewById(R.id.autoComplete);
        autoCompleteTextViewReduction=findViewById(R.id.autoCompleteReduction);
        reduction_date=findViewById(R.id.reductiondate);
        customerName=findViewById(R.id.customer_name);
        btnSave=findViewById(R.id.buttonSave);
        quantity_of_eggs=findViewById(R.id.quantity_of_eggs);
        amount_earned=findViewById(R.id.amount_earned);
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.flocks,flocks);
        arrayAdapter2=new ArrayAdapter<String>(this,R.layout.flocks,reduction);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextViewReduction.setAdapter(arrayAdapter2);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ReductionEggs.this,"Flocks :"+item,Toast.LENGTH_SHORT).show();
                }
        });

        autoCompleteTextViewReduction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(ReductionEggs.this,"Reason for Reduction :"+item,Toast.LENGTH_SHORT).show();
            }
        });
        reduction_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reductionEggs();
            }
        });
    }

    private void reductionEggs() {
        String flocks=autoCompleteTextView.getText().toString();
        String reduction_reason=autoCompleteTextViewReduction.getText().toString();
        String customer_name=customerName.getText().toString();
        String date_reduce=reduction_date.getText().toString();
        int money_earned=Integer.parseInt(String.valueOf(amount_earned.getText()));
        int quantity_of_reduction=Integer.parseInt(String.valueOf(quantity_of_eggs.getText()));
        if(flocks.isEmpty()){
            autoCompleteTextView.setError("Flock Field required");
        }
        if(reduction_reason.isEmpty()){
            autoCompleteTextViewReduction.setError("Reduction reason Field required");
        }
        if(customer_name.isEmpty()){
            customerName.setError("Customer name Field required");
        }
        if(date_reduce.isEmpty()){
            reduction_date.setError("Reduction Date Field required");
        }
        if(String.valueOf(money_earned).isEmpty()){
            amount_earned.setError("Amount Earned Field required");
        }
        if(String.valueOf(quantity_of_reduction).isEmpty()){
            quantity_of_eggs.setError("Quantity of Eggs Field required");
        }
        HashMap<String, Object> hashMap=new HashMap<>();
        hashMap.put("Flock",flocks);
        hashMap.put("Reduction Reason",reduction_reason);
        hashMap.put("Customer_Name",customer_name);
        hashMap.put("Reduction_Date",date_reduce);
        hashMap.put("Money_Earned",money_earned);
        hashMap.put("Quantity of Eggs",quantity_of_reduction);
        databaseReference.child("Additional Eggs")
                .child(flocks).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ReductionEggs.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ReductionEggs.this,"Data Failed to be added",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                reduction_date.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar calendar= Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DATE);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);
    }
}