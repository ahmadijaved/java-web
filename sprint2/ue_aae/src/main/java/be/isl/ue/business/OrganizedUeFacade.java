
package be.isl.ue.business;

import be.isl.ue.entity.OrganizedUe;
import be.isl.ue.viewmodel.OrganizedUeSearchVM;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class OrganizedUeFacade extends AbstractFacade<OrganizedUe, OrganizedUeSearchVM> {

    @PersistenceContext(unitName = "UePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganizedUeFacade() {
        super(OrganizedUe.class);
    }

    @Override
    public List<OrganizedUe> findVM(OrganizedUeSearchVM s) {
        List<OrganizedUe> resultat;
        String jpql= "select s from OrganizedUe s " ;  
        Query q=em.createQuery(jpql);
        resultat =(List<OrganizedUe> )q.getResultList();
                return resultat ;
    }
    
}
