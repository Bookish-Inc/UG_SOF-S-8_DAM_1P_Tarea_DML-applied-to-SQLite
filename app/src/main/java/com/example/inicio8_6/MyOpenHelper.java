package com.example.inicio8_6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {
    //esto se debe ejecutar sola una vez
    private static final String TABLE_CREATE = "CREATE TABLE usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, edad INTEGER, telefono TEXT, correo TEXT, clave TEXT, estadoCivil TEXT, sexo CHAR(1))";
    private static final String DB_NAME = "NetCore.sqlite";
    private static final int DB_VERSION = 1;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
