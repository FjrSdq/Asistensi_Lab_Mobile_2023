package com.example.backgroundthreadassignment;

import java.util.ArrayList;

public class DataPost {
    public  static ArrayList<PostModel> posts = generateDummyPostModels();

    private static ArrayList<PostModel> generateDummyPostModels() {

        ArrayList<PostModel> posts = new ArrayList<>();

       posts.add(new PostModel("Taehyung","Kim Tae Hyung","https://i.pinimg.com/originals/32/37/f6/3237f6f6708e75ed0645198835907cba.jpg","https://i.pinimg.com/originals/32/37/f6/3237f6f6708e75ed0645198835907cba.jpg","Sit amet justo donec enim diam"));
       posts.add(new PostModel("Jungkook","Jeon Jung Kook","https://i.pinimg.com/564x/a3/36/d3/a336d3c95049b131942c9b9b045159a7.jpg","https://i.pinimg.com/564x/a3/36/d3/a336d3c95049b131942c9b9b045159a7.jpg","Sagittis orci"));
       posts.add(new PostModel("Wonwoo","Jeon Won Woo","https://i.pinimg.com/564x/68/df/e0/68dfe07400b9378d5e43c3f29bda514d.jpg","https://i.pinimg.com/564x/68/df/e0/68dfe07400b9378d5e43c3f29bda514d.jpg", "Mauris ultrices eros in cursus"));
       posts.add(new PostModel("Jisoo","Kim Jisoo","https://i.pinimg.com/564x/99/0c/fe/990cfe41b14153a3969eec8d01a198df.jpg","https://i.pinimg.com/564x/99/0c/fe/990cfe41b14153a3969eec8d01a198df.jpg","Arcu cursus euismod quis viverra nibh cras pulvinar. Gravida in fermentum et sollicitudin ac orci phasellus"));
       posts.add(new PostModel("Jimin","Park Ji Min","https://i.pinimg.com/564x/00/63/9b/00639b085a68a3645a8a42dfab93ebeb.jpg","https://i.pinimg.com/564x/00/63/9b/00639b085a68a3645a8a42dfab93ebeb.jpg","Aliquet nibh praesent tristique magna sit"));
       posts.add(new PostModel("Rose","Park Chaeyoung","https://i.pinimg.com/564x/ec/92/af/ec92af7913f9b76d44a6c5f6bb3d81c9.jpg","https://i.pinimg.com/564x/ec/92/af/ec92af7913f9b76d44a6c5f6bb3d81c9.jpg","Et malesuada"));
       posts.add(new PostModel("Suga","Min Yoon Gi","https://i.pinimg.com/736x/9f/e9/13/9fe913df5ee7bdf16e29b589d52cf937.jpg","https://i.pinimg.com/736x/9f/e9/13/9fe913df5ee7bdf16e29b589d52cf937.jpg","Sit amet justo donec enim diam"));
       posts.add(new PostModel("Mingyu","Kim Min Gyu","https://i.pinimg.com/564x/84/6c/3c/846c3c5e95d7452f0331bec35589e943.jpg","https://i.pinimg.com/564x/84/6c/3c/846c3c5e95d7452f0331bec35589e943.jpg","Vel elit scelerisque mauris pellentesque"));
       posts.add(new PostModel("Jennie","Kim Jennie","https://i.pinimg.com/564x/45/d7/f3/45d7f36ed447a8480690540877fbda3f.jpg","https://i.pinimg.com/564x/45/d7/f3/45d7f36ed447a8480690540877fbda3f.jpg","Sagittis orci"));
       posts.add(new PostModel("S-Coups","Choi Seung Cheol","https://i.pinimg.com/564x/33/70/50/3370504d8a1db36907b5fa6971a1f7b7.jpg","https://i.pinimg.com/564x/33/70/50/3370504d8a1db36907b5fa6971a1f7b7.jpg","Purus sit amet volutpat consequat"));
       posts.add(new PostModel("RM","Kim Nam Joon","https://i.pinimg.com/564x/ab/65/ae/ab65ae4dca653f0d0e31282d15ee5872.jpg","https://i.pinimg.com/564x/ab/65/ae/ab65ae4dca653f0d0e31282d15ee5872.jpg","Consectetur adipiscing"));
       posts.add(new PostModel("Lisa","Lalisa Manoban","https://i.pinimg.com/564x/00/7f/ba/007fba46854ee08568656d48fbea13ea.jpg","https://i.pinimg.com/564x/00/7f/ba/007fba46854ee08568656d48fbea13ea.jpg","Purus non enim praesent"));
       posts.add(new PostModel("J-Hope","Jung Ho Seok","https://i.pinimg.com/564x/d8/42/d4/d842d4661b2f4c7077f2fae050ee149d.jpg","https://i.pinimg.com/564x/d8/42/d4/d842d4661b2f4c7077f2fae050ee149d.jpg","Nam at lectus urna duis convallis"));
       posts.add(new PostModel("Joshua","Hong Ji Soo","https://i.pinimg.com/564x/5f/90/20/5f90201f782b39f8d9b99cd514d34ef9.jpg","https://i.pinimg.com/564x/5f/90/20/5f90201f782b39f8d9b99cd514d34ef9.jpg","Mauris ultrices eros in cursus"));
       posts.add(new PostModel("Jaemin","Na Jae Min","https://i.pinimg.com/564x/56/db/fc/56dbfc59d7a7507747267045467e3bc5.jpg","https://i.pinimg.com/564x/56/db/fc/56dbfc59d7a7507747267045467e3bc5.jpg","Nisl nunc mi ipsum"));
        return posts;

    }

}
