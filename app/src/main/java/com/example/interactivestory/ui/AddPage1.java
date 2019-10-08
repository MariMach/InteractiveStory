package com.example.interactivestory.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interactivestory.R;
import com.example.interactivestory.model.DatabaseHelper;

public class AddPage1 extends AppCompatActivity {

    DatabaseHelper db;
    Button buttonstop1, buttonrecord1, addstorypage1;
    EditText storypage1, urlimage1;
    String storyname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page1);

        final Intent intent = new Intent(this, AddPage2.class);
        //startActivity(intent);


        db = new DatabaseHelper(this, "story.db", 1);

        storypage1 = (EditText) findViewById(R.id.storypage1);
        urlimage1 = (EditText) findViewById(R.id.urlimage1);
        buttonstop1 = (Button) findViewById(R.id.buttonstop1);
        buttonrecord1 = (Button) findViewById(R.id.buttonrecord1);
        addstorypage1 = (Button) findViewById(R.id.addstorypage1);


        Intent intent2 = getIntent();
        storyname = intent.getStringExtra("storyname");


        addstorypage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlimag = urlimage1.getText().toString();
                String description = storypage1.getText().toString();

                if(urlimag.equals("") || description.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean insertPagehh = db.insertPage(storyname, urlimag, description);
                        if(insertPagehh == true){
                            Toast.makeText(getApplicationContext(), "Story Registered successfully ", Toast.LENGTH_SHORT).show();
                            intent.putExtra("storyname", storyname);
                            startActivity(intent);
                        } else {
                        Toast.makeText(getApplicationContext(), "Story Page already exists ", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}