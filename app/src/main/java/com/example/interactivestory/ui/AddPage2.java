package com.example.interactivestory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interactivestory.R;
import com.example.interactivestory.model.DatabaseHelper;

public class AddPage2 extends AppCompatActivity {


    DatabaseHelper db;
    Button buttonstop2, buttonrecord2, addstorypage2;
    EditText storypage2, urlimage2;
    String storyname = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page2);
        final Intent intent = new Intent(this, AddPage3.class);
        //startActivity(intent);

        db = new DatabaseHelper(this, "story.db",1);

        storypage2 = (EditText) findViewById(R.id.storypage2);
        urlimage2 = (EditText) findViewById(R.id.urlimage2);
        buttonstop2 = (Button) findViewById(R.id.buttonstop2);
        buttonrecord2 = (Button) findViewById(R.id.buttonrecord2);
        addstorypage2 = (Button) findViewById(R.id.addstorypage2);



        Intent intent2 = getIntent();
        storyname = intent.getStringExtra("storyname");


        addstorypage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String urlimag = urlimage2.getText().toString();
                    String description = storypage2.getText().toString();

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
