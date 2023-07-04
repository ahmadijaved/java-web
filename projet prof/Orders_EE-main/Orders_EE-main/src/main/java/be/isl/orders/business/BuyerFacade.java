/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.Buyer;
import be.isl.orders.ui.viewmodel.BuyerSearch;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eric
 */
@Stateless
public class BuyerFacade extends AbstractFacade<Buyer, BuyerSearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BuyerFacade() {
        super(Buyer.class);
    }

    @Override
    public List<Buyer> findVM(BuyerSearch s) {
        String jpql;
        String where = "";
        List<Buyer> res;

        jpql = "SELECT B FROM Buyer B ";

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE B.name like :name ";
                }
            }
            if (s.getFirstName() != null && !s.getFirstName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE B.firstName like :firstName ";
                } else {
                    where += "AND B.firstName like :firstName ";
                }
            }
            if (s.getAddress() != null && !s.getAddress().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE B.address like :address ";
                } else {
                    where += "AND B.address like :address  ";
                }
            }
        }

        Query q = em.createQuery(jpql + where + "ORDER BY B.name");

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                q.setParameter("name", "%" + s.getName() + "%");
            }
            if (s.getFirstName() != null && !s.getFirstName().isEmpty()) {
                q.setParameter("firstName", "%" + s.getFirstName() + "%");
            }
            if (s.getAddress() != null && !s.getAddress().isEmpty()) {
                q.setParameter("address", "%" + s.getAddress() + "%");
            }
        }
        res = (List<Buyer>) q.getResultList();
        return res;
    }
}
