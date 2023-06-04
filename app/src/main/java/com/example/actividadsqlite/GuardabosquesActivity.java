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

public class GuardabosquesActivity extends AppCompatActivity {
    private EditText etIdGuardabosques, etNombreGuardabosques, etApellidosGuardabosques, etSalarioGuardabosques, etDescripcionGuardabosques;
    ControladorGuardabosquesBD admin_guardabosques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardabosques);
        etIdGuardabosques = findViewById(R.id.txtIdGuardabosques);
        etNombreGuardabosques = findViewById(R.id.txtNombreGuardabosques);
        etApellidosGuardabosques = findViewById(R.id.txtApellidosGuardabosques);
        etSalarioGuardabosques = findViewById(R.id.txtSalarioGuardabosques);
        etDescripcionGuardabosques = findViewById(R.id.txtDescripcionGuardabosques);
        admin_guardabosques = new ControladorGuardabosquesBD(this, "guardabosquesmely.db", null, 1);
    }
    public void registrarGuardabosques(View view) {
        SQLiteDatabase bd = admin_guardabosques.getWritableDatabase();
        String idg = etIdGuardabosques.getText().toString();
        String nomg = etNombreGuardabosques.getText().toString();
        String apeg = etApellidosGuardabosques.getText().toString();
        String salg = etSalarioGuardabosques.getText().toString();
        String desp = etDescripcionGuardabosques.getText().toString();
        if(!idg.isEmpty() && !nomg.isEmpty() && !apeg.isEmpty() && !salg.isEmpty() && !desp.isEmpty()) {
            ContentValues registro_guardabosques = new ContentValues();
            registro_guardabosques.put("idguardabosques", idg);
            registro_guardabosques.put("nombre", nomg);
            registro_guardabosques.put("apellidos", apeg);
            registro_guardabosques.put("sueldo", salg);
            registro_guardabosques.put("descripcion", desp);
            if (bd != null) {
                long x = 0;
                try {
                    x = bd.insert("guardabosques", null, registro_guardabosques);
                } catch (SQLException e) {
                    Log.e("Exception", "Error:" + String.valueOf(e.getMessage()));
                }
                bd.close();
            }
            etIdGuardabosques.setText("");
            etNombreGuardabosques.setText("");
            etApellidosGuardabosques.setText("");
            etSalarioGuardabosques.setText("");
            etDescripcionGuardabosques.setText("");
            etIdGuardabosques.requestFocus();
            Toast.makeText(this, "Guardabosques registrado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscarGuardabosques(View view) {
        SQLiteDatabase bd = admin_guardabosques.getReadableDatabase();
        String idg = etIdGuardabosques.getText().toString();
        if(!idg.isEmpty()) {
            Cursor fila = bd.rawQuery("select nombre, apellidos, sueldo, descripcion from guardabosques " + "where idguardabosques=" + idg, null);
            if(fila.moveToFirst()) {
                etNombreGuardabosques.setText(fila.getString(0));
                etApellidosGuardabosques.setText(fila.getString(1));
                etSalarioGuardabosques.setText(fila.getString(2));
                etDescripcionGuardabosques.setText(fila.getString(3));
                bd.close();
            } else {
                Toast.makeText(this, "ID del guardabosques no existe", Toast.LENGTH_SHORT).show();
                etIdGuardabosques.setText("");
                etIdGuardabosques.requestFocus();
                bd.close();
            }
        } else {
            Toast.makeText(this, "Ingresa ID del guardabosques", Toast.LENGTH_SHORT).show();
            etIdGuardabosques.requestFocus();
        }
    }
    public void actualizarGuardabosques(View view) {
        SQLiteDatabase bd = admin_guardabosques.getWritableDatabase();
        String idg = etIdGuardabosques.getText().toString();
        String nomg = etNombreGuardabosques.getText().toString();
        String apeg = etApellidosGuardabosques.getText().toString();
        String salg = etSalarioGuardabosques.getText().toString();
        String desp = etDescripcionGuardabosques.getText().toString();
        if(!idg.isEmpty() && !nomg.isEmpty() && !apeg.isEmpty() && !salg.isEmpty() && !desp.isEmpty()) {
            ContentValues registro_guardabosques = new ContentValues();
            registro_guardabosques.put("idguardabosques", idg);
            registro_guardabosques.put("nombre", nomg);
            registro_guardabosques.put("apellidos", apeg);
            registro_guardabosques.put("sueldo", salg);
            registro_guardabosques.put("descripcion", desp);
            int cantidad = 0;
            cantidad = bd.update("guardabosques", registro_guardabosques, "idguardabosques="+ idg, null);
            bd.close();
            etIdGuardabosques.setText("");
            etNombreGuardabosques.setText("");
            etApellidosGuardabosques.setText("");
            etSalarioGuardabosques.setText("");
            etDescripcionGuardabosques.setText("");
            etIdGuardabosques.requestFocus();
            if(cantidad > 0) {
                Toast.makeText(this, "Datos del guardabosques actualizados", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El ID del guardabosques no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarGuardabosques(View view) {
        SQLiteDatabase bd = admin_guardabosques.getWritableDatabase();
        String idg = etIdGuardabosques.getText().toString();
        if(!idg.isEmpty()) {
            int cantidad = 0;
            cantidad = bd.delete("guardabosques","idguardabosques = " + idg, null);
            bd.close();
            etIdGuardabosques.setText("");
            etNombreGuardabosques.setText("");
            etApellidosGuardabosques.setText("");
            etSalarioGuardabosques.setText("");
            etDescripcionGuardabosques.setText("");
            etIdGuardabosques.requestFocus();
            if(cantidad > 0) {
                Toast.makeText(this, "Guardabosques eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El ID del guardabosques no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingresa ID del guardabosques", Toast.LENGTH_SHORT).show();
        }
    }
}