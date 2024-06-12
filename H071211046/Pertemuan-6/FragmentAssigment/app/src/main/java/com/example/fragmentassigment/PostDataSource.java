package com.example.fragmentassigment;

import java.util.ArrayList;

public class PostDataSource {

    public static ArrayList<PostModel> dataList = generateDataDummyPostModels();

    private static ArrayList<PostModel> generateDataDummyPostModels() {
        ArrayList<PostModel> dataList = new ArrayList<>();


        dataList.add(new PostModel("Badtzu", "Badtz-Maru", "friends ask you questions; enemies question you", "https://i.pinimg.com/564x/ca/47/86/ca47860d451de038c1364eb3e798020c.jpg", "https://i.pinimg.com/564x/34/3a/0f/343a0fc316dd63b54c78293307d231c1.jpg"));
        dataList.add(new PostModel("Toyhamm", "Hamm", "aspire to fly", "https://i.pinimg.com/564x/5a/8b/98/5a8b98c18d727b757cef7ae88d19613a.jpg", "https://i.pinimg.com/564x/2a/33/e8/2a33e8d7f73ff91254902c0b7fc98d33.jpg"));
        dataList.add(new PostModel("Turneila", "Alien", "along with my friend lotso", "https://i.pinimg.com/564x/06/05/a8/0605a8a5dbce407665f832cd9c0fcd93.jpg", "https://i.pinimg.com/564x/ba/a3/78/baa37890f9d633f3bae54e30e170ac92.jpg"));
        dataList.add(new PostModel("Lotzold", "Lotso", "come fly with me", "https://i.pinimg.com/564x/28/d2/d6/28d2d6c3c2156f943d7ad0319b43faff.jpg", "https://i.pinimg.com/564x/15/e5/d9/15e5d938cf993a148ea699d973e2270b.jpg"));
        dataList.add(new PostModel("Caramelpudding", "Pompompurin", "this flower is for you", "https://i.pinimg.com/564x/02/a4/d4/02a4d4faa38b4dbb19e629619c270b6f.jpg", "https://i.pinimg.com/564x/ba/b0/58/bab058e24bb73eace0781b9c886a68cc.jpg"));
        dataList.add(new PostModel("Pochapocha", "Pochacco", "I'm very tired today", "https://i.pinimg.com/564x/67/2a/30/672a3031f7c12df2ef7efd0555a5a2d7.jpg", "https://i.pinimg.com/564x/fc/b3/0b/fcb30bd3cfb87f876f2b162a31c2ff87.jpg"));
        dataList.add(new PostModel("Keropitos", "Keroppi", "always smiling :)", "https://i.pinimg.com/564x/70/0a/71/700a71ae84605af396d86e40b40cc952.jpg", "https://i.pinimg.com/564x/71/37/ce/7137cef97087634b4759bd9f52daa2b0.jpg"));
        dataList.add(new PostModel("Sweety", "My Sweet Piano", "again enjoying cookies", "https://i.pinimg.com/564x/6f/e0/af/6fe0af35b08a059117413c2b4967c344.jpg", "https://i.pinimg.com/564x/21/e3/7c/21e37cadfb4d85d084c9255d5be11b31.jpg"));
        dataList.add(new PostModel("Minemelody", "My Melody", "thank you for the flowers", "https://i.pinimg.com/564x/38/8c/c0/388cc00e0077a53e8cf370c227e64417.jpg", "https://i.pinimg.com/564x/c4/d2/05/c4d205f72f6cf0d58d920473189d2060.jpg"));
        dataList.add(new PostModel("Pxrple", "Kuromi", "black is my favourite", "https://i.pinimg.com/564x/72/fd/eb/72fdebd580f46cbad3d385df29be436b.jpg", "https://i.pinimg.com/564x/81/c1/c3/81c1c3f5af45568a866447048cb89e71.jpg"));
        dataList.add(new PostModel("Hewwopinky", "Hello Kitty", "i'm like an angel", "https://i.pinimg.com/564x/31/c5/00/31c500aeccab8d6486f0a8aaf1ee198e.jpg", "https://i.pinimg.com/564x/08/3e/cb/083ecb348eee11d6259f83281a9c3e0e.jpg"));

        return dataList;
    }

    public static ArrayList<PostModel> searchPostModels(String query) {
        ArrayList<PostModel> searchedPostModels = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            final PostModel postModel = dataList.get(i);
            String q = query.toLowerCase();
            String name = postModel.getName().toLowerCase();
            String username = postModel.getUsername().toLowerCase();
            if (name.startsWith(query) || username.startsWith(query)) {
                searchedPostModels.add(postModel);
            }
        }
        return searchedPostModels;
    }
}