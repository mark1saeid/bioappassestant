package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Live extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    RecyclerView mPeopleRV;

    private FirebaseRecyclerAdapter<ChatModel, Live.NewsViewHolder> mPeopleRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();





        Intent intent=getIntent();
        final String classchoosen = intent.getStringExtra("class");


        if (classchoosen == null) {
            Toast.makeText(this, "please choose class", Toast.LENGTH_LONG).show();
            Intent intents = new Intent (Live.this, MainActivity.class);
            startActivity(intents);
        }

        Toast.makeText(Live.this,classchoosen,Toast.LENGTH_LONG).show();









        mPeopleRV = findViewById(R.id.newsrecyclar);
        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child(classchoosen).child("Live").child("Chats");
        Query personsQuery = personsRef.orderByKey();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<ChatModel>().setQuery(personsQuery, ChatModel.class).build();
        mPeopleRVAdapter = new FirebaseRecyclerAdapter<ChatModel, Live.NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(Live.NewsViewHolder holder, final int position, final ChatModel model) {
                holder.setName(model.getName());
                holder.setMesg(model.getMesg());
                holder.remove(model.getMesg(),classchoosen);

            }

            @Override
            public Live.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chatmessage, parent, false);

                return new Live.NewsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

    }

    public void back(View view) {
        Intent intent = new Intent (Live.this, MainActivity.class);
        startActivity(intent);
    }


    public void send(View view) {

        Intent intent=getIntent();
        String classchoosen = intent.getStringExtra("class");

        EditText mess = findViewById(R.id.message);

        DatabaseReference ss = FirebaseDatabase.getInstance().getReference().child(classchoosen).child("Live").child("Chats").push();
        ss.child("name").setValue("Assistant");
        ss.child("mesg").setValue(mess.getText().toString());
        Toast.makeText(Live.this,"message sent",Toast.LENGTH_SHORT).show();
        mess.setText("");

    }

    public void livelink(View view) {

        Intent intent=getIntent();
        String classchoosen = intent.getStringExtra("class");

        TextView link = findViewById(R.id.livelink);

        DatabaseReference ss = FirebaseDatabase.getInstance().getReference().child(classchoosen).child("Live");
        ss.child("link").setValue(link.getText().toString());
        Toast.makeText(Live.this,"live sent",Toast.LENGTH_SHORT).show();

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

        public void setName(String names) {
            TextView name = (TextView) mView.findViewById(R.id.name);
            name.setText(names);
        }

        public void setMesg(String mesgs) {
            TextView mesg = (TextView) mView.findViewById(R.id.chatmessage);
            mesg.setText(mesgs);
        }
        public void remove(final String mesgs , final String classchoosen) {

            final Query userQuery = FirebaseDatabase.getInstance().getReference().child(classchoosen).child("Live").child("Chats").orderByChild("mesg");
            userQuery.equalTo(mesgs).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot foodSnapshot: dataSnapshot.getChildren()) {
                                // result
                                final String key = foodSnapshot.getKey();
                                ImageView info = mView.findViewById(R.id.remove);
                                info.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference().child(classchoosen).child("Live").child("Chats");
                                        personsRef.child(key).removeValue();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }
            );


        }



    }


}
