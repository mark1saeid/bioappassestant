package com.troy.assestantapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword,p1,p2,phonenum,name;
    private FirebaseAuth auth;
    private Button btnSignUp;
    private ProgressDialog PD;
    private List<String> test=new ArrayList<String>();
    private MaterialSpinner spinner;
    private String classchoosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);
        auth = FirebaseAuth.getInstance();
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.username);
        p1 = (EditText) findViewById(R.id.p1);
        p2 = (EditText) findViewById(R.id.p2num);
        phonenum = (EditText) findViewById(R.id.phonenumber);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);

        spinner = (MaterialSpinner) findViewById(R.id.nice_spinner);
        test.add("Class");
        test.add("1st prep");
        test.add("2nd prep");
        test.add("3rd prep");
        test.add("1st sec");
        test.add("2nd sec");
        test.add("3rd sec");

        spinner.setItems(test);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {


                if (item.equals("1st prep")){
                    classchoosen=item;

                }
                if (item.equals("2nd prep")){
                    classchoosen=item;
                }
                if (item.equals("3rd prep")){
                    classchoosen=item;
                }
                if (item.equals("1st sec")){
                    classchoosen=item;
                }
                if (item.equals("2nd sec")){
                    classchoosen=item;
                }
                if (item.equals("3rd sec")){
                    classchoosen=item;
                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                final String names = name.getText().toString();
                final String p1s = p1.getText().toString();
                final String p2s = p2.getText().toString();
                final String ps = phonenum.getText().toString();


                Toast.makeText(
                        RegisterActivity.this,
                        classchoosen,
                        Toast.LENGTH_LONG).show();






                try {
                    if (password.length() > 0 && email.length() > 0) {
                        PD.show();
                        //authenticate user
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(
                                                    RegisterActivity.this,
                                                    "Authentication Failed",
                                                    Toast.LENGTH_LONG).show();
                                            Log.v("error", task.getResult().toString());
                                        } else {
                                            auth = FirebaseAuth.getInstance();
                                            FirebaseUser user = auth.getCurrentUser();
                                            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                                            final DatabaseReference mDbRef = mDatabase.getReference().child("users").child(user.getUid());
                                            mDbRef.child("name").setValue(names);
                                            mDbRef.child("phonenum").setValue(ps);
                                            mDbRef.child("p1num").setValue(p1s);
                                            mDbRef.child("p2num").setValue(p2s);
                                            mDbRef.child("stcl").setValue(classchoosen);
                                            mDbRef.child("qlocked").setValue("1");
                                            mDbRef.child("email").setValue(user.getEmail());
                                            Toast.makeText(
                                                    RegisterActivity.this,
                                                    "A Student Added",
                                                    Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        PD.dismiss();
                                    }
                                });
                    } else {
                        Toast.makeText(
                                RegisterActivity.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }
    public void back(View view) {
        Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
