package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.DatosPrenda;
import com.pixels.orobackoup.ViewModel.Prenda.DatosPrendaViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatosdePrendaMYSQL extends Conexion {
    private boolean verficar;
    private boolean verificarE=false;
    private DatosPrendaViewModel ViewModel;
    private int Codigodeprenda;
    private List<DatosPrenda> DatosPren;
    public DatosdePrendaMYSQL(Context context,DatosPrendaViewModel viewModel,int codigodeprenda) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigodeprenda=codigodeprenda;
        DatosPren=new ArrayList<>();
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
                String Sql = "SELECT codigo,pesoinicial,pesofinal,foto,Fecha FROM Fundicion WHERE codigoprenda = ?";
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                stmt.setInt(1, Codigodeprenda);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    DatosPren.add(new DatosPrenda(rs.getInt("codigo"),"fundicion",rs.getFloat("pesoinicial"),rs.getFloat("pesofinal"),rs.getBytes("foto"),rs.getString("Fecha")));
                }
                String SqlETP = "SELECT codigo,pesoinicial,pesofinal,foto,Fecha FROM Electropulidobomba WHERE codigoprenda = ?";
                PreparedStatement stmtETP = (PreparedStatement) connection.prepareStatement(SqlETP);
                stmtETP.setInt(1, Codigodeprenda);
                rs = stmtETP.executeQuery();
                if (rs.next()) {
                    DatosPren.add(new DatosPrenda(rs.getInt("codigo"),"electro",rs.getFloat("pesoinicial"),rs.getFloat("pesofinal"),rs.getBytes("foto"),rs.getString("Fecha")));
                }
                String SqlLM = "SELECT codigo,pesoinicial,pesofinal,foto,Fecha FROM Limado WHERE codigoprenda = ?";
                PreparedStatement stmtLM = (PreparedStatement) connection.prepareStatement(SqlLM);
                stmtLM.setInt(1, Codigodeprenda);
                rs = stmtLM.executeQuery();
                if (rs.next()) {
                    DatosPren.add(new DatosPrenda(rs.getInt("codigo"),"limado",rs.getFloat("pesoinicial"),rs.getFloat("pesofinal"),rs.getBytes("foto"),rs.getString("Fecha")));
                }
                String SqlLJ = "SELECT codigo,pesoinicial,pesofinal,foto,Fecha FROM Lijado WHERE codigoprenda = ?";
                PreparedStatement stmtLJ = (PreparedStatement) connection.prepareStatement(SqlLJ);
                stmtLJ.setInt(1, Codigodeprenda);
                rs = stmtLJ.executeQuery();
                if (rs.next()) {
                    DatosPren.add(new DatosPrenda(rs.getInt("codigo"),"lijado",rs.getFloat("pesoinicial"),rs.getFloat("pesofinal"),rs.getBytes("foto"),rs.getString("Fecha")));
                }
                String SqlEG = "SELECT codigo,pesoinicial,pesofinal,foto,Fecha FROM Engaste WHERE codigoprenda = ?";
                PreparedStatement stmtLG = (PreparedStatement) connection.prepareStatement(SqlEG);
                stmtLG.setInt(1, Codigodeprenda);
                rs = stmtLG.executeQuery();
                if (rs.next()) {
                    DatosPren.add(new DatosPrenda(rs.getInt("codigo"),"engaste",rs.getFloat("pesoinicial"),rs.getFloat("pesofinal"),rs.getBytes("foto"),rs.getString("Fecha")));
                }
                String SqlP = "SELECT codigo,pesoinicial,pesofinal,foto,Fecha FROM Pulido WHERE codigoprenda = ?";
                PreparedStatement stmtP = (PreparedStatement) connection.prepareStatement(SqlP);
                stmtP.setInt(1, Codigodeprenda);
                rs = stmtP.executeQuery();
                if (rs.next()) {
                    DatosPren.add(new DatosPrenda(rs.getInt("codigo"),"pulido",rs.getFloat("pesoinicial"),rs.getFloat("pesofinal"),rs.getBytes("foto"),rs.getString("Fecha")));
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
        ViewModel.DatosdePrenda.setValue(DatosPren);
    }
}
