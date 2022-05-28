package com.example.test_helper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RadioActivity extends AppCompatActivity
{
    public static ArrayList<String> tests = new ArrayList<>();
    private RadioGroup radioGroup11;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private TextView textView;
    private int i = 0;
    private ArrayList<String[]> arrayList = new ArrayList<>();
    private BufferedReader reader;
    private String name;
    private String[] words;
    private int res = 0;
    private int countTrueAns = 0;
    private int maxBalls = 0;

    private String trueAns()
    {
        String str = radioButton1.getText().toString();
        int hash = str.hashCode();
        if (hash == Integer.parseInt(words[6]))
        {
            return "1;";
        }
        str = radioButton2.getText().toString();
        hash = str.hashCode();
        if (hash == Integer.parseInt(words[6]))
        {
            return "2;";
        }
        str = radioButton3.getText().toString();
        hash = str.hashCode();
        if (hash == Integer.parseInt(words[6]))
        {
            return "3;";
        }
        str = radioButton4.getText().toString();
        hash = str.hashCode();
        if (hash == Integer.parseInt(words[6]))
        {
            return "4;";
        }
        return "";
    }

    private String whoCheck(String str1)
    {
        if (radioButton1.isChecked())
        {
            maxBalls += Integer.parseInt(words[7]);
            String str = radioButton1.getText().toString();
            int hash = str.hashCode();
            if (hash == Integer.parseInt(words[6]))
            {
                res += Integer.parseInt(words[7]);
                countTrueAns++;
            }
            return str1 + "1;";
        }
        if (radioButton2.isChecked())
        {
            maxBalls += Integer.parseInt(words[7]);
            if (radioButton2.getText().toString().hashCode() == Integer.parseInt(words[6]))
            {
                res += Integer.parseInt(words[7]);
                countTrueAns++;
            }
            return str1 + "2;";
        }
        if (radioButton3.isChecked())
        {
            maxBalls += Integer.parseInt(words[7]);
            if (radioButton3.getText().toString().hashCode() == Integer.parseInt(words[6]))
            {
                res += Integer.parseInt(words[7]);
                countTrueAns++;
            }
            return str1 + "3;";
        }
        if (radioButton4.isChecked())
        {
            maxBalls += Integer.parseInt(words[7]);
            if (radioButton4.getText().toString().hashCode() == Integer.parseInt(words[6]))
            {
                res += Integer.parseInt(words[7]);
                countTrueAns++;
            }
            return str1 + "4;";
        }
        return str1;
    }

    private void start()
    {
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("testName").toString();
        radioGroup11 = findViewById(R.id.radioGroup1);
        radioButton1 = findViewById(R.id.radio1);
        radioButton2 = findViewById(R.id.radio2);
        radioButton3 = findViewById(R.id.radio3);
        radioButton4 = findViewById(R.id.radio4);
        textView = findViewById(R.id.textQuestion);
    }

    private void getQuestions()
    {
        try
        {
            File sdcard = Environment.getExternalStorageDirectory();
            File f = new File(sdcard, "tests.csv");
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null)
            {
                line = line.replace('~', '\n');
                String[] words = line.split(";");
                for (int g = 0; g < words.length; g++)
                {
                    words[g] = words[g].replace('ў', ';');
                }
                if (!name.equals(words[0]))
                {
                    line = reader.readLine();

                    continue;
                }

                arrayList.add(words);
                line = reader.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (arrayList.size() == 0)
        {
            Intent intent = new Intent(RadioActivity.this, passTestActivity.class);
            intent.putExtra("isNorm", false);
            startActivity(intent);
        }
    }

    private void fillRadio()
    {
        words = arrayList.get(i);
        textView.setText(words[1]);
        radioButton1.setText(words[2]);
        radioButton2.setText(words[3]);
        radioButton3.setText(words[4]);
        radioButton4.setText(words[5]);
        radioGroup11.check(R.id.radio1);
    }

    private void rememberAns()
    {
        String str1 = "";
        String add;
        add = words[1];
        add = add.replace(';', 'ў');
        str1 += add;
        str1 += ";";
        add = words[2];
        add = add.replace(';', 'ў');
        str1 += add;
        str1 += ";";
        add = words[3];
        add = add.replace(';', 'ў');
        str1 += add;
        str1 += ";";
        add = words[4];
        add = add.replace(';', 'ў');
        str1 += add;
        str1 += ";";
        add = words[5];
        add = add.replace(';', 'ў');
        str1 += add;
        str1 += ";";
        str1 = whoCheck(str1);
        str1 += trueAns();
        tests.add(str1);
    }

    private void showRes()
    {
        Intent intent = new Intent(RadioActivity.this, resultsBackgroundActivity.class);
        intent.putExtra("result", res);
        intent.putExtra("countTrue", countTrueAns);
        intent.putExtra("maxBalls", maxBalls);
        intent.putExtra("nameTest", name);
        intent.putExtra("count", arrayList.size());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_radio);
        super.onCreate(savedInstanceState);
        start();
        getQuestions();
        fillRadio();
        final ImageButton b = findViewById(R.id.nexts_Button);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                rememberAns();
                i++;
                if (i >= arrayList.size())
                {
                    showRes();
                } else
                {
                    fillRadio();
                }

            }
        });
    }
}