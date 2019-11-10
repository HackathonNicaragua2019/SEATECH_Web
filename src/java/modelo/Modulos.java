/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AULANO1
 */
@MappedSuperclass
@Table(name = "MODULOS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Modulos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDM", nullable = false, precision = 0, scale = -127)
    private BigDecimal idm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEMESTRE", nullable = false)
    private BigInteger semestre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORAS", nullable = false)
    private BigInteger horas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 22)
    @Column(name = "TIPO", nullable = false, length = 22)
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idm", fetch = FetchType.LAZY)
    private List<Unidades> unidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idm", fetch = FetchType.LAZY)
    private List<Asistencias> asistenciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idm", fetch = FetchType.LAZY)
    private List<Actividades> actividadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idm", fetch = FetchType.LAZY)
    private List<Notasfinales> notasfinalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idm", fetch = FetchType.LAZY)
    private List<AsignacionDocentes> asignacionDocentesList;
    @JoinColumn(name = "IDC", referencedColumnName = "IDC", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Carreras idc;

    public Modulos() {
    }

    public Modulos(BigDecimal idm) {
        this.idm = idm;
    }

    public Modulos(BigDecimal idm, String nombre, BigInteger semestre, BigInteger horas, String tipo) {
        this.idm = idm;
        this.nombre = nombre;
        this.semestre = semestre;
        this.horas = horas;
        this.tipo = tipo;
    }

    public BigDecimal getIdm() {
        return idm;
    }

    public void setIdm(BigDecimal idm) {
        this.idm = idm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getSemestre() {
        return semestre;
    }

    public void setSemestre(BigInteger semestre) {
        this.semestre = semestre;
    }

    public BigInteger getHoras() {
        return horas;
    }

    public void setHoras(BigInteger horas) {
        this.horas = horas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<Unidades> getUnidadesList() {
        return unidadesList;
    }

    public void setUnidadesList(List<Unidades> unidadesList) {
        this.unidadesList = unidadesList;
    }

    @XmlTransient
    public List<Asistencias> getAsistenciasList() {
        return asistenciasList;
    }

    public void setAsistenciasList(List<Asistencias> asistenciasList) {
        this.asistenciasList = asistenciasList;
    }

    @XmlTransient
    public List<Actividades> getActividadesList() {
        return actividadesList;
    }

    public void setActividadesList(List<Actividades> actividadesList) {
        this.actividadesList = actividadesList;
    }

    @XmlTransient
    public List<Notasfinales> getNotasfinalesList() {
        return notasfinalesList;
    }

    public void setNotasfinalesList(List<Notasfinales> notasfinalesList) {
        this.notasfinalesList = notasfinalesList;
    }

    @XmlTransient
    public List<AsignacionDocentes> getAsignacionDocentesList() {
        return asignacionDocentesList;
    }

    public void setAsignacionDocentesList(List<AsignacionDocentes> asignacionDocentesList) {
        this.asignacionDocentesList = asignacionDocentesList;
    }

    public Carreras getIdc() {
        return idc;
    }

    public void setIdc(Carreras idc) {
        this.idc = idc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idm != null ? idm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulos)) {
            return false;
        }
        Modulos other = (Modulos) object;
        if ((this.idm == null && other.idm != null) || (this.idm != null && !this.idm.equals(other.idm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Modulos[ idm=" + idm + " ]";
    }
    
}
