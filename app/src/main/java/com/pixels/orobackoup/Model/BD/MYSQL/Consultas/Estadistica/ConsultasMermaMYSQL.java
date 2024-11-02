package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Estadistica;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosColumn;
import com.pixels.orobackoup.ViewModel.Estadistica.GraficaBarrasDViewModel;
import com.pixels.orobackoup.ViewModel.Prenda.DatosPrendaViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultasMermaMYSQL extends Conexion {

    private boolean verficar;
    private boolean verificarE=false;
    private GraficaBarrasDViewModel ViewModel;
    private String Consulta;
    private List<DatosColumn> listaConsulta;

    public ConsultasMermaMYSQL(Context context, String consulta,GraficaBarrasDViewModel viewModel) {
        super(context);
        this.Consulta=consulta;
        this.ViewModel=viewModel;
        listaConsulta=new ArrayList<>();
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
                String Sql = Consulta;
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    listaConsulta.add(new DatosColumn(rs.getString(1),rs.getString(2),rs.getInt(3)));
                }
                return "";
            }
        }catch (Exception e){
            System.out.println("---------------------------------------------------------------------------"+e);
            return "Error al guardar los datos del producto en la Base de Datos ";
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
        ViewModel.resultado.setValue(listaConsulta);
    }
}
