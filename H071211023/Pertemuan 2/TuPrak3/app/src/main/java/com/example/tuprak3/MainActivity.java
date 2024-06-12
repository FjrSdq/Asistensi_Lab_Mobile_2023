package com.example.tuprak3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    TextView calculated;
    Boolean hasOperation = false;
    String operation = "";
    String text = "0";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculated = findViewById(R.id.calculated);
    }

    public void number(View view) {
        switch (view.getId()) {
            case R.id.button0:
                if (Objects.equals(text, "0")) {
                    text = "0";
                } else {
                    text += "0";
                }
                break;
            case R.id.button1:
                if (Objects.equals(text, "0")) {
                    text = "1";
                } else {
                    text += "1";
                }
                break;
            case R.id.button2:
                if (Objects.equals(text, "0")) {
                    text = "2";
                } else {
                    text += "2";
                }
                break;
            case R.id.button3:
                if (Objects.equals(text, "0")) {
                    text = "3";
                } else {
                    text += "3";
                }
                break;
            case R.id.button4:
                if (Objects.equals(text, "0")) {
                    text = "4";
                } else {
                    text += "4";
                }
                break;
            case R.id.button5:
                if (Objects.equals(text, "0")) {
                    text = "5";
                } else {
                    text += "5";
                }
                break;
            case R.id.button6:
                if (Objects.equals(text, "0")) {
                    text = "6";
                } else {
                    text += "6";
                }
                break;
            case R.id.button7:
                if (Objects.equals(text, "0")) {
                    text = "7";
                } else {
                    text += "7";
                }
                break;
            case R.id.button8:
                if (Objects.equals(text, "0")) {
                    text = "8";
                } else {
                    text += "8";
                }
                break;
            case R.id.button9:
                if (Objects.equals(text, "0")) {
                    text = "9";
                } else {
                    text += "9";
                }
                break;
        }
        calculated.setText(text);
    }

    public void operation(View view) {
        if (!hasOperation) {
            switch (view.getId()) {
                case R.id.buttonbagi:
                    operation = "/";
                    text += "/";
                    break;
                case R.id.buttonkali:
                    operation = "x";
                    text += "x";
                    break;
                case R.id.buttonkurang:
                    operation = "-";
                    text += "-";
                    break;
                case R.id.buttontambah:
                    operation = "+";
                    text += "+";
                    break;
            }
            hasOperation = true;
        } else {
            switch (view.getId()) {
                case R.id.buttonbagi:
                    operation = "/";
                    break;
                case R.id.buttonkali:
                    operation = "x";
                    break;
                case R.id.buttonkurang:
                    operation = "-";
                    break;
                case R.id.buttontambah:
                    operation = "+";
                    break;
            }
            text = String.format("%s%s", text.substring(0, text.length() - 1), operation);
        }
        calculated.setText(text);
    }

    public void result(View view) {
        if (hasOperation && !operation.isEmpty()) {
            String[] values = text.split(Objects.equals(operation, "+") ? "\\+" : operation);

            double value1 = Double.parseDouble(values[0]);
            double value2 = Double.parseDouble(values[1]);

            double result = 0;

            switch (operation) {
                case "/":
                    try {
                        result = value1 / value2;
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "x":
                    try {
                        result = value1 * value2;
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "-":
                    try {
                        result = value1 - value2;
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case "+":
                    try {
                        result = value1 + value2;
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Can't calculate number more than 32 bit", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }

            text = String.valueOf(result);

            calculated.setText(text);

            operation = "";
            hasOperation = false;
        }
    }

    public void ac(View view) {
        text = "0";
        operation = "";
        hasOperation = false;
        calculated.setText(text);
    }

    public void del(View view) {
        StringBuilder stringBuilder = new StringBuilder(calculated.getText());

        String delete = stringBuilder.deleteCharAt(calculated.getText().length() - 1).toString();

        if (delete.equals("")) {
            text = "0";
            operation = "";
            hasOperation = false;
            calculated.setText(text);
        } else {
            text = text.substring(0,text.length()-1);
            calculated.setText(delete);
        }


    }
}