package com.pixels.orobackoup.Model.BD.MYSQL.Consultas;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.ViewModel.FundicionViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class FundicionFotoMYSQL extends Conexion {

    private boolean verficar;
    private boolean verificarE=false;
    private FundicionViewModel ViewModel;
    private int Codigodeprenda;
    byte[] imageBytes = null;
    public FundicionFotoMYSQL(Context context, FundicionViewModel viewModel, int codigodeprenda) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigodeprenda=codigodeprenda;
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
                String Sql = "SELECT foto FROM Fundicion WHERE codigoprenda = ?";
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                stmt.setInt(1, Codigodeprenda);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    imageBytes = rs.getBytes("foto"); // Recupera los bytes de la imagen
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
        ViewModel.resultadov2.setValue(imageBytes);
    }
}
