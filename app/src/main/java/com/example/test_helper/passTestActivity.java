package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class passTestActivity extends AppCompatActivity
{
    public EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_test);
        int background_color = getResources().getColor(R.color.backgroung);
        getWindow().getDecorView().setBackgroundColor(background_color);
        Bundle arguments = getIntent().getExtras();
        if(!arguments.getBoolean("isNorm"))
        {
            Toast toast = Toast.makeText(this, "Не удалось найти тест!",Toast.LENGTH_LONG);
            toast.show();
        }
        name = (EditText)findViewById(R.id.testName);
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