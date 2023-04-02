package com.example.smartphone_assign2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    TextView countedNum;
    EditText stepNum;
    Button btnSub;
    Button btnReset;
    Button btnAdd;
    int step = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화
        countedNum = (TextView) findViewById(R.id.textView_count);
        stepNum = (EditText) findViewById(R.id.editText_increase);
        btnSub = (Button) findViewById(R.id.button_sub);
        btnReset = (Button) findViewById(R.id.button_reset);
        btnAdd = (Button) findViewById(R.id.button_add);


        // 단위 바뀌면 숫자 변경
        stepNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                System.out.println(editable);
                try {
                    step = Integer.parseInt(editable.toString());

                } catch (Exception e) {
                    step = 0;
                }
                System.out.println(step);
            }
        });

        // 증가 버튼
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = Integer.parseInt((String) countedNum.getText());
                countedNum.setText(String.valueOf(current + step));
            }
        });

        // 감소 버튼
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = Integer.parseInt((String) countedNum.getText());
                countedNum.setText(String.valueOf(current - step));
            }
        });

        // 리셋 버튼
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countedNum.setText(String.valueOf('0'));
                stepNum.setText(String.valueOf('1'));
                step = 1;
            }
        });
    }
}