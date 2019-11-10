/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "MAESTROS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Maestros implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDMA", nullable = false, precision = 0, scale = -127)
    private BigDecimal idma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRES", nullable = false, length = 100)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idma", fetch = FetchType.LAZY)
    private List<Notasfinales> notasfinalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idma", fetch = FetchType.LAZY)
    private List<AsignacionDocentes> asignacionDocentesList;

    public Maestros() {
    }

    public Maestros(BigDecimal idma) {
        this.idma = idma;
    }

    public Maestros(BigDecimal idma, String nombres, String apellidos) {
        this.idma = idma;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public BigDecimal getIdma() {
        return idma;
    }

    public void setIdma(BigDecimal idma) {
        this.idma = idma;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @XmlTransient
    public List<Notasfinales> getNotasfinalesList() {
        return notasfinalesList;
    }

    public void setNotasfinalesList(List<Notasfinales> notasfinalesList) {
        this.notasfinalesList = notasfinalesList;
    }

    @XmlTransient
    public List<AsignacionDocentes> getAsignacionDocentesList() {
        return asignacionDocentesList;
    }

    public void setAsignacionDocentesList(List<AsignacionDocentes> asignacionDocentesList) {
        this.asignacionDocentesList = asignacionDocentesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idma != null ? idma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maestros)) {
            return false;
        }
        Maestros other = (Maestros) object;
        if ((this.idma == null && other.idma != null) || (this.idma != null && !this.idma.equals(other.idma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Maestros[ idma=" + idma + " ]";
    }
    
}
