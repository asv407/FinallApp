package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RadioActivity extends AppCompatActivity
{
    public RadioGroup radioGroup11;
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public TextView textView;
    public int i = 0;
    public ArrayList<String[]> arrayList;
    public BufferedReader reader;
    public String name;
    public String[] words;
    public int res = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_radio);
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("testName").toString();
        radioGroup11 = findViewById(R.id.radioGroup1);
        radioButton1= findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        radioButton4 = findViewById(R.id.radio4);
        textView = findViewById(R.id.textQuestion);
        try
        {
            File f = new File("D://ChangZhi/tests.cvs");
            FileReader fr = new FileReader(f);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            StringBuffer nameTest = new StringBuffer();
            while (line != null) {
                String[] words = line.split(";");
                if(words[0] != name)
                {
                    continue;
                }
                arrayList.add(words);
                line = reader.readLine();
            }
        } catch (IOException e){}
        words = arrayList.get(i);
        textView.setText(words[1]);
        radioButton1.setText(words[2]);
        radioButton2.setText(words[3]);
        radioButton3.setText(words[4]);
        radioButton4.setText(words[5]);
        final Button b = findViewById(R.id.nexts_Button);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                if(radioButton1.isChecked())
                {
                    if(Integer.parseInt(words[6]) == 1)
                    {
                        res += Integer.parseInt(words[7]);
                    }
                }
                if(radioButton2.isChecked())
                {
                    if(Integer.parseInt(words[6]) == 2)
                    {
                        res += Integer.parseInt(words[7]);
                    }
                }
                if(radioButton3.isChecked())
                {
                    if(Integer.parseInt(words[6]) == 3)
                    {
                        res += Integer.parseInt(words[7]);
                    }
                }
                if(radioButton4.isChecked())
                {
                    if(Integer.parseInt(words[6]) == 4)
                    {
                        res += Integer.parseInt(words[7]);
                    }
                }
                i++;
                words = arrayList.get(i);
                radioGroup11.check(R.id.radio1);
                textView.setText(words[1]);
                radioButton1.setText(words[2]);
                radioButton2.setText(words[3]);
                radioButton3.setText(words[4]);
                radioButton4.setText(words[5]);

            }
        });
    }
}