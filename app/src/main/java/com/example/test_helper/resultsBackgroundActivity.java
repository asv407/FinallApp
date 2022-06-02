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
            String greetings = getResources().getString(R.string.congrat) + " '" + arguments.getString("nameTest") + "' ";
            greetings += "\n\n" + getResources().getString(R.string.ball) + " ";
            greetings += String.valueOf(arguments.getInt("result"));
            greetings += " " + getResources().getString(R.string.of) + " ";
            greetings += String.valueOf(arguments.getInt("maxBalls"));
            greetings += "\n\n";
            greetings += getResources().getString(R.string.correct) + "\n";
            greetings += String.valueOf(arguments.getInt("countTrue"));
            greetings += " " + getResources().getString(R.string.of) + " ";
            greetings += String.valueOf(arguments.getInt("count"));
            textView.setText(greetings);
        } else
        {
            TextView textView = (TextView) findViewById(R.id.text);
            Bundle arguments = getIntent().getExtras();
            String greetings = getResources().getString(R.string.congrat) + " '" + arguments.getString("nameTest") + "' ";
            greetings += "\n\n" + getResources().getString(R.string.ball) + " ";
            greetings += String.valueOf(arguments.getInt("result"));
            greetings += " " + getResources().getString(R.string.of) + " ";
            greetings += String.valueOf(arguments.getInt("maxBalls"));
            greetings += "\n\n";
            greetings += getResources().getString(R.string.correct) + "\n";
            greetings += String.valueOf(arguments.getInt("countTrue"));
            greetings += " " + getResources().getString(R.string.of) + " ";
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