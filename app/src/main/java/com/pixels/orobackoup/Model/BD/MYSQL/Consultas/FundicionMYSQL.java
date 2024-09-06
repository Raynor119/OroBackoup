package com.pixels.orobackoup.Model.BD.MYSQL.Consultas;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.Fundicion;
import com.pixels.orobackoup.ViewModel.FundicionViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class FundicionMYSQL extends Conexion {

    private boolean verficar;
    private boolean verificarE=false;
    private FundicionViewModel ViewModel;
    private List<Fundicion> Datos;
    public FundicionMYSQL(Context context, FundicionViewModel viewModel, List<Fundicion> datos) {
        super(context);
        this.ViewModel=viewModel;
        this.Datos=datos;
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
                String Sql="INSERT INTO Fundicion (codigoprenda, pesoinicial, pesofinal, imagen) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                stmt.setInt(1, Datos.get(0).getCodigoprenda());
                stmt.setFloat(2, Datos.get(0).getPesoinicial());
                stmt.setFloat(3, Datos.get(0).getPesofinal());
                stmt.setBytes(4, Datos.get(0).getFoto()); // Seteando la imagen como array de bytes
                stmt.executeUpdate();
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
        ViewModel.resultado.setValue(verficar);
    }
}
