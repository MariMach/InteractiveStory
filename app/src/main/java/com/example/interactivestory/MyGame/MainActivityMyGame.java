package com.example.interactivestory.MyGame;

import android.content.Intent;

import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.interactivestory.R;

public class MainActivityMyGame extends AndroidGame {
    private int imageId1;
    private int imageId2;
    private int imageId3;
    private int imageId4;

    @Override
    public Screen getInitScreen() {
        initAssets();
        return new MyScreen(this);
    }

    private void initAssets() {
        Intent intent = getIntent();
        this.imageId1 = (int) intent.getIntExtra("imageId1", 0);
        this.imageId2 =  (int) intent.getIntExtra("imageId2", 0);
        this.imageId3 = (int) intent.getIntExtra("imageId3", 0);
        this.imageId4 =  (int) intent.getIntExtra("imageId4", 0);
        // a modifier
        Asset.imagetoOreder1 = getGraphics().newImage(imageId1, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.imagetoOreder2 = getGraphics().newImage(imageId2, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.imagetoOreder3 = getGraphics().newImage(imageId3, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.imagetoOreder4 = getGraphics().newImage(imageId4, Graphics.ImageFormat.ARGB8888, getResources());
        //Asset.backgr = getGraphics().newImage(R.drawable.backgr, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.backgr = getGraphics().newImage(R.drawable.backback, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.image1 = getGraphics().newImage(R.drawable.image1, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.image2 = getGraphics().newImage(R.drawable.image2, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.image3 = getGraphics().newImage(R.drawable.image3, Graphics.ImageFormat.ARGB8888, getResources());
        Asset.image4 = getGraphics().newImage(R.drawable.image4, Graphics.ImageFormat.ARGB8888, getResources());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
