package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.InicioSesion;

import android.content.Context;
import android.widget.Toast;

import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.Usuarios;
import com.pixels.orobackoup.ViewModel.InicioSesion.InicionSesionViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerificacionUsuarioMYSQL extends Conexion {

    private String UUsuario,CContra;
    private boolean verficar;
    private boolean verificarE=false;
    private InicionSesionViewModel ViewModel;
    private List<Usuarios> datosusuario;

    public VerificacionUsuarioMYSQL(Context context,String usuario,String contra,InicionSesionViewModel viewModel) {
        super(context);
        this.UUsuario=usuario;
        this.CContra=contra;
        this.ViewModel=viewModel;
        datosusuario=new ArrayList<>();
        execute("");
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){
                    onCancelled();
                }else{
                    onCancelled("No se puede conectar a La Base Datos");
                    verificarE=true;
                }
            }
        },11000);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            if(verificarE){
                return "Error en la conexion";
            }else{
                Statement st = connection.createStatement();
                ResultSet rsc1 = st.executeQuery("SELECT COUNT(codigo) FROM Usuarios where usuario='"+UUsuario+"' AND contra='"+CContra+"' ");
                int verficacion=0;
                while (rsc1.next()) {
                    verficacion=rsc1.getInt(1);
                }
                if(verficacion>0){
                    ResultSet rs = st.executeQuery("SELECT codigo,usuario,tipousuario FROM Usuarios where usuario='"+UUsuario+"' AND contra='"+CContra+"' ");
                    while (rs.next()) {
                        datosusuario.add(new Usuarios(rs.getInt(1),rs.getString(2),rs.getString(3)));
                    }
                }else{
                    datosusuario.add(new Usuarios(0,"",""));
                }
                return "";
            }
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            if(result.equals("Error en la conexion")){

            }else{
                //Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(datosusuario);
    }
}
