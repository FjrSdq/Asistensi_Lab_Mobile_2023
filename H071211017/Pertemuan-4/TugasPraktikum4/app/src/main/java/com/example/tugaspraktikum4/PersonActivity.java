package com.example.tugaspraktikum4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class PersonActivity extends AppCompatActivity {
    RecyclerView rvMessage;
    ShapeableImageView image;
    ImageButton btnBack;
    TextView name;
    LinearLayout profile;
    List<Message> listMessage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        rvMessage = findViewById(R.id.rvMessage);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        btnBack = findViewById(R.id.btnBack);
        profile = findViewById(R.id.profile);

        if (getIntent() != null && getIntent().hasExtra("image")) {
            int imageRes = getIntent().getIntExtra("image", 0);
            image.setImageDrawable(null);
            Glide.with(this)
                    .load(imageRes)
                    .into(image);
        }

        if(getIntent() != null && getIntent().hasExtra("nama")) {
            name.setText(getIntent().getStringExtra("nama"));
        }

        profile.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("image", getIntent().getIntExtra("image", 0));
            intent.putExtra("nama", getIntent().getStringExtra("nama"));
            intent.putExtra("nomor", getIntent().getStringExtra("nomor"));
            intent.putExtra("status", getIntent().getStringExtra("status"));
            intent.putExtra("lastStatusChanged", getIntent().getStringExtra("lastStatusChanged"));
            startActivity(intent);
        });

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
//        Jaka
        {
            listMessage.add(new Message("Jaka","Hai", "08:00", true));
            listMessage.add(new Message("Jaka","Halo, apa kabar?", "08:01", false));
            listMessage.add(new Message("Jaka","Baik-baik saja, terima kasih.", "08:02", true));
            listMessage.add(new Message("Jaka","Bagus, ada kabar apa?", "08:03", false));
            listMessage.add(new Message("Jaka","Tidak ada kabar apa-apa, hanya ingin menyapa saja.", "08:04", true));
            listMessage.add(new Message("Jaka","Oh, oke. Sama-sama ya.", "08:05", false));
            listMessage.add(new Message("Jaka","Sudah tidur belum?", "22:15", false));
        }
//        Indra
        {
            listMessage.add(new Message("Indra","Hai", "08:00", false));
            listMessage.add(new Message("Indra","Halo Indra, apa kabar?", "08:01", true));
            listMessage.add(new Message("Indra","Baik-baik saja, terima kasih. Bagaimana kabarmu?", "08:02", false));
            listMessage.add(new Message("Indra","Alhamdulillah baik juga, terima kasih.", "08:03", true));
            listMessage.add(new Message("Indra","Ada kabar apa hari ini?", "08:04", true));
            listMessage.add(new Message("Indra","Tidak ada kabar yang terlalu istimewa. Saya hanya menyelesaikan beberapa pekerjaan kantor.", "08:05", false));
            listMessage.add(new Message("Indra","Oh, pekerjaan kantor? Apa itu?", "08:06", true));
            listMessage.add(new Message("Indra","Ya, saya bekerja sebagai programmer di sebuah perusahaan IT. Saya bekerja pada proyek baru yang menarik.", "08:07", false));
            listMessage.add(new Message("Indra","Wah, itu terdengar menarik. Bagaimana proyeknya?", "08:08", true));
            listMessage.add(new Message("Indra","Proyeknya cukup besar dan menantang, tetapi saya senang dengan tantangan tersebut. Saya harap saya bisa menyelesaikan pekerjaan ini dengan baik.", "08:09", true));
            listMessage.add(new Message("Indra","Saya yakin kamu akan berhasil. Semangat ya!", "08:10", false));
            listMessage.add(new Message("Indra","Terima kasih banyak, Indra. Saya sangat menghargai dukunganmu.", "08:11", true));
            listMessage.add(new Message("Indra","Tidak masalah. Kamu selalu bisa mengandalkan saya.", "08:12", true));
            listMessage.add(new Message("Indra","Saya sangat menghargai persahabatan kita.", "08:13", false));
            listMessage.add(new Message("Indra","Sama-sama, teman. Kamu mau makan siang bersama saya nanti siang?", "08:14", true));
            listMessage.add(new Message("Indra","Tentu saja, saya akan senang sekali.", "08:15", false));
            listMessage.add(new Message("Indra","Oke, saya akan mengatur tempat makan yang bagus untuk kita. Sampai nanti!", "08:16", true));
            listMessage.add(new Message("Indra","Sampai nanti. Terima kasih banyak!", "08:17", false));
            listMessage.add(new Message("Indra","Sudah makan malam?", "21:45", false));
        }
