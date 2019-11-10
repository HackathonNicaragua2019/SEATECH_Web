
package controlador;


import clases.Carreras;
import clases.ConexionOracle;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author SERGIO C...
 */
@Named(value = "unidadesController")
@ViewScoped
public class UnidadesController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int idu;
    private String nombre;
    private int numero;
    private int ponderacion;
    private int idm;
    private int idc;
    private ArrayList<SelectItem> lcarreras = new ArrayList<>();
    ArrayList<SelectItem> lmodulos = new ArrayList<>();
    private String vigencia;
    private Carreras datos = new Carreras();
    
    public UnidadesController() {
        
    }
    
    public Carreras getDatos() {
        return datos;
    }

    public void setDatos(Carreras datos) {
        this.datos = datos;
    }
    private ArrayList<Carreras> lunidad = new ArrayList<>();
    

    public ArrayList<Carreras> getLunidad() {
        return lunidad;
    }

    public void setLunidad(ArrayList<Carreras> lunidad) {
        this.lunidad = lunidad;
    }
    
     

    public ArrayList<SelectItem> getLcarreras() {
        return lcarreras;
    }

    public void setLcarreras(ArrayList<SelectItem> lcarreras) {
        this.lcarreras = lcarreras;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
    

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public ArrayList<SelectItem> getLmodulos() {
        return lmodulos;
    }

    public void setLmodulos(ArrayList<SelectItem> lmodulos) {
        this.lmodulos = lmodulos;
    }
    
    public void vercarreras(){
        c.abrirConexion();
        lcarreras.clear();
        ResultSet r = c.consultar("select idc, nombre from carreras where vigencia = '"+getVigencia()+"'");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idc"),r.getString("nombre"));
                    lcarreras.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void vermodulos(){
        c.abrirConexion();
        lmodulos.clear();
        ResultSet r = c.consultar("select idm, nombre from modulos where idc = '"+getIdc()+"'");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idm"),r.getString("nombre"));
                    lmodulos.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void agregar(){
        int mayor = 0;
        int num;
        c.abrirConexion();
        ResultSet resultado = c.consultar("select idun from unidades");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idun");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idun ");
                c.cerrarConexion();
            }
     }
     
     idu = mayor+1;
        
        c.insertar("insert into unidades (idun, nombre, numero, ponderacion, idm) values ("+getIdu()+", '"+getNombre()+"', "+getNumero()+", "+getPonderacion()+", "+getIdm()+")");
        c.cerrarConexion(); 
    }
    
    public void consultar(){
        lunidad.clear();
        c.abrirConexion();
        ResultSet r = c.consultar("select idun,nombre,numero,ponderacion from unidades where idm = "+getIdm()+"");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    datos = new Carreras();
                    datos.setIdc(r.getInt("idun"));
                    datos.setNombre(r.getString("nombre"));
                    datos.setVigencia(r.getString("numero"));
                    datos.setSemestre(r.getInt("ponderacion"));
                    lunidad.add(datos);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void eliminar(){
        c.abrirConexion();
        c.insertar("delete unidades where idun = "+datos.getIdc()+"");
        c.cerrarConexion();
        
    }
            
}
