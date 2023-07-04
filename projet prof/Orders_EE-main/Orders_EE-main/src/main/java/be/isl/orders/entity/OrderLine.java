/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.entity;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author esc
 */
@Entity
@Table(name = "order_line_table")
@NamedQueries({
    @NamedQuery(name = "OrderLine.findAll", query = "SELECT o FROM OrderLine o")})
public class OrderLine extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_line_id")
    private Integer orderLineId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    
    @JoinColumn(name = "article_id", referencedColumnName = "article_id")
    @ManyToOne
    private Article article;
    
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne
    @JsonbTransient
    private Orders order;

    public OrderLine() {
    }
    
    @Override
    public Integer getId() {
        return orderLineId;
    }
    public OrderLine(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Integer getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderLineId != null ? orderLineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLine)) {
            return false;
        }
        OrderLine other = (OrderLine) object;
        if ((this.orderLineId == null && other.orderLineId != null) || (this.orderLineId != null && !this.orderLineId.equals(other.orderLineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.orders.entity.OrderLine[ orderLineId=" + orderLineId + " ]";
    }
    
}
