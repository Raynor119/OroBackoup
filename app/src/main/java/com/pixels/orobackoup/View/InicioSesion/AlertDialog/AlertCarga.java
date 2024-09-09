package com.pixels.orobackoup.View.InicioSesion.AlertDialog;


import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pixels.orobackoup.R;


public class AlertCarga {
    private AppCompatActivity CContext;
    public AlertDialog.Builder builder;
    public int Inicio=0;

    public AlertDialog dialog;
    public AlertCarga(AppCompatActivity context){
        this.CContext=context;
    }
    public void Cargar(){
        builder = new AlertDialog.Builder(CContext);
        LayoutInflater inflater= CContext.getLayoutInflater();
        View view=inflater.inflate(R.layout.carga, null);
        builder.setView(view);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }
    public void Cerrar(){
        dialog.cancel();
    }

    public int getInicio(){
        return Inicio;
    }

    public void setInicio(int inicio){
        this.Inicio=inicio;
    }
}