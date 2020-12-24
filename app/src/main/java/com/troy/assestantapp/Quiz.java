package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {
    List<String> test1=new ArrayList<String>();
    List<String> test=new ArrayList<String>();
    public String Ass ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent=getIntent();
        final String classchoosen = intent.getStringExtra("class");
        Toast.makeText(Quiz.this,classchoosen,Toast.LENGTH_LONG).show();

        final EditText qnum = findViewById(R.id.qnum);
        final EditText fmark = findViewById(R.id.fullmark);
        final EditText timer = findViewById(R.id.timer);
        final EditText Q = findViewById(R.id.Qtext);
        final EditText QI = findViewById(R.id.Qimage);
        final EditText A = findViewById(R.id.textviewa);
        final EditText B = findViewById(R.id.textViewb);
        final EditText C = findViewById(R.id.textViewc);
        final EditText D = findViewById(R.id.textViewd);


        Button delete = findViewById(R.id.deleteq);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(Quiz.this)
                        .setTitle("Really Delete?")
                        .setMessage("Are you sure you want to delete all data?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                                DatabaseReference mDbRef = mDatabase.getReference().child(classchoosen).child("Quizz");
                                mDbRef.removeValue();
                                Toast.makeText(Quiz.this, "Deleted", Toast.LENGTH_LONG).show();


                                SharedPreferences.Editor editor = getSharedPreferences("saveqs"+classchoosen, MODE_PRIVATE).edit();
                                editor.clear();
                                editor.apply();
                            }
                        }).create().show();
            }
        });


        if (classchoosen == null) {
            Toast.makeText(Quiz.this, "please choose class", Toast.LENGTH_LONG).show();
            Intent intents = new Intent (Quiz.this, MainActivity.class);
            startActivity(intents);
        }


        String qn = qnum.getText().toString();
        String fm = fmark.getText().toString();
        String tm = timer.getText().toString();
       final Button button = findViewById(R.id.Set);





    test.add("Questions");


    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String qn = qnum.getText().toString();
            String fm = fmark.getText().toString();
            String tm = timer.getText().toString();




            if (qnum.getText().toString().equals("")||fmark.getText().toString().equals("")|| timer.getText().toString().equals("")|| classchoosen == null) {
                Toast.makeText(Quiz.this,"Please Fill all EditBoxs",Toast.LENGTH_LONG).show();


            }
            else {

button.setEnabled(false);
                FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference mDbRef = mDatabase.getReference().child(classchoosen).child("Quizz");
                mDbRef.child("fullmark").setValue(fm);
                mDbRef.child("timem").setValue(tm);
                mDbRef.child("qnum").setValue(qn);

                for (int i = 1; i <= Integer.parseInt(qn); i++) {
                    test.add(String.valueOf(i));

                }


                MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.nice_spinner1);
                spinner.setItems(test);
                spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, final String item) {


                        String qn = qnum.getText().toString();
                        String Q1 = Q.getText().toString();
                        String QI1 = QI.getText().toString();
                        String A1 = A.getText().toString();
                        String B1 = B.getText().toString();
                        String C1 = C.getText().toString();
                        String D1 = D.getText().toString();


                        for (int i = 1; i <= Integer.parseInt(qn); i++) {

                            if (item.equals(String.valueOf(i))) {

                                click(String.valueOf(i));
retiveQ(String.valueOf(i));
                            }

                        }
                    }
                });

            }
        }
    });


}

