package com.example.tugaspraktikum2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tugaspraktikum2.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String input;
    private final String[] inputan = new String[3];
    private boolean isOperatorAvailable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        input = "";
        isOperatorAvailable = false;

        binding.button.btnAc.setOnClickListener(view -> handlerAC());
        binding.button.btnDel.setOnClickListener(view -> handlerDel());
        binding.button.btnSamadengan.setOnClickListener(view -> handlerSamaDengan());

        binding.button.btnTambah.setOnClickListener(view -> handlerOperator("+"));
        binding.button.btnKurang.setOnClickListener(view -> handlerOperator("-"));
        binding.button.btnKali.setOnClickListener(view -> handlerOperator("x"));
        binding.button.btnBagi.setOnClickListener(view -> handlerOperator("/"));

        binding.button.btn0.setOnClickListener(view -> {
            if(input.length()>0){
                handlerAngka("0");
            }
        });
        binding.button.btn1.setOnClickListener(view -> handlerAngka("1"));
        binding.button.btn2.setOnClickListener(view -> handlerAngka("2"));
        binding.button.btn3.setOnClickListener(view -> handlerAngka("3"));
        binding.button.btn4.setOnClickListener(view -> handlerAngka("4"));
        binding.button.btn5.setOnClickListener(view -> handlerAngka("5"));
        binding.button.btn6.setOnClickListener(view -> handlerAngka("6"));
        binding.button.btn7.setOnClickListener(view -> handlerAngka("7"));
        binding.button.btn8.setOnClickListener(view -> handlerAngka("8"));
        binding.button.btn9.setOnClickListener(view -> handlerAngka("9"));
    }

    private void handlerAC() {
        input = "";
        isOperatorAvailable = false;
        binding.layar.persamaan.setText("0");
        binding.layar.hasil.setText("0");
    }

    private void handlerDel() {
        isOperatorAvailable(input);
        if(!input.equals("undefined")){
            if(input.length() == 1){
                input = "";
                binding.layar.persamaan.setText("0");
            }
            if (!input.isEmpty() && input.length() > 1) {
                if (isOperator(input.charAt(input.length()-1))) {
                    isOperatorAvailable = false;
                }
                input = input.substring(0, input.length()-1);
                binding.layar.persamaan.setText(input);
            }
        }
    }

    private void handlerAngka(String angka) {
        isOperatorAvailable(input);
        if(!input.equals("undefined")){
            input += angka;
            binding.layar.persamaan.setText(input);
        }
    }

    private void handlerOperator(String operator) {
        isOperatorAvailable(input);
        if(!input.equals("undefined")){
            if (!isOperatorAvailable){
                if (!input.isEmpty()) {
                    char lastChar = input.charAt(input.length() - 1);
                    if (isOperator(lastChar)) {
                        input = input.substring(0, input.length() - 1);
                    }
                } else {
                    input = "0";
                }
                input += operator;
                binding.layar.persamaan.setText(input);
            }
        }
    }

    private void handlerSamaDengan() {
        isOperatorAvailable(input);
        if (!input.isEmpty() && !input.equals("undefined")) {
            if (inputan[0] != null && inputan[1] != null && inputan[2] != null) {
                if(!inputan[0].isEmpty() && !inputan[1].isEmpty() && !inputan[2].isEmpty() ){
                    char lastChar = input.charAt(input.length() - 1);
                    if (isOperator(lastChar)) {
                        input = input.substring(0, input.length() - 1);
                    }
                    String result = hitung();
                    if(result.equals("0")){
                        input = "0";
                    } else {
                        input = result;
                    }
                    isOperatorAvailable = false;
                    binding.layar.persamaan.setText(input);
                    binding.layar.hasil.setText(result);
                }
            }
        }
    }

    private void isOperatorAvailable(String expression){
        for (int i = 0; i < expression.length(); i++){
            if(isOperator(expression.charAt(i))){
                if (i == 0 && expression.charAt(i) == '-') {
                    continue;
                }
                isOperatorAvailable = true;
                inputan[0] = input.substring(0, i);
                inputan[1] = String.valueOf(input.charAt(i));
                inputan[2] = input.substring(i + 1);
                break;
            }
        }
    }

    private String hitung (){
        double result = 0;
        if(Double.parseDouble(inputan[2]) == 0 && inputan[1].equals("/")){
            return "undefined";
        }
        switch (inputan[1]) {
            case "x" :
                result = Double.parseDouble(inputan[0])*Double.parseDouble(inputan[2]);
                break;
            case "/":
                result = Double.parseDouble(inputan[0])/Double.parseDouble(inputan[2]);
                break;
            case "+":
                result = Double.parseDouble(inputan[0])+Double.parseDouble(inputan[2]);
                break;
            case "-":
                result = Double.parseDouble(inputan[0])-Double.parseDouble(inputan[2]);
                break;
        }
        DecimalFormat formattedResult = new DecimalFormat("#.##");

        if(result >= 0.01 || result <= -0.01 || result == 0){
            return formattedResult.format(result);
        } else {
            return Double.toString(result);
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/';
    }
}

//private Double hitung(String expression) {
//        // Menghapus whitespace
//        expression = expression.replaceAll("\\s+", "");
//        // Iterasi tiap karakter pada ekspresi
//        for (int i = 0; i < expression.length(); i++) {
//            char c = expression.charAt(i);
//            // Jika karakter merupakan angka, maka masukkan ke stack2
//            if (Character.isDigit(c)) {
//                double num = 0;
//                // Terus baca angka selanjutnya hingga habis
//                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
//                    num = num * 10 + (expression.charAt(i) - '0');
//                    i++;
//                }
//                i--;
//                stack2.push(num);
//            } else if (isOperator(c)) {
//                while (!stack1.isEmpty() && precedence(c) <= precedence(stack1.peek())) {
//                    double result = applyOperator(stack1.pop(), stack2.pop(), stack2.pop());
//                    stack2.push(result);
//                }
//                stack1.push(c);
//            }
//        }
//
//        // Memproses operator yang belum diproses
//        while (!stack1.isEmpty()) {
//            double result = applyOperator(stack1.pop(), stack2.pop(), stack2.pop());
//            stack2.push(result);
//        }
//
//        // Hasil akhir berada pada elemen terakhir pada stack2
//        return stack2.pop();
//    }
//    // Method untuk menentukan presendensi sebuah operator
//    private static int precedence(char c) {
//        if (c == '+' || c == '-') {
//            return 1;
//        } else if (c == 'x' || c == '/') {
//            return 2;
//        } else {
//            return 0;
//        }
//    }
//    // Method untuk melakukan operasi antara dua operand dengan sebuah operator
//    private static double applyOperator(char operator, double b, double a) {
//        switch (operator) {
//            case '+':
//                return a + b;
//            case '-':
//                return a - b;
//            case 'x':
//                return a * b;
//            case '/':
//                if (b == 0) {
//                    return 0;
//                }
//                return a / b;
//            default:
//                return 0;
//        }
//    }