package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    public boolean ban = true;
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
                ban = false;
                try
                {
                    int gf = Integer.parseInt(trueAns.getText().toString());
                    if(gf > 4 || gf <= 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
                        builder.setTitle(R.string.dialog_title);
                        builder.setMessage(R.string.dialog_message);
                        builder.setCancelable(true);
                        builder.setIcon(R.drawable.icon);
                        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                            dialog.dismiss(); // Отпускает диалоговое окно
                        });
                        builder.show();
                        ban = true;
                    }
                }catch (NumberFormatException e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
                    builder.setTitle(R.string.dialog_title);
                    builder.setMessage(R.string.dialog_message);
                    builder.setCancelable(true);
                    builder.setIcon(R.drawable.icon);
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Отпускает диалоговое окно
                        }
                    });
                    builder.show();
                    ban = true;
                }
                if(!ban)
                {
                    newQuestion += ";";
                    newQuestion += balls.getText().toString();
                    try
                    {
                        int gf = Integer.parseInt(balls.getText().toString());
                    }catch (IllegalArgumentException | NullPointerException e){
                        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
                        builder.setTitle(R.string.dialog_title);
                        builder.setMessage(R.string.dialog_message_balls);
                        builder.setCancelable(true);
                        builder.setIcon(R.drawable.icon);
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); // Отпускает диалоговое окно
                            }
                        });
                        builder.show();
                        ban = true;
                    }
                    if(!ban)
                    {
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
                        } catch (IOException e)
                        {
                        }
                    }
                }
            }
        });
        c.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                ban = true;
                AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
                builder.setTitle("Вы уверены?");
                builder.setCancelable(true);
                builder.setIcon(R.drawable.comet);
                // Кнопка ОК
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        Intent intent = new Intent(createQuestionActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
    }
}