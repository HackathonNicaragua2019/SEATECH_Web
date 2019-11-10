
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
@Named(value = "docenteController")
@ViewScoped
public class DocenteController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int idma;
    private String nombre;
    private String apellido;
    private String vigencia;
    private int idc;
    private int idm;
    private int idg;
    private ArrayList<SelectItem> lcarreras = new ArrayList<>();
    private ArrayList<SelectItem> lmodulos = new ArrayList<>();
    private ArrayList<SelectItem> lgrupos = new ArrayList<>();
    private ArrayList<SelectItem> lmaestros = new ArrayList<>();
    private int idas;

    public int getIdas() {
        return idas;
    }

    public void setIdas(int idas) {
        this.idas = idas;
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

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public int getIdg() {
        return idg;
    }

    public void setIdg(int idg) {
        this.idg = idg;
    }

    public ArrayList<SelectItem> getLcarreras() {
        return lcarreras;
    }

    public void setLcarreras(ArrayList<SelectItem> lcarreras) {
        this.lcarreras = lcarreras;
    }

    public ArrayList<SelectItem> getLmodulos() {
        return lmodulos;
    }

    public void setLmodulos(ArrayList<SelectItem> lmodulos) {
        this.lmodulos = lmodulos;
    }

    public ArrayList<SelectItem> getLgrupos() {
        return lgrupos;
    }

    public void setLgrupos(ArrayList<SelectItem> lgrupos) {
        this.lgrupos = lgrupos;
    }

    public ArrayList<SelectItem> getLmaestros() {
        return lmaestros;
    }

    public void setLmaestros(ArrayList<SelectItem> lmaestros) {
        this.lmaestros = lmaestros;
    }

    public int getIdma() {
        return idma;
    }

    public void setIdma(int idma) {
        this.idma = idma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

   
    public DocenteController() {
        String nom;
        c.abrirConexion();
        lmaestros.clear();
        ResultSet r = c.consultar("select idma, nombres, apellidos from maestros ");
        if (r!= null) {
            try {
                while (r.next()) { 
                    nom = r.getString("nombres") + " " + r.getString("apellidos");
                    SelectItem itemcurso = new SelectItem(r.getInt("idma"),nom);
                    lmaestros.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void agregar() {
        int mayor = 0;
        int num;
        c.abrirConexion();
        
        ResultSet resultado = c.consultar("select idma from maestros ");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idma");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idma ");
                c.cerrarConexion();
            }
     }
     
     idma = mayor+1;
        
        c.insertar("insert into maestros (idma, nombres, apellidos) values ("+getIdma()+", '"+getNombre()+"', '"+getApellido()+"')");
        c.cerrarConexion();
       
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
    
    public void vergrupos(){
        c.abrirConexion();
        lgrupos.clear();
        ResultSet r = c.consultar("select idg, nombre from grupos where idc = '"+getIdc()+"'");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("idg"),r.getString("nombre"));
                    lgrupos.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void asignardocente(){
        int mayor = 0;
        int num;
        c.abrirConexion();
        
        ResultSet resultado = c.consultar("select idasigdoc from asignacion_docentes ");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idasigdoc");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idasigdoc ");
                c.cerrarConexion();
            }
     }
     
     idas = mayor+1;
        
        c.insertar("insert into asignacion_docentes (idasigdoc, idma, idm, idg) values ("+getIdas()+", "+getIdma()+", "+getIdm()+", "+getIdg()+")");
        c.cerrarConexion();
    }
}
