package com.iremse.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnKaloriHesapla = findViewById(R.id.btn_calculate_calorie);
        Button btnVucutKitleIndex = findViewById(R.id.btn_calculate_bmi);
        Button btnFitnessExercises = findViewById(R.id.btn_fitness_exercises);

        btnKaloriHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKaloriHesaplaActivity();
            }
        });

        btnVucutKitleIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVucutKitleIndexActivity();
            }
        });

        btnFitnessExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFitnessExercisesActivity();
            }
        });
    }

    public void openKaloriHesaplaActivity() {
        Intent intent = new Intent(this, KaloriHesapla.class);
        startActivity(intent);
    }

    public void openFitnessExercisesActivity() {
        Intent intent = new Intent(this, FitnessExercises.class);
        startActivity(intent);
    }

    public void openVucutKitleIndexActivity() {
        Intent intent = new Intent(this, VucutKitleIndex.class);
        startActivity(intent);
    }
}