public void click(final String q){


            final TextView textView1 = findViewById(R.id.text1);
            final TextView textView2 = findViewById(R.id.text2);
            final TextView textView3 = findViewById(R.id.text3);
            final TextView textView4 = findViewById(R.id.text4);
            textView1.setBackgroundResource(R.drawable.rc);
            textView2.setBackgroundResource(R.drawable.rc);
            textView3.setBackgroundResource(R.drawable.rc);
            textView4.setBackgroundResource(R.drawable.rc);


            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView1.setBackgroundResource(R.drawable.rb);
                    textView2.setBackgroundResource(R.drawable.rc);
                    textView3.setBackgroundResource(R.drawable.rc);
                    textView4.setBackgroundResource(R.drawable.rc);
                    Ass = "a";

                }
            });

            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView1.setBackgroundResource(R.drawable.rc);
                    textView2.setBackgroundResource(R.drawable.rb);
                    textView3.setBackgroundResource(R.drawable.rc);
                    textView4.setBackgroundResource(R.drawable.rc);
                    Ass = "b";
                }
            });

            textView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView1.setBackgroundResource(R.drawable.rc);
                    textView2.setBackgroundResource(R.drawable.rc);
                    textView3.setBackgroundResource(R.drawable.rb);
                    textView4.setBackgroundResource(R.drawable.rc);
                    Ass = "c";

                }
            });

            textView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView1.setBackgroundResource(R.drawable.rc);
                    textView2.setBackgroundResource(R.drawable.rc);
                    textView3.setBackgroundResource(R.drawable.rc);
                    textView4.setBackgroundResource(R.drawable.rb);
                    Ass = "d";

                }
            });


            final EditText qnum = findViewById(R.id.qnum);
            final EditText fmark = findViewById(R.id.fullmark);
            final EditText timer = findViewById(R.id.timer);
            final EditText Q = findViewById(R.id.Qtext);
            final EditText QI = findViewById(R.id.Qimage);
            final EditText A = findViewById(R.id.textviewa);
            final EditText B = findViewById(R.id.textViewb);
            final EditText C = findViewById(R.id.textViewc);
            final EditText D = findViewById(R.id.textViewd);


            String qn = qnum.getText().toString();
            String fm = fmark.getText().toString();
            String tm = timer.getText().toString();
            String Q1 = Q.getText().toString();
            String QI1 = QI.getText().toString();
            String A1 = A.getText().toString();
            String B1 = B.getText().toString();
            String C1 = C.getText().toString();
            String D1 = D.getText().toString();

         final    Button button3 = findViewById(R.id.finishQ);


                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Q.getText().toString().equals("") || Ass == null|| A.getText().toString().equals("") || B.getText().toString().equals("") || C.getText().toString().equals("") || D.getText().toString().equals("")) {

                            Toast.makeText(Quiz.this, "Editbox is equal null please make sure to fill all edit box", Toast.LENGTH_SHORT).show();

                        } else {


                            String Q1 = Q.getText().toString();
                            String QI1 = QI.getText().toString();
                            String A1 = A.getText().toString();
                            String B1 = B.getText().toString();
                            String C1 = C.getText().toString();
                            String D1 = D.getText().toString();


                            savedata(q+"id",q);
                            savedata(q+"Q1",Q1);
                            savedata(q+"QI1",QI1);
                            savedata(q+"A1",A1);
                            savedata(q+"B1",B1);
                            savedata(q+"C1",C1);
                            savedata(q+"D1",D1);
                            savedata(q+"An",Ass);

                            Intent intent=getIntent();
                            final String classchoosen = intent.getStringExtra("class");

                            if (classchoosen == null) {
                                Toast.makeText(Quiz.this, "please choose class", Toast.LENGTH_LONG).show();
                                Intent intents = new Intent (Quiz.this, MainActivity.class);
                                startActivity(intents);
                            }

                            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                            final DatabaseReference mDbRef = mDatabase.getReference().child(classchoosen).child("Quizz").child("Test").child(q);
                            mDbRef.child("id").setValue(q);
                            mDbRef.child("Q").setValue(Q1);
                            mDbRef.child("An").setValue(Ass);
                            mDbRef.child("image").setValue(QI1);
                            mDbRef.child("a").setValue(A1);
                            mDbRef.child("b").setValue(B1);
                            mDbRef.child("c").setValue(C1);
                            mDbRef.child("d").setValue(D1);

                            Q.setText("");
                            QI.setText("");
                            A.setText("");
                            B.setText("");
                            C.setText("");
                            D.setText("");
Ass = null;
                            textView1.setBackgroundResource(R.drawable.rc);
                            textView2.setBackgroundResource(R.drawable.rc);
                            textView3.setBackgroundResource(R.drawable.rc);
                            textView4.setBackgroundResource(R.drawable.rc);

                            Toast.makeText(Quiz.this, q + "Question is Submitted Successfully", Toast.LENGTH_LONG).show();

                        }
                    }
                });

}


    public void savedata(String key , String value){
        Intent intent=getIntent();
        final String classchoosen = intent.getStringExtra("class");
        SharedPreferences.Editor editor = getSharedPreferences("saveqs"+classchoosen, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }


    public String retrivedata(String key){
        Intent intent=getIntent();
        final String classchoosen = intent.getStringExtra("class");
        SharedPreferences prefs = getSharedPreferences("saveqs"+classchoosen, MODE_PRIVATE);
        String data = prefs.getString(key,null);
        return data;
    }



    public  void retiveQ (String q){
        final EditText Q = findViewById(R.id.Qtext);
        final EditText QI = findViewById(R.id.Qimage);
        final EditText A = findViewById(R.id.textviewa);
        final EditText B = findViewById(R.id.textViewb);
        final EditText C = findViewById(R.id.textViewc);
        final EditText D = findViewById(R.id.textViewd);

        final TextView textView1 = findViewById(R.id.text1);
        final TextView textView2 = findViewById(R.id.text2);
        final TextView textView3 = findViewById(R.id.text3);
        final TextView textView4 = findViewById(R.id.text4);



        if (retrivedata(q+"id") != null) {
            Q.setText(retrivedata(q + "Q1"));
            QI.setText(retrivedata(q + "QI1"));
            A.setText(retrivedata(q + "A1"));
            B.setText(retrivedata(q + "B1"));
            C.setText(retrivedata(q + "C1"));
            D.setText(retrivedata(q + "D1"));


            if (retrivedata(q + "An").equals("a")) {
                textView1.setBackgroundResource(R.drawable.rb);
                textView2.setBackgroundResource(R.drawable.rc);
                textView3.setBackgroundResource(R.drawable.rc);
                textView4.setBackgroundResource(R.drawable.rc);
            }
            if (retrivedata(q + "An").equals("b")) {
                textView1.setBackgroundResource(R.drawable.rc);
                textView2.setBackgroundResource(R.drawable.rb);
                textView3.setBackgroundResource(R.drawable.rc);
                textView4.setBackgroundResource(R.drawable.rc);
            }
            if (retrivedata(q + "An").equals("c")) {
                textView1.setBackgroundResource(R.drawable.rc);
                textView2.setBackgroundResource(R.drawable.rc);
                textView3.setBackgroundResource(R.drawable.rb);
                textView4.setBackgroundResource(R.drawable.rc);
            }
            if (retrivedata(q + "An").equals("d")) {
                textView1.setBackgroundResource(R.drawable.rc);
                textView2.setBackgroundResource(R.drawable.rc);
                textView3.setBackgroundResource(R.drawable.rc);
                textView4.setBackgroundResource(R.drawable.rb);
            }
        }
    }

    public void back(View view) {
        Intent intent = new Intent (Quiz.this, MainActivity.class);
        startActivity(intent);
    }

}