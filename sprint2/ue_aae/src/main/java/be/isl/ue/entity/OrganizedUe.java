
package be.isl.ue.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "organized_ue")
@NamedQueries({
    @NamedQuery(name = "OrganizedUe.findAll", query = "SELECT o FROM OrganizedUe o"),
    @NamedQuery(name = "OrganizedUe.findByOrganizedUeId", query = "SELECT o FROM OrganizedUe o WHERE o.organizedUeId = :organizedUeId"),
    @NamedQuery(name = "OrganizedUe.findByEndDate", query = "SELECT o FROM OrganizedUe o WHERE o.endDate = :endDate"),
    @NamedQuery(name = "OrganizedUe.findByName", query = "SELECT o FROM OrganizedUe o WHERE o.name = :name"),
    @NamedQuery(name = "OrganizedUe.findByStartDate", query = "SELECT o FROM OrganizedUe o WHERE o.startDate = :startDate")})
public class OrganizedUe extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "organized_ue_id")
    private Integer organizedUeId;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @JoinTable(name = "ue_organized_ue", joinColumns = {
        @JoinColumn(name = "organized_organized_ue_id", referencedColumnName = "organized_ue_id")}, inverseJoinColumns = {
        @JoinColumn(name = "ue_ue_id", referencedColumnName = "ue_id")})
    @ManyToMany
    private List<Ue> ueList;
    @JoinTable(name = "teacher_organized_ue", joinColumns = {
        @JoinColumn(name = "organized_ue_id", referencedColumnName = "organized_ue_id")}, inverseJoinColumns = {
        @JoinColumn(name = "person_id", referencedColumnName = "person_id")})
    @ManyToMany
    private List<Person> personList;
    @OneToMany(mappedBy = "organizedUeId")
    private List<Indicator> indicatorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizedUe")
    private List<StudentOrganizedUe> studentOrganizedUeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizedUeId")
    private List<Planning> planningList;
    @JoinColumn(name = "level_id", referencedColumnName = "level_id")
    @ManyToOne
    private Level levelId;
    @JoinColumn(name = "ue_id", referencedColumnName = "ue_id")
    @ManyToOne(optional = false)
    private Ue ueId;

    public OrganizedUe() {
    }

    public OrganizedUe(Integer organizedUeId) {
        this.organizedUeId = organizedUeId;
    }

    public OrganizedUe(Integer organizedUeId, String name, Date startDate) {
        this.organizedUeId = organizedUeId;
        this.name = name;
        this.startDate = startDate;
    }

    public Integer getOrganizedUeId() {
        return organizedUeId;
    }

    public void setOrganizedUeId(Integer organizedUeId) {
        this.organizedUeId = organizedUeId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Ue> getUeList() {
        return ueList;
    }

    public void setUeList(List<Ue> ueList) {
        this.ueList = ueList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Indicator> getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(List<Indicator> indicatorList) {
        this.indicatorList = indicatorList;
    }

    public List<StudentOrganizedUe> getStudentOrganizedUeList() {
        return studentOrganizedUeList;
    }

    public void setStudentOrganizedUeList(List<StudentOrganizedUe> studentOrganizedUeList) {
        this.studentOrganizedUeList = studentOrganizedUeList;
    }

    public List<Planning> getPlanningList() {
        return planningList;
    }

    public void setPlanningList(List<Planning> planningList) {
        this.planningList = planningList;
    }

    public Level getLevelId() {
        return levelId;
    }

    public void setLevelId(Level levelId) {
        this.levelId = levelId;
    }

    public Ue getUeId() {
        return ueId;
    }

    public void setUeId(Ue ueId) {
        this.ueId = ueId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (organizedUeId != null ? organizedUeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizedUe)) {
            return false;
        }
        OrganizedUe other = (OrganizedUe) object;
        if ((this.organizedUeId == null && other.organizedUeId != null) || (this.organizedUeId != null && !this.organizedUeId.equals(other.organizedUeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.OrganizedUe[ organizedUeId=" + organizedUeId + " ]";
    }

    @Override
    public Integer getId() {
       return this.organizedUeId;
    }
    
}
