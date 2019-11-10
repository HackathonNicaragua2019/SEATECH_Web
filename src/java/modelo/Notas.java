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
@Table(name = "NOTAS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Notas implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDN", nullable = false, precision = 0, scale = -127)
    private BigDecimal idn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "FECHA", nullable = false, length = 12)
    private String fecha;
    @Column(name = "IDU")
    private BigInteger idu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTA", nullable = false)
    private BigInteger nota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idn", fetch = FetchType.LAZY)
    private List<NotasReparaciones> notasReparacionesList;
    @JoinColumn(name = "IDCR", referencedColumnName = "IDCR", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Criterios idcr;
    @JoinColumn(name = "IDA", referencedColumnName = "IDA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alumnos ida;
    @JoinColumn(name = "IDACT", referencedColumnName = "IDACT", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AcAcalificar idact;

    public Notas() {
    }

    public Notas(BigDecimal idn) {
        this.idn = idn;
    }

    public Notas(BigDecimal idn, String fecha, BigInteger nota) {
        this.idn = idn;
        this.fecha = fecha;
        this.nota = nota;
    }

    public BigDecimal getIdn() {
        return idn;
    }

    public void setIdn(BigDecimal idn) {
        this.idn = idn;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigInteger getIdu() {
        return idu;
    }

    public void setIdu(BigInteger idu) {
        this.idu = idu;
    }

    public BigInteger getNota() {
        return nota;
    }

    public void setNota(BigInteger nota) {
        this.nota = nota;
    }

    @XmlTransient
    public List<NotasReparaciones> getNotasReparacionesList() {
        return notasReparacionesList;
    }

    public void setNotasReparacionesList(List<NotasReparaciones> notasReparacionesList) {
        this.notasReparacionesList = notasReparacionesList;
    }

    public Criterios getIdcr() {
        return idcr;
    }

    public void setIdcr(Criterios idcr) {
        this.idcr = idcr;
    }

    public Alumnos getIda() {
        return ida;
    }

    public void setIda(Alumnos ida) {
        this.ida = ida;
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
        hash += (idn != null ? idn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.idn == null && other.idn != null) || (this.idn != null && !this.idn.equals(other.idn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Notas[ idn=" + idn + " ]";
    }
    
}
