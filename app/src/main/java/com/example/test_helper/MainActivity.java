package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private static final int REQUEST_CODE_MANAGE_EXTERNAL_STORAGE = 100;
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int background_color = getResources().getColor(R.color.backgroung);
        getWindow().getDecorView().setBackgroundColor(background_color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            int permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE);

            if (permissionStatus == PackageManager.PERMISSION_GRANTED)
            {

            } else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                        REQUEST_CODE_MANAGE_EXTERNAL_STORAGE);
            }
        } else {
            int permissionStatus1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissionStatus1 == PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
            }
        }
        final ImageButton a = (ImageButton)findViewById(R.id.pass_Test);
        a.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(MainActivity.this, passTestActivity.class);
                intent.putExtra("isNorm", true);
                startActivity(intent);
            }
        });
        final ImageButton b = (ImageButton)findViewById(R.id.create_Test);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(MainActivity.this, createTestActivity.class);
                startActivity(intent);
            }
        });

    }


}