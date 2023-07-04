package be.isl.ue.ui;

import be.isl.ue.business.SectionFacade;
import be.isl.ue.entity.Section;
import be.isl.ue.viewmodel.SectionSearchVM;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("sectionController")
@SessionScoped
public class SectionController
         extends AbstractController <Section,SectionFacade,SectionSearchVM>
        implements Serializable {

    @Inject 
    private SectionFacade ejbFacade;
    private Section sectionCopy;

    public SectionController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new SectionSearchVM());
    }

    @Override
    public SectionFacade getFacade() {
        return ejbFacade;
    }
    
    @Override
    public Section create() {
        /*Person p = new Person();
        p.setDateOfBirth(new Date());*/
        return new Section();
    }
    
    @Override
    public void doDelete() {
        doCopy(); // faire une copier avant de supprimer
        super.doDelete();
    }
    
    public void doCopy() {
        if(this.getSelected() != null) {
            this.sectionCopy = this.getSelected();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Copie", "La section" + this.getSelected().getName() +"est bien copiée"));
        }
    }

    public Section getSection(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Section.class)
    public static class SectionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonController controller = (PersonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sectionController");
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
            if (object instanceof Section) {
                Section o = (Section) object;
                return getStringKey(o.getSectionId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Section.class.getName());
            }
        }
    }
}
