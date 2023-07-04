/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.Country;
import be.isl.orders.entity.StateProvince;
import be.isl.orders.ui.viewmodel.StateProvinceSearch;
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
public class StateProvinceFacade extends AbstractFacade<StateProvince, StateProvinceSearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StateProvinceFacade() {
        super(StateProvince.class);
    }

    public List<StateProvince> findByCountry(Country c) {
        if (c != null) {
            return (List<StateProvince>) c.getStateProvinces();
        } else {
            return null;
        }
    }

    @Override
    public List<StateProvince> findVM(StateProvinceSearch s) {
        String jpql;
        String where = "";
        List<StateProvince> res;

        jpql = "SELECT S FROM StateProvince S ";

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE S.name like :name ";
                }
            }
            if (s.getCountry() != null && !s.getCountry().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE S.country.name like :country ";
                } else {
                    where += "AND S.country.name like :country  ";
                }
            }
        }

        Query q = em.createQuery(jpql + where + "ORDER BY S.name");

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                q.setParameter("name", "%" + s.getName() + "%");
            }
            if (s.getCountry() != null && !s.getCountry().isEmpty()) {
                q.setParameter("country", "%" + s.getCountry() + "%");
            }
        }
        res = (List<StateProvince>) q.getResultList();
        return res;
    }

}
