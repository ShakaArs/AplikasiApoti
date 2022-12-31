package com.example.aplikasiapoti;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHealth extends SQLiteOpenHelper {
    private static SQLiteDatabase db;

    static abstract class MyColumns implements BaseColumns {
        static final String NamaTabel = "DataHealth";
        static final String KategoriPos = "KategoriPos";
        static final String NamaPasien = "NamaPasien";
        static final String TinggiBadan = "TinggiBadan";
        static final String BeratBadan = "BeratBadan";
        static final String NIK = "NIK";
        static final String TensiDarah = "TensiDarah";
        static final String Foto = "GambarPic";


    }

    private static final String NamaDatabse = "dbapotihealth3.db";
    private static final int VersiDatabase = 1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + MyColumns.NamaTabel +
            "(" + MyColumns.NIK + " INTEGER PRIMARY KEY , "
            + MyColumns.KategoriPos + " TEXT  NOT NULL, "
            + MyColumns.TinggiBadan + " INTEGER NOT NULL, "
            + MyColumns.BeratBadan + " INTEGER NOT NULL, "
            + MyColumns.TensiDarah + " TEXT NOT NULL, "
            + MyColumns.NamaPasien + " TEXT NOT NULL, "
            + MyColumns.Foto + " TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + MyColumns.NamaTabel;

    DbHealth(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

//    //Get 1 Data By ID
//    public static Cursor oneData(Long id) {
//        Cursor cur = db.rawQuery("SELECT * FROM " + MyColumns.NamaTabel + " WHERE " + MyColumns.KodeBuku + "=" + id, null);
//        return cur;
//    }

}
