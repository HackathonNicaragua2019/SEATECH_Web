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
@Table(name = "ALUMNOS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Alumnos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDA", nullable = false, precision = 0, scale = -127)
    private BigDecimal ida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRE", nullable = false, length = 60)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CARNET", nullable = false, length = 50)
    private String carnet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 22)
    @Column(name = "ESTADO", nullable = false, length = 22)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDG", nullable = false)
    private BigInteger idg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ida", fetch = FetchType.LAZY)
    private List<Asistencias> asistenciasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ida", fetch = FetchType.LAZY)
    private List<Notas> notasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ida", fetch = FetchType.LAZY)
    private List<Notasfinales> notasfinalesList;

    public Alumnos() {
    }

    public Alumnos(BigDecimal ida) {
        this.ida = ida;
    }

    public Alumnos(BigDecimal ida, String nombre, String carnet, String estado, BigInteger idg) {
        this.ida = ida;
        this.nombre = nombre;
        this.carnet = carnet;
        this.estado = estado;
        this.idg = idg;
    }

    public BigDecimal getIda() {
        return ida;
    }

    public void setIda(BigDecimal ida) {
        this.ida = ida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public BigInteger getIdg() {
        return idg;
    }

    public void setIdg(BigInteger idg) {
        this.idg = idg;
    }

    @XmlTransient
    public List<Asistencias> getAsistenciasList() {
        return asistenciasList;
    }

    public void setAsistenciasList(List<Asistencias> asistenciasList) {
        this.asistenciasList = asistenciasList;
    }

    @XmlTransient
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    @XmlTransient
    public List<Notasfinales> getNotasfinalesList() {
        return notasfinalesList;
    }

    public void setNotasfinalesList(List<Notasfinales> notasfinalesList) {
        this.notasfinalesList = notasfinalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ida != null ? ida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumnos)) {
            return false;
        }
        Alumnos other = (Alumnos) object;
        if ((this.ida == null && other.ida != null) || (this.ida != null && !this.ida.equals(other.ida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Alumnos[ ida=" + ida + " ]";
    }
    
}
