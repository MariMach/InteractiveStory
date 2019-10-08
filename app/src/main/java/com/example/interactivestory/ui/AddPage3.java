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

public class AddPage3 extends AppCompatActivity {


    DatabaseHelper db;
    Button buttonstop3, buttonrecord3, addstorypage3;
    EditText storypage3, urlimage3;
    String storyname = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page3);


        final Intent intent = new Intent(this, AddPAge4.class);
        //startActivity(intent);

        db = new DatabaseHelper(this, "story.db",1);

        storypage3 = (EditText) findViewById(R.id.storypage3);
        urlimage3 = (EditText) findViewById(R.id.urlimage3);
        buttonstop3 = (Button) findViewById(R.id.buttonstop3);
        buttonrecord3 = (Button) findViewById(R.id.buttonrecord3);
        addstorypage3 = (Button) findViewById(R.id.addstorypage3);

        addstorypage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }

        });

        Intent intent2 = getIntent();
        storyname = intent.getStringExtra("storyname");


        addstorypage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlimag = urlimage3.getText().toString();
                String description = storypage3.getText().toString();

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
