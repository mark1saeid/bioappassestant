package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Date;

public class Studentinfo1 extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    public static String classchoosen ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo1);

        final TextView name = findViewById(R.id.studentname);
        final TextView email = findViewById(R.id.studentemail);
        final TextView phone = findViewById(R.id.studentphone);
        final TextView p1num = findViewById(R.id.student_p1num);
        final TextView p2num = findViewById(R.id.student_p2num);
        final TextView stid = findViewById(R.id.studentid);
        final TextView stHW = findViewById(R.id.student_Hw);
        final TextView stHclass = findViewById(R.id.studentclass);

        Bundle bundle = getIntent().getExtras();
        final String stuff = bundle.getString("name1");
        name.setText(stuff);















        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("users");
        final Query userQuery = ref.orderByChild("name").equalTo(stuff);

        userQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                stid.setText(key);
               // for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {


                    String email5 = dataSnapshot.child("email").getValue(String.class);
                    String phone5 = dataSnapshot.child("phonenum").getValue(String.class);
                    String p1num5 = dataSnapshot.child("p1num").getValue(String.class);
                    String p2num5 = dataSnapshot.child("p2num").getValue(String.class);
                    String stHw = dataSnapshot.child("HWsubmit").getValue(String.class);
                    String stclass = dataSnapshot.child("stcl").getValue(String.class);

                    email.setText(email5);
                    phone.setText(phone5);
                    p1num.setText(p1num5);
                    p2num.setText(p2num5);
                    stHW.setText(stHw);
                    stHclass.setText(stclass);

                    click(key,stuff);

             //   }
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
        Intent intent = new Intent (Studentinfo1.this, StudentInfo.class);
        startActivity(intent);
    }
    public void click(final String key, final String na) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mDbRef = mDatabase.getReference().child("users").child(key);
        Button button1 =findViewById(R.id.Scores);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Studentinfo1.this, Score.class);
                Bundle bundle = new Bundle();
                bundle.putString("id1",key);
                bundle.putString("name2",na);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        Button button2 =findViewById(R.id.Attends);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Studentinfo1.this, Attend.class);
                Bundle bundle = new Bundle();
                bundle.putString("id1",key);
                bundle.putString("name2",na);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        Button button3 =findViewById(R.id.deleteuser);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TTFancyGifDialog.Builder(Studentinfo1.this)
                        .setTitle("Are You Sure")
                        .setMessage("You will remove student data")
                        .setPositiveBtnText("Ok,I Know")
                        .setPositiveBtnBackground("#3490dc")
                        .setGifResource(R.drawable.sadma)
                        .isCancellable(true)
                        .OnPositiveClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                mDbRef.removeValue();


                            }
                        })
                        .setNegativeBtnBackground("#c1272d")
                        .setNegativeBtnText("Cancel")
                        .OnNegativeClicked(new TTFancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(Studentinfo1.this,"Cancel",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
            }
        });



        Button buttonquiz = findViewById(R.id.validquiz);
        buttonquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbRef.child("qlocked").setValue("1");
                Toast.makeText(Studentinfo1.this,"unlocked",Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonHWs = findViewById(R.id.hws);
        buttonHWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Date d = new Date();
                CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
                mDbRef.child("HWsubmit").setValue(s);
                Toast.makeText(Studentinfo1.this,"HomeWork Submitted",Toast.LENGTH_SHORT).show();
       TextView hw = findViewById(R.id.student_Hw);
       hw.setText(s);
            }
        });

        setClass(key);

    }

    public void  setClass(final String id){

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mDbRef = mDatabase.getReference().child("users").child(id);


        final TextView t1=(TextView) findViewById(R.id.t1);
        final TextView t2=(TextView) findViewById(R.id.t2);
        final TextView t3=(TextView) findViewById(R.id.t3);
        final TextView t4=(TextView) findViewById(R.id.t4);
        final TextView t5=(TextView) findViewById(R.id.t5);
        final TextView t6=(TextView) findViewById(R.id.t6);
        final TextView t7=(TextView) findViewById(R.id.t7);
        final TextView t8=(TextView) findViewById(R.id.t8);





        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t1.getText().toString();

                t1.setBackgroundResource(R.drawable.classchoosen);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();

                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t2.getText().toString();

                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.classchoosen);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t3.getText().toString();

                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.classchoosen);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t4.getText().toString();

                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.classchoosen);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t5.getText().toString();

                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.classchoosen);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t6.getText().toString();

                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.classchoosen);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t7.getText().toString();

                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.classchoosen);
                t8.setBackgroundResource(R.drawable.roundedtext);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t8.getText().toString();
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.classchoosen);
                mDbRef.child("stcl").setValue(classchoosen);
                Toast.makeText(Studentinfo1.this,"Group Changed To "+classchoosen,Toast.LENGTH_SHORT).show();
                TextView classchoosenTV = findViewById(R.id.studentclass);
                classchoosenTV.setText(classchoosen);
            }
        });
    }



}