//        Hana
        {
            listMessage.add(new Message("Hana","Hai, apa kabar?", "10:00", true));
            listMessage.add(new Message("Hana","Halo, kabarku baik. Bagaimana kabarmu?", "10:01", false));
            listMessage.add(new Message("Hana","Baik-baik saja, terima kasih. Ada rencana untuk akhir pekan ini?", "10:02", true));
            listMessage.add(new Message("Hana","Sampai saat ini, belum ada rencana khusus. Bagaimana denganmu?", "10:03", false));
            listMessage.add(new Message("Hana","Saya merencanakan pergi ke taman pada Sabtu pagi. Mau ikut?", "10:04", true));
            listMessage.add(new Message("Hana","Tentu saja, itu terdengar menyenangkan. Aku akan bergabung.", "10:05", false));
            listMessage.add(new Message("Hana","Oke, aku akan memberi tahu detailnya nanti. Bagaimana pekerjaanmu di kantor?", "10:06", true));
            listMessage.add(new Message("Hana","Pekerjaanku cukup padat tetapi saya bisa menanganinya. Terima kasih sudah bertanya.", "10:07", false));
            listMessage.add(new Message("Hana","Aku baru selesai kerja, lelah banget", "20:30", false));
        }
//        Gita
        {
            listMessage.add(new Message("Gita", "Hai, apa kabar?", "10:10", false));
            listMessage.add(new Message("Gita", "Halo, kabarku baik. Bagaimana kabarmu?", "10:11", true));
            listMessage.add(new Message("Gita", "Kabar baik juga. Sudah makan siang?", "10:12", false));
            listMessage.add(new Message("Gita", "Sudah, terima kasih. Bagaimana denganmu?", "10:13", true));
            listMessage.add(new Message("Gita", "Sudah makan juga tadi pagi. Hey, aku butuh bantuanmu, Aku sedang sibuk di kantor.", "13:09", false));
            listMessage.add(new Message("Gita", "Bisa tolong belikan buah?", "19:10", false));
        }
//        Fadhil
        {
            listMessage.add(new Message("Fadhil", "Hai, apa kabar?", "10:10", true));
            listMessage.add(new Message("Fadhil", "Halo, kabarku baik. Bagaimana kabarmu?", "10:11", false));
            listMessage.add(new Message("Fadhil", "Kabar baik juga. Sudah makan siang?", "10:12", true));
            listMessage.add(new Message("Fadhil", "Sudah, terima kasih. Bagaimana denganmu?", "10:13", false));
            listMessage.add(new Message("Fadhil", "Sudah makan juga tadi pagi. Hey, aku ingin sharing tentang game, kamu suka main game?", "14:25", true));
            listMessage.add(new Message("Fadhil", "Ya, aku suka main game, kamu suka game apa?", "14:30", false));
            listMessage.add(new Message("Fadhil", "Aku suka game mobile legends dan PUBG, kamu?", "14:35", true));
            listMessage.add(new Message("Fadhil", "Sama, aku juga suka dua-duanya. Mau main bareng suatu saat?", "14:40", false));
            listMessage.add(new Message("Fadhil", "Pasti dong! Bagus, nanti kapan-kapan aku kabari lagi ya. Sekarang aku ada kerjaan dulu", "14:45", true));
            listMessage.add(new Message("Fadhil", "Oke, salam untuk keluargamu ya. Oh iya, Mau main futsal?", "18:00", false));
        }
//        Eka
        {
            listMessage.add(new Message("Eka", "Halo, apa kabar?", "08:00", false));
            listMessage.add(new Message("Eka", "Hai, kabarku baik. Bagaimana kabarmu?", "08:01", true));
            listMessage.add(new Message("Eka", "Baik juga. Apa rencanamu hari ini?", "08:02", false));
            listMessage.add(new Message("Eka", "Saya masih belum ada rencana khusus. Bagaimana denganmu?", "08:03", true));
            listMessage.add(new Message("Eka", "Saya juga belum ada rencana. Mau ikut jalan-jalan?", "08:04", true));
            listMessage.add(new Message("Eka", "Oh, itu sepertinya menyenangkan. Kapan dan ke mana?", "08:05", false));
            listMessage.add(new Message("Eka", "Bagaimana kalau besok? Ke pantai?", "08:06", false));
            listMessage.add(new Message("Eka", "Bagus ide mu! Besok aku akan pergi ke pantai", "16:20", false));
        }
