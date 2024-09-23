package com.pixels.orobackoup.View.Prenda.ListaPrendas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.orobackoup.R;

public class PrendasListView extends AppCompatActivity {

    private SharedPreferences prefe;
    private String CodigoU,TipoU;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendas_list_view);
        prefe=getSharedPreferences("Sesion",PrendasListView.this.MODE_PRIVATE);
        recyclerView = (RecyclerView) findViewById(R.id.opcion_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TipoU=prefe.getString("TipoUsuario","0");
        if(TipoU.equals("A")){
            CodigoU="none";
        }else{
            CodigoU=prefe.getString("Codigo","0");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setTitle("");
        setSupportActionBar(toolbar);
        reclicler();
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.menuupdate,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id == R.id.opcion){
            reclicler();
        }
        return super.onOptionsItemSelected(item);
    }

    public void reclicler(){

    }
}