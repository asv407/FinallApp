package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle arguments = getIntent().getExtras();
        String s = "Поздравляем, вы прошли тест ";
        s += String.valueOf(arguments.getString("nameTest"));
        s += "\nРезультаты:\n";
        s += String.valueOf(arguments.getInt("result"));
        s += " баллов из ";
        s += String.valueOf(arguments.getInt("maxBalls"));
        s += "\n";
        s += String.valueOf(arguments.getInt("countTrue"));
        s += " правильных ответов из ";
        s += String.valueOf(arguments.getInt("count"));
        TextView textView = findViewById(R.id.text);
        textView.setText(s);
        final Button c = (Button)findViewById(R.id.finish_Button);
        c.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(resultsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}