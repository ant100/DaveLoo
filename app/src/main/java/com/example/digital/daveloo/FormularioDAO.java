package com.example.digital.daveloo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FormularioDAO {

    private DbHelper _dbHelper;

    public FormularioDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String tipo, String texto, String imagen, Integer usuario_id) throws DAOException {
        Log.i("FormularioDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{tipo, texto, imagen};
            ContentValues insertValues = new ContentValues();
            insertValues.put("tipo", tipo);
            insertValues.put("texto", texto);
            insertValues.put("imagen", imagen);
            insertValues.put("usuario_id", usuario_id);
            db.insert("formulario", null, insertValues);
            Log.i("FormularioDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("FormularioDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Formulario obtener() throws DAOException {
        Log.i("FormularioDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Formulario modelo = new Formulario();
        try {
            Cursor c = db.rawQuery("select id, tipo, texto, imagen, usuario_id from formulario", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int id = c.getInt(c.getColumnIndex("id"));
                    int usuario_id = c.getInt(c.getColumnIndex("usuario_id"));
                    String tipo = c.getString(c.getColumnIndex("tipo"));
                    String texto = c.getString(c.getColumnIndex("texto"));
                    String imagen = c.getString(c.getColumnIndex("imagen"));

                    modelo.setId(id);
                    modelo.setTipo(tipo);
                    modelo.setTexto(texto);
                    modelo.setId_usuario(usuario_id);
                    modelo.setImagen(imagen);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("FormularioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

}

