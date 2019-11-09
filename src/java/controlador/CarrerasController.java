
package controlador;

import clases.Carreras;
import clases.ConexionOracle;
import java.io.Serializable;
import java.sql.ResultSet;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
/**
 *
 * @author SERGIO C...
 */
@Named(value = "carrerasController")
@ViewScoped
public class CarrerasController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int idc;
    private String nombre;
    private String vig;
    private Carreras datos = new Carreras();
    private ArrayList<Carreras> lcarreras = new ArrayList<Carreras>();

    public Carreras getDatos() {
        return datos;
    }

    public void setDatos(Carreras datos) {
        this.datos = datos;
    }

    public ArrayList<Carreras> getLcarreras() {
        return lcarreras;
    }

    public void setLcarreras(ArrayList<Carreras> lcarreras) {
        this.lcarreras = lcarreras;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVig() {
        return vig;
    }

    public void setVig(String vig) {
        this.vig = vig;
    }
    
    public CarrerasController() {
        
    }
    
    public void agregar() {
        int mayor = 0;
        int num;
        c.abrirConexion();
        
        ResultSet resultado = c.consultar("select idc from carreras order by idc");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idc");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idc ");
                c.cerrarConexion();
            }
     }
     
     idc = mayor+1;
        
        c.insertar("insert into carreras (idc, nombre, vigencia) values ("+getIdc()+", '"+getNombre()+"', '"+getVig()+"')");
        c.cerrarConexion();
       
    }
    
    public void consultar(){
        lcarreras.clear();
        c.abrirConexion();
        ResultSet r = c.consultar("select * from carreras order by idc");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    datos = new Carreras();
                    datos.setIdc(r.getInt("idc"));
                    datos.setNombre(r.getString("nombre"));
                    datos.setVigencia(r.getString("vigencia"));
                    lcarreras.add(datos);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void editar(){
        c.abrirConexion();
        c.insertar("update carreras set nombre = '"+datos.getNombre()+"' where idc = "+datos.getIdc()+"");
        c.cerrarConexion();
    }
}
