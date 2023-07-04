package be.isl.orders.ui;

import be.isl.orders.entity.Buyer;
import be.isl.orders.ui.util.JsfUtil;
import be.isl.orders.business.BuyerFacade;
import be.isl.orders.business.StateProvinceFacade;
import be.isl.orders.ui.viewmodel.BuyerSearch;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Named("buyerController")
@SessionScoped
public class BuyerController
        extends AbstractController<Buyer, BuyerFacade, BuyerSearch>
        implements Serializable {

    @Inject
    private BuyerFacade ejbFacade;
    @Inject
    StateProvinceFacade stateProvinceFacade;
    private SelectItem[] stateProvinceItems;

    public BuyerController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new BuyerSearch());
    }

    @Override
    public BuyerFacade getFacade() {
        return ejbFacade;
    }

    public SelectItem[] getStateProvinceItems() {
        return stateProvinceItems;
    }

    @Override
    public Buyer create() {
        return new Buyer();
    }

    @Override
    public String prepareEdit(Buyer b) {
        super.setSelected(b);
        if (getSelected() != null && ((Buyer) getSelected()).getCountry() != null) {
            stateProvinceItems = JsfUtil.getSelectItems(stateProvinceFacade.findByCountry(((Buyer) getSelected()).getCountry()), true);
        }
        return "buyer";
    }

    public void listenerCountrySelected(AjaxBehaviorEvent event) {
        if (getSelected() != null) {
            stateProvinceItems = JsfUtil.getSelectItems(stateProvinceFacade.findByCountry(((Buyer) getSelected()).getCountry()), true);
        } else {
            stateProvinceItems = null;
        }
    }

    public Buyer getBuyer(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Buyer.class)
    public static class BuyerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BuyerController controller = (BuyerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "buyerController");
            return controller.getBuyer(getKey(value));
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
            if (object instanceof Buyer) {
                Buyer o = (Buyer) object;
                return getStringKey(o.getBuyerId());
            } else {
                throw new IllegalArgumentException("object " + object + " is o type " + object.getClass().getName() + "; expected type: " + Buyer.class.getName());
            }
        }

    }

}
