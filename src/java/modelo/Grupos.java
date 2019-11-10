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
import javax.persistence.Lob;
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
@Table(name = "GRUPOS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Grupos implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDG", nullable = false, precision = 0, scale = -127)
    private BigDecimal idg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEMESTRE", nullable = false)
    private BigInteger semestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TURNO", nullable = false, length = 20)
    private String turno;
    @Lob
    @Column(name = "FOTO")
    private Serializable foto;
    @JoinColumn(name = "IDC", referencedColumnName = "IDC", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Carreras idc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idg", fetch = FetchType.LAZY)
    private List<Notasfinales> notasfinalesList;

    public Grupos() {
    }

    public Grupos(BigDecimal idg) {
        this.idg = idg;
    }

    public Grupos(BigDecimal idg, String nombre, BigInteger semestre, String turno) {
        this.idg = idg;
        this.nombre = nombre;
        this.semestre = semestre;
        this.turno = turno;
    }

    public BigDecimal getIdg() {
        return idg;
    }

    public void setIdg(BigDecimal idg) {
        this.idg = idg;
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Serializable getFoto() {
        return foto;
    }

    public void setFoto(Serializable foto) {
        this.foto = foto;
    }

    public Carreras getIdc() {
        return idc;
    }

    public void setIdc(Carreras idc) {
        this.idc = idc;
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
        hash += (idg != null ? idg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupos)) {
            return false;
        }
        Grupos other = (Grupos) object;
        if ((this.idg == null && other.idg != null) || (this.idg != null && !this.idg.equals(other.idg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Grupos[ idg=" + idg + " ]";
    }
    
}
