package com.example.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.interactivestory.R;

public class HelpActivity extends AppCompatActivity{
    private ImageView helpImageView;
    private TextView helpTextView;
    private Button returnToplayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        helpImageView = (ImageView) findViewById(R.id.helpImageView);
        helpTextView = (TextView) findViewById(R.id.helpTextView);
        returnToplayButton = (Button) findViewById(R.id.langueButton);
        loadHelpPage();
    }

    private void loadHelpPage(){
        helpTextView.setText(R.string.Help_text);
        returnToplayButton.setText(R.string.Play_Button);
        Drawable image = ContextCompat.getDrawable(this, R.drawable.page6);
        helpImageView.setImageDrawable(image);

        returnToplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToplayButtonm();
            }
        });
    }

    private void returnToplayButtonm(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
