/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
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
@Table(name = "USUARIOS", catalog = "", schema = "ENAH")
@XmlRootElement
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDU", nullable = false, precision = 0, scale = -127)
    private BigDecimal idu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USUARIO", nullable = false, length = 25)
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CONTRASE\u00d1A", nullable = false, length = 10)
    private String contraseña;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TIPO", nullable = false, length = 10)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    public Usuarios() {
    }

    public Usuarios(BigDecimal idu) {
        this.idu = idu;
    }

    public Usuarios(BigDecimal idu, String usuario, String contraseña, String tipo, String nombre) {
        this.idu = idu;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public BigDecimal getIdu() {
        return idu;
    }

    public void setIdu(BigDecimal idu) {
        this.idu = idu;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idu != null ? idu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idu == null && other.idu != null) || (this.idu != null && !this.idu.equals(other.idu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Usuarios[ idu=" + idu + " ]";
    }
    
}
