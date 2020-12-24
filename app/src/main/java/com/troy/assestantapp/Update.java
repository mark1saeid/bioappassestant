package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        DatabaseReference QA = FirebaseDatabase.getInstance().getReference().child("update");

        QA.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView cv = findViewById(R.id.cv);
                String v = dataSnapshot.child("v").getValue(String.class);
                cv.setText(v);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public void back(View view) {
        Intent intent = new Intent (Update.this, MainActivity.class);
        startActivity(intent);
    }

    public void sendupdate(View view) {
        EditText link = findViewById(R.id.updatelink);
        EditText version = findViewById(R.id.updateversion);
        DatabaseReference ss = FirebaseDatabase.getInstance().getReference().child("update");
        ss.child("v").setValue(version.getText().toString());
        ss.child("link").setValue(link.getText().toString());
        Toast.makeText(Update.this,"update sent",Toast.LENGTH_SHORT).show();

    }
}
