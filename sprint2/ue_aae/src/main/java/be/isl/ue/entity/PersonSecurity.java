/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ahmad
 */
@Entity
@Table(name = "person_security")
@NamedQueries({
    @NamedQuery(name = "PersonSecurity.findAll", query = "SELECT p FROM PersonSecurity p"),
    @NamedQuery(name = "PersonSecurity.findByPersonSecurityId", query = "SELECT p FROM PersonSecurity p WHERE p.personSecurityId = :personSecurityId"),
    @NamedQuery(name = "PersonSecurity.findByIsAdmin", query = "SELECT p FROM PersonSecurity p WHERE p.isAdmin = :isAdmin"),
    @NamedQuery(name = "PersonSecurity.findByIsSecretary", query = "SELECT p FROM PersonSecurity p WHERE p.isSecretary = :isSecretary"),
    @NamedQuery(name = "PersonSecurity.findByPwd", query = "SELECT p FROM PersonSecurity p WHERE p.pwd = :pwd")})
public class PersonSecurity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_security_id")
    private Integer personSecurityId;
    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "is_secretary")
    private Boolean isSecretary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "pwd")
    private String pwd;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private Person personId;

    public PersonSecurity() {
    }

    public PersonSecurity(Integer personSecurityId) {
        this.personSecurityId = personSecurityId;
    }

    public PersonSecurity(Integer personSecurityId, String pwd) {
        this.personSecurityId = personSecurityId;
        this.pwd = pwd;
    }

    public Integer getPersonSecurityId() {
        return personSecurityId;
    }

    public void setPersonSecurityId(Integer personSecurityId) {
        this.personSecurityId = personSecurityId;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsSecretary() {
        return isSecretary;
    }

    public void setIsSecretary(Boolean isSecretary) {
        this.isSecretary = isSecretary;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personSecurityId != null ? personSecurityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonSecurity)) {
            return false;
        }
        PersonSecurity other = (PersonSecurity) object;
        if ((this.personSecurityId == null && other.personSecurityId != null) || (this.personSecurityId != null && !this.personSecurityId.equals(other.personSecurityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.PersonSecurity[ personSecurityId=" + personSecurityId + " ]";
    }
    
}
