package com.iremse.fitnessapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "KaloriTakip.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Kahvaltı tablosu oluştur
        String createKahvaltiTable = "CREATE TABLE Kahvalti (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "yumurta INTEGER, " +
                "salatalik INTEGER, " +
                "domates INTEGER, " +
                "peynir INTEGER, " +
                "zeytin INTEGER, " +
                "kcal INTEGER)";
        db.execSQL(createKahvaltiTable);

        // Öğle yemeği tablosu oluştur
        String createOgleYemegiTable = "CREATE TABLE OgleYemegi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mercimek INTEGER, " +
                "pirinc INTEGER, " +
                "tavuk INTEGER, " +
                "salata INTEGER, " +
                "sekerpare INTEGER, " +
                "kcal INTEGER)";
        db.execSQL(createOgleYemegiTable);

        // Akşam yemeği tablosu oluştur
        String createAksamYemegiTable = "CREATE TABLE AksamYemegi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ezogelin INTEGER, " +
                "iclikofte INTEGER, " +
                "manti INTEGER, " +
                "marul INTEGER, " +
                "cilek INTEGER, " +
                "kcal INTEGER)";
        db.execSQL(createAksamYemegiTable);
    }

    public void addKahvaltiCalories(int yumurtaCount, int salatalikCount, int domatesCount, int peynirCount, int zeytinCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("yumurta", yumurtaCount);
        values.put("salatalik", salatalikCount);
        values.put("domates", domatesCount);
        values.put("peynir", peynirCount);
        values.put("zeytin", zeytinCount);

        // Yemek adedi ile kalorilerin çarpımı toplam kaloriyi verir
        int kahvaltiKcal = (yumurtaCount * 50) + (salatalikCount * 10) + (domatesCount * 15) + (peynirCount * 80) + (zeytinCount * 30);
        values.put("kcal", kahvaltiKcal);

        db.insert("Kahvalti", null, values);
        db.close();
    }

    public void addOgleYemegiCalories(int mercimek, int pirinc, int tavuk, int salata, int sekerpare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mercimek", mercimek);
        values.put("pirinc", pirinc);
        values.put("tavuk", tavuk);
        values.put("salata", salata);
        values.put("sekerpare", sekerpare);
        // Toplam kaloriyi hesaplayıp ekleyin
        int ogleKcal = mercimek * 100 + pirinc * 120 + tavuk * 150 + salata * 20 + sekerpare * 200;
        values.put("kcal", ogleKcal);
        db.insert("OgleYemegi", null, values);
        db.close();
    }

    public void addAksamYemegiCalories(int ezogelin, int iclikofte, int manti, int marul, int cilek) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ezogelin", ezogelin);
        values.put("iclikofte", iclikofte);
        values.put("manti", manti);
        values.put("marul", marul);
        values.put("cilek", cilek);
        // Toplam kaloriyi hesaplayıp ekleyin
        int aksamKcal = ezogelin * 90 + iclikofte * 70 + manti * 130 + marul * 15 + cilek * 30;
        values.put("kcal", aksamKcal);
        db.insert("AksamYemegi", null, values);
        db.close();
    }

    public int getKahvaltiKcal() {
        SQLiteDatabase db = this.getReadableDatabase();
        int kahvaltiKcal = 0;

        String selectQuery = "SELECT SUM(kcal) FROM Kahvalti";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            kahvaltiKcal = cursor.getInt(0);
        }

        cursor.close();
        return kahvaltiKcal;
    }

    public int getOgleYemegiKcal() {
        SQLiteDatabase db = this.getReadableDatabase();
        int ogleKcal = 0;

        String selectQuery = "SELECT SUM(kcal) FROM OgleYemegi";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            ogleKcal = cursor.getInt(0);
        }

        cursor.close();
        return ogleKcal;
    }

    public int getAksamYemegiKcal() {
        SQLiteDatabase db = this.getReadableDatabase();
        int aksamKcal = 0;

        String selectQuery = "SELECT SUM(kcal) FROM AksamYemegi";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            aksamKcal = cursor.getInt(0);
        }

        cursor.close();
        return aksamKcal;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Veritabanı sürümü değişirse burada güncelleme işlemleri yapılabilir
    }
}
