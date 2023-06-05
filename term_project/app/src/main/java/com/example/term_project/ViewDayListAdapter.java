package com.example.term_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewDayListAdapter extends BaseAdapter {
    Context myContext;
    LayoutInflater myLayoutInflater;
    ArrayList<Plan> planList;

    // DB 컨트롤러
    MySQLite mySQLite;
    SQLiteDatabase dbWriter;

    ViewDayListAdapter(Context context, ArrayList<Plan> planList){
        myContext = context;
        myLayoutInflater = LayoutInflater.from(myContext);
        this.planList = planList;

        // DB 컨트롤러
        mySQLite = new MySQLite(context);
        dbWriter = mySQLite.getWritableDatabase();
    }

    @Override
    public int getCount() {
        return planList.size();
    }

    @Override
    public Object getItem(int i) {
        return planList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return planList.get(i).id;
    }

    // 레이아웃 모습 구현
    @Override
    public View getView(int i, View coverView, ViewGroup viewGroup) {
        View view = myLayoutInflater.inflate(R.layout.view_day_page_listview, null);

        // 컴포넌트
        TextView text_title = view.findViewById(R.id.viewDayList_text_title);
        TextView text_time = view.findViewById(R.id.viewDayList_text_time);
        TextView text_expense = view.findViewById(R.id.viewDayList_text_expense);
        Switch switch_isDone = view.findViewById(R.id.viewDayList_switch);

        // 초기화
        text_title.setText(planList.get(i).title);
        text_time.setText(planList.get(i).sTime + " ~ " + planList.get(i).eTime);
        text_expense.setText(String.valueOf(planList.get(i).expense));
        if (planList.get(i).isDone.equals("TRUE")){
            switch_isDone.setChecked(true);
        } else{
            switch_isDone.setChecked(false);
        }

        // 스위치 이벤트
        switch_isDone.setOnClickListener(view1 -> {
            if (switch_isDone.isChecked()){
                String querry = String.format("UPDATE PLAN SET is_done = 'TRUE' WHERE id = %d", planList.get(i).id);
                dbWriter.execSQL(querry);
            } else{
                String querry = String.format("UPDATE PLAN SET is_done = 'FALSE' WHERE id = %d", planList.get(i).id);
                dbWriter.execSQL(querry);
            }
        });

        return view;
    }
}
