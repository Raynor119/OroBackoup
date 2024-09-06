package com.pixels.orobackoup.Model.DatosEncapsulados;

public class Fundicion {
    private int Codigoprenda;
    private float Pesoinicial,Pesofinal;
    private byte[] Foto;

    public Fundicion(int codigoprenda, float pesoinicial, float pesofinal, byte[] foto) {
        this.Codigoprenda = codigoprenda;
        this.Pesoinicial = pesoinicial;
        this.Pesofinal = pesofinal;
        this.Foto = foto;
    }

    public int getCodigoprenda() {
        return this.Codigoprenda;
    }

    public void setCodigoprenda(int codigoprenda) {
        this.Codigoprenda = codigoprenda;
    }

    public float getPesoinicial() {
        return this.Pesoinicial;
    }

    public void setPesoinicial(float pesoinicial) {
        this.Pesoinicial = pesoinicial;
    }

    public float getPesofinal() {
        return this.Pesofinal;
    }

    public void setPesofinal(float pesofinal) {
        this.Pesofinal = pesofinal;
    }

    public byte[] getFoto() {
        return this.Foto;
    }

    public void setFoto(byte[] foto) {
        this.Foto = foto;
    }
}
