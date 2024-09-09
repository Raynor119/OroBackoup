package com.pixels.orobackoup.View.Menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.InicioSession;

public class Menu extends AppCompatActivity {

    private SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TextView titulo=(TextView) findViewById(R.id.titulomenu);
        prefe=getSharedPreferences("Sesion",Menu.this.MODE_PRIVATE);
        if(prefe.getString("TipoUsuario","0").equals("A")){
            titulo.setText("Menu de Administrador");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setTitle("");
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menui,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id == R.id.opcion){
            CerrarSession();
        }
        return super.onOptionsItemSelected(item);
    }
    public void CerrarSession(){
        SharedPreferences preferencias=getSharedPreferences("Sesion",Menu.this.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("Codigo", "");
        editor.putString("Usuario", "");
        editor.putString("TipoUsuario", "");
        editor.putString("SesionD", "0");
        editor.commit();
        Intent intent=new Intent(Menu.this, InicioSession.class);
        startActivity(intent);
        finish();
    }
}