package com.example.test_helper;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

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
    private ImageButton b;
    private ImageButton c;
    private FileOutputStream writer;
    private String name;
    private void confirming()
    {
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
    private void warnings(String message, Drawable iconId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(iconId);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss(); // Отпускает диалоговое окно
        });
        builder.show();
    }
    private void start()
    {
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("testName").toString();
        questionName = findViewById(R.id.questionName);
        varA = findViewById(R.id.varA);
        varB = findViewById(R.id.varB);
        varC = findViewById(R.id.varC);
        varD = findViewById(R.id.varD);
        trueAns = findViewById(R.id.trueAns);
        balls = findViewById(R.id.balls);
        b = findViewById(R.id.next_Buttons);
        c = findViewById(R.id.finish_Button);
    }
    private String fieldToLine()
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
        return newQuestion;
    }
    private String addHash(int numTrue)
    {
        String add = "";
        if (numTrue == 1)
        {
            String str = varA.getText().toString();
            str = str.replace('ў', ';');
            str = str.replace('~', '\n');
            int hash = str.hashCode();
            add = Integer.toString(hash);
        }
        if (numTrue == 2)
        {
            String str = varB.getText().toString().replace('ў', ';');
            int hash = str.replace('~', '\n').hashCode();
            add = Integer.toString(hash);
        }
        if (numTrue == 3)
        {
            String str = varC.getText().toString().replace('ў', ';');
            int hash = str.replace('~', '\n').hashCode();
            add = Integer.toString(hash);
        }
        if (numTrue == 4)
        {
            String str = varD.getText().toString().replace('ў', ';');
            int hash = str.replace('~', '\n').hashCode();
            add = Integer.toString(hash);
        }
        return add;
    }
    private void eraseField()
    {
        questionName.getText().clear();
        varA.getText().clear();
        varB.getText().clear();
        varC.getText().clear();
        varD.getText().clear();
        trueAns.getText().clear();
        balls.getText().clear();
    }
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
        start();
        b.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v)
            {

                String newQuestion = fieldToLine();
                ban = false;
                int numTrue = 0;
                try
                {
                    int gf = Integer.parseInt(trueAns.getText().toString());
                    if (gf > 4 || gf <= 0)
                    {
                        warnings(getResources().getString(R.string.dialog_message), getResources().getDrawable( R.drawable.icon));
                        ban = true;
                    } else
                    {
                        numTrue = gf;
                    }
                }catch (NumberFormatException e){
                    warnings(getResources().getString(R.string.dialog_message), getResources().getDrawable( R.drawable.icon));
                    ban = true;
                }
                if(!ban)
                {
                    newQuestion += addHash(numTrue);
                    String add = "";
                    newQuestion += ";";
                    add = balls.getText().toString();
                    newQuestion += add;
                    try
                    {
                        int gf = Integer.parseInt(balls.getText().toString());
                    } catch (IllegalArgumentException | NullPointerException e)
                    {
                        warnings(getResources().getString(R.string.dialog_message_balls), getResources().getDrawable( R.drawable.icon));
                        ban = true;
                    }
                    if(!ban)
                    {
                        newQuestion = newQuestion.replace('\n', '~');
                        newQuestion += '\n';
                        eraseField();
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
                confirming();
            }
        });
    }
}