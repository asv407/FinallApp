package com.example.test_helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class passTestActivity extends AppCompatActivity
{
    private EditText name;
    private String item;
    private boolean ban = false;
    private void createSpinner(HashSet<String> s)
    {
        String[] names = new String[s.size() + 1];
        int i = 0;
        for (String ii : s)
        {
            if (ii.equals(""))
            {
                continue;
            }
            names[i] = ii;
            i++;
        }
        names[i] = getResources().getString(R.string.chooseTest);
        Spinner spinner = findViewById(R.id.spinner);
        CustomAdapter adapter = new CustomAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(names.length - 1);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int text_color = getResources().getColor(R.color.best_black);
                item = (String) parent.getItemAtPosition(position);
                ((TextView) parent.getChildAt(0)).setTextColor(text_color);
                ((TextView) parent.getChildAt(0)).setTextSize(22);
                ban = true;
            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {
                ban = false;
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    private HashSet<String> getTestNames()
    {
        HashSet<String> s = new HashSet<>();
        try
        {
            ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
            File ff = contextWrapper.getDir("tests", Context.MODE_PRIVATE);
            File f = new File(ff.getPath(), "tests.csv");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while (line != null)
            {
                line = line.replace('~', '\n');
                String[] words = line.split(";");
                words[0] = words[0].replace('ў', ';');
                s.add(words[0]);
                line = reader.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_test);
        RadioActivity.tests.clear();
        int background_color = getResources().getColor(R.color.backgroung);
        getWindow().getDecorView().setBackgroundColor(background_color);
        Bundle arguments = getIntent().getExtras();
        if (!arguments.getBoolean("isNorm"))
        {
            Toast toast = Toast.makeText(this, R.string.notFound,Toast.LENGTH_LONG);
            toast.show();
        }

        HashSet<String> s = new HashSet<>();
        s = getTestNames();
        createSpinner(s);
        final ImageButton a = findViewById(R.id.next_Button);
        final ImageButton b = findViewById(R.id.home_Button);
        a.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                if (ban)
                {
                    Intent intent = new Intent(passTestActivity.this, RadioActivity.class);
                    intent.putExtra("testName", item);
                    startActivity(intent);
                } else
                {
                    Toast toast = Toast.makeText(getBaseContext(), "Пожалуйста, выберите тест", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(passTestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}