package be.isl.ue.ui;

import be.isl.ue.business.OrganizedUeFacade;
import be.isl.ue.entity.OrganizedUe;
import be.isl.ue.entity.Person;
import be.isl.ue.viewmodel.OrganizedUeSearchVM;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("organizedUeController")
@SessionScoped
public class OrganizedUeController
         extends AbstractController <OrganizedUe,OrganizedUeFacade,OrganizedUeSearchVM>
        implements Serializable {

    @Inject 
    private OrganizedUeFacade ejbFacade;
    private String sectionName;
    private String dateSco;
    private List<Person> selectedTeachers = new ArrayList<>();

    public OrganizedUeController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new OrganizedUeSearchVM());
    }

    @Override
    public OrganizedUeFacade getFacade() {
        return ejbFacade;
    }
    
    @Override
    public OrganizedUe create() {
        return new OrganizedUe();
    }
    
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    
    public List<Person> getSelectedTeachers() {
        return this.selectedTeachers;
    }
    
    
    public void setSelectedTeachers(List<Person> list) {
        this.selectedTeachers = list;
    }
    
    public String getSectionName() {
        return this.sectionName;
    }
    
    public void setDateSco(String date) {
        this.dateSco = date;
    }
    
    public void doCopy() {
        Optional<OrganizedUe> oragOptinal = this.getFacade().findAll()
                .stream()
                .filter(item-> item.getLevelId().getName().equals(this.getSectionName()))
                .findFirst();
        
        if(oragOptinal.isPresent()){
            OrganizedUe org = oragOptinal.get();
            this.ejbFacade.create(org);
        }
                
    }
    
    public List<Person> getTeachersByOrganizedUeSelected(){
        if(!this.getSelected().getPersonList().isEmpty())
            return this.getSelected().getPersonList()
                .stream()
                .filter(item-> item.getIsTeacher() == true)
                .toList();
        
        return new ArrayList<>();
    }
    
    public String getDateSco() {
        return this.dateSco;
    }

    public OrganizedUe getOrganizedUe(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = OrganizedUe.class)
    public static class OrganizedUeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrganizedUeController controller = (OrganizedUeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "organizedUeController");
            return controller.getOrganizedUe(getKey(value));
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
            if (object instanceof OrganizedUe) {
                OrganizedUe o = (OrganizedUe) object;
                return getStringKey(o.getOrganizedUeId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + OrganizedUe.class.getName());
            }
        }
    }
}
