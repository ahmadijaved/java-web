/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.OrderLine;
import be.isl.orders.ui.viewmodel.OrderLineSearch;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Eric
 */
@Stateless
public class OrderLineFacade extends AbstractFacade<OrderLine, OrderLineSearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderLineFacade() {
        super(OrderLine.class);
    }

    @Override
    public List<OrderLine> findVM(OrderLineSearch s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
