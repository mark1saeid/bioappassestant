package com.troy.assestantapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentInfo extends AppCompatActivity {
    ListView listView;
    FirebaseAuth auth;
    FirebaseUser user;
    EditText etSearch;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();




        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        DatabaseReference ref = database.child("users");

        listView = (ListView) findViewById(R.id.studentinfo);
        etSearch = findViewById(R.id.etSearch);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    String key = postSnapshot.getKey();
                    if (key.equals("name")) {
                        String value = postSnapshot.getValue(String.class);
                        arrayList.add(value);
                    }


                    arrayAdapter = new ArrayAdapter<String>(StudentInfo.this, R.layout.list_view_item, arrayList);
                    listView.setAdapter(arrayAdapter);


                    etSearch.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            arrayAdapter.getFilter().filter(s);
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);

                            Intent i = new Intent(StudentInfo.this, Studentinfo1.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name1", selectedItem);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
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
        Intent intent = new Intent (StudentInfo.this, MainActivity.class);
        startActivity(intent);
    }
}
