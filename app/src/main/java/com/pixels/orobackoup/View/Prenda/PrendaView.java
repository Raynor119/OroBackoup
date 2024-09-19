package com.pixels.orobackoup.View.Prenda;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.itextpdf.text.pdf.parser.Line;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosPrenda;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.InicioSesion.AlertDialog.AlertCarga;
import com.pixels.orobackoup.ViewModel.Prenda.DatosPrendaViewModel;

import java.util.List;

public class PrendaView extends AppCompatActivity {
    public LinearLayout LayoutF,LayoutG,LayoutL,LayoutLL,LayoutE,LayoutP;
    public TextInputEditText nombreP;
    String CodigoP;
    public CardView mostrarF,mostrarG,mostrarL,mostrarLL,mostrarE,mostrarP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenda_view);
        CodigoP=getIntent().getExtras().getString("codigo");
        LayoutF=findViewById(R.id.LayoutF);
        LayoutG=findViewById(R.id.LayoutG);
        LayoutL=findViewById(R.id.LayoutL);
        LayoutLL=findViewById(R.id.Layoutll);
        LayoutE=findViewById(R.id.LayoutE);
        LayoutP=findViewById(R.id.LayoutP);
        nombreP=findViewById(R.id.nomprenda);
        mostrarF=findViewById(R.id.mostrarF);
        mostrarG=findViewById(R.id.mostrarG);
        mostrarL=findViewById(R.id.mostrarL);
        mostrarLL=findViewById(R.id.mostrarLL);
        mostrarE=findViewById(R.id.mostrarE);
        mostrarP=findViewById(R.id.mostrarP);
        LayoutF.setVisibility(LinearLayout.GONE);
        LayoutG.setVisibility(LinearLayout.GONE);
        LayoutL.setVisibility(LinearLayout.GONE);
        LayoutLL.setVisibility(LinearLayout.GONE);
        LayoutE.setVisibility(LinearLayout.GONE);
        LayoutP.setVisibility(LinearLayout.GONE);
        mostrarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutF.getVisibility()== LinearLayout.GONE){
                    LayoutF.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutF.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutG.getVisibility()== LinearLayout.GONE){
                    LayoutG.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutG.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutL.getVisibility()== LinearLayout.GONE){
                    LayoutL.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutL.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutLL.getVisibility()== LinearLayout.GONE){
                    LayoutLL.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutLL.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutE.getVisibility()== LinearLayout.GONE){
                    LayoutE.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutE.setVisibility(LinearLayout.GONE);
                }
            }
        });
        mostrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LayoutP.getVisibility()== LinearLayout.GONE){
                    LayoutP.setVisibility(LinearLayout.VISIBLE);
                }else{
                    LayoutP.setVisibility(LinearLayout.GONE);
                }
            }
        });
        AlertCarga carga =new AlertCarga(PrendaView.this);
        DatosPrendaViewModel prendaViewModel= ViewModelProviders.of(PrendaView.this).get(DatosPrendaViewModel.class);
        prendaViewModel.reset();
        carga.Cargar();
        prendaViewModel.nombreprenda(PrendaView.this,CodigoP);
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("0nohayfalse")){
                    Toast.makeText(PrendaView.this, "Error en la conexion de la base de datos", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    nombreP.setText(s);
                    nombreP.setEnabled(false);
                    prendaViewModel.DatosdePrendas(PrendaView.this,CodigoP);
                    Observer<List<DatosPrenda>> observer1=new Observer<List<DatosPrenda>>() {
                        @Override
                        public void onChanged(List<DatosPrenda> datosPrendas) {
                            carga.setInicio(1);
                            carga.Cerrar();
                            Toast.makeText(PrendaView.this, "datos:"+datosPrendas.size(), Toast.LENGTH_SHORT).show();
                        }
                    };
                    prendaViewModel.getResultadoDatos().observe(PrendaView.this,observer1);
                }
            }
        };
        prendaViewModel.getResultado().observe(PrendaView.this,observer);
        nombreP.setEnabled(false);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(carga.getInicio()==0){
                    Toast.makeText(PrendaView.this, "Error no hay conexion", Toast.LENGTH_LONG).show();
                    carga.Cerrar();
                    finish();
                }
            }
        },12000);

    }
}