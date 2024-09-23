package com.pixels.orobackoup.Model.DatosEncapsulados;

public class ListaPrenda {
    private int Codigo;
    private String Nombre,Estado,Fecha;

    public ListaPrenda(int codigo, String nombre,String estado, String fecha) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.Estado=estado;
        this.Fecha = fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
