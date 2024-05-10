package com.iremse.fitnessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.iremse.fitnessapp.R;

public class KaloriHesapla extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private EditText yumurtaEditText, salatalikEditText, domatesEditText, peynirEditText, zeytinEditText;
    private EditText mercimekEditText, pirincEditText, tavukEditText, salataEditText, sekerpareEditText;
    private EditText ezogelinEditText, iclikofteEditText, mantiEditText, marulEditText, cilekEditText;
    private Button hesaplaButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori_hesapla);
        databaseHelper = new DatabaseHelper(this);

        // XML dosyasındaki bileşenlerin referanslarını al
        yumurtaEditText = findViewById(R.id.yumurta);
        salatalikEditText = findViewById(R.id.salatalik);
        domatesEditText = findViewById(R.id.domates);
        peynirEditText = findViewById(R.id.peynir);
        zeytinEditText = findViewById(R.id.zeytin);

        mercimekEditText = findViewById(R.id.mercimek);
        pirincEditText = findViewById(R.id.pirinc);
        tavukEditText = findViewById(R.id.tavuk);
        salataEditText = findViewById(R.id.salata);
        sekerpareEditText = findViewById(R.id.sekerpare);

        ezogelinEditText = findViewById(R.id.ezogelin);
        iclikofteEditText = findViewById(R.id.iclikofte);
        mantiEditText = findViewById(R.id.manti);
        marulEditText = findViewById(R.id.marul);
        cilekEditText = findViewById(R.id.cilek);

        hesaplaButton = findViewById(R.id.hesapla_btn);

        // Hesapla butonuna tıklandığında
        hesaplaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toplam kaloriyi hesapla
                int toplamKalori = calculateTotalCalories();

                // Veritabanına ekle
                int yumurtaCount = getCaloriesFromEditText(yumurtaEditText);
                int salatalikCount = getCaloriesFromEditText(salatalikEditText);
                int domatesCount = getCaloriesFromEditText(domatesEditText);
                int peynirCount = getCaloriesFromEditText(peynirEditText);
                int zeytinCount = getCaloriesFromEditText(zeytinEditText);

                databaseHelper.addKahvaltiCalories(yumurtaCount, salatalikCount, domatesCount, peynirCount, zeytinCount);

                int mercimekCount = getCaloriesFromEditText(mercimekEditText);
                int pirincCount = getCaloriesFromEditText(pirincEditText);
                int tavukCount = getCaloriesFromEditText(tavukEditText);
                int salataCount = getCaloriesFromEditText(salataEditText);
                int sekerpareCount = getCaloriesFromEditText(sekerpareEditText);

                databaseHelper.addOgleYemegiCalories(mercimekCount, pirincCount, tavukCount, salataCount, sekerpareCount);

                int ezogelinCount = getCaloriesFromEditText(ezogelinEditText);
                int iclikofteCount = getCaloriesFromEditText(iclikofteEditText);
                int mantiCount = getCaloriesFromEditText(mantiEditText);
                int marulCount = getCaloriesFromEditText(marulEditText);
                int cilekCount = getCaloriesFromEditText(cilekEditText);

                databaseHelper.addAksamYemegiCalories(ezogelinCount, iclikofteCount, mantiCount, marulCount, cilekCount);

                Toast.makeText(KaloriHesapla.this, "Kaloriler başarıyla eklendi!", Toast.LENGTH_SHORT).show();

                // Sonucu göstermek için SonucSayfasi'na geçiş yap
                Intent intent = new Intent(KaloriHesapla.this, SonucSayfasi.class);
                intent.putExtra("toplamKalori", toplamKalori); // Toplam kaloriyi intent ile gönder
                startActivity(intent);
            }
        });

    }

    // Girilen kalorileri toplayarak toplam kaloriyi döndürür
    private int calculateTotalCalories() {
        int toplamKalori = 0;

        // Her bir yiyeceğin kalorilerini al ve toplam kaloriye ekle
        toplamKalori += getCaloriesFromEditText(yumurtaEditText) * 50; // Yumurta için kalori: 50 kcal
        toplamKalori += getCaloriesFromEditText(salatalikEditText) * 10; // Salatalık için kalori: 10 kcal
        toplamKalori += getCaloriesFromEditText(domatesEditText) * 15; // Domates için kalori: 15 kcal
        toplamKalori += getCaloriesFromEditText(peynirEditText) * 80; // Peynir için kalori: 80 kcal
        toplamKalori += getCaloriesFromEditText(zeytinEditText) * 30; // Zeytin için kalori: 30 kcal

        toplamKalori += getCaloriesFromEditText(mercimekEditText) * 100; // Mercimek için kalori: 100 kcal
        toplamKalori += getCaloriesFromEditText(pirincEditText) * 120; // Pirinç için kalori: 120 kcal
        toplamKalori += getCaloriesFromEditText(tavukEditText) * 150; // Tavuk için kalori: 150 kcal
        toplamKalori += getCaloriesFromEditText(salataEditText) * 20; // Salata için kalori: 20 kcal
        toplamKalori += getCaloriesFromEditText(sekerpareEditText) * 200; // Şekerpare için kalori: 200 kcal

        toplamKalori += getCaloriesFromEditText(ezogelinEditText) * 90; // Ezogelin çorbası için kalori: 90 kcal
        toplamKalori += getCaloriesFromEditText(iclikofteEditText) * 70; // İçli köfte için kalori: 70 kcal
        toplamKalori += getCaloriesFromEditText(mantiEditText) * 130; // Mantı için kalori: 130 kcal
        toplamKalori += getCaloriesFromEditText(marulEditText) * 15; // Marul için kalori: 15 kcal
        toplamKalori += getCaloriesFromEditText(cilekEditText) * 30; // Çilek için kalori: 30 kcal

        return toplamKalori;
    }

    // EditText'ten girilen değeri alır ve integer'a çevirir
    private int getCaloriesFromEditText(EditText editText) {
        String text = editText.getText().toString();
        if (!text.isEmpty()) {
            return Integer.parseInt(text);
        }
        return 0;
    }
}
