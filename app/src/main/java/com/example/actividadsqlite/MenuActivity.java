package com.example.actividadsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.itmParques:
                Intent parques = new Intent(this, ParquesActivity.class);
                startActivity(parques);
                break;

            case R.id.itmListadoP:
                Intent listado_parques = new Intent(this, ListadoParquesActivity.class);
                startActivity(listado_parques);
                break;

            case R.id.itmGuardabosques:
                Intent guardabosques = new Intent(this, GuardabosquesActivity.class);
                startActivity(guardabosques);
                break;

            case R.id.itmListadoG:
                Intent listado_guardabosques = new Intent(this, ListadoGuardabosquesActivity.class);
                startActivity(listado_guardabosques);
                break;

            case R.id.itmCerrar:
                cerrarSesion();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void cerrarSesion() {
        SharedPreferences preferencias = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.clear();
        editor.apply();
        Intent loyout = new Intent(this, MainActivity.class);
        loyout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(loyout);
        finish();
    }
}