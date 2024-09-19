package com.pixels.orobackoup.Model.DatosEncapsulados;

public class DatosPrenda {
    private int Codigo;
    private String TipoEstado;
    private float Pesoinicial,Pesofinal;
    private byte[] Foto;
    private String Fecha;

    public DatosPrenda(int codigo, String tipoEstado, float pesoinicial, float pesofinal, byte[] foto, String fecha) {
        this.Codigo = codigo;
        this.TipoEstado = tipoEstado;
        this.Pesoinicial = pesoinicial;
        this.Pesofinal = pesofinal;
        this.Foto = foto;
        this.Fecha = fecha;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getTipoEstado() {
        return TipoEstado;
    }

    public void setTipoEstado(String tipoEstado) {
        TipoEstado = tipoEstado;
    }

    public float getPesoinicial() {
        return Pesoinicial;
    }

    public void setPesoinicial(float pesoinicial) {
        Pesoinicial = pesoinicial;
    }

    public float getPesofinal() {
        return Pesofinal;
    }

    public void setPesofinal(float pesofinal) {
        Pesofinal = pesofinal;
    }

    public byte[] getFoto() {
        return Foto;
    }

    public void setFoto(byte[] foto) {
        Foto = foto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
