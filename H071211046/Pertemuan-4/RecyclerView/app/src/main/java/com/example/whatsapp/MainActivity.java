package com.example.whatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    LinearLayoutManager lm;
    List<ModelClass>userList;
    Adapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initdata();
        initRecyclerview();
    }

    private void initdata() {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.harry,"Harry Potter", "7:45 pm","I'm with Hagrid again","Hi i'm Harry","081355072297"));
        userList.add(new ModelClass(R.drawable.hermione,"Hermione Granger", "12:10 pm","Can I borrow your book?","love books","0896734561234"));
        userList.add(new ModelClass(R.drawable.ron,"Ron Weasley", "5:42 pm","Where's harry?","arachnofobia","081322121234"));
        userList.add(new ModelClass(R.drawable.draco,"Draco Malfoy", "9:45 am","have you seen Harry Potter?","i hate harry","08889996655"));
        userList.add(new ModelClass(R.drawable.oliver,"Oliver Wood", "7:00 pm","Harry rehearsed with me","Quidditch captain", "989898989"));
        userList.add(new ModelClass(R.drawable.luna,"Luna Lovegood", "6:45 pm","We meet in the library","always cheerful","90908989898"));
        userList.add(new ModelClass(R.drawable.susan,"Susan Bones", "3:28 pm","You're the best","half-blood","8388383838"));
        userList.add(new ModelClass(R.drawable.daphne,"Daphne Greengrass", "2:45 pm","Malfoy is in Diagon Alley","pure-blood witch","889898989898"));
        userList.add(new ModelClass(R.drawable.eddie,"Eddie Carmichael", "1:13 am","Let's study together","I'm Ravenclaw","83983983983"));
    }

    private void initRecyclerview() {
        rv = findViewById(R.id.recycler_view);
        lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(lm);
        ad = new Adapter(userList);
        rv.setAdapter(ad);
        ad.notifyDataSetChanged();
//        ad.setOnItemClickListener(new Adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
//                intent.putExtra("image",userList.get(position).getImageview1());
//                intent.putExtra("textview",userList.get(position).getTextview());
//                intent.putExtra("textview2",userList.get(position).getTextview3());
//                intent.putExtra("textview3",userList.get(position).getTextview4());
//                startActivity(intent);
//            }
//        });

    }
}