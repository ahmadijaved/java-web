/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.business;

import be.isl.ue.entity.Capacity;
import be.isl.ue.viewmodel.CapacitySearchVM;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ahmad
 */
@Stateless
public class CapacityFacade extends AbstractFacade<Capacity, CapacitySearchVM> {

    @PersistenceContext(unitName = "UePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacityFacade() {
        super(Capacity.class);
    }

    @Override
    public List<Capacity> findVM(CapacitySearchVM s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
