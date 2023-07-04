/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.orders.business;

import be.isl.orders.entity.Orders;
import be.isl.orders.ui.viewmodel.OrdersSearch;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Eric
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders, OrdersSearch> {

    @PersistenceContext(unitName = "Orders_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findVM(OrdersSearch s) {
        String jpql;
        String where = "";
        List<Orders> res;

        Date endDeliveryDate = null;
        Date startDeliveryDate = null;

        Date endOrderDate = null;
        Date startOrderDate = null;

        jpql = "SELECT O FROM Orders O ";

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE O.name like :name ";
                }
            }
            if (s.getOrderDate() != null && !s.getOrderDate().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    startOrderDate = dateFormat.parse(s.getOrderDate());  // 15-01-2023

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startOrderDate);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    startOrderDate = cal.getTime(); // 14-01-2023
                    cal.add(Calendar.DAY_OF_MONTH, 2);
                    endOrderDate = cal.getTime(); // 16-01-2023
                    if (where.isEmpty()) {
                        where = "WHERE O.orderDate BETWEEN :dayBefore AND :dayAfter ";
                    } else {
                        where += "AND O.orderDate BETWEEN :dayBefore AND :dayAfter ";
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(OrdersFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (s.getDeliveryDate() != null && !s.getDeliveryDate().isEmpty()) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    startDeliveryDate = dateFormat.parse(s.getDeliveryDate());

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startDeliveryDate);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    startDeliveryDate = cal.getTime();
                    cal.add(Calendar.DAY_OF_MONTH, 2);
                    endDeliveryDate = cal.getTime();
                    if (where.isEmpty()) {
                        where = "WHERE O.deliveryDate BETWEEN :dayBeforeDelivery AND :dayAfterDelivery ";
                    } else {
                        where += "AND O.deliveryDate BETWEEN :dayBeforeDelivery AND :dayAfterDelivery ";
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(OrdersFacade.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (s.getBuyer() != null && !s.getBuyer().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE O.buyer.name like :buyer ";
                } else {
                    where += "AND O.buyer.name like :buyer ";
                }
            }
            if (s.getArticle() != null && !s.getArticle().isEmpty()) {
                if (where.isEmpty()) {
                    where = "WHERE O IN ( SELECT OL.order FROM OrderLine OL WHERE OL.order = O AND OL.article.name LIKE :article) ";
                } else {
                    where += "AND O IN ( SELECT OL.order FROM OrderLine OL WHERE OL.order = O AND OL.article.name LIKE :article) ";
                }
            }
        }
        System.out.println(jpql + where + " ORDER BY O.name");
        Query q = em.createQuery(jpql + where + " ORDER BY O.name");

        if (s != null) {
            if (s.getName() != null && !s.getName().isEmpty()) {
                q.setParameter("name", "%" + s.getName() + "%");
            }
            if (startOrderDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startOrderDate);
                q.setParameter("dayBefore", cal.getTime());

                cal.setTime(endOrderDate);
                q.setParameter("dayAfter", cal.getTime());
            }
            if (startDeliveryDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDeliveryDate);
                q.setParameter("dayBeforeDelivery", cal.getTime());

                cal.setTime(endDeliveryDate);
                q.setParameter("dayAfterDelivery", cal.getTime());
            }
            if (s.getBuyer() != null && !s.getBuyer().isEmpty()) {
                q.setParameter("buyer", "%" + s.getBuyer() + "%");
            }
            if (s.getArticle() != null && !s.getArticle().isEmpty()) {
                q.setParameter("article", "%" + s.getArticle() + "%");
            }
        }
        res = (List<Orders>) q.getResultList();
        return res;
    }
}
