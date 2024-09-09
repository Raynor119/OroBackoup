package com.pixels.orobackoup.Model.DatosEncapsulados;

public class MenuLista {
    private String Titulo,Icono;

    public MenuLista(String titulo, String icono) {
        this.Titulo = titulo;
        this.Icono = icono;
    }

    public String getTitulo() {
        return this.Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public String getIcono() {
        return this.Icono;
    }

    public void setIcono(String icono) {
        this.Icono = icono;
    }
}
