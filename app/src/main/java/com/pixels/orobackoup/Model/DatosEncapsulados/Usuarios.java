package com.pixels.orobackoup.Model.DatosEncapsulados;

public class Usuarios {
    private int Codigo;
    private String Usuario,TipoUsuario;

    public Usuarios(int codigo, String usuario, String tipoUsuario) {
        this.Codigo = codigo;
        this.Usuario = usuario;
        this.TipoUsuario = tipoUsuario;
    }

    public int getCodigo() {
        return this.Codigo;
    }

    public void setCodigo(int codigo) {
        this.Codigo = codigo;
    }

    public String getUsuario() {
        return this.Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getTipoUsuario() {
        return this.TipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.TipoUsuario = tipoUsuario;
    }
}
