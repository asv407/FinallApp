package com.example.test_helper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class createQuestionActivity extends AppCompatActivity
{
    private EditText questionName;
    private boolean ban = true;
    private EditText varA;
    private EditText varB;
    private EditText varC;
    private EditText varD;
    private EditText trueAns;
    private EditText balls;
    private FileOutputStream writer;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        File sdcard = Environment.getExternalStorageDirectory();
        File f = new File(sdcard, "tests.csv");
        try
        {
            writer = new FileOutputStream(f, true);
        } catch (IOException e)
        {
        }
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("testName").toString();
        questionName = (EditText) findViewById(R.id.questionName);
        varA = (EditText) findViewById(R.id.varA);
        varB = (EditText) findViewById(R.id.varB);
        varC = (EditText) findViewById(R.id.varC);
        varD = (EditText) findViewById(R.id.varD);
        trueAns = (EditText) findViewById(R.id.trueAns);
        balls = (EditText) findViewById(R.id.balls);
        final ImageButton b = (ImageButton) findViewById(R.id.next_Buttons);
        final ImageButton c = (ImageButton) findViewById(R.id.finish_Button);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newQuestion = name;
                newQuestion = newQuestion.replace(';', 'ў');
                newQuestion += ";";
                String add;
                add = questionName.getText().toString();
                add = add.replace(';', 'ў');
                newQuestion += add;
                newQuestion += ";";
                add = varA.getText().toString();
                add = add.replace(';', 'ў');
                newQuestion += add;
                newQuestion += ";";
                add = varB.getText().toString();
                add = add.replace(';', 'ў');
                newQuestion += add;
                newQuestion += ";";
                add = varC.getText().toString();
                add = add.replace(';', 'ў');
                newQuestion += add;
                newQuestion += ";";
                add = varD.getText().toString();
                add = add.replace(';', 'ў');
                newQuestion += add;
                newQuestion += ";";
                ban = false;
                int numTrue = 0;
                try
                {
                    int gf = Integer.parseInt(trueAns.getText().toString());
                    if (gf > 4 || gf <= 0)
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
                    } else
                    {
                        numTrue = gf;
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
                    if (numTrue == 1)
                    {
                        String str = varA.getText().toString();
                        str = str.replace('ў', ';');
                        str = str.replace('~', '\n');
                        int hash = str.hashCode();
                        newQuestion += Integer.toString(hash);
                    }
                    if (numTrue == 2)
                    {
                        String str = varB.getText().toString().replace('ў', ';');
                        int hash = str.replace('~', '\n').hashCode();
                        newQuestion += Integer.toString(hash);
                    }
                    if (numTrue == 3)
                    {
                        String str = varC.getText().toString().replace('ў', ';');
                        int hash = str.replace('~', '\n').hashCode();
                        newQuestion += Integer.toString(hash);
                    }
                    if (numTrue == 4)
                    {
                        String str = varD.getText().toString().replace('ў', ';');
                        int hash = str.replace('~', '\n').hashCode();
                        newQuestion += Integer.toString(hash);
                    }
                    newQuestion += ";";
                    add = balls.getText().toString();
                    newQuestion += add;
                    try
                    {
                        int gf = Integer.parseInt(balls.getText().toString());
                    } catch (IllegalArgumentException | NullPointerException e)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
                        builder.setTitle(R.string.dialog_title);
                        builder.setMessage(R.string.dialog_message_balls);
                        builder.setCancelable(true);
                        builder.setIcon(R.drawable.icon);
                        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                        { // Кнопка ОК
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
                            Toast toast = Toast.makeText(createQuestionActivity.this, "Вопрос успешно добавлен!", Toast.LENGTH_LONG);
                            toast.show();
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