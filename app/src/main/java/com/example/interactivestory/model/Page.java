package com.example.interactivestory.model;

public class Page {
    private int imageId;
    private int textId;
    private Choice choice1;
    private Boolean isFinalPage = false;
    private int sound1234;

    public Page(int imageId, int textId, int sound1234,Choice choice1) {
        this.imageId = imageId;
        this.textId = textId;
        this.choice1 = choice1;
        this.sound1234 = sound1234;
    }


    public Page(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
        this.isFinalPage = true;
    }

    public Boolean getFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(Boolean finalPage) {
        isFinalPage = finalPage;
    }

    public int getImageId() {
        return imageId;
    }

    public int getSoundId(){
        return sound1234;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

}
