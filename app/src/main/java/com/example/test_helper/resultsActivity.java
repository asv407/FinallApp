package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        s += String.valueOf(arguments.getInt("results"));
        s += " баллов из ";
        s += String.valueOf(arguments.getInt("maxBalls"));
        s += "\n";
        s += String.valueOf(arguments.getInt("countTrue"));
        s += " правильных ответов из ";
        s += String.valueOf(arguments.getInt("count"));
    }
}