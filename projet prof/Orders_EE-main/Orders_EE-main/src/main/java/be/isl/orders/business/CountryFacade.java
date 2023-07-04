/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.Country;
import be.isl.orders.ui.viewmodel.CountrySearch;
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
public class CountryFacade extends AbstractFacade<Country, CountrySearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountryFacade() {
        super(Country.class);
    }

    @Override
    public List<Country> findVM(CountrySearch s) {
        String jpql;
        String where = "";
        List<Country> res;

        jpql = "SELECT C FROM Country C ";

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE C.name like :name ";
                }
            }
            if (s.getCapital() != null && !s.getCapital().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE C.capital like :capital ";
                } else {
                    where += "AND C.capital like :capital ";
                }
            }
        }

        Query q = em.createQuery(jpql + where + "ORDER BY C.name");

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                q.setParameter("name", "%" + s.getName() + "%");
            }
            if (s.getCapital() != null && !s.getCapital().isEmpty()) {
                q.setParameter("capital", "%" + s.getCapital() + "%");
            }
        }
        res = (List<Country>) q.getResultList();
        return res;
    }
}
