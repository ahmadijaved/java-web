/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.Category;
import be.isl.orders.ui.viewmodel.CategorySearch;
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
public class CategoryFacade extends AbstractFacade<Category, CategorySearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    @Override
    public List<Category> findVM(CategorySearch s) {
        String jpql;
        String where = "";
        List<Category> res;

        jpql = "SELECT C FROM Category C ";

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE C.name like :name ";
                }
            }
        }

        Query q = em.createQuery(jpql + where + "ORDER BY C.name");

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                q.setParameter("name", "%" + s.getName() + "%");
            }
        }
        res = (List<Category>) q.getResultList();
        return res;
    }
}
