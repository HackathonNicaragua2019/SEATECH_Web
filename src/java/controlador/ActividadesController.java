
 
package controlador;


import clases.ConexionOracle;
import java.io.Serializable;
import java.sql.ResultSet;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
/**
 *
 * @author SERGIO C...
 */
@Named(value = "actividadesController")
@ViewScoped
public class ActividadesController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int ida;
    private String nombre;
    private int puntaje;
    private int idun;
    private String vigencia;
    private int semestre;
    private int idm;
    private int idc;
    private ArrayList<SelectItem> lcarrera = new ArrayList<>();
    private ArrayList<SelectItem> lmodulos = new ArrayList<>();
    private ArrayList<SelectItem> lunidades = new ArrayList<>();
    private int lectivo;
    private int calificada;
    private int idact;

    public int getIdact() {
        return idact;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }

    public int getCalificada() {
        return calificada;
    }

    public void setCalificada(int calificada) {
        this.calificada = calificada;
    }

    public int getLectivo() {
        return lectivo;
    }

    public void setLectivo(int lectivo) {
        this.lectivo = lectivo;
    }
    
    
    public ActividadesController() {
        
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getIdun() {
        return idun;
    }

    public void setIdun(int idun) {
        this.idun = idun;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
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

    public ArrayList<SelectItem> getLcarrera() {
        return lcarrera;
    }

    public void setLcarrera(ArrayList<SelectItem> lcarrera) {
        this.lcarrera = lcarrera;
    }

    public ArrayList<SelectItem> getLmodulos() {
        return lmodulos;
    }

    public void setLmodulos(ArrayList<SelectItem> lmodulos) {
        this.lmodulos = lmodulos;
    }

    public ArrayList<SelectItem> getLunidades() {
        return lunidades;
    }

    public void setLunidades(ArrayList<SelectItem> lunidades) {
        this.lunidades = lunidades;
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
        System.out.println("aqui todo bien peluche");
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
    
    public void verunidades(){
        c.abrirConexion();
        lunidades.clear();
        ResultSet r = c.consultar("select idun, nombre from unidades where idm = '"+getIdm()+"'");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idun"),r.getString("nombre"));
                    lunidades.add(itemcurso);
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
        ResultSet resultado = c.consultar("select ida from actividades");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("ida");
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
     
     ida = mayor+1;
        
        c.insertar("insert into actividades (ida, nombre, puntaje, idun, vigencia, semestre, idm) values ("+getIda()+", '"+getNombre()+"', "+getPuntaje()+", "+getIdun()+", "+getLectivo()+", "+getSemestre()+", "+getIdm()+")");
        if(calificada == 1){
            guardaractividad();
        }
        c.cerrarConexion(); 
    }
    
    private void guardaractividad(){
        int mayor = 0;
        int num;
        ResultSet resultado = c.consultar("select idact from ac_acalificar");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idact");
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
     
     idact = mayor+1;
     c.insertar("insert into ac_acalificar (idact, ida) values ("+getIdact()+", "+getIda()+")");
    }
}
