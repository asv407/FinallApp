package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
    public ArrayList<String[]> arrayList = new ArrayList<>();
    public BufferedReader reader;
    public String name;
    public String[] words;
    public int res = 0;
    public int countTrueAns = 0;
    public int maxBalls = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_radio);
        super.onCreate(savedInstanceState);
        int background_color = getResources().getColor(R.color.backgroung);
        getWindow().getDecorView().setBackgroundColor(background_color);
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
            File sdcard = Environment.getExternalStorageDirectory();
            File f = new File(sdcard,"tests.csv");
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null) {
                line = line.replace('~', '\n');
                String[] words = line.split(";");
                if(!name.equals(words[0]))
                {
                    line = reader.readLine();

                    continue;
                }

                arrayList.add(words);
                line = reader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        if(arrayList.size() == 0)
        {
            Intent intent = new Intent(RadioActivity.this, passTestActivity.class);
            intent.putExtra("isNorm", false);
            startActivity(intent);
        }
        else
        {
            words = arrayList.get(i);
            textView.setText(words[1]);
            radioButton1.setText(words[2]);
            radioButton2.setText(words[3]);
            radioButton3.setText(words[4]);
            radioButton4.setText(words[5]);
        }


        final Button b = findViewById(R.id.nexts_Button);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                if(radioButton1.isChecked())
                {
                    maxBalls += Integer.parseInt(words[7]);
                    if(Integer.parseInt(words[6]) == 1)
                    {
                        res += Integer.parseInt(words[7]);
                        countTrueAns++;
                    }
                }
                if(radioButton2.isChecked())
                {
                    maxBalls += Integer.parseInt(words[7]);
                    if(Integer.parseInt(words[6]) == 2)
                    {
                        res += Integer.parseInt(words[7]);
                        countTrueAns++;
                    }
                }
                if(radioButton3.isChecked())
                {
                    maxBalls += Integer.parseInt(words[7]);
                    if(Integer.parseInt(words[6]) == 3)
                    {
                        res += Integer.parseInt(words[7]);
                        countTrueAns++;
                    }
                }
                if(radioButton4.isChecked())
                {
                    maxBalls += Integer.parseInt(words[7]);
                    if(Integer.parseInt(words[6]) == 4)
                    {
                        res += Integer.parseInt(words[7]);
                        countTrueAns++;
                    }
                }
                i++;
                if(i >= arrayList.size())
                {
                    Intent intent = new Intent(RadioActivity.this, resultsBackgroundActivity.class);
                    intent.putExtra("result", res);
                    intent.putExtra("countTrue", countTrueAns);
                    intent.putExtra("maxBalls", maxBalls);
                    intent.putExtra("nameTest", name);
                    intent.putExtra("count", arrayList.size());
                    startActivity(intent);
                }
                else
                {
                    words = arrayList.get(i);
                    radioGroup11.check(R.id.radio1);
                    textView.setText(words[1]);
                    radioButton1.setText(words[2]);
                    radioButton2.setText(words[3]);
                    radioButton3.setText(words[4]);
                    radioButton4.setText(words[5]);
                }

            }
        });
    }
}