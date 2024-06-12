package com.example.tuprak2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView jari, panjang, lebar, tinggi, hasil;

    EditText jari2, panjang2, lebar2, tinggi2;

    Button button;

    String[] BangunRuang = {"Bola", "Kerucut", "Balok"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bruang, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        jari = findViewById(R.id.jari);
        jari2 = findViewById(R.id.jari2);
        panjang = findViewById(R.id.panjang);
        panjang2 = findViewById(R.id.panjang2);
        lebar = findViewById(R.id.lebar);
        lebar2 = findViewById(R.id.lebar2);
        tinggi = findViewById(R.id.tinggi);
        tinggi2 = findViewById(R.id.tinggi2);
        button = findViewById(R.id.button);
        hasil = findViewById(R.id.hasil);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItem().toString().equals(BangunRuang[0])){
                    if (jari2.getText().toString().isEmpty()) {
                        jari2.setError("Field ini tidak boleh kosong");
                    }else {
                        Double jari = Double.valueOf(jari2.getText().toString());
                        Double volumeBola = (4.00/3.00) * Math.PI * Math.pow(jari, 3);
                        String duaFormat = String.format("%.2f", volumeBola);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(BangunRuang[1])) {
                    if (jari2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()){
                        jari2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (jari2.getText().toString().isEmpty()) {
                        jari2.setError("Field ini tidak boleh kosong");
                    } else if (tinggi2.getText().toString().isEmpty()) {
                        tinggi2.setError("Field ini tidak boleh kosong");
                    }else {
                        Double jari = Double.valueOf(jari2.getText().toString());
                        Double tinggi = Double.valueOf(tinggi2.getText().toString());
                        Double volumeKerucut = (Math.PI * Math.pow(jari, 2) * tinggi) / 3.00;
                        String duaFormat = String.format("%.2f", volumeKerucut);
                        hasil.setText(duaFormat);
                    }
                } else if (spinner.getSelectedItem().toString().equals(BangunRuang[2])) {
                    if (panjang2.getText().toString().isEmpty() && lebar2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                        lebar2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (panjang2.getText().toString().isEmpty() && lebar2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                        lebar2.setError("Field ini tidak boleh kosong");
                    } else if (panjang2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (lebar2.getText().toString().isEmpty() && tinggi2.getText().toString().isEmpty()) {
                        lebar2.setError("Field ini tidak boleh kosong");
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else if (panjang2.getText().toString().isEmpty()) {
                        panjang2.setError("Field ini tidak boleh kosong");
                    } else if (lebar2.getText().toString().isEmpty()) {
                        lebar2.setError("Field ini tidak boleh kosong");
                    } else if (tinggi2.getText().toString().isEmpty()) {
                        tinggi2.setError("Field ini tidak boleh kosong");
                    } else {
                        Double panjang = Double.valueOf(panjang2.getText().toString());
                        Double lebar = Double.valueOf(lebar2.getText().toString());
                        Double tinggi = Double.valueOf(tinggi2.getText().toString());
                        Double volumeBalok = (panjang * lebar * tinggi);
                        String duaFormat = String.format("%.2f", volumeBalok);
                        hasil.setText(duaFormat);
                    }
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();
        switch (position){
            case 0:
                jari.setVisibility(View.VISIBLE);
                jari2.setVisibility(View.VISIBLE);
                panjang.setVisibility(View.GONE);
                panjang2.setVisibility(View.GONE);
                lebar.setVisibility(View.GONE);
                lebar2.setVisibility(View.GONE);
                tinggi.setVisibility(View.GONE);
                tinggi2.setVisibility(View.GONE);
                hasil.setText("Hasil");
                break;
            case 1:
                jari.setVisibility(View.VISIBLE);
                jari2.setVisibility(View.VISIBLE);
                panjang.setVisibility(View.GONE);
                panjang2.setVisibility(View.GONE);
                lebar.setVisibility(View.GONE);
                lebar2.setVisibility(View.GONE);
                tinggi.setVisibility(View.VISIBLE);
                tinggi2.setVisibility(View.VISIBLE);
                hasil.setText("Hasil");
                break;
            case 2:
                jari.setVisibility(View.GONE);
                jari2.setVisibility(View.GONE);
                panjang.setVisibility(View.VISIBLE);
                panjang2.setVisibility(View.VISIBLE);
                lebar.setVisibility(View.VISIBLE);
                lebar2.setVisibility(View.VISIBLE);
                tinggi.setVisibility(View.VISIBLE);
                tinggi2.setVisibility(View.VISIBLE);
                hasil.setText("Hasil");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}