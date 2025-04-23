package com.pixels.orobackoup.View.TermoCupla;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


import com.pixels.orobackoup.R;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.VerificarWSViewModel;

public class TermoCupla extends AppCompatActivity {

    public VerificarWSViewModel ConexionWS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_cupla);
        WSConnect();
    }
    public void WSConnect(){
        ConexionWS= ViewModelProviders.of(TermoCupla.this).get(VerificarWSViewModel.class);
        ConexionWS.reset();
        String Consulta="{\"type\": \"mobile_app\"}";
        //Toast.makeText(this, ""+Consulta, Toast.LENGTH_SHORT).show();
        ConexionWS.EjecutarSession(TermoCupla.this,Consulta);
    }
}