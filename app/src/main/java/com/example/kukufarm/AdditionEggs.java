package com.example.kukufarm;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class AdditionEggs extends AppCompatActivity {

    String [] flocks={};

    String [] remarks={"Newly Collected","Returned"};

    ArrayAdapter<String> arrayAdapter;
    DatePickerDialog datePickerDialog;
    Button btnSave;
    TextInputEditText editTextGoodEggs,editTextNotTrayGoodEggs,editTextBadEggs,editDatePicker;
    AutoCompleteTextView autoCompleteFlocks,autoCompleteRemarks;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_eggs);
        autoCompleteFlocks=findViewById(R.id.autoComplete);
        autoCompleteRemarks=findViewById(R.id.autoCompleteRemarks);
        editTextGoodEggs=findViewById(R.id.editTextGoodEggs);
        editTextNotTrayGoodEggs=findViewById(R.id.editTextNotTrayGoodEggs);
        editTextBadEggs=findViewById(R.id.editTextBadEggs);
        editDatePicker=findViewById(R.id.editTextDatePicker);
        btnSave=findViewById(R.id.buttonSave);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();
        arrayAdapter=new ArrayAdapter<String>(this, R.layout.remarks,remarks);
        autoCompleteRemarks.setAdapter(arrayAdapter);
        editDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEggsInformation();
            }
        });

    }
    private void AddEggsInformation(){
        String flock=autoCompleteFlocks.getText().toString().trim();
        String remarks=autoCompleteRemarks.getText().toString().trim();
        int goodEggs=Integer.parseInt(String.valueOf(editTextGoodEggs.getText()));
        int notTray=Integer.parseInt(String.valueOf(editTextNotTrayGoodEggs.getText()));
        int badEggs=Integer.parseInt(String.valueOf(editTextBadEggs.getText()));
        String date=editDatePicker.getText().toString().trim();

        if(flock.isEmpty()){
            autoCompleteFlocks.setError("Flock Field required");
        }
        if(remarks.isEmpty()){
            autoCompleteRemarks.setError("Remarks Field required");
        }
        if(String.valueOf(goodEggs).isEmpty()){
            editTextGoodEggs.setError("Good Eggs Field required");
        }
        if(String.valueOf(notTray).isEmpty()){
            editTextNotTrayGoodEggs.setError("Good Eggs Not Tray Field required");
        }
        if(String.valueOf(badEggs).isEmpty()){
            editTextBadEggs.setError("Bad Eggs Field required");
        }
        if(date.isEmpty()){
            editDatePicker.setError("Date picker Field required");
        }
        HashMap<String, Object> hashMap=new HashMap<>();
        hashMap.put("Flock",flock);
        hashMap.put("Remarks",remarks);
        hashMap.put("Good Eggs",goodEggs);
        hashMap.put("Good Eggs Not in Tray",notTray);
        hashMap.put("Bad Eggs",badEggs);
        hashMap.put("Date Collected",date);
        databaseReference.child("Additional Eggs")
                .child(flock).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AdditionEggs.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdditionEggs.this,"Data Failed to be added",Toast.LENGTH_SHORT).show();

                    }
                });
    }
    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                editDatePicker.setText(i2+"/"+i1+"/"+i);
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