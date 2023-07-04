package be.isl.orders.ui;

import be.isl.orders.entity.Article;
import be.isl.orders.business.ArticleFacade;
import be.isl.orders.ui.viewmodel.ArticleSearch;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("articleController")
@SessionScoped
public class ArticleController
        extends AbstractController<Article, ArticleFacade, ArticleSearch>
        implements Serializable {

    @Inject
    private ArticleFacade ejbFacade;

    public ArticleController() {
    }

    @PostConstruct
    public void init() {
        setSearch(new ArticleSearch());
    }

    @Override
    public ArticleFacade getFacade() {
        return ejbFacade;
    }
    
    @Override
    public Article create() {
        return new Article();
    }

    public Article getArticle(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Article.class)
    public static class ArticleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticleController controller = (ArticleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articleController");
            return controller.getArticle(getKey(value));
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
            if (object instanceof Article) {
                Article o = (Article) object;
                return getStringKey(o.getArticleId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Article.class.getName());
            }
        }
    }
}
