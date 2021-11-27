package com.misiontic.mytinyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView goToToDo = findViewById(R.id.goToToDo);
        goToToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToToDo = new Intent(HomeActivity.this,ToDoActivity.class);
                startActivity(goToToDo);
            }
        });

    }
}