package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int background_color = getResources().getColor(R.color.backgroung);
        getWindow().getDecorView().setBackgroundColor(background_color);
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick (View v){
                switch (v.getId())
                {
                    case R.id.pass_Test:

                    case R.id.create_Test:

                        Intent intent = new Intent(MainActivity.this, createTestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.results:

                }
            }
        };
    }


}