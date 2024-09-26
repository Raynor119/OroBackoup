package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda.ListaPrendas;

import android.content.Context;
import android.widget.Toast;

import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.Model.DatosEncapsulados.ListaPrenda;
import com.pixels.orobackoup.ViewModel.Prenda.ListaPrendas.Lista_prendasViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lista_prendasMYSQL extends Conexion {
    private boolean verficar;
    private boolean verificarE=false;
    private Lista_prendasViewModel ViewModel;
    private int CodigoU;
    private String TipoU;
    private List<ListaPrenda> ListaPrendas;
    int conteo=0;

    public Lista_prendasMYSQL(Context context,int codigoU,String tipoU,Lista_prendasViewModel viewModel) {
        super(context);
        this.ViewModel=viewModel;
        this.CodigoU=codigoU;
        this.TipoU=tipoU;
        ListaPrendas=new ArrayList<>();
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
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery("SELECT codigo,nombre,estado,Fecha FROM Prendas");
                    while (rs.next()) {
                        //conteo++;
                        ListaPrendas.add(new ListaPrenda(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                    }
                }else{
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery( "SELECT codigo,nombre,estado,Fecha FROM Prendas WHERE codigousu = "+CodigoU+" AND estado = 'N'");
                    while (rs.next()) {
                        //conteo++;
                        ListaPrendas.add(new ListaPrenda(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
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
            //Toast.makeText(Context, "El conteo es :"+conteo, Toast.LENGTH_SHORT).show();
            verficar=true;
            ConsultaBaseDatos();
        }else {
            verficar=false;
            ListaPrendas=null;
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
