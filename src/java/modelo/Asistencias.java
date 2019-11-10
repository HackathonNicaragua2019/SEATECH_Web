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
@Table(name = "ASISTENCIAS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Asistencias implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDAS", nullable = false, precision = 0, scale = -127)
    private BigDecimal idas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASISTENCIA", nullable = false)
    private BigInteger asistencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "FECHA", nullable = false, length = 12)
    private String fecha;
    @JoinColumn(name = "IDM", referencedColumnName = "IDM", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulos idm;
    @JoinColumn(name = "IDA", referencedColumnName = "IDA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Alumnos ida;

    public Asistencias() {
    }

    public Asistencias(BigDecimal idas) {
        this.idas = idas;
    }

    public Asistencias(BigDecimal idas, BigInteger asistencia, String fecha) {
        this.idas = idas;
        this.asistencia = asistencia;
        this.fecha = fecha;
    }

    public BigDecimal getIdas() {
        return idas;
    }

    public void setIdas(BigDecimal idas) {
        this.idas = idas;
    }

    public BigInteger getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(BigInteger asistencia) {
        this.asistencia = asistencia;
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

    public Alumnos getIda() {
        return ida;
    }

    public void setIda(Alumnos ida) {
        this.ida = ida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idas != null ? idas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencias)) {
            return false;
        }
        Asistencias other = (Asistencias) object;
        if ((this.idas == null && other.idas != null) || (this.idas != null && !this.idas.equals(other.idas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Asistencias[ idas=" + idas + " ]";
    }
    
}
