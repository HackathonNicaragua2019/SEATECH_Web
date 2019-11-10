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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AULANO1
 */
@MappedSuperclass
@Table(name = "AC_ACALIFICAR", catalog = "", schema = "ENAH")
@XmlRootElement
public class AcAcalificar implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDACT", nullable = false, precision = 0, scale = -127)
    private BigDecimal idact;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idact", fetch = FetchType.LAZY)
    private List<Criterios> criteriosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idact", fetch = FetchType.LAZY)
    private List<Notas> notasList;
    @JoinColumn(name = "IDA", referencedColumnName = "IDA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Actividades ida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idact", fetch = FetchType.LAZY)
    private List<Periodoactividades> periodoactividadesList;

    public AcAcalificar() {
    }

    public AcAcalificar(BigDecimal idact) {
        this.idact = idact;
    }

    public BigDecimal getIdact() {
        return idact;
    }

    public void setIdact(BigDecimal idact) {
        this.idact = idact;
    }

    @XmlTransient
    public List<Criterios> getCriteriosList() {
        return criteriosList;
    }

    public void setCriteriosList(List<Criterios> criteriosList) {
        this.criteriosList = criteriosList;
    }

    @XmlTransient
    public List<Notas> getNotasList() {
        return notasList;
    }

    public void setNotasList(List<Notas> notasList) {
        this.notasList = notasList;
    }

    public Actividades getIda() {
        return ida;
    }

    public void setIda(Actividades ida) {
        this.ida = ida;
    }

    @XmlTransient
    public List<Periodoactividades> getPeriodoactividadesList() {
        return periodoactividadesList;
    }

    public void setPeriodoactividadesList(List<Periodoactividades> periodoactividadesList) {
        this.periodoactividadesList = periodoactividadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idact != null ? idact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcAcalificar)) {
            return false;
        }
        AcAcalificar other = (AcAcalificar) object;
        if ((this.idact == null && other.idact != null) || (this.idact != null && !this.idact.equals(other.idact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AcAcalificar[ idact=" + idact + " ]";
    }
    
}
