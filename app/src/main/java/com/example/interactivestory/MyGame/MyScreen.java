package com.example.interactivestory.MyGame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.interactivestory.R;
import com.example.interactivestory.ui.HelpActivity;
import com.example.interactivestory.ui.MainActivity;
import com.example.interactivestory.ui.MyStories;

public class MyScreen extends Screen {

    private MySprite mysprite[];
    //private MediaPlayer  soundsb;
    private SoundPool soundpool;
    private int bravo1, bravo2, bravo3, tryagain;
    private int widthx, heighty;
    private Context context;

    public MyScreen(Game game) {
        super(game);
         context = (Context) game;
        //soundsb = (MediaPlayer) MediaPlayer.create((context, R.raw.bravo3);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes =  new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
            soundpool = new SoundPool.Builder().setMaxStreams(6).setAudioAttributes(audioAttributes).build();
        } else {
            soundpool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
        bravo1 = (int) soundpool.load(context, R.raw.bravo1, 1);
        bravo2 = (int) soundpool.load(context, R.raw.bravo2, 1);
        bravo3 = (int) soundpool.load(context, R.raw.bravo3, 1);
        tryagain = (int) soundpool.load(context, R.raw.tryagain1, 1);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        widthx = size.x;
        heighty = size.y;

        mysprite = new MySprite[9];
        mysprite[0] =  new MySprite(Asset.backgr, 0 , 0 ,heighty, widthx);
        mysprite[0].setStatic(true);
        addSprite(mysprite[0]);

        /////////////////



        mysprite[3] =  new MySprite(Asset.image3, (int) (0.1*widthx), (int) (0.75*heighty) ,(int) (0.35*widthx),(int) (0.35*widthx));
        mysprite[3].setStatic(true);
        addSprite(mysprite[3]);

        mysprite[4] =  new MySprite(Asset.image4, (int) (0.55*widthx) , (int) (0.75*heighty) ,(int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[4].setStatic(true);
        addSprite(mysprite[4]);

        mysprite[2] =  new MySprite(Asset.image2, (int) (0.55*widthx) , (int) (0.52*heighty) ,(int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[2].setStatic(true);
        addSprite(mysprite[2]);

        mysprite[1] =  new MySprite(Asset.image1, (int) (0.1*widthx) , (int)(0.52*heighty) ,(int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[1].setStatic(true);
        addSprite(mysprite[1]);



        ///////////////////
        mysprite[5] =  new MySprite(Asset.imagetoOreder1,(int) (0.55*widthx) , (int)(0.28*heighty) , (int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[5].setStatic(false);
        addSprite(mysprite[5]);

        mysprite[6] =  new MySprite(Asset.imagetoOreder2, (int)(0.1*widthx), (int)(0.05*heighty),(int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[6].setStatic(false);
        addSprite(mysprite[6]);

        mysprite[7] =  new MySprite(Asset.imagetoOreder3, (int) (0.55*widthx) , (int)(0.05*heighty),(int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[7].setStatic(false);
        addSprite(mysprite[7]);

        mysprite[8] =  new MySprite(Asset.imagetoOreder4, (int)(0.1*widthx) , (int)(0.28*heighty) ,(int) (0.35*widthx), (int) (0.35*widthx));
        mysprite[8].setStatic(false);
        addSprite(mysprite[8]);
    }

    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    @Override
    public void render(float deltaTime) {
        if(mysprite[1].contain(mysprite[5].getX(),mysprite[5].getY()) &&  mysprite[5].getHeight() != 0){
            mysprite[5].setHeight(0);
            mysprite[5].setWidth(0);
            mysprite[1].setImage(Asset.imagetoOreder1);
            //soundsb.start();
            soundpool.play(bravo1, 1, 1, 0, 0, 1);
        } else if(mysprite[1].contain(mysprite[6].getX(),mysprite[6].getY()) || mysprite[1].contain(mysprite[7].getX(),mysprite[7].getY()) || mysprite[1].contain(mysprite[8].getX(),mysprite[8].getY())){
              if(a == 0){
                  a = 1;
                  soundpool.play(tryagain, 1, 1, 0, 0, 1);
              }
        } else {
            a = 0;
        }

        if(mysprite[2].contain(mysprite[6].getX(),mysprite[6].getY())  &&  mysprite[6].getHeight() != 0){
            mysprite[6].setHeight(0);
            mysprite[6].setWidth(0);
            mysprite[2].setImage(Asset.imagetoOreder2);
            //soundsb.start();
            soundpool.play(bravo2, 1, 1, 0, 0, 1);
        } else if(mysprite[2].contain(mysprite[5].getX(),mysprite[5].getY()) || mysprite[2].contain(mysprite[7].getX(),mysprite[7].getY()) || mysprite[2].contain(mysprite[8].getX(),mysprite[8].getY())){
            if(b == 0){
                b = 1;
                soundpool.play(tryagain, 1, 1, 0, 0, 1);
            }
        } else {
            b = 0;
        }

        if(mysprite[3].contain(mysprite[7].getX(),mysprite[7].getY())  &&  mysprite[7].getHeight() != 0){
            mysprite[7].setHeight(0);
            mysprite[7].setWidth(0);
            mysprite[3].setImage(Asset.imagetoOreder3);
            //soundsb.start();
            soundpool.play(bravo3, 1, 1, 0, 0, 1);
        } else if(mysprite[3].contain(mysprite[5].getX(),mysprite[5].getY()) || mysprite[3].contain(mysprite[6].getX(),mysprite[6].getY()) || mysprite[3].contain(mysprite[8].getX(),mysprite[8].getY())){
            if(c == 0){
                c = 1;
                soundpool.play(tryagain, 1, 1, 0, 0, 1);
            }
        } else {
            c = 0;
        }

        if(mysprite[4].contain(mysprite[8].getX(),mysprite[8].getY())  &&  mysprite[8].getHeight() != 0){
            mysprite[8].setHeight(0);
            mysprite[8].setWidth(0);
            mysprite[4].setImage(Asset.imagetoOreder4);
            //soundsb.start();
            soundpool.play(bravo2, 1, 1, 0, 0, 1);
        } else if(mysprite[4].contain(mysprite[5].getX(),mysprite[5].getY()) || mysprite[4].contain(mysprite[7].getX(),mysprite[7].getY()) || mysprite[4].contain(mysprite[6].getX(),mysprite[6].getY())){
            if(d == 0){
                d = 1;
                soundpool.play(tryagain, 1, 1, 0, 0, 1);
            }
        } else {
            d = 0;
        }


        if((mysprite[8].getHeight() == 0) && (mysprite[7].getHeight() == 0) && (mysprite[6].getHeight() == 0) && (mysprite[5].getHeight() == 0)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            soundpool.play(bravo2, 1, 1, 0, 0, 1);

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void backButton() {

    }

   // @Override
    //public void handleDragging(int x,int y, int pointer){



    //}



}
