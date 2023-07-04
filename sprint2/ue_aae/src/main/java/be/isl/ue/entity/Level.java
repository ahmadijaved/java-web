
package be.isl.ue.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ahmad
 */
@Entity
@Table(name = "level")
@NamedQueries({
    @NamedQuery(name = "Level.findAll", query = "SELECT l FROM Level l"),
    @NamedQuery(name = "Level.findByLevelId", query = "SELECT l FROM Level l WHERE l.levelId = :levelId"),
    @NamedQuery(name = "Level.findByName", query = "SELECT l FROM Level l WHERE l.name = :name"),
    @NamedQuery(name = "Level.findByInsertDatetime", query = "SELECT l FROM Level l WHERE l.insertDatetime = :insertDatetime")})
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "level_id")
    private Integer levelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "insert_datetime")
    @Temporal(TemporalType.TIME)
    private Date insertDatetime;
    @OneToMany(mappedBy = "levelId")
    private List<OrganizedUe> organizedUeList;

    public Level() {
    }

    public Level(Integer levelId) {
        this.levelId = levelId;
    }

    public Level(Integer levelId, String name, Date insertDatetime) {
        this.levelId = levelId;
        this.name = name;
        this.insertDatetime = insertDatetime;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInsertDatetime() {
        return insertDatetime;
    }

    public void setInsertDatetime(Date insertDatetime) {
        this.insertDatetime = insertDatetime;
    }

    public List<OrganizedUe> getOrganizedUeList() {
        return organizedUeList;
    }

    public void setOrganizedUeList(List<OrganizedUe> organizedUeList) {
        this.organizedUeList = organizedUeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (levelId != null ? levelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Level)) {
            return false;
        }
        Level other = (Level) object;
        if ((this.levelId == null && other.levelId != null) || (this.levelId != null && !this.levelId.equals(other.levelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Level[ levelId=" + levelId + " ]";
    }
    
}
