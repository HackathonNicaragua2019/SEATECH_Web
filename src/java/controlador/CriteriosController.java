
package controlador;


import clases.Carreras;
import clases.ConexionOracle;
import java.io.Serializable;
import java.sql.ResultSet;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import javax.inject.Named;
/**
 *
 * @author SERGIO C...
 */
@Named(value = "criteriosController")
@ViewScoped
public class CriteriosController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int idcr;
    private int puntaje;
    private int idact;
    private String nombre;
    private int idm;
    private int idc;
    private int act;
    private int vigencia;
    private ArrayList<SelectItem> lcarrera = new ArrayList<>();
    private ArrayList<SelectItem> lmodulo = new ArrayList<>();
    private ArrayList<SelectItem> lactividades = new ArrayList<>();
    private Carreras datos = new Carreras();
    private ArrayList<Carreras> lcriterios = new ArrayList<>();

    public Carreras getDatos() {
        return datos;
    }

    public void setDatos(Carreras datos) {
        this.datos = datos;
    }

    public ArrayList<Carreras> getLcriterios() {
        return lcriterios;
    }

    public void setLcriterios(ArrayList<Carreras> lcriterios) {
        this.lcriterios = lcriterios;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public int getIdcr() {
        return idcr;
    }

    public void setIdcr(int idcr) {
        this.idcr = idcr;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getIdact() {
        return idact;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getAct() {
        return act;
    }

    public void setAct(int act) {
        this.act = act;
    }

    public ArrayList<SelectItem> getLcarrera() {
        return lcarrera;
    }

    public void setLcarrera(ArrayList<SelectItem> lcarrera) {
        this.lcarrera = lcarrera;
    }

    public ArrayList<SelectItem> getLmodulo() {
        return lmodulo;
    }

    public void setLmodulo(ArrayList<SelectItem> lmodulo) {
        this.lmodulo = lmodulo;
    }

    public ArrayList<SelectItem> getLactividades() {
        return lactividades;
    }

    public void setLactividades(ArrayList<SelectItem> lactividades) {
        this.lactividades = lactividades;
    }
    
    public CriteriosController() {
        
    }
    
    public void vercarreras(){
        c.abrirConexion();
        lcarrera.clear();
        ResultSet r = c.consultar("select idc, nombre from carreras where vigencia = '"+getVigencia()+"' ");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idc"),r.getString("nombre"));
                    lcarrera.add(itemcurso);
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
        lmodulo.clear();
        ResultSet r = c.consultar("select idm, nombre from modulos where idc = '"+getIdc()+"'");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idm"),r.getString("nombre"));
                    lmodulo.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void veractividades(){
        c.abrirConexion();
        lactividades.clear();
        ResultSet r = c.consultar(" SELECT ACTIVIDADES.ida,nombre,idact FROM actividades INNER JOIN AC_ACALIFICAR ON ac_acalificar.IDA = ACTIVIDADES.IDA WHERE idm = "+getIdm()+"");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idact"),r.getString("nombre"));
                    lactividades.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void guardar(){
        int mayor = 0;
        int num;
        c.abrirConexion();
        
        ResultSet resultado = c.consultar("select idcr from criterios order by idcr");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idcr");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idcr ");
                c.cerrarConexion();
            }
     }
     
     idcr = mayor+1;
        
        c.insertar("insert into criterios (idcr, nombre, puntuacion, idact) values ("+getIdcr()+", '"+getNombre()+"', "+getPuntaje()+", "+getIdact()+")");
        c.cerrarConexion();
    }
    
    public void consultar(){
        lcriterios.clear();
        c.abrirConexion();
        ResultSet r = c.consultar("select idcr,nombre,puntuacion from criterios where idact = "+getIdact()+"");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    datos = new Carreras();
                    datos.setIdc(r.getInt("idcr"));
                    datos.setNombre(r.getString("nombre"));
                    datos.setVigencia(r.getString("puntuacion"));
                    lcriterios.add(datos);
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
        c.insertar("delete criterios where idcr = "+datos.getIdc()+"");
        c.cerrarConexion();
    }
}
