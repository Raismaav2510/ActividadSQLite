package com.example.actividadsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoGuardabosquesActivity extends AppCompatActivity {
    private TextView etListadoG;
    ControladorGuardabosquesBD admin_guardabosques;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_guardabosques);
        etListadoG = findViewById(R.id.txtDetalleGuardabosques);
        admin_guardabosques = new ControladorGuardabosquesBD(this, "guardabosquesmely.db", null, 1);
        SQLiteDatabase bd = admin_guardabosques.getReadableDatabase();
        Cursor registro = bd.rawQuery("select * from guardabosques", null);
        int n = registro.getCount();
        int nr = 1;
        if(n > 0) {
            registro.moveToFirst();
            do {
                etListadoG.setText(etListadoG.getText() + "\nRegistro #" + nr);
                etListadoG.setText(etListadoG.getText() + "\nID: " + registro.getString(0));
                etListadoG.setText(etListadoG.getText() + "\nNombre: " + registro.getString(1));
                etListadoG.setText(etListadoG.getText() + "\nApellidos: " + registro.getString(2));
                etListadoG.setText(etListadoG.getText() + "\nSalario: " + registro.getString(3));
                etListadoG.setText(etListadoG.getText() + "\nEdad: " + registro.getString(4));
                etListadoG.setText(etListadoG.getText() + "\n");
                nr++;
            } while(registro.moveToNext());
        } else {
            Toast.makeText(this, "Sin registro de guardabosques", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }
    public void regresarPrincipal(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}