/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AULANO1
 */
@MappedSuperclass
@Table(name = "NOTAS_REPARACIONES", catalog = "", schema = "ENAH")
@XmlRootElement
public class NotasReparaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDREP", nullable = false, precision = 0, scale = -127)
    private BigDecimal idrep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTAR", nullable = false)
    private BigInteger notar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "FECHA", nullable = false, length = 25)
    private String fecha;
    @JoinColumn(name = "IDN", referencedColumnName = "IDN", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Notas idn;

    public NotasReparaciones() {
    }

    public NotasReparaciones(BigDecimal idrep) {
        this.idrep = idrep;
    }

    public NotasReparaciones(BigDecimal idrep, BigInteger notar, String fecha) {
        this.idrep = idrep;
        this.notar = notar;
        this.fecha = fecha;
    }

    public BigDecimal getIdrep() {
        return idrep;
    }

    public void setIdrep(BigDecimal idrep) {
        this.idrep = idrep;
    }

    public BigInteger getNotar() {
        return notar;
    }

    public void setNotar(BigInteger notar) {
        this.notar = notar;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Notas getIdn() {
        return idn;
    }

    public void setIdn(Notas idn) {
        this.idn = idn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrep != null ? idrep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotasReparaciones)) {
            return false;
        }
        NotasReparaciones other = (NotasReparaciones) object;
        if ((this.idrep == null && other.idrep != null) || (this.idrep != null && !this.idrep.equals(other.idrep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NotasReparaciones[ idrep=" + idrep + " ]";
    }
    
}
