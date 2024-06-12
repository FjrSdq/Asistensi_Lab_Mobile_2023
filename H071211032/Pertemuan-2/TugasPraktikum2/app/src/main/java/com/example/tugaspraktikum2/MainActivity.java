package com.example.tugaspraktikum2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugaspraktikum2.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

//import org.mozilla.javascript.Context;
//import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity {
    String process;
    String operator;
    int lenghtsecondNumber;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.input.AngkaMasuk.setText("");
                binding.input.AngkaKeluar.setText("");
                operator = null;
                binding.button.btnBagi.setEnabled(true);
                binding.button.btnKurang.setEnabled(true);
                binding.button.btnkali.setEnabled(true);
                binding.button.btnTambah.setEnabled(true);
                lenghtsecondNumber=0;
            }
        });

        binding.button.btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "0");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "1");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "2");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "3");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "4");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "5");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "6");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "7");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "8");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process = binding.input.AngkaMasuk.getText().toString();
                binding.input.AngkaMasuk.setText(process + "9");
                if (operator != null){
                    lenghtsecondNumber++;
                }
            }
        });

        binding.button.btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lenghtsecondNumber == 0){
                    process = binding.input.AngkaMasuk.getText().toString();
                    if(operator == null){
                        binding.input.AngkaMasuk.setText(process + "+");
                    }else {
                        binding.input.AngkaMasuk.setText(process.substring(0,process.length()-1)+ "+");
                    }
                    operator = "+";
                } else {
                    binding.button.btnBagi.setEnabled(false);
                    binding.button.btnKurang.setEnabled(false);
                    binding.button.btnkali.setEnabled(false);
                }
            }
        });

        binding.button.btnKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lenghtsecondNumber == 0){
                    process = binding.input.AngkaMasuk.getText().toString();
                    if(operator == null){
                        binding.input.AngkaMasuk.setText(process + "-");
                    }else {
                        binding.input.AngkaMasuk.setText(process.substring(0,process.length()-1)+ "-");
                    }
                    operator = "-";

                } else {
                    binding.button.btnTambah.setEnabled(false);
                    binding.button.btnBagi.setEnabled(false);
                    binding.button.btnkali.setEnabled(false);
                }
            }
        });

        binding.button.btnkali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lenghtsecondNumber == 0){
                    process = binding.input.AngkaMasuk.getText().toString();
                    if(operator == null){
                        binding.input.AngkaMasuk.setText(process + "X");
                    }else {
                        binding.input.AngkaMasuk.setText(process.substring(0,process.length()-1)+ "X");
                    }
                    operator = "X";
                } else {
                    binding.button.btnTambah.setEnabled(false);
                    binding.button.btnKurang.setEnabled(false);
                    binding.button.btnBagi.setEnabled(false);
                }
            }
        });

        binding.button.btnBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (lenghtsecondNumber == 0){
                    process = binding.input.AngkaMasuk.getText().toString();
                    if(operator == null){
                        binding.input.AngkaMasuk.setText(process + ":");
                    }else {
                        binding.input.AngkaMasuk.setText(process.substring(0,process.length()-1)+ ":");
                    }
                    operator = ":";

                } else {
                    binding.button.btnTambah.setEnabled(false);
                    binding.button.btnKurang.setEnabled(false);
                    binding.button.btnkali.setEnabled(false);
                }
            }
        });

        binding.button.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = binding.input.AngkaMasuk.getText().toString();
                int input = word.length();
                if (input > 0) {
                    binding.input.AngkaMasuk.setText(word.substring(0, input -1));
                    binding.button.btnBagi.setEnabled(true);
                    binding.button.btnKurang.setEnabled(true);
                    binding.button.btnkali.setEnabled(true);
                    binding.button.btnTambah.setEnabled(true);
                    if (lenghtsecondNumber==0){
                        operator=null;
                        lenghtsecondNumber=0;
                        binding.button.btnBagi.setEnabled(true);
                        binding.button.btnKurang.setEnabled(true);
                        binding.button.btnkali.setEnabled(true);
                        binding.button.btnTambah.setEnabled(true);
                    } else {
                        lenghtsecondNumber--;
                    }
                }
            }
        });

        binding.button.btnHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double finalResult=0;
                int finalResult2=0;
                process = binding.input.AngkaMasuk.getText().toString();
                String hasil[] = process.split("[:X+-]");

                try {
                    System.out.println(process);
                    double satu = Integer.parseInt(hasil[0]);
                    double dua = Integer.parseInt(hasil[1]);

                    if (operator == "X") {
                        finalResult2 = (int) satu * (int) dua;
                        System.out.println(satu * dua);
                        binding.input.AngkaKeluar.setText(String.valueOf(finalResult2));
                    } else if (operator == "-") {
                        finalResult2 = (int) satu - (int) dua;
                        System.out.println(satu - dua);
                        binding.input.AngkaKeluar.setText(String.valueOf(finalResult2));
                    } else if (operator == "+") {
                        finalResult2 = (int) satu + (int) dua;
                        System.out.println(satu + dua);
                        binding.input.AngkaKeluar.setText(String.valueOf(finalResult2));
                    } else if (operator == ":"){
                        finalResult = satu / dua;
                        DecimalFormat format = new DecimalFormat("0.#");
                        String Tiga = (format.format(finalResult));
                        System.out.println(satu / dua);
                        binding.input.AngkaKeluar.setText(Tiga);}


                } catch (Exception e){

                }
            }
        });

    }
}