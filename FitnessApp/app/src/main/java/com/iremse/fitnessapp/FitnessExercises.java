package com.iremse.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FitnessExercises extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_exercises);

        // Hareket 1
        Button hareket1Button = findViewById(R.id.hareket1);
        hareket1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessExercises.this, LateralWalkActivity.class);
                startActivity(intent);
            }
        });

        // Hareket 2
        Button hareket2Button = findViewById(R.id.hareket2);
        hareket2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessExercises.this, SquatActivity.class);
                startActivity(intent);
            }
        });

        // Hareket 3
        Button hareket3Button = findViewById(R.id.hareket3);
        hareket3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FitnessExercises.this, AbsStretchActivity.class);
                startActivity(intent);
            }
        });
    }
}