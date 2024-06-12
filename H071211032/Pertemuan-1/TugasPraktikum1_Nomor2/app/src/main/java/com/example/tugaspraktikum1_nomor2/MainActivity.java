package com.example.tugaspraktikum1_nomor2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Spinner select;
    ViewFlipper content;
    Button calculate;
    String geometria;
    TextView result;
    EditText ballRadius, coneRadius, coneHeight, blockLength, blockHeight, blockWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select = (Spinner) findViewById(R.id.selectionDropdown);
        content = (ViewFlipper) findViewById(R.id.containerContent);
        calculate = (Button) findViewById(R.id.calculate);
        result = (TextView) findViewById(R.id.result);

        ballRadius = (EditText) findViewById(R.id.jariJariBola);
        coneRadius = (EditText) findViewById(R.id.jariJariKerucut);
        coneHeight = (EditText) findViewById(R.id.tinggiKerucut);
        blockLength = (EditText) findViewById(R.id.panjangBalok);
        blockHeight = (EditText) findViewById(R.id.tinggiBalok);
        blockWidth = (EditText) findViewById(R.id.lebarBalok);

        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // Pemilihan geometria dari spinner
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                geometria = (String) select.getSelectedItem().toString();

                switch (geometria) {
                    case "Bola":
                        content.setDisplayedChild(0);
                        break;
                    case "Kerucut":
                        content.setDisplayedChild(1);
                        break;
                    case "Balok":
                        content.setDisplayedChild(2);
                        break;
                }

                result.setText(R.string.result);
                reset(ballRadius, coneRadius, coneHeight, blockLength, blockHeight, blockWidth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {/* NOPE */}
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            double volume = 0;
            float r, l, w, h;

            @Override
            public void onClick(View view) {
                if (geometria.equalsIgnoreCase("Bola")) {
                    if (filter(ballRadius)) return;

                    /* Hitung Volume Bola */
                    r = (float) Integer.parseInt(ballRadius.getText().toString());
                    volume = Math.round(4.0/3.0 * Math.PI * Math.pow(r, 3) * 100.0) / 100.0;

                    result.setText(Double.toString(volume));

                } else if (geometria.equalsIgnoreCase("Kerucut")) {
                    if (filter(coneRadius, coneHeight)) return;

                    /* Hitung Volume Kerucut */
                    r = (float) Integer.parseInt(coneRadius.getText().toString());
                    h = (float) Integer.parseInt(coneHeight.getText().toString());
                    volume = Math.round(1.0/3.0 * Math.PI * Math.pow(r, 2) * h * 100.0) / 100.0;

                    result.setText(Double.toString(volume));

                } else if (geometria.equalsIgnoreCase("Balok")) {
                    if (filter(blockLength, blockHeight, blockWidth)) return;

                    /* Hitung Volume Balok */
                    l = (float) Integer.parseInt(blockLength.getText().toString());
                    w = (float) Integer.parseInt(blockHeight.getText().toString());
                    h = (float) Integer.parseInt(blockWidth.getText().toString());
                    volume = Math.round(l * w * h * 100.0) / 100.0;

                    result.setText(Double.toString(volume));
                }
            }
        });
    }

    protected boolean filter(EditText... input) { // Filter input kosong
        for (EditText i : input) {
            String filter = i.getText().toString();

            if (filter.matches("")) {
                i.setError("Input nilai dengan benar!");
                this.result.setText(R.string.result_warning);
                return true;
            }
        }
        return false;
    }

    protected void reset(EditText... input) { // Reset input setiap geometria berubah
        for (EditText i : input)
            i.getText().clear();
    }
}