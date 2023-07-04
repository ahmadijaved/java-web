/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.entity;

import java.io.Serializable;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author esc
 */
@Entity
@Table(name = "state_province_table")
@NamedQueries({
    @NamedQuery(name = "StateProvince.findAll", query = "SELECT s FROM StateProvince s"),
    @NamedQuery(name = "StateProvince.findByStateProvinceId", query = "SELECT s FROM StateProvince s WHERE s.stateProvinceId = :stateProvinceId"),
    @NamedQuery(name = "StateProvince.findByName", query = "SELECT s FROM StateProvince s WHERE s.name = :name")})
public class StateProvince extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "state_province_id")
    private Integer stateProvinceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    @ManyToOne(optional = false)
    private Country country;
    @OneToMany(mappedBy = "stateProvince")
    @JsonbTransient
    private List<Buyer> buyers;

    public StateProvince() {
    }

    @Override
    public Integer getId() {
        return stateProvinceId;
    }
    
    public StateProvince(Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public StateProvince(Integer stateProvinceId, String name) {
        this.stateProvinceId = stateProvinceId;
        this.name = name;
    }

    public Integer getStateProvinceId() {
        return stateProvinceId;
    }

    public void setStateProvinceId(Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateProvinceId != null ? stateProvinceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StateProvince)) {
            return false;
        }
        StateProvince other = (StateProvince) object;
        if ((this.stateProvinceId == null && other.stateProvinceId != null) || (this.stateProvinceId != null && !this.stateProvinceId.equals(other.stateProvinceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
