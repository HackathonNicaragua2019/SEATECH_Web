package controlador;

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
@Named(value = "alumnoscontroller")
@ViewScoped
public class AlumnosController implements Serializable {

    ConexionOracle c = new ConexionOracle();
    private int idg;
    private String nombre;
    private int idc;
    private int semestre;
    private String turno;
    private ArrayList<SelectItem> lcarreras = new ArrayList<>();
    private String vigencia;
    private String estudiante;
    private String carnet;
    private String estado;
    private ArrayList<SelectItem> lgrupos = new ArrayList<>();
    private int ida;

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public ArrayList<SelectItem> getLgrupos() {
        return lgrupos;
    }

    public void setLgrupos(ArrayList<SelectItem> lgrupos) {
        this.lgrupos = lgrupos;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public int getIdg() {
        return idg;
    }

    public void setIdg(int idg) {
        this.idg = idg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public ArrayList<SelectItem> getLcarreras() {
        return lcarreras;
    }

    public void setLcarreras(ArrayList<SelectItem> lcarreras) {
        this.lcarreras = lcarreras;
    }

    public AlumnosController() {

    }

    public void vercarreras() {
        c.abrirConexion();
        lcarreras.clear();
        ResultSet r = c.consultar("select idc, nombre from carreras where vigencia = '" + getVigencia() + "'");
        if (r != null) {
            try {
                while (r.next()) {
                    SelectItem itemcurso = new SelectItem(r.getInt("idc"), r.getString("nombre"));
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

    public void guardargrupo() {
        int mayor = 0;
        int num;
        c.abrirConexion();

        ResultSet resultado = c.consultar("select idg from grupos");
        if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("idg");
                    if (mayor > num) {
                        mayor = mayor;
                    } else {
                        mayor = num;
                    }

                }
                resultado.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular idc ");
                c.cerrarConexion();
            }
        }

        idg = mayor + 1;

        c.insertar("insert into grupos (idg, nombre, semestre, idc, turno) values (" + getIdg() + ", '" + getNombre() + "', " + getSemestre() + ", " + getIdc() + ", '" + getTurno() + "')");
        c.cerrarConexion();

    }

    public void vergrupos() {
        c.abrirConexion();
        lgrupos.clear();
        ResultSet r = c.consultar("select idg, nombre from grupos where idc = '" + getIdc() + "'");
        if (r != null) {
            try {
                while (r.next()) {
                    SelectItem itemcurso = new SelectItem(r.getInt("idg"), r.getString("nombre"));
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

    public void guardarestudiantes() {
        int mayor = 0;
        int num;
        c.abrirConexion();

        ResultSet resultado = c.consultar("select ida from alumnos");
        if (resultado != null) {
            try {
                while (resultado.next()) {
                    num = resultado.getInt("ida");
                    if (mayor > num) {
                        mayor = mayor;
                    } else {
                        mayor = num;
                    }

                }
                resultado.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR .. en  calcular ida ");
                c.cerrarConexion();
            }
        }

        ida = mayor + 1;
        estado= "Activo";
        c.insertar("insert into alumnos (ida, nombre, carnet, estado, idg) values (" + getIda() + ", '" + getEstudiante() + "', '" + getCarnet() + "', '" + getEstado() + "', " + getIdg() + ")");
        c.cerrarConexion();
    }
}
