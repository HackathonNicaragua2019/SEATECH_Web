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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AULANO1
 */
@MappedSuperclass
@Table(name = "ASIGNACION_DOCENTES", catalog = "", schema = "ENAH")
@XmlRootElement
public class AsignacionDocentes implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDASIGDOC", nullable = false, precision = 0, scale = -127)
    private BigDecimal idasigdoc;
    @Column(name = "IDP")
    private BigInteger idp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDG", nullable = false)
    private BigInteger idg;
    @JoinColumn(name = "IDM", referencedColumnName = "IDM", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulos idm;
    @JoinColumn(name = "IDMA", referencedColumnName = "IDMA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Maestros idma;

    public AsignacionDocentes() {
    }

    public AsignacionDocentes(BigDecimal idasigdoc) {
        this.idasigdoc = idasigdoc;
    }

    public AsignacionDocentes(BigDecimal idasigdoc, BigInteger idg) {
        this.idasigdoc = idasigdoc;
        this.idg = idg;
    }

    public BigDecimal getIdasigdoc() {
        return idasigdoc;
    }

    public void setIdasigdoc(BigDecimal idasigdoc) {
        this.idasigdoc = idasigdoc;
    }

    public BigInteger getIdp() {
        return idp;
    }

    public void setIdp(BigInteger idp) {
        this.idp = idp;
    }

    public BigInteger getIdg() {
        return idg;
    }

    public void setIdg(BigInteger idg) {
        this.idg = idg;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasigdoc != null ? idasigdoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionDocentes)) {
            return false;
        }
        AsignacionDocentes other = (AsignacionDocentes) object;
        if ((this.idasigdoc == null && other.idasigdoc != null) || (this.idasigdoc != null && !this.idasigdoc.equals(other.idasigdoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AsignacionDocentes[ idasigdoc=" + idasigdoc + " ]";
    }
    
}
