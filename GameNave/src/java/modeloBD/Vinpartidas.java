/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloBD;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OPEN
 */
@Entity
@Table(name = "vinpartidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vinpartidas.findAll", query = "SELECT v FROM Vinpartidas v")
    , @NamedQuery(name = "Vinpartidas.findByIdPartida", query = "SELECT v FROM Vinpartidas v WHERE v.idPartida = :idPartida")
    , @NamedQuery(name = "Vinpartidas.findByInicio", query = "SELECT v FROM Vinpartidas v WHERE v.inicio = :inicio")
    , @NamedQuery(name = "Vinpartidas.findByFin", query = "SELECT v FROM Vinpartidas v WHERE v.fin = :fin")
    , @NamedQuery(name = "Vinpartidas.findByVelocidad", query = "SELECT v FROM Vinpartidas v WHERE v.velocidad = :velocidad")})
public class Vinpartidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_partida")
    private Integer idPartida;
    @Basic(optional = false)
    @Column(name = "inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    @Basic(optional = false)
    @Column(name = "fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fin;
    @Basic(optional = false)
    @Column(name = "velocidad")
    private float velocidad;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Vinusuarios idUsuario;

    public Vinpartidas() {
    }

    public Vinpartidas(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Vinpartidas(Integer idPartida, Date inicio, Date fin, float velocidad) {
        this.idPartida = idPartida;
        this.inicio = inicio;
        this.fin = fin;
        this.velocidad = velocidad;
    }

    public Integer getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public Vinusuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Vinusuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartida != null ? idPartida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vinpartidas)) {
            return false;
        }
        Vinpartidas other = (Vinpartidas) object;
        if ((this.idPartida == null && other.idPartida != null) || (this.idPartida != null && !this.idPartida.equals(other.idPartida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeloBD.Vinpartidas[ idPartida=" + idPartida + " ]";
    }
    
}
