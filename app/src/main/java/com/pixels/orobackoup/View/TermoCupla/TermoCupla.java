package com.pixels.orobackoup.View.TermoCupla;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


import com.pixels.orobackoup.R;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.VerificarWSViewModel;

public class TermoCupla extends AppCompatActivity {

    public VerificarWSViewModel ConexionWS;
    public Button Start,Stop;
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
        //
    }
    public void WSConnect(){
        ConexionWS= ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta="{\"type\": \"mobile_app\"}";
        //Toast.makeText(this, ""+Consulta, Toast.LENGTH_SHORT).show();
        ConexionWS.EjecutarSession(TermoCupla.this,Consulta,"S");
    }
    public void WSStop(){
        ConexionWS= ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta="{\"type\": \"mobile_app\"}";
        //Toast.makeText(this, ""+Consulta, Toast.LENGTH_SHORT).show();
        ConexionWS.EjecutarSession(TermoCupla.this,Consulta,"N");
    }
}