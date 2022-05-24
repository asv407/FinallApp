package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class createTestActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);
        final ImageButton b = (ImageButton) findViewById(R.id.next_Button);
        final ImageButton a = (ImageButton) findViewById(R.id.prev_Button);
        EditText name = (EditText)findViewById(R.id.testName);
        a.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(createTestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(createTestActivity.this, createQuestionActivity.class);
                String testName = name.getText().toString();
                if(testName.equals(""))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(createTestActivity.this);
                    builder.setTitle(R.string.dialog_title);
                    builder.setMessage("Название не может быть пустым");
                    builder.setCancelable(true);
                    builder.setIcon(R.drawable.icon);
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // Кнопка ОК
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Отпускает диалоговое окно
                        }
                    });
                    builder.show();
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