//        Dita
        {
            listMessage.add(new Message("Dita", "Hi, apa kabar?", "11:20", true));
            listMessage.add(new Message("Dita", "Hai, kabar saya baik. Kamu sendiri?", "11:21", false));
            listMessage.add(new Message("Dita", "Kabar saya juga baik. Kamu baru pulang dari kampus ya?", "15:44", true));
            listMessage.add(new Message("Dita", "Ya, benar. Baru saja pulang dari kampus.", "15:45", false));
        }
//        Citra
        {
            listMessage.add(new Message("Citra", "Hai, apa kabar?", "11:20", false));
            listMessage.add(new Message("Citra", "Halo, aku baik. Bagaimana kabarmu?", "11:21", true));
            listMessage.add(new Message("Citra", "Kabarku juga baik-baik saja. Apa yang kamu lakukan hari ini?", "11:22", false));
            listMessage.add(new Message("Citra", "Aku baru saja keluar dari kampus. Kamu sendiri?", "11:23", true));
            listMessage.add(new Message("Citra", "Aku sedang mengerjakan tugas akhir. Kamu bisa temani makan siang nanti?", "12:12", false));
            listMessage.add(new Message("Citra", "Tentu saja, kita bisa makan di kantin kampus. Jam berapa kamu bisa?", "12:13", true));
            listMessage.add(new Message("Citra", "Kita bisa makan siang jam 1?", "13:14", false));
            listMessage.add(new Message("Citra", "Baiklah, aku akan siap-siap dan kita bisa bertemu di kantin kampus jam 1", "13:15", true));
            listMessage.add(new Message("Citra", "Kamu mau makan siang?", "13:16", false));
        }
//        Budi
        {
            listMessage.add(new Message("Budi", "Hai, apa kabar?", "09:10", true));
            listMessage.add(new Message("Budi", "Halo, kabarku baik. Bagaimana kabarmu?", "09:11", false));
            listMessage.add(new Message("Budi", "Baik juga. Lagi apa?", "09:12", true));
            listMessage.add(new Message("Budi", "Lagi kerja nih, numpuk banget deadline nya", "09:13", false));
            listMessage.add(new Message("Budi", "Semangat ya! Ada yang bisa aku bantu?", "10:09", true));
            listMessage.add(new Message("Budi", "Makasih, sejauh ini masih bisa dihandle. Ada apa?", "11:30", false));
        }
//        Aisha
        {
            listMessage.add(new Message("Aisha", "Halo, apa kabar?", "09:12", false));
            listMessage.add(new Message("Aisha", "Hai, kabarku baik. Bagaimana kabarmu?", "09:13", true));
            listMessage.add(new Message("Aisha", "Kabar baik juga. Sudah sarapan?", "09:14", false));
            listMessage.add(new Message("Aisha", "Sudah, terima kasih. Bagaimana denganmu?", "09:15", true));
            listMessage.add(new Message("Aisha", "Sudah sarapan juga tadi pagi. Hey, aku ada kabar baik nih, aku mendapatkan pekerjaan baru!", "11:09", false));
            listMessage.add(new Message("Aisha", "Wah, selamat ya! Apa pekerjaannya?", "11:10", true));
            listMessage.add(new Message("Aisha", "Aku akan menjadi seorang programmer di sebuah perusahaan startup. Aku sangat senang!", "11:11", false));
            listMessage.add(new Message("Aisha", "Wow, itu keren sekali! Aku sangat bangga padamu.", "11:12", true));
            listMessage.add(new Message("Aisha", "Terima kasih banyak! Aku sedang menyiapkan segala sesuatunya, jadi aku sedikit sibuk. Ada kabar apa lainnya?", "11:13", false));
            listMessage.add(new Message("Aisha", "Tidak ada kabar spesial sih. Hanya ingin bertanya kabarmu saja. Semoga sukses dengan pekerjaan barumu!", "11:14", true));
            listMessage.add(new Message("Aisha", "Terima kasih banyak! Tentu, semoga kita bisa bertemu dan berbicara lagi suatu saat nanti.", "11:15", false));
            listMessage.add(new Message("Aisha", "Tentu saja, selamat bekerja!", "11:16", true));
            listMessage.add(new Message("Aisha", "Terima kasih, sampai jumpa!", "11:17", false));
            listMessage.add(new Message("Aisha", "Halo, apa kabar?", "15:45", false));
        }

        List<Message> filteredList = filterMessagesBySender(listMessage, getIntent().getStringExtra("nama"));
        MessageAdapter messageAdapter = new MessageAdapter(filteredList,this);
        rvMessage.setAdapter(messageAdapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));

    }
    public List<Message> filterMessagesBySender(List<Message> messages, String senderName) {
        List<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getName().equals(senderName)) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }
}