package com.example.digital.daveloo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        // null porque se va a usar el SQLiteCursor
        super(context, "daveloo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS formulario (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT NOT NULL, texto TEXT NOT NULL, imagen TEXT NULL, usuario_id INTEGER NOT NULL)";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS usuario (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, usuario_nombre TEXT NOT NULL, usuario_password TEXT NOT NULL, usuario_email TEXT NOT NULL)";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE IF NOT EXISTS telefono (id_telefono INTEGER PRIMARY KEY AUTOINCREMENT, servicio TEXT NOT NULL, telefono INTEGER NOT NULL)";
        db.execSQL(sql3);

        /*String sql4 = "INSERT INTO telefono (servicio,telefono)\n" + "VALUES ('Policía',105)";
        db.execSQL(sql4);
        String sql5 = "INSERT INTO telefono (servicio,telefono)\n" + "VALUES ('Bomberos',116)";
        db.execSQL(sql5);
        String sql6 = "INSERT INTO telefono (servicio,telefono)\n" + "VALUES ('SAMU',106)";
        db.execSQL(sql6);
        String sql7 = "INSERT INTO telefono (servicio,telefono)\n" + "VALUES ('Cruz Roja',115)";
        db.execSQL(sql7);
        String sql8 = "INSERT INTO telefono (servicio,telefono)\n" + "VALUES ('Defensa Civil',110)";
        db.execSQL(sql8);
        String sql9 = "INSERT INTO telefono (servicio,telefono)\n" + "VALUES ('Emergencias ESSALUD',117)";
        db.execSQL(sql9);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS formulario");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS telefono");
        onCreate(db);
    }
}

