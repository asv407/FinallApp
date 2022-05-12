package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class createQuestionActivity extends AppCompatActivity
{
    public EditText questionName;
    public EditText varA;
    public EditText varB;
    public EditText varC;
    public EditText varD;
    public EditText trueAns;
    public EditText balls;
    public FileOutputStream writer;
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        int background_color = getResources().getColor(R.color.backgroung);
        getWindow().getDecorView().setBackgroundColor(background_color);
        File sdcard = Environment.getExternalStorageDirectory();
        File f = new File(sdcard,"tests.csv");
        try
        {
            writer = new FileOutputStream(f, true);
        }catch (IOException e){}
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
                String newQuestion = name;
                newQuestion += ";";
                newQuestion += questionName.getText().toString();
                newQuestion += ";";
                newQuestion += varA.getText().toString();
                newQuestion += ";";
                newQuestion += varB.getText().toString();
                newQuestion += ";";
                newQuestion += varC.getText().toString();
                newQuestion += ";";
                newQuestion += varD.getText().toString();
                newQuestion += ";";
                newQuestion += trueAns.getText().toString();
                newQuestion += ";";
                newQuestion += balls.getText().toString();
                newQuestion = newQuestion.replace('\n', '~');
                newQuestion += '\n';
                questionName.getText().clear();
                varA.getText().clear();
                varB.getText().clear();
                varC.getText().clear();
                varD.getText().clear();
                trueAns.getText().clear();
                balls.getText().clear();
                try
                {
                    writer.write(newQuestion.getBytes());
                }catch (IOException e){}

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