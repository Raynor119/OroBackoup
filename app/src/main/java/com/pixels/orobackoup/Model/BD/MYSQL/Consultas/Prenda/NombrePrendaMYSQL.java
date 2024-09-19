package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.ViewModel.Prenda.DatosPrendaViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class NombrePrendaMYSQL extends Conexion {
    private boolean verficar;
    private boolean verificarE=false;
    private DatosPrendaViewModel ViewModel;
    private int Codigodeprenda;
    private String NombrePrenda;
    public NombrePrendaMYSQL(Context context,DatosPrendaViewModel viewModel,int codigodeprenda) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigodeprenda=codigodeprenda;
        NombrePrenda="0nohayfalse";
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
                String Sql = "SELECT nombre FROM Prendas WHERE codigo = ?";
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                stmt.setInt(1, Codigodeprenda);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    NombrePrenda = rs.getString("nombre"); // Recupera los bytes de la imagen
                }
                return "";
            }
        }catch (Exception e){
            return "Error al guardar los datos del producto en la Base de Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
            verficar=true;
            ConsultaBaseDatos();
        }else {
            verficar=false;
            ConsultaBaseDatos();
            if(result.equals("Error en la conexion")){

            }else{
                Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void ConsultaBaseDatos() {
        ViewModel.Nombre.setValue(NombrePrenda);
    }
}
