
package be.isl.ue.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "section")
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findBySectionId", query = "SELECT s FROM Section s WHERE s.sectionId = :sectionId"),
    @NamedQuery(name = "Section.findByDescription", query = "SELECT s FROM Section s WHERE s.description = :description"),
    @NamedQuery(name = "Section.findByName", query = "SELECT s FROM Section s WHERE s.name = :name")})
public class Section extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "section_id")
    private Integer sectionId;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne
    private Person personId;
    @OneToMany(mappedBy = "sectionId")
    private List<Ue> ueList;

    public Section() {
    }

    public Section(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Section(Integer sectionId, String name) {
        this.sectionId = sectionId;
        this.name = name;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public List<Ue> getUeList() {
        return ueList;
    }

    public void setUeList(List<Ue> ueList) {
        this.ueList = ueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sectionId != null ? sectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.sectionId == null && other.sectionId != null) || (this.sectionId != null && !this.sectionId.equals(other.sectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Section[ sectionId=" + sectionId + " ]";
    }

    @Override
    public Integer getId() {
        return this.sectionId;
    }
    
}
