package com.example.test_helper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

class CustomAdapter<Str> extends ArrayAdapter<Str>
{
    private final Context cntxt;
    private final Str[] objects;

    CustomAdapter(Context cntxt, int resource, @NonNull Str[] objects)
    {
        super(cntxt, resource, objects);
        this.cntxt = cntxt;
        this.objects = objects;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup p)
    {
        TextView tv = (TextView) super.getView(pos, convertView, p);
        tv.setTextColor(pos == objects.length - 1 ? ContextCompat.getColor(cntxt, R.color.best_black) : Color.BLACK);
        return tv;
    }

    public View getDropDownView(int pos, View convertView, ViewGroup p)
    {
        if (pos == objects.length - 1)
        {
            TextView tv = new TextView(getContext());
            tv.setVisibility(View.GONE);
            tv.setHeight(0);
            return tv;
        } else return super.getDropDownView(pos, null, p);
    }
}