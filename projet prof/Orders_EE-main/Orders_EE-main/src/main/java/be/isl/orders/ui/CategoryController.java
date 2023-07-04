package be.isl.orders.ui;

import be.isl.orders.entity.Category;
import be.isl.orders.business.CategoryFacade;
import be.isl.orders.ui.viewmodel.CategorySearch;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("categoryController")
@SessionScoped
public class CategoryController
        extends AbstractController <Category, CategoryFacade, CategorySearch>
        implements Serializable {

    @Inject
    private CategoryFacade ejbFacade;

    public CategoryController() {
    }
    
    @PostConstruct
    public void init() {
        setSearch(new CategorySearch());
    }

    @Override
    public CategoryFacade getFacade() {
        return ejbFacade;
    }

    @Override
    public Category create() {
        return new Category();
    }

    public Category getCategory(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Category.class)
    public static class CategoryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoryController controller = (CategoryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoryController");
            return controller.getCategory(getKey(value));
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
            if (object instanceof Category) {
                Category o = (Category) object;
                return getStringKey(o.getCategoryId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Category.class.getName());
            }
        }

    }
}
