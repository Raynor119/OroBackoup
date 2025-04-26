package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.TermoCupla;

import android.content.Context;

import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.Sessiones;
import com.pixels.orobackoup.Model.DatosEncapsulados.TermoCalor;
import com.pixels.orobackoup.ViewModel.TermoCupla.WS.GraficaTiempoRealViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaSessiones extends Conexion {

    private boolean verficar;
    private boolean verificarE=false;
    private GraficaTiempoRealViewModel ViewModel;

    public List<Sessiones> ListaTiempoReal=new ArrayList<>();
    int conteo=0;


    public ListaSessiones(Context context) {
        super(context);
        //this.ViewModel=viewModel;
        //this.Session=session;

        this.Url="jdbc:mysql://"+"192.168.20.254"+"/"+"horno_monitoring";
        execute("");
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){
                    onCancelled();
                }else{
                    onCancelled("No se puede conectar a La Base Datos");
                    verificarE=true;
                }
            }
        },11000);
    }
}
