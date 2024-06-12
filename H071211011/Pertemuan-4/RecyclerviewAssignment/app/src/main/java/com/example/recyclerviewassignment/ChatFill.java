package com.example.recyclerviewassignment;

import java.util.ArrayList;

public class ChatFill {
    public static ArrayList<ChatFillModel> fills = generateDummyChatFillModel();

    private static ArrayList<ChatFillModel> generateDummyChatFillModel() {

        ArrayList<ChatFillModel> fills = new ArrayList<>();
        fills.add(new ChatFillModel("Lorem ipsum dolor sit amet","12.20"));
        fills.add(new ChatFillModel("Consectetur adipiscing elit","12.50"));
        fills.add(new ChatFillModel("Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","13.00"));
        fills.add(new ChatFillModel("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat","13.10"));
        fills.add(new ChatFillModel("Duis aute irure dolor in reprehenderit in voluptate","16.23"));
        fills.add(new ChatFillModel("Velit esse cillum dolore eu fugiat nulla pariatur","16.39"));
        fills.add(new ChatFillModel("Excepteur sint occaecat cupidatat non proident","17.30"));
        fills.add(new ChatFillModel("Sunt in culpa","18.21"));
        fills.add(new ChatFillModel("Qui officia deserunt mollit anim id est laborum","18.30"));
        fills.add(new ChatFillModel("Quis eleifend quam adipiscing vitae","18.49"));
        fills.add(new ChatFillModel("Aliquam faucibus purus in massa tempor nec feugiat nisl","19.12"));
        fills.add(new ChatFillModel("Ullamcorper dignissim","19.18"));
        fills.add(new ChatFillModel("Morbi enim nunc faucibus a pellentesque sit amet porttitor","19.25"));
        fills.add(new ChatFillModel("Purus viverra accumsan","19.34"));
        fills.add(new ChatFillModel("Sapien nec sagittis aliquam malesuada bibendum arcu","20.00"));


        return fills;
    }
}
