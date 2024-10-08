package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Menu;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.ViewModel.Menu.AgregarPrendaViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class AgregarPrendaMYSQL extends Conexion {
    private boolean verficar=true;
    private boolean verificarE=false;
    private String CodigoU,NombreP;
    private int CodigoP=0;
    private AgregarPrendaViewModel ViewModel;
    public AgregarPrendaMYSQL(Context context,String codigoU,String nombreP,AgregarPrendaViewModel viewModel) {
        super(context);
        this.CodigoU=codigoU;
        this.NombreP=nombreP;
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
                String Sql="INSERT INTO Prendas (codigousu, nombre) VALUES (?, ?)";
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, Integer.parseInt(CodigoU));
                stmt.setString(2, NombreP);
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int codigoGenerado = generatedKeys.getInt(1); // El código generado
                    CodigoP=codigoGenerado;
                }
                return "";
            }
        }catch (Exception e){
            return "Error al guardar los datos del producto en la Base de Datos e:"+e;
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
            verficar=true;
            //Toast.makeText(Context, "codigo generado:"+CodigoP, Toast.LENGTH_SHORT).show();
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
        ViewModel.resultado.setValue(CodigoP);
    }
}
