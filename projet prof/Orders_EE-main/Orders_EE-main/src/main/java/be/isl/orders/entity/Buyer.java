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
import javax.persistence.CascadeType;
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
@Table(name = "buyer_table")
@NamedQueries({
    @NamedQuery(name = "Buyer.findAll", query = "SELECT b FROM Buyer b"),
    @NamedQuery(name = "Buyer.findByBuyerId", query = "SELECT b FROM Buyer b WHERE b.buyerId = :buyerId"),
    @NamedQuery(name = "Buyer.findByName", query = "SELECT b FROM Buyer b WHERE b.name = :name"),
    @NamedQuery(name = "Buyer.findByFirstName", query = "SELECT b FROM Buyer b WHERE b.firstName = :firstName"),
    @NamedQuery(name = "Buyer.findByAddress", query = "SELECT b FROM Buyer b WHERE b.address = :address"),
    @NamedQuery(name = "Buyer.findByDiscount", query = "SELECT b FROM Buyer b WHERE b.discount = :discount")})
public class Buyer extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "buyer_id")
    private Integer buyerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private Double discount;
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    @ManyToOne
    private Country country;
    @JoinColumn(name = "state_province_id", referencedColumnName = "state_province_id")
    @ManyToOne
    private StateProvince stateProvince;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    @JsonbTransient
    private List<Orders> orders;

    public Buyer() {
    }

    @Override
    public Integer getId() {
        return buyerId;
    }
    
    public Buyer(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Buyer(Integer buyerId, String name) {
        this.buyerId = buyerId;
        this.name = name;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public StateProvince getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(StateProvince stateProvince) {
        this.stateProvince = stateProvince;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buyerId != null ? buyerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buyer)) {
            return false;
        }
        Buyer other = (Buyer) object;
        if ((this.buyerId == null && other.buyerId != null) || (this.buyerId != null && !this.buyerId.equals(other.buyerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
