package com.example.tugas2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
 Spinner spinner;
 EditText etJari2, etPanjang, etLebar, etTinggi;
 Button hitung;
 TextView jari2, panjang, lebar, tinggi, hasil;

 String[] bangunRuang = {"Bola", "Kerucut", "Balok"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner1);
        etJari2 = findViewById(R.id.etJari2);
        etPanjang = findViewById(R.id.etPanjang);
        etLebar = findViewById(R.id.etLebar);
        etTinggi = findViewById(R.id.etTinggi);
        hitung = findViewById(R.id.hitung);
        jari2 = findViewById(R.id.jari2);
        panjang = findViewById(R.id.panjang);
        lebar= findViewById(R.id.lebar);
        tinggi = findViewById(R.id.tinggi);
        hasil = findViewById(R.id.hasil);



        hitung.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItem().toString().equals(bangunRuang[0])){

                    if (etJari2.getText().toString().isEmpty()) {
                        etJari2.setError("Field ini tidak boleh kosong");
                    }else {
                        Double jari2 = Double.valueOf(etJari2.getText().toString());
                        Double volBola = (4.00 / 3.00) * Math.PI * Math.pow(jari2, 3);
                        String duaFormat = String.format("%.2f", volBola);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(bangunRuang[1])) {
                    if (etJari2.getText().toString().isEmpty() && etTinggi.getText().toString().isEmpty()){
                        etJari2.setError("Field ini tidak boleh kosong");
                        etTinggi.setError("Field ini tidak boleh kosong");
                    } else if (etJari2.getText().toString().isEmpty()) {
                        etJari2.setError("Field ini tidak boleh kosong");
                    } else if (etTinggi.getText().toString().isEmpty()) {
                        etTinggi.setError("Field ini tidak boleh kosong");
                    }else {
                        Double jari2 = Double.valueOf(etJari2.getText().toString());
                        Double tinggi = Double.valueOf(etTinggi.getText().toString());
                        Double volKerucut = (Math.PI * Math.pow(jari2, 2) * tinggi) / 3.00;
                        String duaFormat = String.format("%.2f", volKerucut);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(bangunRuang[2])) {
                    if (etPanjang.getText().toString().isEmpty() && etLebar.getText().toString().isEmpty() && etTinggi.getText().toString().isEmpty()){
                        etPanjang.setError("Field ini tidak boleh kosong");
                        etLebar.setError("Field ini tidak boleh kosong");
                        etTinggi.setError("Field ini tidak boleh kosong");
                    } else if (etPanjang.getText().toString().isEmpty() && etLebar.getText().toString().isEmpty()) {
                        etPanjang.setError("Field ini tidak boleh kosong");
                        etLebar.setError("Field ini tidak boleh kosong");
                    } else if (etLebar.getText().toString().isEmpty() && etTinggi.getText().toString().isEmpty()) {
                        etLebar.setError("Field ini tidak boleh kosong");
                        etTinggi.setError("Field ini tidak boleh kosong");
                    } else if (etPanjang.getText().toString().isEmpty() && etTinggi.getText().toString().isEmpty()) {
                        etPanjang.setError("Field ini tidak boleh kosong");
                        etTinggi.setError("Field ini tidak boleh kosong");
                    } else if (etPanjang.getText().toString().isEmpty()) {
                        etPanjang.setError("Field ini tidak boleh kosong");
                    } else if (etLebar.getText().toString().isEmpty()) {
                        etLebar.setError("Field ini tidak boleh kosong");
                    } else if (etTinggi.getText().toString().isEmpty()) {
                        etTinggi.setError("Field ini tidak boleh kosong");
                    }else {
                        Double panjang = Double.valueOf(etPanjang.getText().toString());
                        Double tinggi = Double.valueOf(etTinggi.getText().toString());
                        Double lebar = Double.valueOf(etLebar.getText().toString());
                        Double volBalok = (panjang * tinggi * lebar);
                        String duaFormat = String.format("%.2f", volBalok);
                        hasil.setText(duaFormat);
                    }
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, bangunRuang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){
                    case 0:
                        jari2.setVisibility(View.VISIBLE);
                        etJari2.setVisibility(View.VISIBLE);
                        panjang.setVisibility(View.GONE);
                        etPanjang.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        etLebar.setVisibility(View.GONE);
                        tinggi.setVisibility(View.GONE);
                        etTinggi.setVisibility(View.GONE);
                        hasil.setText("Hasil");
                        break;
                    case 1:
                        jari2.setVisibility(View.VISIBLE);
                        etJari2.setVisibility(View.VISIBLE);
                        panjang.setVisibility(View.GONE);
                        etPanjang.setVisibility(View.GONE);
                        lebar.setVisibility(View.GONE);
                        etLebar.setVisibility(View.GONE);
                        tinggi.setVisibility(View.VISIBLE);
                        etTinggi.setVisibility(View.VISIBLE);
                        hasil.setText("Hasil");
                        break;
                    case 2:
                        jari2.setVisibility(View.GONE);
                        etJari2.setVisibility(View.GONE);
                        panjang.setVisibility(View.VISIBLE);
                        etPanjang.setVisibility(View.VISIBLE);
                        lebar.setVisibility(View.VISIBLE);
                        etLebar.setVisibility(View.VISIBLE);
                        tinggi.setVisibility(View.VISIBLE);
                        etTinggi.setVisibility(View.VISIBLE);
                        hasil.setText("Hasil");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}