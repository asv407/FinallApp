package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class passTestActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_test);
        EditText name = (EditText)findViewById(R.id.testName);
        final Button a = (Button)findViewById(R.id.next_Button);
        a.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(passTestActivity.this, RadioActivity.class);
                String testName = name.getText().toString();
                intent.putExtra("testName", testName);
                startActivity(intent);
            }
        });
    }
}