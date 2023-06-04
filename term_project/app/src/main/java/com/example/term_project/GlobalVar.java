package com.example.term_project;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class GlobalVar extends Application {
    List<String> userList = new ArrayList<>();  // 사용자 목록
    String user_name = "";
    String selectedDay = "";



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
    String getUserName() { return  user_name;}
    String getSelectedDay(){return selectedDay;}

    // Setter
    void setUserName(String userName){this.user_name = userName;}
    void setSelectedDay(String day){selectedDay = day;}
}
