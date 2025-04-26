package com.pixels.orobackoup.Model.DatosEncapsulados;

public class Sessiones {
    private int id;
    private String Fecha;

    public Sessiones(int id, String fecha) {
        this.id = id;
        Fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
