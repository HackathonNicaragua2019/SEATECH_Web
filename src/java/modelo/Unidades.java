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
@Table(name = "UNIDADES", catalog = "", schema = "ENAH")
@XmlRootElement
public class Unidades implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUN", nullable = false, precision = 0, scale = -127)
    private BigDecimal idun;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PONDERACION", nullable = false)
    private BigInteger ponderacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO", nullable = false)
    private BigInteger numero;
    @JoinColumn(name = "IDM", referencedColumnName = "IDM", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modulos idm;

    public Unidades() {
    }

    public Unidades(BigDecimal idun) {
        this.idun = idun;
    }

    public Unidades(BigDecimal idun, BigInteger ponderacion, String nombre, BigInteger numero) {
        this.idun = idun;
        this.ponderacion = ponderacion;
        this.nombre = nombre;
        this.numero = numero;
    }

    public BigDecimal getIdun() {
        return idun;
    }

    public void setIdun(BigDecimal idun) {
        this.idun = idun;
    }

    public BigInteger getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(BigInteger ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
    }

    public Modulos getIdm() {
        return idm;
    }

    public void setIdm(Modulos idm) {
        this.idm = idm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idun != null ? idun.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unidades)) {
            return false;
        }
        Unidades other = (Unidades) object;
        if ((this.idun == null && other.idun != null) || (this.idun != null && !this.idun.equals(other.idun))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Unidades[ idun=" + idun + " ]";
    }
    
}
