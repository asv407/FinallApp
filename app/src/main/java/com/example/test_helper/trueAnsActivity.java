package com.example.test_helper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class trueAnsActivity extends AppCompatActivity
{
    String[] words;
    private int numberTest = 0;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private TextView textView;
    private void start()
    {
        radioButton1 = findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        radioButton4 = findViewById(R.id.radio4);
        textView = findViewById(R.id.textQuestion);
    }
    private String[] starting()
    {
        String gf = RadioActivity.tests.get(numberTest);
        words = gf.split(";");
        words[0] = words[0].replace('ў', ';');
        words[1] = words[1].replace('ў', ';');
        words[2] = words[2].replace('ў', ';');
        words[3] = words[3].replace('ў', ';');
        words[4] = words[4].replace('ў', ';');
        return words;
    }

    private void fillFields()
    {
        int startColor = getResources().getColor(R.color.buttons1);
        radioButton1.setText(words[1]);
        radioButton2.setText(words[2]);
        radioButton3.setText(words[3]);
        radioButton4.setText(words[4]);
        textView.setText(words[0]);
        radioButton1.setTextColor(startColor);
        radioButton2.setTextColor(startColor);
        radioButton3.setTextColor(startColor);
        radioButton4.setTextColor(startColor);
    }

    private void changeColor()
    {
        int falseColor = getResources().getColor(R.color.falseColor);
        int trueColor = getResources().getColor(R.color.trueColor);
        if (words[5].equals("1"))
        {
            radioButton1.setTextColor(falseColor);
        }
        if (words[5].equals("2"))
        {
            radioButton2.setTextColor(falseColor);
        }
        if (words[5].equals("3"))
        {
            radioButton3.setTextColor(falseColor);
        }
        if (words[5].equals("4"))
        {
            radioButton4.setTextColor(falseColor);
        }
        if (words[6].equals("1"))
        {
            radioButton1.setTextColor(trueColor);
        }
        if (words[6].equals("2"))
        {
            radioButton2.setTextColor(trueColor);
        }
        if (words[6].equals("3"))
        {
            radioButton3.setTextColor(trueColor);
        }
        if (words[6].equals("4"))
        {
            radioButton4.setTextColor(trueColor);
        }
    }

    private void closing()
    {
        radioButton1.setText("");
        radioButton2.setText("");
        radioButton3.setText("");
        radioButton4.setText("");
        textView.setText(R.string.click);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_ans);
        start();
        if (numberTest == 0)
        {
            starting();
            fillFields();
            changeColor();
        }
        final ImageButton c = findViewById(R.id.next_Button);
        final ImageButton b = findViewById(R.id.prev_Button);
        c.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberTest++;
                if (numberTest == RadioActivity.tests.size())
                {
                    closing();
                } else if (numberTest > RadioActivity.tests.size())
                {
                    numberTest--;
                } else
                {
                    starting();
                    fillFields();
                    changeColor();

                }

            }
        });
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (numberTest != 0)
                {
                    numberTest--;
                    starting();
                    fillFields();
                    changeColor();
                }
            }
        });
    }
}