package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioActivity extends AppCompatActivity
{
    public RadioGroup radioGroup11;
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public TextView textView;
    public int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_radio);
        super.onCreate(savedInstanceState);
        radioGroup11 = findViewById(R.id.radioGroup1);
        radioButton1= findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        radioButton4 = findViewById(R.id.radio4);
        textView = findViewById(R.id.textQuestion);


        final Button b = findViewById(R.id.nexts_Button);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                i++;
                String as = "Example of question \n # " + String.valueOf(i);
                textView.setText(as);
                radioGroup11.check(R.id.radio1);
                radioButton1.setText("example # " + String.valueOf(i));
                radioButton2.setText("example # " + String.valueOf(i + 1));
                radioButton3.setText("example # " + String.valueOf(i + 2));
                radioButton4.setText("example # " + String.valueOf(i + 3));
            }
        });
    }
}