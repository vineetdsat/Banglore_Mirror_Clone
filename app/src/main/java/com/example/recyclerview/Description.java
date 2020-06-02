package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Description extends AppCompatActivity {
    TextView descrip, Head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descrip = findViewById(R.id.tv_des);
        Head = findViewById(R.id.desc_head);

        Intent i = getIntent();
        Updates updates = i.getParcelableExtra("des");

        String des = updates.getDescription();
        String head = updates.getHead();


        descrip.setText(des);
        Head.setText(head);
    }
}
