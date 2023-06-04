package com.example.term_project;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class GlobalVar extends Application {
    List<String> userList = new ArrayList<>();  // 사용자 목록
    int id;     // Plan id
    String user_id;
    String title;
    String day;
    String s_time;
    String e_time;
    int expense;    // 지출액


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    // 사용자 리스트 추가
    int addUserToList(String userName){
        if (userList.contains(userName)){
            return -1;
        }
        userList.add(userName);
        return 0;
    }

    // Getter
    List<String> getUserList(){
        return userList;
    }
}
