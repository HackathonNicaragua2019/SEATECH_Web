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
@Table(name = "CRITERIOS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Criterios implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCR", nullable = false, precision = 0, scale = -127)
    private BigDecimal idcr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUNTUACION", nullable = false)
    private BigInteger puntuacion;
    @JoinColumn(name = "IDACT", referencedColumnName = "IDACT", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AcAcalificar idact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcr", fetch = FetchType.LAZY)
    private List<Notas> notasList;

    public Criterios() {
    }

    public Criterios(BigDecimal idcr) {
        this.idcr = idcr;
    }

    public Criterios(BigDecimal idcr, String nombre, BigInteger puntuacion) {
        this.idcr = idcr;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public BigDecimal getIdcr() {
        return idcr;
    }

    public void setIdcr(BigDecimal idcr) {
        this.idcr = idcr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(BigInteger puntuacion) {
        this.puntuacion = puntuacion;
    }

    public AcAcalificar getIdact() {
        return idact;
    }

    public void setIdact(AcAcalificar idact) {
        this.idact = idact;
    }

    @XmlTransient
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcr != null ? idcr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterios)) {
            return false;
        }
        Criterios other = (Criterios) object;
        if ((this.idcr == null && other.idcr != null) || (this.idcr != null && !this.idcr.equals(other.idcr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Criterios[ idcr=" + idcr + " ]";
    }
    
}
