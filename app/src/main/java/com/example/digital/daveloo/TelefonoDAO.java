package com.example.digital.daveloo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class TelefonoDAO {

    private DbHelper _dbHelper;

    public TelefonoDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public ArrayList<Telefono> obtener() throws DAOException {
        Log.i("telefonoDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Telefono> telefono = new ArrayList<Telefono>();
        try {
            Cursor c = db.rawQuery("select id_telefono, servicio, telefono from telefono", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id_telefono"));
                    String servicio = c.getString(c.getColumnIndex("servicio"));
                    int numero = c.getInt(c.getColumnIndexOrThrow("telefono"));
                    Telefono tel = new Telefono();
                    tel.setTelefono_id(id);
                    tel.setServicio(servicio);
                    tel.setNumero(numero);

                    telefono.add(tel);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("telefonoDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return telefono;
    }
}
