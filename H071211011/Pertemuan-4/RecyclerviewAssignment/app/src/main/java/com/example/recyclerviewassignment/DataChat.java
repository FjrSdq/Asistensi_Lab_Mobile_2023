package com.example.recyclerviewassignment;

import java.util.ArrayList;

public class DataChat {
    public  static ArrayList<ChatModel> chats = generateDummyChatModels();

    private static ArrayList<ChatModel> generateDummyChatModels() {
        ArrayList<ChatModel> chats = new ArrayList<>();
        chats.add(new ChatModel("Taehyung","https://i.pinimg.com/originals/32/37/f6/3237f6f6708e75ed0645198835907cba.jpg","+62 812 4687-8739","Winter Bear","February 8,2022","Leo integer malesuada nunc vel","22.44"));
        chats.add(new ChatModel("Jungkook","https://i.pinimg.com/564x/a3/36/d3/a336d3c95049b131942c9b9b045159a7.jpg","+62 812 7483-8899","Dreamer","March 21,2021","Purus semper eget duis at tellus at urna","22.35"));
        chats.add(new ChatModel("Wonwoo","https://i.pinimg.com/564x/68/df/e0/68dfe07400b9378d5e43c3f29bda514d.jpg","+62 812 1210-3292","Home","January 5,2023","Neque gravida","22.17"));
        chats.add(new ChatModel("Jisoo","https://i.pinimg.com/564x/99/0c/fe/990cfe41b14153a3969eec8d01a198df.jpg","+62 812 3309-3434","Flowers","August 25,2020","Arcu cursus euismod quis viverra nibh cras pulvinar. Gravida in fermentum et sollicitudin ac orci phasellus","21.59"));
        chats.add(new ChatModel("Jimin","https://i.pinimg.com/564x/00/63/9b/00639b085a68a3645a8a42dfab93ebeb.jpg","+62 812 6373-8378","Like Crazy","June 2,2022","Aliquet nibh praesent tristique magna sit","21.48"));
        chats.add(new ChatModel("Rose","https://i.pinimg.com/564x/ec/92/af/ec92af7913f9b76d44a6c5f6bb3d81c9.jpg","+62 812 6722-8439","On The Ground","October 27,2021","Et malesuada","21.38"));
        chats.add(new ChatModel("Suga","https://i.pinimg.com/736x/9f/e9/13/9fe913df5ee7bdf16e29b589d52cf937.jpg","+62 812 4744-7860","Daechwita","April 18,2023","Sit amet justo donec enim diam","21.26"));
        chats.add(new ChatModel("Mingyu","https://i.pinimg.com/564x/84/6c/3c/846c3c5e95d7452f0331bec35589e943.jpg","+62 812 8482-3767","World","May 10,2022","Vel elit scelerisque mauris pellentesque","21.12"));
        chats.add(new ChatModel("Jennie","https://i.pinimg.com/564x/45/d7/f3/45d7f36ed447a8480690540877fbda3f.jpg","+62 812 4783-9892","Solo","September 4,2022","Sagittis orci","21.03"));
        chats.add(new ChatModel("S-Coups","https://i.pinimg.com/564x/33/70/50/3370504d8a1db36907b5fa6971a1f7b7.jpg","+62 812 2341-3652","Left and Right","July 26,2023","Purus sit amet volutpat consequat","20.56"));
        chats.add(new ChatModel("RM","https://i.pinimg.com/564x/ab/65/ae/ab65ae4dca653f0d0e31282d15ee5872.jpg","+62 812 6736-7463","Wild Flower","December 1,2021","Consectetur adipiscing ","20.45"));
        chats.add(new ChatModel("Lisa","https://i.pinimg.com/564x/00/7f/ba/007fba46854ee08568656d48fbea13ea.jpg","+62 812 9898-7843","Lalisa","November 11,2022","Purus non enim praesent","20.37"));
        chats.add(new ChatModel("J-Hope","https://i.pinimg.com/564x/d8/42/d4/d842d4661b2f4c7077f2fae050ee149d.jpg","+62 812 6791-2102","On the Street","October 9,2020","Nam at lectus urna duis convallis","20.32"));
        chats.add(new ChatModel("Joshua","https://i.pinimg.com/564x/5f/90/20/5f90201f782b39f8d9b99cd514d34ef9.jpg","+62 812 9091-0082","Hot","July 12,2022","Mauris ultrices eros in cursus","20.15"));
        chats.add(new ChatModel("Jaemin","https://i.pinimg.com/564x/56/db/fc/56dbfc59d7a7507747267045467e3bc5.jpg","+62 812 1104-7921","Candy","December 20,2023","Nisl nunc mi ipsum","20.10"));
        return chats;
    }


}