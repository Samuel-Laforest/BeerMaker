package com.example.beermaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String create = " CREATE TABLE `BddRecette` (\n" +
            "\t`id` INT,\n" +
            "\t`ingredient1` VARCHAR,\n" +
            "\t`ingredient2` VARCHAR,\n" +
            "\t`ingredient3` VARCHAR\n" +
            ");";

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
