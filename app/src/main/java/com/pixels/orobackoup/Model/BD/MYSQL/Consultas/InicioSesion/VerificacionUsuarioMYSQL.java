package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.InicioSesion;

import android.content.Context;
import android.widget.Toast;

import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.ViewModel.InicioSesion.InicionSesionViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VerificacionUsuarioMYSQL extends Conexion {

    private String Usuario,Contra;
    private boolean verficar;
    private boolean verificarE=false;
    private InicionSesionViewModel ViewModel;

    public VerificacionUsuarioMYSQL(Context context,String usuario,String contra,InicionSesionViewModel viewModel) {
        super(context);
        this.Usuario=usuario;
        this.Contra=contra;
        this.ViewModel=viewModel;
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
                ResultSet rs = st.executeQuery("SELECT * FROM Producto");
                while (rs.next()) {

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
                Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue("");
    }
}
