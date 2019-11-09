

package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author SERGIO C..
 */
public class ConexionOracle {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    // **** Conexion a oracle desde el constructor ******
    public ConexionOracle() {

    }

    public void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
            conexion = DriverManager.getConnection(BaseDeDatos, "ENAH", "enah2019");
            if (conexion != null) {
                System.out.println("Conexion exitosa a esquema Enah");

            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            conexion.close();
            System.out.print("Conexion Cerreda");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertar(String sql) {
        try {
            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            sentencia.executeUpdate(sql);
            getConexion().setAutoCommit(true);
            sentencia.close();
            mjsbien();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("ERROR ... ... al Insertar ... clase conexionOracle ");
            cerrarConexion();
            mjs();
            return false;

        }

        return true;

    }

    public ResultSet consultar(String sql) {

        ResultSet resultado = null;
        try {

            Statement sentencia;
            sentencia = getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            resultado = sentencia.executeQuery(sql);
            getConexion().setAutoCommit(true);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("Error ... al consultar .... clase conexionOracle");
            System.out.print(" ");
            cerrarConexion();
            mjs();
            return null;

        }

        return resultado;

    }

    private void mjs() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Ocurrio un error y no se ejecuto lo que Deseaba ", "State is = ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void mjsbien() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Datos guardados correctamente, ", "State is = ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

}

