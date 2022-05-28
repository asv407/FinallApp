package com.example.test_helper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultsBackgroundActivity extends AppCompatActivity
{
    private void congratulation()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            TextView textView = (TextView) findViewById(R.id.text);
            Bundle arguments = getIntent().getExtras();
            String greetings = "Поздравляем! Вы прошли тест " + arguments.getString("nameTest");
            greetings += "\n\nбаллов";
            greetings += String.valueOf(arguments.getInt("result"));
            greetings += " из ";
            greetings += String.valueOf(arguments.getInt("maxBalls"));
            greetings += "\n\n";
            greetings += " правильных ответов ";
            greetings += String.valueOf(arguments.getInt("countTrue"));
            greetings += " из ";
            greetings += String.valueOf(arguments.getInt("count"));
            textView.setText(greetings);
        } else
        {
            TextView textView = (TextView) findViewById(R.id.text);
            Bundle arguments = getIntent().getExtras();
            String greetings = "Поздравляем! Вы прошли тест " + arguments.getString("nameTest");
            greetings += "\nбаллов";
            greetings += String.valueOf(arguments.getInt("result"));
            greetings += " из ";
            greetings += String.valueOf(arguments.getInt("maxBalls"));
            greetings += "\n";
            greetings += " правильных ответов ";
            greetings += String.valueOf(arguments.getInt("countTrue"));
            greetings += " из ";
            greetings += String.valueOf(arguments.getInt("count"));
            textView.setText(greetings);
            textView.setTextSize(24);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_background);
        congratulation();
        final ImageButton a = findViewById(R.id.trueAns);
        a.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(resultsBackgroundActivity.this, trueAnsActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton b = findViewById(R.id.finish);
        b.setOnClickListener(v -> {
            Intent intent = new Intent(resultsBackgroundActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}