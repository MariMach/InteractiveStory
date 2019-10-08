package com.example.interactivestory.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public String nom_db;
    public Context context;
    public int version;

    public DatabaseHelper(Context context, String nom_db, int version) {
        super(context, "story.db", null, 1);
        this.context = context;
        this.nom_db = nom_db;
        this.version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table story (labelStory text primary key, imageurl text, description text)");
        db.execSQL("Create table page (labelSt text ,imageurl text, description text,PRIMARY KEY(labelSt, imageurl, description))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists story");
        db.execSQL("drop table if exists page");
    }

    public boolean insertStory(String labelStory, String image, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("labelStory", labelStory);
        contentValues.put("imageurl", image);
        contentValues.put("description", description);
        long ins = db.insert("story", null ,contentValues);
        if(ins == -1){
            return  false;
        } else {
            return true;
        }
    }

    public boolean insertPage(String labelStory, String image, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("labelSt", labelStory);
        contentValues.put("imageurl", image);
        contentValues.put("description", description);
        long ins = db.insert("page", null ,contentValues);
        if(ins == -1){
            return  false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getStoryURL(){
        ArrayList<String> mImageUrls = new ArrayList<>();
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from story", null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(1);
                mImageUrls.add(name);
            } while (cursor.moveToNext());
        }
        return mImageUrls;
    }

    public ArrayList<String> getStoryslabel(){
        ArrayList<String> mNames = new ArrayList<>();
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from story", null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(0);
                mNames.add(name);
            } while (cursor.moveToNext());
        }
        return mNames;
    }
    public ArrayList<String> getStorysDesc(){
        ArrayList<String> mNames = new ArrayList<>();
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from story", null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(2);
                mNames.add(name);
            } while (cursor.moveToNext());
        }
        return mNames;
    }


    public boolean checkStory(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from story", null);
        if(cursor.getCount() > 0){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkStoryLabel(String label){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from story where labelStory = ?", new String[]{label});
        if(cursor.getCount() > 0){
            return false;
        }else {
            return true;
        }
    }

    public void deleteTableF(String tablename) {
        String selectQuery = "DELETE FROM " + tablename;
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL(selectQuery);
    }
}
