package com.example.speedychat;

import java.util.ArrayList;
import java.util.List;

public class DataChat {

    private static List<List<ModelBubble>>chats(){
        List<List<ModelBubble>>chats = new ArrayList<>();

        List<ModelBubble>chat1= new ArrayList<>();
        chat1.add(new ModelBubble("Congrats Leo","19.20", true));
        chat1.add(new ModelBubble("You are the GOAT","19.21", false));
        chat1.add(new ModelBubble("Way Better than Cristiano","19.21", true));
        chat1.add(new ModelBubble("Anyways","19.25", false));
        chat1.add(new ModelBubble("Wanna hang after the ceremony?","19.26", false));

        chats.add(chat1);

        List<ModelBubble>chat2= new ArrayList<>();
        chat2.add(new ModelBubble("Hey Ney?","19.01", true));
        chat2.add(new ModelBubble("Got any suggestions for a place to take a girl out at Paris?","19.01", false));

        chats.add(chat2);

        List<ModelBubble>chat3= new ArrayList<>();
        chat3.add(new ModelBubble("Hey Cris","18.47", true));
        chat3.add(new ModelBubble("Enjoy your stay at Al-Nassr?","18.47", false));

        chats.add(chat3);

        List<ModelBubble>chat4= new ArrayList<>();
        chat4.add(new ModelBubble("Terminator","18.46", true));
        chat4.add(new ModelBubble("Are you gonna break the most goals scored in a Premier League Season record?","18.46", false));

        chats.add(chat4);

        List<ModelBubble>chat5= new ArrayList<>();
        chat5.add(new ModelBubble("Hey Pedri","18.45", true));
        chat5.add(new ModelBubble("Hope you heal soon man. Barca need you.","18.45", false));

        chats.add(chat5);

        List<ModelBubble>chat6= new ArrayList<>();
        chat6.add(new ModelBubble("Hey Barca, just got you mail","18.30", true));
        chat6.add(new ModelBubble("So, you want to offer me a spot in the First Team?","18.31", false));

        chats.add(chat6);

        List<ModelBubble>chat7= new ArrayList<>();
        chat7.add(new ModelBubble("Hey Ninja Turtle","18.22", true));
        chat7.add(new ModelBubble("I think I know why you lost with France","18.23", false));
        chat7.add(new ModelBubble("I guess they just don't pay you enough lol","18.23", true));

        chats.add(chat7);

        List<ModelBubble>chat8= new ArrayList<>();
        chat8.add(new ModelBubble("Angel","18.11", true));
        chat8.add(new ModelBubble("Thank you for scoring in the World Cup finals to help Messi win it","18.12", false));

        chats.add(chat8);

        List<ModelBubble>chat9= new ArrayList<>();
        chat9.add(new ModelBubble("Robert","17.11", true));
        chat9.add(new ModelBubble("I know why you like to score goals","17.11", false));
        chat9.add(new ModelBubble("Cuz you name is Lewangoalski right? LMAO","17.12", true));

        chats.add(chat9);

        List<ModelBubble>chat10= new ArrayList<>();
        chat10.add(new ModelBubble("HAKIMIIII","16.43", true));
        chat10.add(new ModelBubble("YOURE THE MANN!!","16.44", false));
        chat10.add(new ModelBubble("Hope your ex-wife learned her lesson","16.45", true));
        chats.add(chat10);


        return chats;
    }

    public static ArrayList<ModelChat>
    ambilDataChat() {
        ArrayList<ModelChat> dataChat = new ArrayList<>();
        dataChat.add(new ModelChat("Lionel Messi",chats().get(0), "https://cdn-japantimes.com/wp-content/uploads/2022/12/np_file_200271-scaled.jpeg", "081567895526", "I am the GOAT", "15.48"));
        dataChat.add(new ModelChat("Neymar Jr", chats().get(1), "https://sportsmatik.com/uploads/world-events/players/nymer_1564473570.jpg", "082658466528", "Still in the Hospital", "12.19"));
        dataChat.add(new ModelChat("Cristiano Ronaldo", chats().get(2), "https://i.pinimg.com/originals/6a/7c/b1/6a7cb1f2ed5898d34418c50651ec3bf8.jpg", "08225136531", "I am have more Champions League goals than Messi ", "17.32"));
        dataChat.add(new ModelChat("Erling Haaland", chats().get(3), "https://images.tokopedia.net/img/JFrBQq/2022/9/5/f1732629-6e94-4cdd-a5a3-02608b214c6b.jpg", "08452684128", "Top Scorer of Premier League", "00.02"));
        dataChat.add(new ModelChat("Pedri", chats().get(4), "https://www.thesun.co.uk/wp-content/uploads/2022/04/crop-18155527-1.jpg?w=620", "083456789012", "The Next Iniesta", "05.49"));
        dataChat.add(new ModelChat("Barcelona FC", chats().get(5), "https://c4.wallpaperflare.com/wallpaper/639/159/622/soccer-fc-barcelona-logo-wallpaper-preview.jpg", "08111111111", "Best Team in the World", "14.04"));
        dataChat.add(new ModelChat("Kylian Mbappe", chats().get(6), "https://c4.wallpaperflare.com/wallpaper/363/408/878/kylian-mbappe-celebrates-fifa-world-cup-win-wallpaper-preview.jpg", "082585516457", "I love money", "11.56"));
        dataChat.add(new ModelChat("Angel Di Maria", chats().get(7), "https://upload.wikimedia.org/wikipedia/commons/4/41/%C3%81ngel_Di_Mar%C3%ADa_2018.jpg", "082853971554", "Just helped the GOAT win the world cup", "19.21"));
        dataChat.add(new ModelChat("Robert Lewandowski", chats().get(8), "https://scontent.fupg6-1.fna.fbcdn.net/v/t1.6435-9/117433346_2736825096539568_7517131907909859858_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=730e14&_nc_ohc=jHAXOO2OZ1IAX9NDRhJ&_nc_ht=scontent.fupg6-1.fna&oh=00_AfDZ9o6loUae3sa8tup4tLH9ZAzxRDAoMAdv5iT2lM94fA&oe=646ADC59", "08662145177", "Barcelona > Bayern", "08.36"));
        dataChat.add(new ModelChat("Achraf Hakimi", chats().get(9), "https://library.sportingnews.com/styles/crop_style_16_9_desktop_webp/s3/2022-12/Achraf%20Hakimi%20Morocco.jpg.webp?itok=_ByWd810", "086452379561", "Just humiliated ex-wife", "23.51"));
        return dataChat;


    }
}

