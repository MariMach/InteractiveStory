package com.example.interactivestory.model;

import com.example.interactivestory.R;

public class Story {

    private Page[] pages;
    public Story(String name,int imageId1, int imageId2, int imageId3, int imageId4, int textId1, int textId2, int textId3, int textId4, int sound1, int sound2, int sound3, int sound4){
        pages = new Page[5];

        pages[0] = new Page(imageId1,
                textId1, sound1,
                new Choice(R.string.page0_choice1, 1)
        );

        pages[1] = new Page(imageId2,
                textId2, sound2,
                new Choice(R.string.page1_choice1, 2)
        );

        pages[2] = new Page(imageId3,
                textId3, sound3,
                new Choice(R.string.page2_choice1, 3)
        );

        pages[3] = new Page(imageId4,
                textId4, sound4,
                new Choice(R.string.page4_choice1, 4)
        );

        // change the image
        pages[4] = new Page(R.drawable.readytoplay, R.string.readytoplaytext);

    }

    public Page getPage(int pageNumber) {
        if(pageNumber >= pages.length){
            pageNumber = 0;
        }
        return pages[pageNumber];
    }
}
