package com.pixels.orobackoup.Model.DatosEncapsulados;

public class DatosPrenda {
    private int Codigo;
    private String TipoEstado;
    private float Pesoinicial,Pesofinal;
    private byte[] Foto;
    private byte[] Fotov2;
    private String Fecha;

    public DatosPrenda(int codigo, String tipoEstado, float pesoinicial, float pesofinal, byte[] foto,byte[] fotov2, String fecha) {
        this.Codigo = codigo;
        this.TipoEstado = tipoEstado;
        this.Pesoinicial = pesoinicial;
        this.Pesofinal = pesofinal;
        this.Foto = foto;
        this.Fotov2 = fotov2;
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

    public byte[] getFotov2() {
        return Fotov2;
    }

    public void setFotov2(byte[] fotov2) {
        Fotov2 = fotov2;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
