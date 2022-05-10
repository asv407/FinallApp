package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createQuestionActivity extends AppCompatActivity
{
    public EditText questionName;
    public EditText varA;
    public EditText varB;
    public EditText varC;
    public EditText varD;
    public EditText trueAns;
    public EditText balls;
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("testName").toString();
        questionName = (EditText) findViewById(R.id.questionName);
        varA = (EditText) findViewById(R.id.varA);
        varB = (EditText) findViewById(R.id.varB);
        varC = (EditText) findViewById(R.id.varC);
        varD = (EditText) findViewById(R.id.varD);
        trueAns = (EditText) findViewById(R.id.trueAns);
        balls = (EditText) findViewById(R.id.balls);
        final Button b = (Button)findViewById(R.id.next_Button);
        final Button c = (Button)findViewById(R.id.finish_Button);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {

            }
        });
        c.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(createQuestionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}