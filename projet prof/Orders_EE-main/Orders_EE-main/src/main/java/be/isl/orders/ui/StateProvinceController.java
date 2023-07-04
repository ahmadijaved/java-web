package be.isl.orders.ui;

import be.isl.orders.entity.StateProvince;
import be.isl.orders.business.StateProvinceFacade;
import be.isl.orders.ui.viewmodel.StateProvinceSearch;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("stateProvinceController")
@SessionScoped
public class StateProvinceController
        extends AbstractController<StateProvince, StateProvinceFacade, StateProvinceSearch>
        implements Serializable {

    @Inject
    private StateProvinceFacade ejbFacade;

    public StateProvinceController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new StateProvinceSearch());
    }

    @Override
    public StateProvinceFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public StateProvince create() {
        return new StateProvince();
    }

    public StateProvince getStateProvince(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = StateProvince.class)
    public static class StateProvinceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            StateProvinceController controller = (StateProvinceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "stateProvinceController");
            return controller.getStateProvince(getKey(value));
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
            if (object instanceof StateProvince) {
                StateProvince o = (StateProvince) object;
                return getStringKey(o.getStateProvinceId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + StateProvince.class.getName());
            }
        }
    }
}
