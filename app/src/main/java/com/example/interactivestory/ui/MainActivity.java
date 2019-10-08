package com.example.interactivestory.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interactivestory.LocaleHelper;
import com.example.interactivestory.R;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    //private EditText nameEditText;
    private Button StartButton;
    private Button settingsButton;
    private Button helpButton;
    private Button AddStoryButton;
    /*
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Paper.init(this);
       // String langu = Paper.book().read("language");
       // if(langu == null){
         //   Paper.book().write("language", "en");
        //}
        //updateView((String)Paper.book().read("language"));

        //nameEditText = (EditText) findViewById(R.id.nameEditText);
        StartButton = (Button) findViewById(R.id.StartButton);
        helpButton = (Button) findViewById(R.id.helpButton);
        AddStoryButton = (Button) findViewById(R.id.AddStoryButton);


        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String name =  nameEditText.getText().toString();
                String name = "";
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
                startSotry(name);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHelp();
            }
        });

        AddStoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdd();
            }
        });

    }

    private void updateView(String lang){
        Context context = LocaleHelper.selLocale(this, lang);
        Resources res = context.getResources();

    }

    protected void onResume(){
        super.onResume();
        //nameEditText.setText("");
    }

    private void startSotry(String name) {
        Intent intent = new Intent(this, MyStories.class);
        Resources resources = getResources();
        String key = resources.getString(R.string.Key_name);
        intent.putExtra(key, name);
        startActivity(intent);
    }

    private void startHelp(){
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    private void startAdd(){
        Intent intent = new Intent(this, AddStory.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
       getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.lang_ar){
            //Paper.book().write("language", "en");

            //Paper.book().write("language", "ar-rSA");
            //updateView((String) Paper.book().read("language"));

        } else if(item.getItemId() == R.id.lang_en){
            //Paper.book().write("language", "en");

            //Paper.book().write("language", "en");
            //updateView((String) Paper.book().read("language"));

        } else if(item.getItemId() == R.id.lang_fr){
            //Paper.book().write("language", "en");

            //Paper.book().write("language", "fr-rFR");
            //updateView((String) Paper.book().read("language"));

        }
        return true;
    }


    //@Override
    //protected void attachBaseContext(Context newBase) {
      //  super.attachBaseContext(LocaleHelper.onAttach(newBase, "en"));
    //}
}