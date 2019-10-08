package com.example.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;
import com.example.interactivestory.MyGame.MainActivityMyGame;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story story;
    private ImageView storyImageView;
    private ExpandableTextView storyTextView;
    private Button choice1Button;
    private Button play, pause, stop;
    private String name, storylabel;
    private Stack<Integer> pageStack = new Stack<Integer>();
    private RelativeLayout relayout;
    private int pausePosition;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (ExpandableTextView) findViewById(R.id.expandable_text_View);
        choice1Button = (Button) findViewById(R.id.langueButton);
        relayout = (RelativeLayout) findViewById(R.id.relayout);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.Key_name));
        storylabel = intent.getStringExtra(getString(R.string.storylabel));

        if(name == null || name.isEmpty()){
            name = "Friend";
        }
        Log.d(TAG, name);
        if(storylabel.equals("Cendrillon")){
            story = new Story(name, R.drawable.cendrillon1, R.drawable.cendrillon2, R.drawable.cendrillon3, R.drawable.cendrillon4, R.string.pagecendrillon1,  R.string.pagecendrillon2,  R.string.pagecendrillon3,  R.string.pagecendrillon4 ,R.raw.mpcendrillon1,R.raw.mpcendrillon2, R.raw.mpcendrillon3, R.raw.mpcendrillon4 );
            loadPage(0);
        }else if(storylabel.equals("Hansel et Gretel")){
            story = new Story(name, R.drawable.hansel1, R.drawable.hansel2, R.drawable.hansel3, R.drawable.hansel4,  R.string.pagehansel1, R.string.pagehansel2, R.string.pagehansel3, R.string.pagehansel4, R.raw.mphansel1,  R.raw.mphansel2,  R.raw.mphansel3,  R.raw.mphansel4);
            loadPage(0);
        }
    }

    private void loadPage(int pageNumber) {
        if(mediaPlayer != null){
            mediaPlayer.pause();
            mediaPlayer = null;
        }

        pageStack.push(pageNumber);
        final Page page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);

        // add name if placeholder inculded, won't add if not
            String pageText = getString(page.getTextId());
            pageText = String.format(pageText, name);
            storyTextView.setText(pageText);

        play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if(mediaPlayer == null){
                      mediaPlayer = MediaPlayer.create(getApplicationContext(), page.getSoundId());
                      mediaPlayer.start();
                  } else if(!mediaPlayer.isPlaying()){
                      mediaPlayer.seekTo(pausePosition);
                      mediaPlayer.start();
                  }
                }
            });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null){
                    mediaPlayer.pause();
                    pausePosition = mediaPlayer.getCurrentPosition();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer = null;
                }
            }
        });

        if (page.getFinalPage()) {
            choice1Button.setText(R.string.play_again_button_text);
            relayout.setVisibility(View.GONE);
            play.setVisibility(View.GONE);
            pause.setVisibility(View.GONE);
            stop.setVisibility(View.GONE);
            choice1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // add the game screen
                    if(storylabel.equals("Cendrillon")){

                        Intent intent = new Intent(StoryActivity.this, MainActivityMyGame.class);
                        intent.putExtra("imageId1", R.drawable.cendrillon1);
                        intent.putExtra("imageId2", R.drawable.cendrillon2);
                        intent.putExtra("imageId3", R.drawable.cendrillon3);
                        intent.putExtra("imageId4", R.drawable.cendrillon4);
                        startActivity(intent);

                    }else if(storylabel.equals("Hansel et Gretel")){

                        Intent intent = new Intent(StoryActivity.this ,MainActivityMyGame.class);
                        intent.putExtra("imageId1", R.drawable.hansel1);
                        intent.putExtra("imageId2", R.drawable.hansel2);
                        intent.putExtra("imageId3", R.drawable.hansel3);
                        intent.putExtra("imageId4", R.drawable.hansel4);
                        startActivity(intent);
                    }
                    //finish();
                }
            });
        } else {
            loadButtons(page);
        }
    }
    private void loadButtons(final Page page) {
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if(pageStack.isEmpty()){
          super.onBackPressed();
        }else{
           loadPage(pageStack.pop());
        }
    }
}