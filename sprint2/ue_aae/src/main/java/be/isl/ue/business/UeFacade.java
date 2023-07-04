/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.business;

import be.isl.ue.entity.Ue;
import be.isl.ue.viewmodel.UeSearchVM;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ahmad
 */
@Stateless
public class UeFacade extends AbstractFacade<Ue, UeSearchVM> {

    @PersistenceContext(unitName = "UePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UeFacade() {
        super(Ue.class);
    }

    @Override
    public List<Ue> findVM(UeSearchVM s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
