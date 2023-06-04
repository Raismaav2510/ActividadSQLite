package com.example.actividadsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private CheckBox guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.edtUsuario);
        password = findViewById(R.id.edtContrasena);
        guardar = findViewById(R.id.cbGuardar);
    }
    public void ingresarMenu(View view) {
        Usuario usr = new Usuario();
        usr.setUsername(username.getText().toString());
        usr.setPassword(password.getText().toString());
        usr.setRegistrado(true);
        if(guardar.isChecked())
            guardarPreferencias(usr);
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
    public void salirMenu(View view) {
        finish();
    }
    public void guardarPreferencias(Usuario u){
        //Almacenar datos (objeto) en archivo interno
        SharedPreferences preferencias = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("Usuario", u.getUsername());
        editor.putString("Contraseña", u.getPassword());
        editor.putBoolean("Registrado", u.isRegistrado());
        editor.apply();
    }
}