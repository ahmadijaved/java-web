/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.business;

import be.isl.ue.entity.Section;
import be.isl.ue.viewmodel.SectionSearchVM;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ahmad
 */
@Stateless
public class SectionFacade extends AbstractFacade<Section, SectionSearchVM> {

    @PersistenceContext(unitName = "UePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SectionFacade() {
        super(Section.class);
    }

    @Override
    public List<Section> findVM(SectionSearchVM vm) {
        List<Section> resultat;
        String jpql= "select s from section s " ;  
        Query q=em.createQuery(jpql);
        resultat =(List<Section> )q.getResultList();
                return resultat ;
    }
    
}
