package com.pixels.orobackoup.View.TermoCupla;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.R;
import com.pixels.orobackoup.View.TermoCupla.GraficasFragment.GraficaLineaTR;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.VerificarWSViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TermoCupla extends AppCompatActivity {

    public VerificarWSViewModel ConexionWS;
    public Button Start,Stop;
    public LinearLayout LayoutF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_cupla);
        Start=(Button) findViewById(R.id.start);
        Stop=(Button) findViewById(R.id.stop);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WSConnect();
            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WSStop();
            }
        });
        LayoutF=findViewById(R.id.LayoutF);

        //
    }
    public void WSConnect(){
        ConexionWS= ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta="{\"type\": \"mobile_app\"}";
        //Toast.makeText(this, ""+Consulta, Toast.LENGTH_SHORT).show();
        ConexionWS.EjecutarSession(TermoCupla.this,Consulta,"S");
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(TermoCupla.this,"Session:"+s,Toast.LENGTH_LONG).show();
                try{
                    GraficaLineaTR graficaColumna=new GraficaLineaTR(new ArrayList<TermoCalor>());
                    Handler handler = new Handler();
                    int delay = 500; // Tiempo en milisegundos entre cada fragment
                    handler.postDelayed(() -> {
                        getSupportFragmentManager().beginTransaction().replace(R.id.containerF, graficaColumna).commitAllowingStateLoss();
                    }, delay);
                }catch (Exception e){

                }
            }
        };
        ConexionWS.getResultado().observe(TermoCupla.this,observer);

    }
    public void WSStop(){
        ConexionWS= ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta="{\"type\": \"mobile_app\"}";
        //Toast.makeText(this, ""+Consulta, Toast.LENGTH_SHORT).show();
        ConexionWS.EjecutarSession(TermoCupla.this,Consulta,"N");
    }
}