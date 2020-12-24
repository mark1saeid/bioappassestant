package com.troy.assestantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog;
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    List<String> test=new ArrayList<String>();

    boolean connected = false;
   public static String classchoosen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (classchoosen == null){
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Choose Class", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, chat.class);
                    intent.putExtra("class", classchoosen);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Chat Pressed", Toast.LENGTH_SHORT).show();
                }


                        }});







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
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.classchoosen);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t2.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.classchoosen);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t3.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.classchoosen);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t4.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.classchoosen);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t5.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.classchoosen);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t6.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.classchoosen);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });



        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t7.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.classchoosen);
                t8.setBackgroundResource(R.drawable.roundedtext);
            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classchoosen = t8.getText().toString();
                getClasschoosen(classchoosen);
                t1.setBackgroundResource(R.drawable.roundedtext);
                t2.setBackgroundResource(R.drawable.roundedtext);
                t3.setBackgroundResource(R.drawable.roundedtext);
                t4.setBackgroundResource(R.drawable.roundedtext);
                t5.setBackgroundResource(R.drawable.roundedtext);
                t6.setBackgroundResource(R.drawable.roundedtext);
                t7.setBackgroundResource(R.drawable.roundedtext);
                t8.setBackgroundResource(R.drawable.classchoosen);
            }
        });



        Fragment mFragment = new Home();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, mFragment).commit();



        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.nice_spinner);
        test.add("Menu");
        test.add("Home");


        spinner.setItems(test);

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {


                if (item.equals("Home")){
                    Fragment mFragment = new Home();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.content_frame, mFragment).commit();

                }

            }
        });









        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        else {
            connected = false;
            new TTFancyGifDialog.Builder(MainActivity.this)
                    .setTitle("No Internet!")
                    .setMessage("Please make sure to connect your phone to the internet.")
                    .setPositiveBtnText("Ok")
                    .setPositiveBtnBackground("#3490dc")
                    .setGifResource(R.drawable.nonetwork)      //pass your gif, png or jpg
                    .isCancellable(true)
                    .OnPositiveClicked(new TTFancyGifDialogListener() {
                        @Override
                        public void OnClick() {
                            Toast.makeText(MainActivity.this,"Ok",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build();
        }

    }


    public String  getClasschoosen(String s){
        return s;
    }

    //Notification by Firebase

    private void addNotification() {
        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Test")
                .setContentText("TEEEEEEESSSSSSSSST");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }



    public void openbarcode(View view) {
     //   Intent intent = new Intent (MainActivity.this, MyBarcode.class);
     //   startActivity(intent);
    }
    }

