package com.pixels.orobackoup.ViewModel.InicioSesion;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.InicioSesion.VerificacionUsuarioMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.Usuarios;

import java.util.List;

public class InicionSesionViewModel extends ViewModel {
    public MutableLiveData<List<Usuarios>> resultado;
    public InicionSesionViewModel(){
        this.resultado=new MutableLiveData<>();

    }
    public void reset(){
        this.resultado=new MutableLiveData<>();

    }
    public LiveData<List<Usuarios>> getResultado(){
        return resultado;
    }
    public void verificarUsuario(Context context,String Usuario,String Contra){
        VerificacionUsuarioMYSQL BD=new VerificacionUsuarioMYSQL(context,Usuario,Contra,InicionSesionViewModel.this);
    }
}
