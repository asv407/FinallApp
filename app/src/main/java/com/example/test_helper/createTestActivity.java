package com.example.test_helper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class createTestActivity extends AppCompatActivity
{
    private void warning(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(createTestActivity.this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
        { // Кнопка ОК
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss(); // Отпускает диалоговое окно
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);
        final ImageButton b = findViewById(R.id.next_Button);
        final ImageButton a = findViewById(R.id.prev_Button);
        EditText name = findViewById(R.id.testName);
        a.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(createTestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(createTestActivity.this, createQuestionActivity.class);
                String testName = name.getText().toString();
                if(testName.equals(""))
                {
                    warning("Название не может быть пустым!");
                }
                else
                {
                    intent.putExtra("testName", testName);
                    startActivity(intent);
                }
            }
        });
    }

}