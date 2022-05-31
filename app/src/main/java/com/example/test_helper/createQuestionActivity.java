package com.example.test_helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class createQuestionActivity extends AppCompatActivity
{
    private EditText questionName;
    private boolean ban = true;
    private EditText varA;
    private EditText varB;
    private EditText varC;
    private EditText varD;
    private EditText trueAns;
    private EditText balls;
    private ImageButton b;
    private ImageButton a;
    private ImageButton c;
    private FileOutputStream writer;
    private String name;
    private String imageBit = "";
    public static final int GET_FROM_GALLERY = 3;
    private boolean checkImage()
    {
        if(imageBit.equals(""))
        {
            Toast toast = Toast.makeText(createQuestionActivity.this, R.string.iNotFound, Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }
    private String BitMapToString(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int halfWidth = width / 4;
        int halfHeight = height / 4;
        bitmap = Bitmap.createScaledBitmap(bitmap, halfWidth,
                halfHeight, false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK)
        {
            Uri selectedImage = data.getData();
            try
            {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imageBit = BitMapToString(bitmap);
            } catch (FileNotFoundException e)
            {
                Toast toast = Toast.makeText(createQuestionActivity.this, R.string.downloadError, Toast.LENGTH_LONG);
                toast.show();
            } catch (IOException e)
            {
                Toast toast = Toast.makeText(createQuestionActivity.this, R.string.downloadError, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private void confirming()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
        builder.setTitle("Вы уверены?");
        builder.setCancelable(true);
        builder.setIcon(R.drawable.comet);
        // Кнопка ОК
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
                Intent intent = new Intent(createQuestionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private void confirmingImage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
        builder.setTitle("Что вы хотите сделать?");
        builder.setCancelable(true);
        builder.setIcon(R.drawable.comet);
        // Кнопка ОК
        builder.setPositiveButton(R.string.newImage, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.oldImage, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
                if(checkImage())
                {
                    Intent intent = new Intent(createQuestionActivity.this, showImage.class);
                    RadioActivity.bitString = imageBit;
                    startActivity(intent);
                }
            }
        });
        builder.show();
    }
    private void warnings(String message, Drawable iconId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(createQuestionActivity.this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(iconId);
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss(); // Отпускает диалоговое окно
        });
        builder.show();
    }
    private void start()
    {
        Bundle arguments = getIntent().getExtras();
        name = arguments.get("testName").toString();
        questionName = findViewById(R.id.questionName);
        varA = findViewById(R.id.varA);
        varB = findViewById(R.id.varB);
        varC = findViewById(R.id.varC);
        varD = findViewById(R.id.varD);
        trueAns = findViewById(R.id.trueAns);
        balls = findViewById(R.id.balls);
        b = findViewById(R.id.next_Buttons);
        a = findViewById(R.id.image_Button);
        c = findViewById(R.id.finish_Button);
    }
    private String fieldToLine()
    {
        String newQuestion = name;
        newQuestion = newQuestion.replace(';', 'ў');
        newQuestion += ";";
        String add;
        add = questionName.getText().toString();
        add = add.replace(';', 'ў');
        newQuestion += add;
        newQuestion += ";";
        add = varA.getText().toString();
        add = add.replace(';', 'ў');
        newQuestion += add;
        newQuestion += ";";
        add = varB.getText().toString();
        add = add.replace(';', 'ў');
        newQuestion += add;
        newQuestion += ";";
        add = varC.getText().toString();
        add = add.replace(';', 'ў');
        newQuestion += add;
        newQuestion += ";";
        add = varD.getText().toString();
        add = add.replace(';', 'ў');
        newQuestion += add;
        newQuestion += ";";
        return newQuestion;
    }
    private String addHash(int numTrue)
    {
        String add = "";
        if (numTrue == 1)
        {
            String str = varA.getText().toString();
            str = str.replace('ў', ';');
            str = str.replace('~', '\n');
            int hash = str.hashCode();
            add = Integer.toString(hash);
        }
        if (numTrue == 2)
        {
            String str = varB.getText().toString().replace('ў', ';');
            int hash = str.replace('~', '\n').hashCode();
            add = Integer.toString(hash);
        }
        if (numTrue == 3)
        {
            String str = varC.getText().toString().replace('ў', ';');
            int hash = str.replace('~', '\n').hashCode();
            add = Integer.toString(hash);
        }
        if (numTrue == 4)
        {
            String str = varD.getText().toString().replace('ў', ';');
            int hash = str.replace('~', '\n').hashCode();
            add = Integer.toString(hash);
        }
        return add;
    }
    private void eraseField()
    {
        questionName.getText().clear();
        varA.getText().clear();
        varB.getText().clear();
        varC.getText().clear();
        varD.getText().clear();
        trueAns.getText().clear();
        balls.getText().clear();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_question);
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File ff = contextWrapper.getDir("tests", Context.MODE_PRIVATE);
        File f = new File(ff.getPath(), "tests.csv");
        try
        {
            writer = new FileOutputStream(f, true);
        } catch (IOException e)
        {
        }
        start();
        a.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v)
            {
                confirmingImage();
            }
        });
        b.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v)
            {

                String newQuestion = fieldToLine();
                ban = false;
                int numTrue = 0;
                try
                {
                    int gf = Integer.parseInt(trueAns.getText().toString());
                    if (gf > 4 || gf <= 0)
                    {
                        warnings(getResources().getString(R.string.dialog_message), getResources().getDrawable( R.drawable.icon));
                        ban = true;
                    } else
                    {
                        numTrue = gf;
                    }
                }catch (NumberFormatException e){
                    warnings(getResources().getString(R.string.dialog_message), getResources().getDrawable( R.drawable.icon));
                    ban = true;
                }
                if(!ban)
                {
                    newQuestion += addHash(numTrue);
                    String add = "";
                    newQuestion += ";";
                    add = balls.getText().toString();
                    newQuestion += add;
                    newQuestion += ";";
                    newQuestion += imageBit;
                    try
                    {
                        int gf = Integer.parseInt(balls.getText().toString());
                    } catch (IllegalArgumentException | NullPointerException e)
                    {
                        warnings(getResources().getString(R.string.dialog_message_balls), getResources().getDrawable(R.drawable.icon));
                        ban = true;
                    }
                    if (!ban)
                    {
                        newQuestion = newQuestion.replace('\n', '~');
                        newQuestion += '\n';
                        eraseField();
                        try
                        {
                            writer.write(newQuestion.getBytes());
                            Toast toast = Toast.makeText(createQuestionActivity.this, "Вопрос успешно добавлен!", Toast.LENGTH_LONG);
                            toast.show();
                        } catch (IOException e)
                        {
                        }
                    }
                }
            }
        });
        c.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                ban = true;
                confirming();
            }
        });
    }
}