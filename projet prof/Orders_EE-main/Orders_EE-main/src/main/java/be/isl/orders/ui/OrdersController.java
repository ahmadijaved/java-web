package be.isl.orders.ui;

import be.isl.orders.business.OrderLineFacade;
import be.isl.orders.entity.Orders;
import be.isl.orders.ui.util.JsfUtil;
import be.isl.orders.business.OrdersFacade;
import be.isl.orders.entity.OrderLine;
import be.isl.orders.ui.viewmodel.OrdersSearch;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("ordersController")
@SessionScoped
public class OrdersController
        extends AbstractController<Orders, OrdersFacade, OrdersSearch>
        implements Serializable {

    @Inject
    private OrdersFacade ejbFacade;
    @Inject
    private OrderLineFacade orderLineFacade;

    public OrdersController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new OrdersSearch());
    }

    @Override
    public OrdersFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public Orders create() {
        return new Orders();
    }

    @Override
    public String doUpdate() {
        try {
            if (getSelected().getId() == null) {
                getFacade().create(getSelected());
                doSearch();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdersCreated"));
            } else {
                for (OrderLine ol : ((Orders) getSelected()).getOrderLines()) {
                    if (ol.getOrderLineId() == null) {
                        orderLineFacade.create(ol);
                    } else {
                        orderLineFacade.edit(ol);
                    }
                }
                getFacade().edit(getSelected());
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("OrdersUpdated"));
            }
            return FacesContext.getCurrentInstance().getViewRoot().getViewId();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String doOLNew() {
        OrderLine ol = new OrderLine();
        ol.setOrder(getSelected());
        ((Orders) getSelected()).getOrderLines().add(ol);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public String doOLRemove(OrderLine ol) {
        ((Orders) getSelected()).getOrderLines().remove(ol);
        ejbFacade.edit(getSelected());
        orderLineFacade.remove(ol);
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public Orders getOrders(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Orders.class)
    public static class OrdersControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdersController controller = (OrdersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordersController");
            return controller.getOrders(getKey(value));
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
            if (object instanceof Orders) {
                Orders o = (Orders) object;
                return getStringKey(o.getOrderId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Orders.class.getName());
            }
        }

    }
}
