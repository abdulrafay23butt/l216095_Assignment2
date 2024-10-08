package com.example.myapplication;

public class Tasks {
    String name,description;

    public Tasks()
    {

    }
    public Tasks(String n, String d)
    {
        name=n;
        description=d;
    }
    public void setName(String n)
    {
        name=n;
    }
    public void setDescription(String d)
    {
        description=d;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
