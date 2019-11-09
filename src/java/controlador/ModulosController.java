
package controlador;


import clases.Carreras;
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
@Named(value = "modulosController")
@ViewScoped
public class ModulosController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    ArrayList<SelectItem> lcarreras = new ArrayList<>();
    Carreras datos = new Carreras();
    ArrayList<Carreras> lmodulos = new ArrayList<Carreras>();
    private int idm;
    private String nombre;
    private int horas;
    private int semestre;
    private String tipo;
    private int idc;
    private String vigencia;

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Carreras getDatos() {
        return datos;
    }

    public void setDatos(Carreras datos) {
        this.datos = datos;
    }

    public ArrayList<Carreras> getLmodulos() {
        return lmodulos;
    }

    public void setLmodulos(ArrayList<Carreras> lmodulos) {
        this.lmodulos = lmodulos;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }
    public ArrayList<SelectItem> getLcarreras() {
        return lcarreras;
    }

    public void setLcarreras(ArrayList<SelectItem> lcarreras) {
        this.lcarreras = lcarreras;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public ModulosController() {
        
    }
    
    public void agregar(){
       int mayor = 0;
        int num;
        c.abrirConexion();
        
        ResultSet resultado = c.consultar("select idm from modulos");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idm");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idm ");
                c.cerrarConexion();
            }
     }
     
     idm = mayor+1;
        
        c.insertar("insert into modulos (idm, nombre, tipo, horas, semestre, idc) values ("+getIdm()+", '"+getNombre()+"', '"+getTipo()+"', "+getHoras()+", "+getSemestre()+", "+getIdc()+")");
        c.cerrarConexion(); 
    }
    
    public void consultar(){
        lmodulos.clear();
        c.abrirConexion();
        ResultSet r = c.consultar("select idm,nombre,tipo,semestre,horas from modulos where idc = "+idc+" order by idm");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    datos = new Carreras();
                    datos.setIdm(r.getInt("idm"));
                    datos.setNombre(r.getString("nombre"));
                    datos.setTipo(r.getString("tipo"));
                    datos.setHoras(r.getInt("horas"));
                    datos.setSemestre(r.getInt("semestre"));
                    lmodulos.add(datos);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
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
    
    public void editarnombre(){
        
        c.abrirConexion();
        c.insertar("update modulos set nombre = '"+datos.getNombre()+"' where idm = "+datos.getIdm()+"");
        c.cerrarConexion();
     
    }
}
