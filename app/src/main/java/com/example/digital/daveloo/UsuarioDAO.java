package com.example.digital.daveloo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDAO {

    private DbHelper _dbHelper;

    public UsuarioDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String nombre, String password, String email) throws DAOException {
        Log.i("UsuarioDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombre, password, email};
            ContentValues insertValues = new ContentValues();
            insertValues.put("usuario_nombre", nombre);
            insertValues.put("usuario_password", password);
            insertValues.put("usuario_email", email);
            db.insert("usuario", null, insertValues);
            Log.i("UsuarioDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public int autenticar(String nombre, String password) throws DAOException {
        int usuario_id = 0;
        Log.i("UsuarioDAO", "autenticar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();

        try {
            String args[] = new String[]{nombre, password};
            Cursor c = db.rawQuery("select id_usuario, usuario_nombre, usuario_email from usuario where usuario_nombre = ? and usuario_password = ?", args);

            if (c.getCount() > 0) {
                c.moveToFirst();
                usuario_id = c.getInt(c.getColumnIndex("id_usuario"));
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return usuario_id;
    }


}