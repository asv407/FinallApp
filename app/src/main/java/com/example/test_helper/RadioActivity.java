package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class RadioActivity extends AppCompatActivity
{
    public RadioGroup radioGroup11;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        radioGroup11 = findViewById(R.id.radioGroup1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        final Button b = findViewById(R.id.nexts_Button);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
               // radioGroup11.check(R.id.radio1);
            }
        });
    }
}