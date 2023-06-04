package com.example.actividadsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoParquesActivity extends AppCompatActivity {
    private TextView etListadoP;
    ControladorParquesBD admin_parques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_parques);
        etListadoP = findViewById(R.id.txtDetalleParques);
        admin_parques = new ControladorParquesBD(this, "parquesmely.db", null, 1);
        SQLiteDatabase bd = admin_parques.getReadableDatabase();
        Cursor registro = bd.rawQuery("select * from parques", null);
        int n = registro.getCount();
        int nr = 1;
        if(n > 0) {
            registro.moveToFirst();
            do {
                etListadoP.setText(etListadoP.getText() + "\nRegistro #" + nr);
                etListadoP.setText(etListadoP.getText() + "\nID: " + registro.getString(0));
                etListadoP.setText(etListadoP.getText() + "\nNombre: " + registro.getString(1));
                etListadoP.setText(etListadoP.getText() + "\nDirección: " + registro.getString(2));
                etListadoP.setText(etListadoP.getText() + "\nDescripción: " + registro.getString(3));
                etListadoP.setText(etListadoP.getText() + "\nExtensión: " + registro.getString(4) + "m2");
                etListadoP.setText(etListadoP.getText() + "\n");
                nr++;
            } while(registro.moveToNext());
        } else {
            Toast.makeText(this, "Sin registro de parques", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }
    public void regresarPrincipal(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}