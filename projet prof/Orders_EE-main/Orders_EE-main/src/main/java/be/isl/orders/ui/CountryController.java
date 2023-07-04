package be.isl.orders.ui;

import be.isl.orders.entity.Country;
import be.isl.orders.business.CountryFacade;
import be.isl.orders.ui.viewmodel.CountrySearch;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("countryController")
@SessionScoped
public class CountryController 
        extends AbstractController<Country, CountryFacade, CountrySearch>
        implements Serializable {

    @Inject
    private CountryFacade ejbFacade;

    public CountryController() {
    }
    
    @PostConstruct
    public void init() {
        setSearch(new CountrySearch());
    }
    
    @Override
    public CountryFacade getFacade() {
        return ejbFacade;
    }
    
    @Override
    public Country create() {
        return new Country();
    }
    
    public Country getCountry(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Country.class)
    public static class CountryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CountryController controller = (CountryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "countryController");
            return controller.getCountry(getKey(value));
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
            if (object instanceof Country) {
                Country o = (Country) object;
                return getStringKey(o.getCountryId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Country.class.getName());
            }
        }

    }
}