package com.example.interactivestory.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.interactivestory.R;
import com.example.interactivestory.model.DatabaseHelper;

import java.util.ArrayList;

public class MyStories extends AppCompatActivity {

    public static final String TAG = MyStories.class.getSimpleName();
    private String name;

    DatabaseHelper db = new DatabaseHelper(this, "story.db",1);

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDesc = new ArrayList<>();

    // other vars
    private ArrayList<String> mImageNames = new ArrayList<String>();
    private ArrayList<String> mImages = new ArrayList<String>();
    private ArrayList<String> mDescc = new ArrayList<String>();
    private ArrayList<Integer> mImagesint = new ArrayList<Integer>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stories);



       //db.deleteTableF("story");
       //db.insertStory("Cendrillon", "http://2.bp.blogspot.com/_9jqV39uuMwg/TGQUgOSDFoI/AAAAAAAAAok/4vToFXW8vAE/s640/disney+princess+couple+2.jpg", "Cendrillon, une douce jeune fille, subit les méchancetés de sa belle-mère et de ses deux horribles filles. Un jour, transformée par sa marraine la bonne fée en princesse éclatante de beauté, elle se rend au bal du prince qui tombe fou amoureux d'elle...");
       //db.insertStory("Hansel et Gretel", "https://st2.depositphotos.com/1967477/8545/v/950/depositphotos_85452686-stock-illustration-classic-children-story-hansel-and.jpg", "Hansel et Gretel ou Jeannot et Margot, est un conte populaire figurant parmi ceux recueillis par les frères Grimm dans le premier volume des Contes de l'enfance et du foyer.");


        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.Key_name));
        if(name == null || name.isEmpty()){
            name = "Friend";
        }
        Log.d(TAG, name);

        initImageBitmaps();
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

       // mImageUrls = db.getStoryURL();
       // mNames = db.getStoryslabel();
       // mDesc = db.getStorysDesc();

            mImageNames.add("Cendrillon");
            mImagesint.add(R.drawable.storycend);
            mDescc.add(this.getString(R.string.cend_descripsion));

            mImageNames.add("Hansel et Gretel");
            mImagesint.add(R.drawable.storyhan);
            mDescc.add(this.getString(R.string.hansel_desc));


        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImageNames, mImagesint, mDescc);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    private void startSotryActivity(String name) {
        Intent intent = new Intent(this, StoryActivity.class);
        Resources resources = getResources();
        String key = resources.getString(R.string.Key_name);
        intent.putExtra(key, name);
        startActivity(intent);

    }

}
