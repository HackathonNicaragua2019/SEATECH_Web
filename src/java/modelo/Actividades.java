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
@Table(name = "ACTIVIDADES", catalog = "", schema = "ENAH")
@XmlRootElement
public class Actividades implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDA", nullable = false, precision = 0, scale = -127)
    private BigDecimal ida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOMBRE", nullable = false, length = 250)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUNTAJE", nullable = false)
    private BigInteger puntaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUN", nullable = false)
    private BigInteger idun;
    @Size(max = 4)
    @Column(name = "CODIGO", length = 4)
    private String codigo;
    @Column(name = "SEMESTRE")
    private BigInteger semestre;
    @Column(name = "VIGENCIA")
    private BigInteger vigencia;
    @JoinColumn(name = "IDM", referencedColumnName = "IDM", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulos idm;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ida", fetch = FetchType.LAZY)
    private List<AcAcalificar> acAcalificarList;

    public Actividades() {
    }

    public Actividades(BigDecimal ida) {
        this.ida = ida;
    }

    public Actividades(BigDecimal ida, String nombre, BigInteger puntaje, BigInteger idun) {
        this.ida = ida;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.idun = idun;
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

    public BigInteger getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(BigInteger puntaje) {
        this.puntaje = puntaje;
    }

    public BigInteger getIdun() {
        return idun;
    }

    public void setIdun(BigInteger idun) {
        this.idun = idun;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigInteger getSemestre() {
        return semestre;
    }

    public void setSemestre(BigInteger semestre) {
        this.semestre = semestre;
    }

    public BigInteger getVigencia() {
        return vigencia;
    }

    public void setVigencia(BigInteger vigencia) {
        this.vigencia = vigencia;
    }

    public Modulos getIdm() {
        return idm;
    }

    public void setIdm(Modulos idm) {
        this.idm = idm;
    }

    @XmlTransient
    public List<AcAcalificar> getAcAcalificarList() {
        return acAcalificarList;
    }

    public void setAcAcalificarList(List<AcAcalificar> acAcalificarList) {
        this.acAcalificarList = acAcalificarList;
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
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.ida == null && other.ida != null) || (this.ida != null && !this.ida.equals(other.ida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Actividades[ ida=" + ida + " ]";
    }
    
}
