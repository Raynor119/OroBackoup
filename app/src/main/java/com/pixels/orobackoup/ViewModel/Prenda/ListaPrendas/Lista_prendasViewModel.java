package com.pixels.orobackoup.ViewModel.Prenda.ListaPrendas;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.ListaPrendas.Lista_prendasMYSQL;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosPrenda;
import com.pixels.orobackoup.Model.DatosEncapsulados.ListaPrenda;

import java.util.List;

public class Lista_prendasViewModel extends ViewModel {

    public MutableLiveData<List<ListaPrenda>> listaprendas;

    public Lista_prendasViewModel(){
        this.listaprendas=new MutableLiveData<>();
    }
    public void reset(){
        this.listaprendas=new MutableLiveData<>();
    }
    public LiveData<List<ListaPrenda>> getResultadoPrendas(){
        return listaprendas;
    }
    public void listadeprendas(Context context,String tipoU,String codigoU){
        Lista_prendasMYSQL BD=new Lista_prendasMYSQL(context,Integer.parseInt(codigoU),tipoU,Lista_prendasViewModel.this);
    }

}
