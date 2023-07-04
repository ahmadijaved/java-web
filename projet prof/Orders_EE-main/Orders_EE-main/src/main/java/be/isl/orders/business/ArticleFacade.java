/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.Article;
import be.isl.orders.ui.viewmodel.ArticleSearch;
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
public class ArticleFacade extends AbstractFacade<Article, ArticleSearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    @Override
    public List<Article> findVM(ArticleSearch s) {
        String jpql;
        String where = "";
        List<Article> res;

        jpql = "SELECT A FROM Article A ";

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE A.name like :name ";
                }
            }
            if (s.getDescription() != null && !s.getDescription().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE A.description like :description ";
                } else {
                    where += "AND A.description like :description ";
                }
            }
            if (s.getCategory() != null && !s.getCategory().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE A.category.name like :category ";
                } else {
                    where += "AND A.category.name like :category  ";
                }
            }
        }

        Query q = em.createQuery(jpql + where + "ORDER BY A.name");

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                q.setParameter("name", "%" + s.getName() + "%");
            }
            if (s.getDescription() != null && !s.getDescription().isEmpty()) {
                q.setParameter("description", "%" + s.getDescription() + "%");
            }
            if (s.getCategory() != null && !s.getCategory().isEmpty()) {
                q.setParameter("category", "%" + s.getCategory() + "%");
            }
        }
        res = (List<Article>) q.getResultList();
        return res;
    }
}
