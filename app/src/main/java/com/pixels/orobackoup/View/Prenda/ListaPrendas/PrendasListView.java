package com.pixels.orobackoup.View.Prenda.ListaPrendas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pixels.orobackoup.Model.DatosEncapsulados.ListaPrenda;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.AlertDialog.AlertCarga;
import com.pixels.orobackoup.View.Prenda.ListaPrendas.RecyclerViewAdapter.RecyclerAdaptadorPrendas;
import com.pixels.orobackoup.ViewModel.Prenda.DatosPrendaViewModel;
import com.pixels.orobackoup.ViewModel.Prenda.ListaPrendas.Lista_prendasViewModel;

import java.util.List;

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
            CodigoU=prefe.getString("Codigo","0");
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
        recyclerView.setAdapter(null);
        AlertCarga carga =new AlertCarga(PrendasListView.this);
        Lista_prendasViewModel prendaViewModel= ViewModelProviders.of(PrendasListView.this).get(Lista_prendasViewModel.class);
        prendaViewModel.reset();
        carga.Cargar();
        prendaViewModel.listadeprendas(PrendasListView.this,TipoU,CodigoU);
        Observer<List<ListaPrenda>> observer=new Observer<List<ListaPrenda>>() {
            @Override
            public void onChanged(List<ListaPrenda> listaPrendas) {
                carga.setInicio(1);
                carga.Cerrar();
                if (listaPrendas==null){

                }else{
                    Toast.makeText(PrendasListView.this, "entro", Toast.LENGTH_SHORT).show();
                    if (listaPrendas.size()>0){
                        recyclerView.setAdapter(new RecyclerAdaptadorPrendas(PrendasListView.this,listaPrendas));
                    }else{
                        Toast.makeText(PrendasListView.this, "No hay Prendas Registradas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        prendaViewModel.getResultadoPrendas().observe(PrendasListView.this,observer);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(carga.getInicio()==0){
                    Toast.makeText(PrendasListView.this, "Error no hay conexion", Toast.LENGTH_LONG).show();
                    carga.Cerrar();
                    finish();
                }
            }
        },12000);


    }
}