package com.troy.assestantapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class Home extends Fragment {
    FrameLayout button1,button2,button3,button4,button5,button6,button7,button8,button9;

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);






        button1 =(FrameLayout)view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(), ScanBarcode.class);
                startActivity(intent);

            }
        });


        button2 =(FrameLayout)view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = MainActivity.classchoosen;

                Intent intent = new Intent(getActivity(), Quiz.class);
                intent.putExtra("class",str);
                startActivity(intent);


            }

        });


        button3 =(FrameLayout)view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://console.firebase.google.com/u/0/project/chemapp-d6b37/notification"));
                startActivity(browserIntent);
            }
        });

        button4 =(FrameLayout)view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (getActivity(), RegisterActivity.class);
                  startActivity(intent);


            }
        });

        button5 =(FrameLayout)view.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = MainActivity.classchoosen;

                    Intent intent = new Intent(getActivity(), Homework.class);
                    intent.putExtra("class",str);
                    startActivity(intent);



            }
        });


        button6 =(FrameLayout)view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classchoosen = MainActivity.classchoosen;
                if (classchoosen==null) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Choose Class", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), Reason.class);
                    intent.putExtra("class", classchoosen);
                    startActivity(intent);

                }
            }
        });


        button7 =(FrameLayout)view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getActivity(), StudentInfo.class);
                startActivity(intent);
            }
        });

        button8 =(FrameLayout)view.findViewById(R.id.news);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = MainActivity.classchoosen;

                    button8.setEnabled(true);
                    Intent intent = new Intent(getActivity(), News.class);
                    intent.putExtra("class",str);
                    startActivity(intent);

            }
        });


        button9 =(FrameLayout)view.findViewById(R.id.live);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classchoosen = MainActivity.classchoosen;
                if (classchoosen==null) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Choose Class", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), Live.class);
                    intent.putExtra("class", classchoosen);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Live Pressed", Toast.LENGTH_SHORT).show();
                }  }
        });

        FrameLayout button10 = (FrameLayout) view.findViewById(R.id.button9);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classchoosen = MainActivity.classchoosen;

                    Intent intent = new Intent(getActivity(), Update.class);
                    startActivity(intent);
            }
        });

        return view;
    }


}
