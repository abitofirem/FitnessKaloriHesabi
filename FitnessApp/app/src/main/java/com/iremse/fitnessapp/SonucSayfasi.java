package com.iremse.fitnessapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SonucSayfasi extends AppCompatActivity {
    private TextView sabahKcalTextView, ogleKcalTextView, aksamKcalTextView, toplamKcalTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc_sayfasi);

        sabahKcalTextView = findViewById(R.id.sabahkcal_txt);
        ogleKcalTextView = findViewById(R.id.oglekcal_txt);
        aksamKcalTextView = findViewById(R.id.aksamkcal_txt);
        toplamKcalTextView = findViewById(R.id.toplamkcal_txt);

        databaseHelper = new DatabaseHelper(this);

        // Veritabanından sabah, öğle ve akşam yemeği kalorilerini al
        int sabahKcal = databaseHelper.getKahvaltiKcal();
        int ogleKcal = databaseHelper.getOgleYemegiKcal();
        int aksamKcal = databaseHelper.getAksamYemegiKcal();

        // Toplam kaloriyi hesapla
        int toplamKcal = sabahKcal + ogleKcal + aksamKcal;

        // TextView'lara verileri yerleştir
        sabahKcalTextView.setText(String.valueOf(sabahKcal));
        ogleKcalTextView.setText(String.valueOf(ogleKcal));
        aksamKcalTextView.setText(String.valueOf(aksamKcal));
        toplamKcalTextView.setText(String.valueOf(toplamKcal));
    }
}
