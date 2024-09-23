package com.pixels.orobackoup.ViewModel.Prenda;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.DatosdePrendaMYSQL;
import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.NombrePrendaMYSQL;
import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.TerminacionPrendaMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosPrenda;

import java.util.List;

public class DatosPrendaViewModel extends ViewModel {
    public MutableLiveData<String> Nombre;
    public MutableLiveData<Boolean> VerificacionT;
    public MutableLiveData<List<DatosPrenda>> DatosdePrenda;
    public  DatosPrendaViewModel(){
        this.Nombre=new MutableLiveData<>();
        this.DatosdePrenda=new MutableLiveData<>();
        this.VerificacionT=new MutableLiveData<>();
    }
    public void reset(){
        this.Nombre=new MutableLiveData<>();
        this.DatosdePrenda=new MutableLiveData<>();
        this.VerificacionT=new MutableLiveData<>();
    }
    public LiveData<String> getResultado(){
        return Nombre;
    }
    public LiveData<List<DatosPrenda>> getResultadoDatos(){
        return DatosdePrenda;
    }
    public LiveData<Boolean> getResultadoTerminacion(){
        return VerificacionT;
    }
    public void nombreprenda(Context context, String CodigoP){
        NombrePrendaMYSQL BD=new NombrePrendaMYSQL(context,DatosPrendaViewModel.this,Integer.parseInt(CodigoP));
    }
    public void DatosdePrendas(Context context, String CodigoP){
        DatosdePrendaMYSQL BD=new DatosdePrendaMYSQL(context,DatosPrendaViewModel.this,Integer.parseInt(CodigoP));
    }
    public void terminaPrenda(Context context, String CodigoP){
        TerminacionPrendaMYSQL BD=new TerminacionPrendaMYSQL(context,DatosPrendaViewModel.this,Integer.parseInt(CodigoP));
    }
}