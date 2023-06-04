package com.example.actividadsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ParquesActivity extends AppCompatActivity {
    private EditText etIdParque, etNombreParque, etLugarParque, etDescripcionParque, etCaracteristicasParque;
    ControladorParquesBD admin_parques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parques);
        etIdParque = findViewById(R.id.txtIdParque);
        etNombreParque = findViewById(R.id.txtNombreParque);
        etLugarParque = findViewById(R.id.txtLugarParque);
        etDescripcionParque = findViewById(R.id.txtDescripcionParque);
        etCaracteristicasParque = findViewById(R.id.txtCaracteristicasParque);
        admin_parques = new ControladorParquesBD(this, "parquesmely.db", null, 1);
    }
    public void registrarParque(View view) {
        SQLiteDatabase bd = admin_parques.getWritableDatabase();
        String idp = etIdParque.getText().toString();
        String nomp = etNombreParque.getText().toString();
        String lugp = etLugarParque.getText().toString();
        String desp = etDescripcionParque.getText().toString();
        String carp = etCaracteristicasParque.getText().toString();
        if(!idp.isEmpty() && !nomp.isEmpty() && !lugp.isEmpty() && !desp.isEmpty() && !carp.isEmpty()) {
            ContentValues registro_parque = new ContentValues();
            registro_parque.put("idparque", idp);
            registro_parque.put("nombre", nomp);
            registro_parque.put("lugar", lugp);
            registro_parque.put("descripcion", desp);
            registro_parque.put("caracteristicas", carp);
            if (bd != null) {
                long x = 0;
                try {
                    x = bd.insert("parques", null, registro_parque);
                } catch (SQLException e) {
                    Log.e("Exception", "Error:" + String.valueOf(e.getMessage()));
                }
                bd.close();
            }
            etIdParque.setText("");
            etNombreParque.setText("");
            etLugarParque.setText("");
            etDescripcionParque.setText("");
            etCaracteristicasParque.setText("");
            etIdParque.requestFocus();
            Toast.makeText(this, "Parque registrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscarParque(View view) {
        SQLiteDatabase bd = admin_parques.getReadableDatabase();
        String idp = etIdParque.getText().toString();
        if(!idp.isEmpty()) {
            Cursor fila = bd.rawQuery("select nombre, lugar, descripcion, caracteristicas from parques " + "where idparque=" + idp, null);
            if(fila.moveToFirst()) {
                etNombreParque.setText(fila.getString(0));
                etLugarParque.setText(fila.getString(1));
                etDescripcionParque.setText(fila.getString(2));
                etCaracteristicasParque.setText(fila.getString(3));
                bd.close();
            } else {
                Toast.makeText(this, "ID de parque no existe", Toast.LENGTH_SHORT).show();
                etIdParque.setText("");
                etIdParque.requestFocus();
                bd.close();
            }
        } else {
            Toast.makeText(this, "Ingresa ID de parque", Toast.LENGTH_SHORT).show();
            etIdParque.requestFocus();
        }
    }
    public void actualizarParque(View view) {
        SQLiteDatabase bd = admin_parques.getWritableDatabase();
        String idp = etIdParque.getText().toString();
        String nomp = etNombreParque.getText().toString();
        String lugp = etLugarParque.getText().toString();
        String desp = etDescripcionParque.getText().toString();
        String carp = etCaracteristicasParque.getText().toString();
        if(!idp.isEmpty() && !nomp.isEmpty() && !lugp.isEmpty() && !desp.isEmpty() && !carp.isEmpty()) {
            ContentValues registro_parque = new ContentValues();
            registro_parque.put("idparque", idp);
            registro_parque.put("nombre", nomp);
            registro_parque.put("lugar", lugp);
            registro_parque.put("descripcion", desp);
            registro_parque.put("caracteristicas", carp);
            int cantidad = 0;
            cantidad = bd.update("parques", registro_parque, "idparque="+ idp, null);
            bd.close();
            etIdParque.setText("");
            etNombreParque.setText("");
            etLugarParque.setText("");
            etDescripcionParque.setText("");
            etCaracteristicasParque.setText("");
            etIdParque.requestFocus();
            if(cantidad > 0) {
                Toast.makeText(this, "Datos del parque actualizados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El ID del parque no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarParque(View view) {
        SQLiteDatabase bd = admin_parques.getWritableDatabase();
        String idp = etIdParque.getText().toString();
        if(!idp.isEmpty()) {
            int cantidad = 0;
            cantidad = bd.delete("parques","idparque = " + idp, null);
            bd.close();
            etIdParque.setText("");
            etNombreParque.setText("");
            etLugarParque.setText("");
            etDescripcionParque.setText("");
            etCaracteristicasParque.setText("");
            etIdParque.requestFocus();
            if(cantidad > 0) {
                Toast.makeText(this, "Parque eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El ID de parque no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingresa ID de parque", Toast.LENGTH_SHORT).show();
        }
    }
}