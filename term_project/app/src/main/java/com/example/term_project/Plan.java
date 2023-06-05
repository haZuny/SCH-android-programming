package com.example.term_project;

public class Plan {
    int id;
    String title;
    String day;
    String sTime;
    String eTime;
    int expense;
    String isDone;

    Plan(int id, String title, String day, String sTime, String eTime, int expense, String isDone){
        this.id = id;
        this.title = title;
        this.day = day;
        this.sTime = sTime;
        this.eTime = eTime;
        this.expense = expense;
        this.isDone = isDone;
    }
}
