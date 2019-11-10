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
@Table(name = "NOTASFINALES", catalog = "", schema = "ENAH")
@XmlRootElement
public class Notasfinales implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDNF", nullable = false, precision = 0, scale = -127)
    private BigDecimal idnf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTAF", nullable = false)
    private BigInteger notaf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "FECHA", nullable = false, length = 12)
    private String fecha;
    @JoinColumn(name = "IDM", referencedColumnName = "IDM", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulos idm;
    @JoinColumn(name = "IDMA", referencedColumnName = "IDMA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Maestros idma;
    @JoinColumn(name = "IDG", referencedColumnName = "IDG", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grupos idg;
    @JoinColumn(name = "IDA", referencedColumnName = "IDA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alumnos ida;

    public Notasfinales() {
    }

    public Notasfinales(BigDecimal idnf) {
        this.idnf = idnf;
    }

    public Notasfinales(BigDecimal idnf, BigInteger notaf, String fecha) {
        this.idnf = idnf;
        this.notaf = notaf;
        this.fecha = fecha;
    }

    public BigDecimal getIdnf() {
        return idnf;
    }

    public void setIdnf(BigDecimal idnf) {
        this.idnf = idnf;
    }

    public BigInteger getNotaf() {
        return notaf;
    }

    public void setNotaf(BigInteger notaf) {
        this.notaf = notaf;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Modulos getIdm() {
        return idm;
    }

    public void setIdm(Modulos idm) {
        this.idm = idm;
    }

    public Maestros getIdma() {
        return idma;
    }

    public void setIdma(Maestros idma) {
        this.idma = idma;
    }

    public Grupos getIdg() {
        return idg;
    }

    public void setIdg(Grupos idg) {
        this.idg = idg;
    }

    public Alumnos getIda() {
        return ida;
    }

    public void setIda(Alumnos ida) {
        this.ida = ida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnf != null ? idnf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notasfinales)) {
            return false;
        }
        Notasfinales other = (Notasfinales) object;
        if ((this.idnf == null && other.idnf != null) || (this.idnf != null && !this.idnf.equals(other.idnf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Notasfinales[ idnf=" + idnf + " ]";
    }
    
}
