package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Score extends AppCompatActivity {
    ListView listView;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);



        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("id1");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        DatabaseReference ref = database.child("users").child(stuff).child("quizz").child("scores");

        listView = (ListView) findViewById(R.id.quizzmarks);


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String value = postSnapshot.getValue(String.class);
                    String key = postSnapshot.getKey();
                    arrayList.add(key + "  :  " + value);
                    arrayAdapter = new ArrayAdapter<String>(Score.this, R.layout.list_view_item, arrayList);
                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void back(View view) {
        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("name2");


        Intent i = new Intent(Score.this, Studentinfo1.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("name1", stuff);
        i.putExtras(bundle1);
        startActivity(i);
    }
}
