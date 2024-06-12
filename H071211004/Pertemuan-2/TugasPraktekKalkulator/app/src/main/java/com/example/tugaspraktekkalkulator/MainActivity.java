package com.example.tugaspraktekkalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    char op;
    double hasilAkhir = 0;
    double hasilBagi = 0;
    boolean isNewOp = true;

    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

    }

    public void numberEvent(View view) {
        if (isNewOp) {
            result.setText("");
            isNewOp = false;
        }
        String angka = result.getText().toString();
        switch (view.getId()) {
            case R.id.btnZero:
                angka += "0";
                break;
            case R.id.btnOne:
                angka += "1";
                break;
            case R.id.btnTwo:
                angka += "2";
                break;
            case R.id.btnThree:
                angka += "3";
                break;
            case R.id.btnFour:
                angka += "4";
                break;
            case R.id.btnFive:
                angka += "5";
                break;
            case R.id.btnSix:
                angka += "6";
                break;
            case R.id.btnSeven:
                angka += "7";
                break;
            case R.id.btnEight:
                angka += "8";
                break;
            case R.id.btnNine:
                angka += "9";
                break;
            case R.id.btnDot:
                angka += ".";
                break;
        }
        result.setText(angka);
    }

    public void operationEvent(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                if (op == '\0') {
                } else {
                    return;
                }
                op = '+';
                break;
            case R.id.btnSubtract:
                if (op == '\0') {
                } else {
                    return;
                }
                op = '-';
                break;
            case R.id.btnMultiply:
                if (op == '\0') {
                } else {
                    return;
                }
                op = 'x';
                break;
            case R.id.btnDivide:
                if (op == '\0') {
                } else {
                    return;
                }
                op = '/';
                break;
            case R.id.btnMod:
                if (op == '\0') {
                } else {
                    return;
                }
                op = '%';
        }
        result.setText(result.getText().toString() + op);
    }

    public void equalEvent(View view) {
        String[] data = result.getText().toString().split("[x\\+\\-\\/\\%]");
        isNewOp = false;
        if (data.length > 1) {

            switch (op) {
                case '+':
                    try {
                        double number1 = Double.parseDouble(data[0]);
                        double number2 = Double.parseDouble(data[1]);
                        hasilAkhir = number1 + number2;
                        result.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Bit too large, Haiyaa", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case '-':
                    try {
                        double number1 = Double.parseDouble(data[0]);
                        double number2 = Double.parseDouble(data[1]);
                        hasilAkhir = number1 - number2;
                        result.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Bit too large, Haiyaa", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 'x':
                    try {
                        double number1 = Double.parseDouble(data[0]);
                        double number2 = Double.parseDouble(data[1]);
                        hasilAkhir = number1 * number2;
                        result.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Bit too large, Haiyaa", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case '/':
                    double num1 = Double.parseDouble(data[0]);
                    double num2 = Double.parseDouble(data[1]);
                    if (num2 == 0) {
                        Toast.makeText(this, "Why u try to divide by 0 ? Stoopid", Toast.LENGTH_SHORT).show();
                    } else {
                        hasilBagi = num1 / num2;
                        result.setText(String.valueOf(hasilBagi));
                        op = '\0';
                    }
                    break;

                case '%':
                    try {
                        double number1 = Double.parseDouble(data[0]);
                        double number2 = Double.parseDouble(data[1]);
                        hasilAkhir = number1 % number2;
                        result.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Bit too large, Haiyaa", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    public void delEvent(View view) {
        String data = result.getText().toString();
        isNewOp = true;
        switch (view.getId()) {
            case R.id.btnClear:
                op = '\0';
                result.setText("0");
                break;
            case R.id.btnC:
                if (data.length() == 1){
                    data = "0";
                    result.setText(data);
                }else {
                    if (data.length() != 0) {
                        data = data.substring(0, data.length() - 1);
                        result.setText(data);
                    }
                }

        }
    }
}