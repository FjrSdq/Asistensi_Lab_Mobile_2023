package com.example.pertemuan2;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    TextView inputText;
    Boolean isNewOp = true;
    String op = "";
    String newNumber = "";
    String oldNumber = "";
    String number = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);

    }
    public void numberEvent(View view) {

        isNewOp = false;
        number = inputText.getText().toString();

        switch (view.getId()){
            case R.id.btn_0:
                if (number.equals("0")){
                    number="0";

                } else {
                    number+="0";
                }

                break;
            case R.id.btn_1:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="1";
                }else {
                    number += "1";
                }
                break;
            case R.id.btn_2:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="2";

                }else {
                    number += "2";
                }
                break;
            case R.id.btn_3:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="3";

                }else {
                    number += "3";
                }
                break;
            case R.id.btn_4:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="4";

                }else {
                    number += "4";
                }
                break;
            case R.id.btn_5:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="5";

                }else {
                    number += "5";
                }
                break;
            case R.id.btn_6:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="6";

                }else {
                    number += "6";
                }
                break;
            case R.id.btn_7:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="7";

                }else {
                    number += "7";
                }
                break;
            case R.id.btn_8:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="8";

                }else {
                    number += "8";
                }
                break;
            case R.id.btn_9:
                if (number.length()==1 && number.equals("0")) {
                    number.substring(1);
                    number="9";

                }else {
                    number += "9";
                }
                break;
        }
        inputText.setText(number);
    }

    public void operatorEvent(View view) {
        isNewOp = false;

        switch (view.getId()){
            case  R.id.btn_division:
                if (!op.isEmpty()){
                    return;
                }
                op = "/";
                break;

            case  R.id.btn_multiple:
                if (!op.isEmpty()){
                    return;
                }
                op = "X";
                break;

            case  R.id.btn_plus:
                if (!op.isEmpty()){
                    return;
                }
                op = "+";
                break;

            case  R.id.btn_minus:
                if (!op.isEmpty()){
                    return;
                }
                op = "-";
                break;

        }inputText.setText(inputText.getText().toString() + op);

    }
    public void equalEvents(View view) {
        String [] nums = inputText.getText().toString().split("[X\\+\\-\\/]");
        if (nums.length == 1){

        }else{
            oldNumber = nums[0];
            newNumber = nums[1];
            Integer result = 0;
            Double result2 = 0.0;

            switch (op){
                case "+":
                    try {
                        result =Integer.parseInt(oldNumber) + Integer.parseInt(newNumber);
                        inputText.setText(String.valueOf(result));
                        result=0;
                        op = "";
                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Cant calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "-":
                    try {
                        result =Integer.parseInt(oldNumber) - Integer.parseInt(newNumber);
                        inputText.setText(String.valueOf(result));
                        result=0;
                        op = "";
                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Cant calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "X":
                    try {
                        result =Integer.parseInt(oldNumber) * Integer.parseInt(newNumber);
                        inputText.setText(String.valueOf(result));
                        result=0;
                        op = "";
                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Cant calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "/":
                    try {
                        result2 =Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                        inputText.setText(String.valueOf(result2));
                        result2=0.0;
                        op = "";
                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Cant calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    }
    public void acEvent(View view) {
        op ="";
        inputText.setText("0");
        number = "0";
        isNewOp = true;

    }
    public void delEvent(View view) {
        String del;
        if (inputText.getText().length() > 0) {
            StringBuilder stringBuilder = new StringBuilder(inputText.getText());
            stringBuilder.deleteCharAt(inputText.getText().length() - 1);
            op = "";
            del = stringBuilder.toString();
            if (del == "") {
                inputText.setText("0");
                isNewOp = true;
            } else {
                inputText.setText(del);
            }
        }
    }
}