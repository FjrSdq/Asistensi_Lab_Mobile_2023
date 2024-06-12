package com.example.chattingapp;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSource {

    public static ArrayList<Chat> chats = new ArrayList<>(
            Arrays.asList( // revisi
                    new Chat("Tutu","oits","13.00","+62 821-3465-0908","affh iyah","Wed at 23.01",R.drawable.p1),
                    new Chat("Ega","halo","12.20","+62 812-3456-7890","busy","1 Sep 2020",R.drawable.p2),
                    new Chat("Edgar","ayo ayo ayo","10.04","+62 852-5677-8998","sedang olahraga","21 Feb 2023",R.drawable.p3),
                    new Chat("Silvanus","haii","09.55","+62 890-0110-0011","fr?","5 Aug 2021",R.drawable.p4),
                    new Chat("Limba","iyaa","13.00","+62 811-2233-4455","rill kah cuy","10 Apr 2022",R.drawable.p5),
                    new Chat("Bagoes","spill tugasmu dlu sob" ,"14.36","+62 822-3443-5775","fire","30 Dec 2018",R.drawable.p6),
                    new Chat("Ibnu","bah gasmi","23.02","+62 855-6677-8899","sedang mengetik...","14 Jun 2020",R.drawable.p7),
                    new Chat("Silby","hmmm","05.15","+62 813-2244-6688","typing...","17 Dec 2022",R.drawable.p8)
            )
    );
}
