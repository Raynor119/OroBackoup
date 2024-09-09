package com.pixels.orobackoup.Model.DatosEncapsulados;

public class MenuLista {
    private String Titulo;
    private int Id;

    public MenuLista(int id,String titulo) {
        this.Titulo = titulo;
        this.Id = id;
    }

    public String getTitulo() {
        return this.Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
