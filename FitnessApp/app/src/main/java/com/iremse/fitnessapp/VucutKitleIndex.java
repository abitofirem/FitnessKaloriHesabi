package com.iremse.fitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VucutKitleIndex extends AppCompatActivity {

    private EditText editTextAge, editTextHeight, editTextWeight;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vucut_kitle_index);

        editTextAge = findViewById(R.id.editTextAge);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Kullanıcıdan yaş, boy ve kilo bilgilerini al
        int age = Integer.parseInt(editTextAge.getText().toString());
        float height = Float.parseFloat(editTextHeight.getText().toString()) / 100; // cm'yi m'ye çevir
        float weight = Float.parseFloat(editTextWeight.getText().toString());

        // Vücut kitle indeksini hesapla
        float bmi = weight / (height * height);

        // Vücut kitle indeksi sonucunu göster
        String result = "Vücut Kitle İndeksi: " + bmi + "\nDurum: ";
        if (bmi < 18.5) {
            result += "Zayıf";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            result += "Normal";
        } else if (bmi >= 24.9 && bmi < 29.9) {
            result += "Fazla Kilolu";
        } else {
            result += "Obez";
        }

        textViewResult.setText(result);
    }
}