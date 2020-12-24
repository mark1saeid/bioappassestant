package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Reason extends AppCompatActivity {

    private FirebaseRecyclerAdapter<ReasonModel, NewsViewHolder> mPeopleRVAdapter;
    RecyclerView mPeopleRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason);

        Intent intent=getIntent();
        String s = intent.getStringExtra("class");



        mPeopleRV = findViewById(R.id.newsrecyclar);
        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child(s).child("Events");
        Query personsQuery = personsRef.orderByKey();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<ReasonModel>().setQuery(personsQuery, ReasonModel.class).build();
        mPeopleRVAdapter = new FirebaseRecyclerAdapter<ReasonModel, Reason.NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(Reason.NewsViewHolder holder, final int position, final ReasonModel model) {


                holder.setname(model.getName());
                holder.setreason(model.getReason());
                holder.setmarks(model.getMarks());

            }

            @Override
            public Reason.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.reasonpost, parent, false);

                return new Reason.NewsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);
    }

    public void back(View view) {
        Intent intent = new Intent(Reason.this, MainActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent=getIntent();
        String s = intent.getStringExtra("class");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child(s).child("Events").removeValue();
        Toast.makeText(Reason.this,"Deleted",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();
    }







    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

      public void setname(String data){
            TextView textView = mView.findViewById(R.id.name);
            textView.setText(data);
      }
        public void setreason(String data){
            TextView textView = mView.findViewById(R.id.reason);
            textView.setText(data);
        }
        public void setmarks(String data){
            TextView textView = mView.findViewById(R.id.marks);
            textView.setText(data);
        }
    }

}
