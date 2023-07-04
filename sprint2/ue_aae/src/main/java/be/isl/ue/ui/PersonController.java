package be.isl.ue.ui;

import be.isl.ue.entity.Person;
import be.isl.ue.business.PersonFacade;
import be.isl.ue.viewmodel.PersonSearchVM;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("personController")
@SessionScoped
public class PersonController
         extends AbstractController <Person,PersonFacade,PersonSearchVM>
        implements Serializable {

    @Inject 
    private PersonFacade ejbFacade;

    public PersonController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new PersonSearchVM());
    }

    @Override
    public PersonFacade getFacade() {
        return ejbFacade;
    }
    
    @Override
    public Person create() {
        /*Person p = new Person();
        p.setDateOfBirth(new Date());*/
        return new Person();
    }

    public Person getPerson(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Person.class)
    public static class PersonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonController controller = (PersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personController");
            return controller.getPerson(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Person o) {
                return getStringKey(o.getPersonId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Person.class.getName());
            }
        }
    }
}
