package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.ListaPrendas;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.ListaPrenda;
import com.pixels.orobackoup.ViewModel.Prenda.ListaPrendas.Lista_prendasViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Lista_prendasMYSQL extends Conexion {
    private boolean verficar;
    private boolean verificarE=false;
    private Lista_prendasViewModel ViewModel;
    private int CodigoU;
    private String TipoU;
    private List<ListaPrenda> ListaPrendas;

    public Lista_prendasMYSQL(Context context,int codigoU,String tipoU,Lista_prendasViewModel viewModel) {
        super(context);
        this.ViewModel=viewModel;
        this.CodigoU=codigoU;
        this.TipoU=tipoU;
        ListaPrendas=null;
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
                if(TipoU.equals("A")){
                    String Sql = "SELECT codigo,nombre,estado,Fecha FROM Prendas";
                    PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                    ResultSet rs = stmt.executeQuery();
                    ListaPrendas=new ArrayList<>();
                    if (rs.next()) {
                        ListaPrendas.add(new ListaPrenda(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("estado"),rs.getString("Fecha")));
                    }
                }else{
                    String Sql = "SELECT codigo,nombre,estado,Fecha FROM Prendas WHERE codigousu = ? AND estado = 'N'";
                    PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql);
                    stmt.setInt(1, CodigoU);
                    ResultSet rs = stmt.executeQuery();
                    ListaPrendas=new ArrayList<>();
                    if (rs.next()) {
                        ListaPrendas.add(new ListaPrenda(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("estado"),rs.getString("Fecha")));
                    }
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
        ViewModel.listaprendas.setValue(ListaPrendas);
    }
}
