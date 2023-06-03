package com.example.term_project;

public class Plan {
    int id;
    String user_id;
    String title;
    String day;
    String s_time;
    String e_time;
    int expense;

    Plan(int id, String user_id, String title, String day, String s_time, String e_time, int expense){
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.day = day;
        this.s_time = s_time;
        this.e_time = e_time;
        this.expense = expense;
    }
}
