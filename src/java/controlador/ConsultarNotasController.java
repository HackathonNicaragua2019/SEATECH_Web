

package controlador;

import clases.ConexionOracle;
import clases.Notas;
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
@Named(value = "consultarNotasController")
@ViewScoped
public class ConsultarNotasController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int suma;
    private int idc;
    private int idm;
    private int idg;
    private int vigencia;
    private ArrayList<SelectItem> lcarreras = new ArrayList<>();
    private ArrayList<SelectItem> lmodulos = new ArrayList<>();
    private ArrayList<SelectItem> lgrupos = new ArrayList<>();
    private ArrayList<SelectItem> lactividades = new ArrayList<>();
    private int idact;
    private Notas datos = new Notas();
    private ArrayList<Notas> lista = new ArrayList<>();
    private ArrayList<SelectItem> listaestudiantes = new ArrayList<>();
    private int ida;

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public ArrayList<SelectItem> getListaestudiantes() {
        return listaestudiantes;
    }

    public void setListaestudiantes(ArrayList<SelectItem> listaestudiantes) {
        this.listaestudiantes = listaestudiantes;
    }

    public Notas getDatos() {
        return datos;
    }

    public void setDatos(Notas datos) {
        this.datos = datos;
    }

    public ArrayList<Notas> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Notas> lista) {
        this.lista = lista;
    }

    public int getIdact() {
        return idact;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
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

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
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

    public ArrayList<SelectItem> getLactividades() {
        return lactividades;
    }

    public void setLactividades(ArrayList<SelectItem> lactividades) {
        this.lactividades = lactividades;
    }

    public ConsultarNotasController() {
        
    }
    
     public void vercarreras(){
        c.abrirConexion();
        lcarreras.clear();
        ResultSet r = c.consultar("select idc, nombre from carreras where vigencia = '"+getVigencia()+"' ");
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
    public void lestudiantes(){
        listaestudiantes.clear();
        c.abrirConexion();
        ResultSet r = c.consultar("select ida, nombre from alumnos where idg = "+getIdg()+"");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    SelectItem itemcurso = new SelectItem(r.getInt("ida"),r.getString("nombre"));
                    listaestudiantes.add(itemcurso);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void vernotas(){
        lista.clear();
        c.abrirConexion();
        ResultSet r = c.consultar("SELECT fecha,notas.idcr,ida,nota,notas.idact,criterios.NOMBRE FROM notas INNER JOIN CRITERIOS ON criterios.IDCR = notas.idcr where notas.idact = "+getIdact()+" and notas.ida = "+getIda()+"");
        if(r!=null){
                           
                try {
                    while (r.next()) { 
                      datos = new Notas();
                      datos.setNombre(r.getString("nombre"));
                      datos.setCriterio(r.getString("fecha"));
                      datos.setNota(r.getInt("nota"));
                      suma += r.getInt("nota");
                      datos.setSuma(suma);
                      lista.add(datos);
                    }
                    r.close();
                }
                 catch (Exception e) {
                    e.printStackTrace();
                }
            }
        c.cerrarConexion();
    }
}

