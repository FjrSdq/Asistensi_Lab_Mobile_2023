package com.example.tugaspraktikum3b;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugaspraktikum3b.databinding.ActivityKuisBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KuisActivity extends AppCompatActivity {

    ActivityKuisBinding binding;
    List<Pertanyaan> listPertanyaan = new ArrayList<>();
    private int score, bestscoreKuis, nomor;
    private String kuis;
    String newBestScore = "New Best Score";
    String bestScoreText = "Best Score";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKuisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inisialisasiPertanyaan();

        String usernameKuis = getIntent().getStringExtra("username");
        String profilKuis = getIntent().getStringExtra("profil");

        Intent home = new Intent(KuisActivity.this, MainActivity.class);

        binding.opsi1.setOnClickListener(view -> onClickOpsi(binding.opsi1.getText().toString()));
        binding.opsi2.setOnClickListener(view -> onClickOpsi(binding.opsi2.getText().toString()));
        binding.opsi3.setOnClickListener(view -> onClickOpsi(binding.opsi3.getText().toString()));
        binding.opsi4.setOnClickListener(view -> onClickOpsi(binding.opsi4.getText().toString()));

        binding.btnHome.setOnClickListener(view -> {
            home.putExtra("profil",profilKuis);
            home.putExtra("username",usernameKuis);
            home.putExtra("bestscore",bestscoreKuis);
            startActivity(home);
        });

    }

    private void inisialisasiPertanyaan() {

        score = 0;
        bestscoreKuis = getIntent().getIntExtra("bestscore",0);
        nomor = 1;

        Pertanyaan pertanyaan1 = new Pertanyaan("Hasil dari warna kuning campur biru?", "Hijau", "Kuning", "Ungu", "Merah", "Hijau");
        Pertanyaan pertanyaan2 = new Pertanyaan("Apa nama ibu kota Indonesia?", "Sunda Empire", "Bali", "Jakarta", "Makassar", "Jakarta");
        Pertanyaan pertanyaan3 = new Pertanyaan("Apa nama ibu kota Jepang?", "Kyoto", "Tokyo", "Seoul", "Solo", "Tokyo");
        Pertanyaan pertanyaan4 = new Pertanyaan("Raja para dewa di mitologi Yunani adalah?", "Poseidon", "Zeus", "Hades", "Odin", "Zeus");
        Pertanyaan pertanyaan5 = new Pertanyaan("Panggilan untuk orang yang suka hal-hal tentang Jepang?", "Spek anime", "Onion", "Otaku", "Wibu", "Wibu");

        listPertanyaan.add(pertanyaan1);
        listPertanyaan.add(pertanyaan2);
        listPertanyaan.add(pertanyaan3);
        listPertanyaan.add(pertanyaan4);
        listPertanyaan.add(pertanyaan5);

        Collections.shuffle(listPertanyaan);

        kuis = "Quiz " + nomor + " of 5";
        binding.nomor.setText(kuis);

        binding.cardPertanyaanAtas.setVisibility(View.VISIBLE);
        binding.cardHasilAtas.setVisibility(View.GONE);
        binding.cardPertanyaanBawah.setVisibility(View.VISIBLE);
        binding.cardHasilBawah.setVisibility(View.GONE);

        Pertanyaan pertanyaan = listPertanyaan.get(0);
        binding.pertanyaan.setText(pertanyaan.getPertanyaan());
        binding.opsi1.setText(pertanyaan.getOpsi1());
        binding.opsi2.setText(pertanyaan.getOpsi2());
        binding.opsi3.setText(pertanyaan.getOpsi3());
        binding.opsi4.setText(pertanyaan.getOpsi4());

    }

    public void disableOnClickOpsi() {
        binding.opsi1.setClickable(false);
        binding.opsi2.setClickable(false);
        binding.opsi3.setClickable(false);
        binding.opsi4.setClickable(false);
    }
    public void enableOnClickOpsi() {
        binding.opsi1.setClickable(true);
        binding.opsi2.setClickable(true);
        binding.opsi3.setClickable(true);
        binding.opsi4.setClickable(true);
    }


    public void onClickOpsi(String opsi) {
        disableOnClickOpsi();

        ColorStateList warnaDefault = binding.cardOpsi1.getCardBackgroundColor();
        if (listPertanyaan.isEmpty()) {
            return;
        }

        Pertanyaan pertanyaan = listPertanyaan.remove(0);
        int hijau = Color.parseColor("#77DD77");
        int merah = Color.parseColor("#ff6961");

//        Ubah warna card
        if (opsi.equals(pertanyaan.getJawaban())) {
            if (opsi.equals(pertanyaan.getOpsi1())) {
                binding.cardOpsi1.setCardBackgroundColor(hijau);
            } else if (opsi.equals(pertanyaan.getOpsi2())) {
                binding.cardOpsi2.setCardBackgroundColor(hijau);
            } else if (opsi.equals(pertanyaan.getOpsi3())) {
                binding.cardOpsi3.setCardBackgroundColor(hijau);
            } else if (opsi.equals(pertanyaan.getOpsi4())) {
                binding.cardOpsi4.setCardBackgroundColor(hijau);
            }
            score += 20;
        }
        else {
            if (opsi.equals(pertanyaan.getOpsi1())) {
                binding.cardOpsi1.setCardBackgroundColor(merah);
            } else if (opsi.equals(pertanyaan.getOpsi2())) {
                binding.cardOpsi2.setCardBackgroundColor(merah);
            } else if (opsi.equals(pertanyaan.getOpsi3())) {
                binding.cardOpsi3.setCardBackgroundColor(merah);
            } else if (opsi.equals(pertanyaan.getOpsi4())) {
                binding.cardOpsi4.setCardBackgroundColor(merah);
            }
        }

//        Cek apakah pertanyaan masih ada
        new Handler().postDelayed(() -> {
            cekPertanyaan(warnaDefault);
            enableOnClickOpsi();
        }, 1000);

    }

    private void cekPertanyaan(ColorStateList warnaDefault) {
        String usernameKuis = getIntent().getStringExtra("username");
        String ggwpNick = "GGWP "+usernameKuis+"!";
        if (listPertanyaan.size() == 0) {
            if (score > bestscoreKuis) {
                binding.bestScoreText.setText(newBestScore);
                bestscoreKuis = score;
            } else {
                binding.bestScoreText.setText(bestScoreText);
            }
            binding.nick.setText(ggwpNick);
            binding.cardPertanyaanAtas.setVisibility(View.GONE);
            binding.cardPertanyaanBawah.setVisibility(View.GONE);
            binding.cardHasilAtas.setVisibility(View.VISIBLE);
            binding.cardHasilBawah.setVisibility(View.VISIBLE);
            binding.score.setText(String.valueOf(score));
            binding.bestScore.setText(String.valueOf(bestscoreKuis));
            score = 0;
        }
        else {
            nomor++;
            kuis = "Quiz " + nomor + " of 5";
            binding.nomor.setText(kuis);
            binding.cardOpsi1.setCardBackgroundColor(warnaDefault);
            binding.cardOpsi2.setCardBackgroundColor(warnaDefault);
            binding.cardOpsi3.setCardBackgroundColor(warnaDefault);
            binding.cardOpsi4.setCardBackgroundColor(warnaDefault);
            Pertanyaan pertanyaan = listPertanyaan.get(0);
            binding.pertanyaan.setText(pertanyaan.getPertanyaan());
            binding.opsi1.setText(pertanyaan.getOpsi1());
            binding.opsi2.setText(pertanyaan.getOpsi2());
            binding.opsi3.setText(pertanyaan.getOpsi3());
            binding.opsi4.setText(pertanyaan.getOpsi4());
        }
    }
}

class Pertanyaan{
    private final String pertanyaan, opsi1, opsi2, opsi3, opsi4, jawaban;

    public Pertanyaan(String pertanyaan, String opsi1, String opsi2, String opsi3, String opsi4, String jawaban) {
        this.pertanyaan = pertanyaan;
        this.opsi1 = opsi1;
        this.opsi2 = opsi2;
        this.opsi3 = opsi3;
        this.opsi4 = opsi4;
        this.jawaban = jawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public String getOpsi1() {
        return opsi1;
    }

    public String getOpsi2() {
        return opsi2;
    }

    public String getOpsi3() {
        return opsi3;
    }

    public String getOpsi4() {
        return opsi4;
    }

    public String getJawaban() {
        return jawaban;
    }
}