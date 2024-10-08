package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Tasks> {
    Context context;
    int resource;

    public TaskAdapter(@NonNull Context context, int resource, @NonNull List<Tasks> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null)
        {
            // view attachment
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // set data to this view
        Tasks tasks = getItem(position);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);

       tvName.setText(tasks.getName());
       tvDescription.setText(tasks.getDescription());





        return convertView;
    }
}
