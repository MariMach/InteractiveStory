package com.example.interactivestory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interactivestory.R;
import com.example.interactivestory.model.DatabaseHelper;

public class AddPAge4 extends AppCompatActivity {

    DatabaseHelper db;
    Button buttonstop4, buttonrecord4, addstorypage4;
    EditText storypage4, urlimage4;
    String storyname = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page4);


        final Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);


        db = new DatabaseHelper(this, "story.db",1);

        storypage4 = (EditText) findViewById(R.id.storypage4);
        urlimage4 = (EditText) findViewById(R.id.urlimage4);
        buttonstop4 = (Button) findViewById(R.id.buttonstop4);
        buttonrecord4 = (Button) findViewById(R.id.buttonrecord4);
        addstorypage4 = (Button) findViewById(R.id.addstorypage4);

        Intent intent2 = getIntent();
        storyname = intent.getStringExtra("storyname");


        addstorypage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlimag = urlimage4.getText().toString();
                String description = storypage4.getText().toString();

                if(urlimag.equals("") || description.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean insertPagehh = db.insertPage(storyname, urlimag, description);
                    if(insertPagehh == true){
                        Toast.makeText(getApplicationContext(), "Story Registered successfully ", Toast.LENGTH_SHORT).show();
                        //intent.putExtra("storyname", storyname);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Story Page already exists ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

}
