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
@Table(name = "PERIODOACTIVIDADES", catalog = "", schema = "ENAH")
@XmlRootElement
public class Periodoactividades implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDP", nullable = false, precision = 0, scale = -127)
    private BigDecimal idp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SEMESTRE", nullable = false)
    private BigInteger semestre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VIGENCIA", nullable = false)
    private BigInteger vigencia;
    @JoinColumn(name = "IDACT", referencedColumnName = "IDACT", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AcAcalificar idact;

    public Periodoactividades() {
    }

    public Periodoactividades(BigDecimal idp) {
        this.idp = idp;
    }

    public Periodoactividades(BigDecimal idp, BigInteger semestre, BigInteger vigencia) {
        this.idp = idp;
        this.semestre = semestre;
        this.vigencia = vigencia;
    }

    public BigDecimal getIdp() {
        return idp;
    }

    public void setIdp(BigDecimal idp) {
        this.idp = idp;
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

    public AcAcalificar getIdact() {
        return idact;
    }

    public void setIdact(AcAcalificar idact) {
        this.idact = idact;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idp != null ? idp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodoactividades)) {
            return false;
        }
        Periodoactividades other = (Periodoactividades) object;
        if ((this.idp == null && other.idp != null) || (this.idp != null && !this.idp.equals(other.idp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Periodoactividades[ idp=" + idp + " ]";
    }
    
}
