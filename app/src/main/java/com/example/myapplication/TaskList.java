package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity {
    TextView tvName,tvDescription;
    TextInputEditText tietDescription,tietTaskName;
    Button btnSave,btnCancel;
    ImageView ivFAB;
    FragmentManager fragmentManager;
    AddTaskFragment addTaskFragment;
    View TaskDetail;
    ArrayList<Tasks> tasks;
    TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        ivFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().show(addTaskFragment).commit();
            }
        });
        btnCancel=TaskDetail.findViewById(R.id.btnCancel);
        btnSave=TaskDetail.findViewById(R.id.btnSave);
        tietTaskName=TaskDetail.findViewById(R.id.tietTaskName);
        tietDescription=TaskDetail.findViewById(R.id.tietDescription);
        taskAdapter=new TaskAdapter(this,R.layout.activity_task_list,tasks);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().hide(addTaskFragment).commit();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=tietTaskName.getText().toString().trim();
                String Description=tietDescription.getText().toString().trim();
                if(name.isEmpty() || Description.isEmpty())
                {
                    Toast.makeText(TaskList.this, "Please Fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                Tasks newtasks=new Tasks(name,Description);
                tasks.add(newtasks);
                taskAdapter.notifyDataSetChanged();
                fragmentManager.beginTransaction().hide(addTaskFragment).commit();
            }
        });




    }
    private void init()
    {
        tvName=findViewById(R.id.tvName);
        tvDescription=findViewById(R.id.tvDescription);
        ivFAB=findViewById(R.id.ivFAB);
        fragmentManager = getSupportFragmentManager();
        addTaskFragment=(AddTaskFragment) fragmentManager.findFragmentById(R.id.frag_AddTask);
        TaskDetail=addTaskFragment.getView();
        fragmentManager.beginTransaction().hide(addTaskFragment).commit();
        tasks=new ArrayList<>();
    }
}