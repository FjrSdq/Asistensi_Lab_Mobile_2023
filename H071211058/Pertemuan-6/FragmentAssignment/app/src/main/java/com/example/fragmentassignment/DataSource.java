package com.example.fragmentassignment;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

import android.net.Uri;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSource {

    public static ArrayList<PostModel> posts = generateDummyPostModels();

    private static ArrayList<PostModel> generateDummyPostModels(){

        ArrayList<PostModel> posts = new ArrayList<>(

                Arrays.asList(
                        new PostModel("Bismillah ngetest aja", "https://pbs.twimg.com/media/Fmq15I-aYAI2UBt.jpg:large","minjikim_", "Kim Minji", "https://pbs.twimg.com/media/Fmq15I-aYAI2UBt.jpg:large"),
                        new PostModel("Ini namanya caption", "https://pbs.twimg.com/media/FYcWm6SXgBAdgkA.jpg","hanni_pham", "Pham Hanni", "https://pbs.twimg.com/media/FYcWm6SXgBAdgkA.jpg"),
                        new PostModel("Caption can't be empty", "https://pbs.twimg.com/media/FYNKx2yXkAEVgGq.jpg", "marsh.danielle", "Danielle Marsh", "https://pbs.twimg.com/media/FYNKx2yXkAEVgGq.jpg"),
                        new PostModel("Bingung mau isi apa", "https://pbs.twimg.com/media/FYcu6BoUcAA2F1v.jpg", "haerinkang_", "Kang Haerin", "https://pbs.twimg.com/media/FYcu6BoUcAA2F1v.jpg"),
                        new PostModel("Namanya juga usaha", "https://pbs.twimg.com/media/FaY7DiJXgAAcWb2?format=jpg&name=large", "leehyein", "Lee Hyein", "https://pbs.twimg.com/media/FaY7DiJXgAAcWb2?format=jpg&name=large")
                )


        );

        return posts;

    }

}
