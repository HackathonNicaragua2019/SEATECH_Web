
package controlador;



import clases.Carreras;
import clases.ConexionOracle;
import clases.Notas;
import java.io.Serializable;
import java.sql.ResultSet;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.faces.model.SelectItem;

@Named(value = "notasController")
@ViewScoped
public class NotasController implements Serializable{
    ConexionOracle c = new ConexionOracle();
    private int idn;
    private int nota;
    private int idact;
    private String fecha;
    private Notas datos = new Notas();
    private ArrayList<SelectItem> listaestudiantes = new ArrayList<>();
    private ArrayList<Notas> listacriterios = new ArrayList<>();
    private ArrayList<SelectItem> lcarrera = new ArrayList<>();
    private ArrayList<SelectItem> lgrupos = new ArrayList<>();
    private ArrayList<SelectItem> lmodulos = new ArrayList<>();
    private ArrayList<SelectItem> lactividades = new ArrayList<>();
    private int vigencia;
    private int idc;
    private int idm;
    private int idg;
    private int idac;
    private int ida;
    
   
    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
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

    public int getIdac() {
        return idac;
    }

    public void setIdac(int idac) {
        this.idac = idac;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public int getIdn() {
        return idn;
    }

    public void setIdn(int idn) {
        this.idn = idn;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getIdact() {
        return idact;
    }

    public void setIdact(int idact) {
        this.idact = idact;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Notas getDatos() {
        return datos;
    }

    public void setDatos(Notas datos) {
        this.datos = datos;
    }

    public ArrayList<SelectItem> getListaestudiantes() {
        return listaestudiantes;
    }

    public void setListaestudiantes(ArrayList<SelectItem> listaestudiantes) {
        this.listaestudiantes = listaestudiantes;
    }

    public ArrayList<Notas> getListacriterios() {
        return listacriterios;
    }

    public void setListacriterios(ArrayList<Notas> listacriterios) {
        this.listacriterios = listacriterios;
    }

    public ArrayList<SelectItem> getLcarrera() {
        return lcarrera;
    }

    public void setLcarrera(ArrayList<SelectItem> lcarrera) {
        this.lcarrera = lcarrera;
    }

    public ArrayList<SelectItem> getLgrupos() {
        return lgrupos;
    }

    public void setLgrupos(ArrayList<SelectItem> lgrupos) {
        this.lgrupos = lgrupos;
    }

    public ArrayList<SelectItem> getLmodulos() {
        return lmodulos;
    }

    public void setLmodulos(ArrayList<SelectItem> lmodulos) {
        this.lmodulos = lmodulos;
    }

    public ArrayList<SelectItem> getLactividades() {
        return lactividades;
    }

    public void setLactividades(ArrayList<SelectItem> lactividades) {
        this.lactividades = lactividades;
    }

    public NotasController() {
        
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
    
    public void lcriterios(){
        listacriterios.clear();
        c.abrirConexion();
        ResultSet r = c.consultar(" SELECT idcr, nombre, puntuacion FROM CRITERIOS INNER JOIN AC_ACALIFICAR ON CRITERIOS.IDACT = AC_ACALIFICAR.IDACT WHERE ac_acalificar.IDACT = "+getIdact()+"");
        if (r!= null) {
            try {
                while (r.next()) {                    
                    datos = new Notas();
                    datos.setIdcr(r.getInt("idcr"));
                    datos.setCriterio(r.getString("nombre"));
                    datos.setNota(r.getInt("puntuacion"));
                    listacriterios.add(datos);
                }
                r.close();
            } catch (Exception e) {
               e.printStackTrace();
               c.cerrarConexion();
            }
        }
        c.cerrarConexion();
    }
    
    public void agregarnota(){
        
       c.abrirConexion();
       int mayor = 0;
        int num;
        c.abrirConexion();
        
        ResultSet resultado = c.consultar("select idn from notas ");       
         if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idn");
                    if(mayor > num )
                    mayor = mayor;
                    else
                    mayor = num;
                    
                }
                resultado.close();
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idn ");
                c.cerrarConexion();
            }
            idn = mayor + 1;
            fecha = cargarf();
       /* Iterator it = listacriterios.iterator();
        while(it.hasNext()){
            datos = (Notas)it.next();
            
         c.insertar("insert into notas (idn, fecha, idcr, ida,nota,idact) values ("+getIdn()+", '"+getFecha()+"', "+datos.getIdcr()+","+getIda()+", "+datos.getNota()+","+getIdact()+")");*/
        //}
        }
         c.cerrarConexion();
    }
    
    private String cargarf(){
        Calendar f = new GregorianCalendar();
                String fe = "";
                int año = f.get(Calendar.YEAR);
                int mes = f.get(Calendar.MONTH);
                int dia = f.get(Calendar.DAY_OF_MONTH);
                mes++;
                fe = dia + "/" + mes + "/" + año;
                return fe;
    }
}
