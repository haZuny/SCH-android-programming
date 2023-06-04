package com.example.term_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLite extends SQLiteOpenHelper {
    static String dbName = "MySQLite.db";
    static int dbVersion = 1;  // db 버전

    // 생성자
    public MySQLite(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    // db 생성시 실행
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE USER(name TEXT PRIMARY KEY);";
        db.execSQL(query);
        query="CREATE TABLE PLAN(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " user_name TEXT, title TEXT, day TEXT, s_time TEXT, e_time TEXT, expense INT," +
                "FOREIGN KEY (user_name) REFERENCES USER (name) ON UPDATE CASCADE);";
        db.execSQL(query);
    }

    // db 버전 올라갈 때 마다 실행
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query="DROP TABLE USER"; // 테이블 삭제
        db.execSQL(query);
        onCreate(db);   // DB 다시 생성
    }
}
