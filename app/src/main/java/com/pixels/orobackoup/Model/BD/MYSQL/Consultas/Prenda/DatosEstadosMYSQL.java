package com.pixels.orobackoup.Model.BD.MYSQL.Consultas.Prenda;

import android.content.Context;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.pixels.orobackoup.Model.BD.MYSQL.Conexion;
import com.pixels.orobackoup.ViewModel.Prenda.DatosEstadosViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatosEstadosMYSQL extends Conexion {
    private int CodigoP;
    private String TipoEstado;
    private float Peso_inicial;
    private float Peso_final;
    private byte[] Foto;
    private boolean verficar=true;
    private boolean verificarE=false;
    private DatosEstadosViewModel ViewModel;
    private String Fecha="false";
    public DatosEstadosMYSQL(Context context,String tipoEstado,int codigoP,float peso_inicial,float peso_final, byte[] foto, DatosEstadosViewModel viewModel) {
        super(context);
        this.TipoEstado=tipoEstado;
        this.CodigoP=codigoP;
        this.Peso_inicial=peso_inicial;
        this.Peso_final=peso_final;
        this.Foto=foto;
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
                String Sql="INSERT INTO "+TipoEstado+" (codigoprenda, pesoinicial, pesofinal, foto) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, CodigoP);
                stmt.setFloat(2, Peso_inicial);
                stmt.setFloat(3, Peso_final);
                stmt.setBytes(4, Foto);
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                int codigopp=0;
                if (generatedKeys.next()) {
                    int codigoGenerado = generatedKeys.getInt(1); // El código generado
                    codigopp=codigoGenerado;
                    String SqlF = "SELECT Fecha FROM "+TipoEstado+" WHERE codigo = ?";
                    PreparedStatement stmtF = (PreparedStatement) connection.prepareStatement(SqlF);
                    stmtF.setInt(1, codigopp);
                    ResultSet rs = stmtF.executeQuery();
                    if (rs.next()) {
                        Fecha = rs.getString("Fecha"); // Recupera los bytes de la imagen
                    }
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
        ViewModel.datosman.setValue(Fecha);
    }
}
