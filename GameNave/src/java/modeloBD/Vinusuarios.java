/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloBD;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author OPEN
 */
@Entity
@Table(name = "vinusuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vinusuarios.findAll", query = "SELECT v FROM Vinusuarios v")
    , @NamedQuery(name = "Vinusuarios.findByIdUsuario", query = "SELECT v FROM Vinusuarios v WHERE v.idUsuario = :idUsuario")
    , @NamedQuery(name = "Vinusuarios.findByNombre", query = "SELECT v FROM Vinusuarios v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "Vinusuarios.findByContrase\u00f1a", query = "SELECT v FROM Vinusuarios v WHERE v.contrase\u00f1a = :contrase\u00f1a")
    , @NamedQuery(name = "Vinusuarios.findByMail", query = "SELECT v FROM Vinusuarios v WHERE v.mail = :mail")
    , @NamedQuery(name = "Vinusuarios.findByTelefono", query = "SELECT v FROM Vinusuarios v WHERE v.telefono = :telefono")})
public class Vinusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "contrase\u00f1a")
    private String contraseña;
    @Column(name = "mail")
    private String mail;
    @Column(name = "telefono")
    private Integer telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Vinpartidas> vinpartidasList;

    public Vinusuarios() {
    }

    public Vinusuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Vinusuarios(Integer idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Vinpartidas> getVinpartidasList() {
        return vinpartidasList;
    }

    public void setVinpartidasList(List<Vinpartidas> vinpartidasList) {
        this.vinpartidasList = vinpartidasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vinusuarios)) {
            return false;
        }
        Vinusuarios other = (Vinusuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeloBD.Vinusuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
