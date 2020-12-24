package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Date;

public class ScanBarcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

      Button buttonScan = findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonScan_onClick(view);
            }
        });


    }
    private void buttonScan_onClick(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            String productId = intentResult.getContents();
            TextView studentid = findViewById(R.id.studentid);
            studentid.setText(productId);

            Date d = new Date();
            CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());


            DatabaseReference QA = FirebaseDatabase.getInstance().getReference().child("users").child(productId);
            QA.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.child("name").getValue(String.class);
                    TextView Studentname = findViewById(R.id.studentname);
                    Studentname.setText(value);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
           final DatabaseReference mDbRef = mDatabase.getReference().child("users").child(productId);
            mDbRef.child("Attend").push().child("date").setValue(s);


            Button buttonScan = findViewById(R.id.quizzvalid);
            buttonScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDbRef.child("qlocked").setValue("1");
                }
            });

            Toast.makeText(getApplicationContext(), productId, Toast.LENGTH_LONG).show();
        }
    }
    public void back(View view) {
        Intent intent = new Intent (ScanBarcode.this, MainActivity.class);
        startActivity(intent);
    }
}
