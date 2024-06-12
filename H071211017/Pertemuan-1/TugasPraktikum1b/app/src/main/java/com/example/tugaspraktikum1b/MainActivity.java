package com.example.tugaspraktikum1b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Spinner dropdown;
    Button hitung;
    TextView label1, label2, label3, labelHasil;
    EditText input1, input2, input3;
    double nilai1, nilai2, nilai3, hasil;
    final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    String formatted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dropdown = (Spinner) findViewById(R.id.dropdown);
        hitung = (Button) findViewById(R.id.tombol_hitung);
        label1 = (TextView) findViewById(R.id.label1);
        input1 = (EditText) findViewById(R.id.input1);
        label2 = (TextView) findViewById(R.id.label2);
        input2 = (EditText) findViewById(R.id.input2);
        label3 = (TextView) findViewById(R.id.label3);
        input3 = (EditText) findViewById(R.id.input3);
        labelHasil = (TextView) findViewById(R.id.hasil);

//        Hide all label and input
        label1.setVisibility(View.GONE);
        input1.setVisibility(View.GONE);
        label2.setVisibility(View.GONE);
        input2.setVisibility(View.GONE);
        label3.setVisibility(View.GONE);
        input3.setVisibility(View.GONE);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
//                    Kubus
                    case 0:
                        label1.setText("Panjang rusuk");
                        label1.setVisibility(View.VISIBLE);
                        input1.setVisibility(View.VISIBLE);
                        input1.getText().clear();
                        input1.setError(null);
                        label2.setVisibility(View.GONE);
                        input2.setVisibility(View.GONE);
                        label3.setVisibility(View.GONE);
                        input3.setVisibility(View.GONE);
                        labelHasil.setText("Masukkan input untuk melihat hasil");
                        break;
//                        Bola
                    case 1:
                        label1.setText("Jari-jari");
                        label1.setVisibility(View.VISIBLE);
                        input1.setVisibility(View.VISIBLE);
                        input1.getText().clear();
                        input1.setError(null);
                        label2.setVisibility(View.GONE);
                        input2.setVisibility(View.GONE);
                        label3.setVisibility(View.GONE);
                        input3.setVisibility(View.GONE);
                        labelHasil.setText("Masukkan input untuk melihat hasil");
                        break;
//                        Silinder
                    case 2:
//                        Kerucut
                    case 3:
                        label1.setText("Jari-jari");
                        label2.setText("Tinggi");
                        label1.setVisibility(View.VISIBLE);
                        input1.setVisibility(View.VISIBLE);
                        input1.getText().clear();
                        input1.setError(null);
                        label2.setVisibility(View.VISIBLE);
                        input2.setVisibility(View.VISIBLE);
                        input2.getText().clear();
                        input2.setError(null);
                        label3.setVisibility(View.GONE);
                        input3.setVisibility(View.GONE);
                        labelHasil.setText("Masukkan input untuk melihat hasil");
                        break;
                    //                        Balok
                    case 4:
                        label1.setText("Panjang");
                        label2.setText("Lebar");
                        label3.setText("Tinggi");
                        label1.setVisibility(View.VISIBLE);
                        input1.setVisibility(View.VISIBLE);
                        input1.getText().clear();
                        input1.setError(null);
                        label2.setVisibility(View.VISIBLE);
                        input2.setVisibility(View.VISIBLE);
                        input2.getText().clear();
                        input2.setError(null);
                        label3.setVisibility(View.VISIBLE);
                        input3.setVisibility(View.VISIBLE);
                        input3.getText().clear();
                        input3.setError(null);
                        labelHasil.setText("Masukkan input untuk melihat hasil");
                        break;
                }

                hitung.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
//                Hitung berdasarkan bangun ruang
                        switch (position){
//                            Kubus
                            case 0:
                                if( input1.getText().toString().isEmpty() ){
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                } else {
                                    input1.setError(null);
                                    nilai1 = Double.parseDouble(input1.getText().toString());
                                    hasil = Math.pow(nilai1, 3);
                                    formatted = decimalFormat.format(hasil);
                                    labelHasil.setText(formatted);
                                }
                                break;
//                            Bola
                            case 1:
                                if( input1.getText().toString().isEmpty() ){
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                } else {
                                    input1.setError(null);
                                    nilai1 = Double.parseDouble(input1.getText().toString());
                                    hasil = (4*Math.PI*Math.pow(nilai1,3))/3;
                                    formatted = decimalFormat.format(hasil);
                                    labelHasil.setText(formatted);
                                }
                                break;
//                            Silinder
                            case 2:
                                if ( input1.getText().toString().isEmpty() && input2.getText().toString().isEmpty() ) {
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                } else if( input1.getText().toString().isEmpty() ){
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError(null);
                                } else if ( input2.getText().toString().isEmpty() ) {
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input1.setError(null);
                                } else {
                                    nilai1 = Double.parseDouble(input1.getText().toString());
                                    nilai2 = Double.parseDouble(input2.getText().toString());
                                    hasil = Math.PI*Math.pow(nilai1,2)*nilai2;
                                    formatted = decimalFormat.format(hasil);
                                    labelHasil.setText(formatted);
                                }
                                break;
//                            Kerucut
                            case 3:
                                if ( input1.getText().toString().isEmpty() && input2.getText().toString().isEmpty() ) {
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                } else if( input1.getText().toString().isEmpty() ){
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError(null);
                                } else if ( input2.getText().toString().isEmpty() ) {
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input1.setError(null);
                                } else {
                                    nilai1 = Double.parseDouble(input1.getText().toString());
                                    nilai2 = Double.parseDouble(input2.getText().toString());
                                    hasil = (Math.PI*Math.pow(nilai1,2)*nilai2)/3;
                                    formatted = decimalFormat.format(hasil);
                                    labelHasil.setText(formatted);
                                }
                                break;
//                            Balok
                            case 4:
                                if ( input1.getText().toString().isEmpty() && input2.getText().toString().isEmpty() && input3.getText().toString().isEmpty() ){
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input3.setError("Masukkan "+label3.getText().toString().toLowerCase()+" terlebih dahulu!");
                                } else if ( input1.getText().toString().isEmpty() && input2.getText().toString().isEmpty() ) {
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input3.setError(null);
                                } else if ( input2.getText().toString().isEmpty() && input3.getText().toString().isEmpty() ) {
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input3.setError("Masukkan "+label3.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input1.setError(null);
                                } else if ( input1.getText().toString().isEmpty() && input3.getText().toString().isEmpty() ) {
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input3.setError("Masukkan "+label3.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError(null);
                                } else if( input1.getText().toString().isEmpty() ){
                                    input1.setError("Masukkan "+label1.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input2.setError(null);
                                    input3.setError(null);
                                } else if ( input2.getText().toString().isEmpty() ) {
                                    input2.setError("Masukkan "+label2.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input1.setError(null);
                                    input3.setError(null);
                                } else if (input3.getText().toString().isEmpty()){
                                    input3.setError("Masukkan "+label3.getText().toString().toLowerCase()+" terlebih dahulu!");
                                    input1.setError(null);
                                    input2.setError(null);
                                } else {
                                    nilai1 = Double.parseDouble(input1.getText().toString());
                                    nilai2 = Double.parseDouble(input2.getText().toString());
                                    nilai3 = Double.parseDouble(input3.getText().toString());
                                    hasil = nilai1*nilai2*nilai3;
                                    formatted = decimalFormat.format(hasil);
                                    labelHasil.setText(formatted);
                                }
                                break;
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}