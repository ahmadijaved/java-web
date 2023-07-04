
package be.isl.ue.business;

import be.isl.ue.entity.Person;
import be.isl.ue.viewmodel.PersonSearchVM;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonFacade extends AbstractFacade<Person, PersonSearchVM> {

    @PersistenceContext(unitName = "UePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonFacade() {
        super(Person.class);
    }

    @Override
    public List<Person> findVM(PersonSearchVM s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
