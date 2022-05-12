package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultsBackgroundActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_background);
        TextView textView = (TextView) findViewById(R.id.text);
        Bundle arguments = getIntent().getExtras();
        String greetings = "Поздравляем! Вы прошли тест " + arguments.getString("nameTest");
        textView.setText(greetings);
        final Button a = (android.widget.Button)findViewById(R.id.finish_Button);
        a.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(resultsBackgroundActivity.this, resultsActivity.class);

                intent.putExtra("result", arguments.getInt("result"));
                intent.putExtra("countTrue", arguments.getInt("countTrue"));
                intent.putExtra("maxBalls", arguments.getInt("maxBalls"));
                intent.putExtra("count", arguments.getInt("count"));
                startActivity(intent);
            }
        });
        final Button b = (Button)findViewById(R.id.finish);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(resultsBackgroundActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}