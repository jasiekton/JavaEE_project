/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author student
 */
@Entity
@Table(name = "WCY21IG1S1_JASIEK_NOWICKI")
@NamedQueries({
    @NamedQuery(name = "Wcy21ig1s1JasiekNowicki.findAll", query = "SELECT w FROM Wcy21ig1s1JasiekNowicki w"),
    @NamedQuery(name = "Wcy21ig1s1JasiekNowicki.findById", query = "SELECT w FROM Wcy21ig1s1JasiekNowicki w WHERE w.id = :id"),
    @NamedQuery(name = "Wcy21ig1s1JasiekNowicki.findByNazwa", query = "SELECT w FROM Wcy21ig1s1JasiekNowicki w WHERE w.nazwa = :nazwa"),
    @NamedQuery(name = "Wcy21ig1s1JasiekNowicki.findByProwadz\u0105cy", query = "SELECT w FROM Wcy21ig1s1JasiekNowicki w WHERE w.prowadz\u0105cy = :prowadz\u0105cy"),
    @NamedQuery(name = "Wcy21ig1s1JasiekNowicki.findByData", query = "SELECT w FROM Wcy21ig1s1JasiekNowicki w WHERE w.data = :data"),
    @NamedQuery(name = "Wcy21ig1s1JasiekNowicki.findBySala", query = "SELECT w FROM Wcy21ig1s1JasiekNowicki w WHERE w.sala = :sala")})
public class Wcy21ig1s1JasiekNowicki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAZWA")
    private String nazwa;
    @Basic(optional = false)
    @Column(name = "PROWADZ\u0104CY")
    private String prowadzący;
    @Basic(optional = false)
    @Column(name = "DATA")
    private String data;
    @Basic(optional = false)
    @Column(name = "SALA")
    private String sala;

    public Wcy21ig1s1JasiekNowicki() {
    }

    public Wcy21ig1s1JasiekNowicki(Integer id) {
        this.id = id;
    }

    public Wcy21ig1s1JasiekNowicki(Integer id, String nazwa, String prowadzący, String data, String sala) {
        this.id = id;
        this.nazwa = nazwa;
        this.prowadzący = prowadzący;
        this.data = data;
        this.sala = sala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getProwadzący() {
        return prowadzący;
    }

    public void setProwadzący(String prowadzący) {
        this.prowadzący = prowadzący;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wcy21ig1s1JasiekNowicki)) {
            return false;
        }
        Wcy21ig1s1JasiekNowicki other = (Wcy21ig1s1JasiekNowicki) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPA.Wcy21ig1s1JasiekNowicki[ id=" + id + " ]";
    }
    
}
