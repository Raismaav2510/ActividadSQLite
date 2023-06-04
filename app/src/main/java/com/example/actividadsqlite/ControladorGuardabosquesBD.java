package com.example.actividadsqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ControladorGuardabosquesBD extends SQLiteOpenHelper {
    public ControladorGuardabosquesBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase nombreBD) {
        String sql = "create table guardabosques (idguardabosques int primary key, nombre text, apellidos text, sueldo text, descripcion text)";
        try {
            nombreBD.execSQL(sql);
        } catch (SQLException e) {
            Toast.makeText(null, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }
}
