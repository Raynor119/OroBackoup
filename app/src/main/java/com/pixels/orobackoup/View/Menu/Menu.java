package com.pixels.orobackoup.View.Menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pixels.orobackoup.Model.DatosEncapsulados.MenuLista;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.InicioSession;
import com.pixels.orobackoup.View.Menu.RecyclerViewAdaptador.RecyclerAdaptadorMenu;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    private SharedPreferences prefe;
    private List<MenuLista> menuopciones= new ArrayList<>();
    public static FloatingActionButton fab;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = (RecyclerView) findViewById(R.id.opcion_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView titulo=(TextView) findViewById(R.id.titulomenu);
        fab = findViewById(R.id.fab);
        prefe=getSharedPreferences("Sesion",Menu.this.MODE_PRIVATE);
        if(prefe.getString("TipoUsuario","0").equals("A")){
            titulo.setText("Menu de Administrador");
            fab.setVisibility(View.GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        Recicler();
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

    private void Recicler(){
        recyclerView.setAdapter(null);
        menuopciones=new ArrayList<>();
        prefe=getSharedPreferences("Sesion",Menu.this.MODE_PRIVATE);

        if(prefe.getString("TipoUsuario","0").equals("U")){
            menuopciones.add(new MenuLista(1,"Prendas Registradas"));
            menuopciones.add(new MenuLista(4,"Salir"));
        }else{
            menuopciones.add(new MenuLista(2,"Prendas Registradas"));
            menuopciones.add(new MenuLista(3,"Estadisticas"));
            menuopciones.add(new MenuLista(4,"Salir"));
        }
        recyclerView.setAdapter(new RecyclerAdaptadorMenu(Menu.this,menuopciones));
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