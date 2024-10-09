package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;
import android.view.View;
public class TaskList extends AppCompatActivity implements TaskAdapter.TaskSelected {
    TextView tvName,tvDescription;
    TextInputEditText tietDescription,tietTaskName;
    Button btnSave,btnCancel;
    FloatingActionButton FAB;
    FragmentManager fragmentManager;
    AddTaskFragment addTaskFragment;
    View TaskDetail;
    ArrayList<Tasks> tasks;
    TaskAdapter taskAdapter;
    ListView list;
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
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().show(addTaskFragment).commit();
            }
        });
        btnCancel=TaskDetail.findViewById(R.id.btnCancel);
        btnSave=TaskDetail.findViewById(R.id.btnSave);
        tietTaskName=TaskDetail.findViewById(R.id.tietTaskName);
        tietDescription=TaskDetail.findViewById(R.id.tietDescription);
        taskAdapter=new TaskAdapter(this,R.layout.listdesign,tasks);
        list.setAdapter(taskAdapter);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().hide(addTaskFragment).commit();
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                tietDescription.setText("");
                tietTaskName.setText("");
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });




    }
    private void init()
    {
        tvName=findViewById(R.id.tvName);
        tvDescription=findViewById(R.id.tvDescription);
        FAB=findViewById(R.id.FAB);
        fragmentManager = getSupportFragmentManager();
        addTaskFragment=(AddTaskFragment) fragmentManager.findFragmentById(R.id.frag_AddTask);
        TaskDetail=addTaskFragment.getView();
        fragmentManager.beginTransaction().hide(addTaskFragment).commit();
        tasks=new ArrayList<>();
        list=findViewById(R.id.list);
    }

    @Override
    public void onTaskClick(int position) {

    }
}