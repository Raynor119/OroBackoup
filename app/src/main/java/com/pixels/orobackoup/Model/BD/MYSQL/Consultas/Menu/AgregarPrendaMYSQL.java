package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Menu;

import android.content.Context;

import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.ViewModel.Menu.AgregarPrendaViewModel;

public class AgregarPrendaMYSQL extends Conexion {
    private boolean verficar;
    private boolean verificarE=false;
    private String CodigoU,NombreP;
    private AgregarPrendaViewModel ViewModel;
    public AgregarPrendaMYSQL(Context context,String codigoU,String nombreP,AgregarPrendaViewModel viewModel) {
        super(context);
        this.CodigoU=codigoU;
        this.NombreP=nombreP;
        this.ViewModel=ViewModel;
    }
}
