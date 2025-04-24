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
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.GraficaTiempoRealViewModel;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.VerificarWSViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TermoCupla extends AppCompatActivity {

    public VerificarWSViewModel ConexionWS;
    public Button Start,Stop;
    public LinearLayout LayoutF;

    private boolean isFetching = false;
    private Handler handler = new Handler();
    private Runnable dataFetchRunnable;
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
    public void WSConnect() {
        ConexionWS = ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta = "{\"type\": \"mobile_app\"}";
        ConexionWS.EjecutarSession(TermoCupla.this, Consulta, "S");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(TermoCupla.this, "Session:" + s, Toast.LENGTH_LONG).show();
                ConexionWS.getResultado().removeObserver(this); // eliminar el observer una vez usada la sesión

                isFetching = true;

                dataFetchRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if (!isFetching) return;

                        try {
                            GraficaTiempoRealViewModel MYSQLTR = ViewModelProviders.of(TermoCupla.this).get(GraficaTiempoRealViewModel.class);
                            MYSQLTR.reset();
                            MYSQLTR.GraficaTiempoReal(TermoCupla.this, s);

                            Observer<List<TermoCalor>> observer1 = new Observer<List<TermoCalor>>() {
                                @Override
                                public void onChanged(List<TermoCalor> termoCalors) {
                                    Toast.makeText(TermoCupla.this, "Tamanno de la lista "+termoCalors.size(), Toast.LENGTH_SHORT).show();
                                    GraficaLineaTR graficaColumna = new GraficaLineaTR(termoCalors);
                                    graficaColumna.Temperaturas = termoCalors;

                                    Handler uiHandler = new Handler();
                                    int delay = 500;
                                    uiHandler.postDelayed(() -> {
                                        getSupportFragmentManager().beginTransaction().replace(R.id.containerF, graficaColumna).commitAllowingStateLoss();
                                    }, delay);

                                    MYSQLTR.getResultado().removeObserver(this);
                                }
                            };

                            MYSQLTR.getResultado().observe(TermoCupla.this, observer1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        handler.postDelayed(this, 4000); // repetir cada 4 segundos
                    }
                };

                handler.post(dataFetchRunnable); // iniciar el primer ciclo
            }
        };

        ConexionWS.getResultado().observe(TermoCupla.this, observer);
    }
    public void WSStop() {
        ConexionWS = ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta = "{\"type\": \"mobile_app\"}";
        ConexionWS.EjecutarSession(TermoCupla.this, Consulta, "N");

        isFetching = false;
        handler.removeCallbacks(dataFetchRunnable);
    }
}