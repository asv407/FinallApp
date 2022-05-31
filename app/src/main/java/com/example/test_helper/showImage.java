package com.example.test_helper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

public class showImage extends AppCompatActivity
{
    private ImageView imageView;
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            Toast toast = Toast.makeText(showImage.this, R.string.iNotFound, Toast.LENGTH_LONG);
            toast.show();
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imageView = findViewById(R.id.image);
        String gf = RadioActivity.bitString;
        Bitmap bitmap = StringToBitMap(gf);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int halfWidth = width * 4;
        int halfHeight = height * 4;
        bitmap = Bitmap.createScaledBitmap(bitmap, halfWidth,
                halfHeight, false);
        imageView.setImageBitmap(bitmap);
    }
}