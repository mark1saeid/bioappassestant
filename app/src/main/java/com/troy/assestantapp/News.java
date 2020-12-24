package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {
    List<String> stclass =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final EditText title = findViewById(R.id.post_title);
        final EditText image = findViewById(R.id.post_image);
        final EditText desc = findViewById(R.id.post_desc);

        Intent intent=getIntent();
        final String classchoosen = intent.getStringExtra("class");
        Toast.makeText(News.this,classchoosen,Toast.LENGTH_LONG).show();

        if (classchoosen == null) {
            Toast.makeText(this, "please choose class", Toast.LENGTH_LONG).show();
            Intent intents = new Intent (News.this, MainActivity.class);
            startActivity(intents);
        }

        if (title == null || desc == null){
            Button button3 = findViewById(R.id.post);
            button3.setEnabled(false);
        }else {
            Button button3 = findViewById(R.id.post);
            button3.setEnabled(true);
        }

        Button button3 = findViewById(R.id.post);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

post(classchoosen);



            }
        });



    }
    public void post(final String classchoosen) {
        final EditText title = findViewById(R.id.post_title);
        final EditText image = findViewById(R.id.post_image);
        final EditText desc = findViewById(R.id.post_desc);

                String t = title.getText().toString();
                String i = image.getText().toString();
                String d = desc.getText().toString();


                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference mDbRef = mDatabase.getReference().child(classchoosen).child("News").push();
                mDbRef.child("title").setValue(t);
                mDbRef.child("image").setValue(i);
                mDbRef.child("desc").setValue(d);

                Toast.makeText(News.this,"Posted Successfully", Toast.LENGTH_SHORT).show();

    }
    public void back(View view) {
        Intent intent = new Intent (News.this, MainActivity.class);
        startActivity(intent);
    }
}
