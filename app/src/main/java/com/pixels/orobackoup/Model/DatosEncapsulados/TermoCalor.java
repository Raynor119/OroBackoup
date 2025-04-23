package com.pixels.orobackoup.Model.DatosEncapsulados;

public class TermoCalor {
    private int Id,Session;
    private String Tiempo;
    private float Temperatura;

    public TermoCalor(int id, int session, String tiempo, float temperatura) {
        Id = id;
        Session = session;
        Tiempo = tiempo;
        Temperatura = temperatura;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getSession() {
        return Session;
    }

    public void setSession(int session) {
        Session = session;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }

    public float getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(float temperatura) {
        Temperatura = temperatura;
    }
}
