package com.example.activityassignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    char op;
    int hasilAkhir = 0;

    double hasilBagi = 0;
    boolean isNewOp = true;

    TextView hasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasil = findViewById(R.id.hasil);

    }

    public void numberEvent(View view) {
        if (isNewOp) {
            hasil.setText("");
            isNewOp = false;
        }
        String angka = hasil.getText().toString();
        switch (view.getId()) {
            case R.id.btn0:
                angka += "0";
                break;
            case R.id.btn1:
                angka += "1";
                break;
            case R.id.btn2:
                angka += "2";
                break;
            case R.id.btn3:
                angka += "3";
                break;
            case R.id.btn4:
                angka += "4";
                break;
            case R.id.btn5:
                angka += "5";
                break;
            case R.id.btn6:
                angka += "6";
                break;
            case R.id.btn7:
                angka += "7";
                break;
            case R.id.btn8:
                angka += "8";
                break;
            case R.id.btn9:
                angka += "9";
                break;

        }
        hasil.setText(angka);
    }



    public void operationEvent(View view) {
        switch (view.getId()) {
            case R.id.btnTambah:
                if(op == '\0'){
                }else{
                    return;
                }
                op ='+';
                break;
            case R.id.btnKurang:
                if(op == '\0'){
                }else{
                    return;
                }
                op = '-';
                break;
            case R.id.btnKali:
                if(op == '\0'){
                }else{
                    return;
                }
                op = 'x';
                break;
            case R.id.btnBagi:
                if(op == '\0'){
                }else{
                    return;
                }
                op = '/';
                break;
        }
        hasil.setText(hasil.getText().toString() + op);
    }

    public void equalEvent(View view) {
        String[] data = hasil.getText().toString().split("[x\\+\\-\\/]");
        isNewOp = false;
        if (data.length > 1 ) {

            switch (op) {
                case '+':
                    try {
                        int number1 = Integer.parseInt(data[0]);
                        int number2 = Integer.parseInt(data[1]);
                        hasilAkhir = number1 + number2;
                        hasil.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "kebesaran bit", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case '-':
                    try {
                        int number1 = Integer.parseInt(data[0]);
                        int number2 = Integer.parseInt(data[1]);
                        hasilAkhir = number1 - number2;
                        hasil.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "kebesaran bit", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 'x':
                    try {
                        int number1 = Integer.parseInt(data[0]);
                        int number2 = Integer.parseInt(data[1]);
                        hasilAkhir = number1 * number2;
                        hasil.setText(String.valueOf(hasilAkhir));
                        op = '\0';
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "kebesaran bit", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case '/':
                    double num1 = Double.parseDouble(data[0]);
                    double num2 = Double.parseDouble(data[1]);
                    if (num2 == 0) {
                        Toast.makeText(this, "nda bisa di bagi 0", Toast.LENGTH_SHORT).show();
                    } else {
                        hasilBagi = num1 / num2;
                        hasil.setText(String.valueOf(hasilBagi));
                        op = '\0';
                    }
                    break;
            }
        }

    }



    public void delEvent(View view) {
        String data = hasil.getText().toString();
        isNewOp = true;
        switch (view.getId()) {
            case R.id.btnAC:
                op = '\0';
                hasil.setText("0");
                break;
            case R.id.btnDel:
                if (data.length() == 1){
                    data = "0";
                    hasil.setText(data);
                }else {
                    if (data.length() != 0) {
                        data = data.substring(0, data.length() - 1);
                        hasil.setText(data);
                    }
                }

        }
    }
}