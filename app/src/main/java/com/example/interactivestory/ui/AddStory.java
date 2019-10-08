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

public class AddStory extends AppCompatActivity {

    DatabaseHelper db;
    Button addstory;
    EditText title, urlimage, descrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        final Intent intent = new Intent(this, AddPage1.class);

        db = new DatabaseHelper(this, "story.db",1);
        urlimage = (EditText) findViewById(R.id.urlimage);
        title = (EditText) findViewById(R.id.title);
        descrip = (EditText) findViewById(R.id.descrip);
        addstory = (Button) findViewById(R.id.addstory);


        addstory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlimages = urlimage.getText().toString();
                String noms = title.getText().toString();
                String description = descrip.getText().toString();

                if(urlimages.equals("") || noms.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean chkecolenom = db.checkStoryLabel(noms);
                    if(chkecolenom == true){
                        Boolean insert = db.insertStory(noms, urlimages, description);
                        if(insert == true){
                            Toast.makeText(getApplicationContext(), "Story Registered successfully ", Toast.LENGTH_SHORT).show();
                            intent.putExtra("storyname", noms);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Story already exists ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

/*


    DatabaseHelper db;
    Button addecole;
    EditText nom, urlimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        db = new DatabaseHelper(this, "story.db",1);
        urlimage = (EditText) findViewById(R.id.urlimage);
        nom = (EditText) findViewById(R.id.nom);
        addecole = (Button) findViewById(R.id.addecole);

        addecole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlimages = urlimage.getText().toString();
                String noms = nom.getText().toString();

                if(urlimages.equals("") || noms.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean chkecolenom = db.chkecolenom(noms);
                    if(chkecolenom == true){
                        Boolean insert = db.insertecole(noms, urlimages);
                        if(insert == true){
                            Toast.makeText(getApplicationContext(), "Ecole Registered successfully ", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Ecole already exists ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
*/