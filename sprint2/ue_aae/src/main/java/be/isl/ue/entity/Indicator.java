
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
import javax.validation.constraints.Size;

/**
 *
 * @author ahmad
 */
@Entity
@Table(name = "indicator")
@NamedQueries({
    @NamedQuery(name = "Indicator.findAll", query = "SELECT i FROM Indicator i"),
    @NamedQuery(name = "Indicator.findByIndicatorId", query = "SELECT i FROM Indicator i WHERE i.indicatorId = :indicatorId"),
    @NamedQuery(name = "Indicator.findByDescription", query = "SELECT i FROM Indicator i WHERE i.description = :description"),
    @NamedQuery(name = "Indicator.findByIsCriteria", query = "SELECT i FROM Indicator i WHERE i.isCriteria = :isCriteria"),
    @NamedQuery(name = "Indicator.findByMaxPossible", query = "SELECT i FROM Indicator i WHERE i.maxPossible = :maxPossible"),
    @NamedQuery(name = "Indicator.findByName", query = "SELECT i FROM Indicator i WHERE i.name = :name")})
public class Indicator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "indicator_id")
    private Integer indicatorId;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "is_criteria")
    private Boolean isCriteria;
    @Size(max = 255)
    @Column(name = "max_possible")
    private String maxPossible;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "capacity_id", referencedColumnName = "capacity_id")
    @ManyToOne
    private Capacity capacityId;
    @OneToMany(mappedBy = "parentIndicatorId")
    private List<Indicator> indicatorList;
    @JoinColumn(name = "parent_indicator_id", referencedColumnName = "indicator_id")
    @ManyToOne
    private Indicator parentIndicatorId;
    @JoinColumn(name = "organized_ue_id", referencedColumnName = "organized_ue_id")
    @ManyToOne
    private OrganizedUe organizedUeId;
    @OneToMany(mappedBy = "indicatorId")
    private List<Evaluation> evaluationList;

    public Indicator() {
    }

    public Indicator(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public Integer getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(Integer indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCriteria() {
        return isCriteria;
    }

    public void setIsCriteria(Boolean isCriteria) {
        this.isCriteria = isCriteria;
    }

    public String getMaxPossible() {
        return maxPossible;
    }

    public void setMaxPossible(String maxPossible) {
        this.maxPossible = maxPossible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Capacity getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(Capacity capacityId) {
        this.capacityId = capacityId;
    }

    public List<Indicator> getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(List<Indicator> indicatorList) {
        this.indicatorList = indicatorList;
    }

    public Indicator getParentIndicatorId() {
        return parentIndicatorId;
    }

    public void setParentIndicatorId(Indicator parentIndicatorId) {
        this.parentIndicatorId = parentIndicatorId;
    }

    public OrganizedUe getOrganizedUeId() {
        return organizedUeId;
    }

    public void setOrganizedUeId(OrganizedUe organizedUeId) {
        this.organizedUeId = organizedUeId;
    }

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indicatorId != null ? indicatorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicator)) {
            return false;
        }
        Indicator other = (Indicator) object;
        if ((this.indicatorId == null && other.indicatorId != null) || (this.indicatorId != null && !this.indicatorId.equals(other.indicatorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Indicator[ indicatorId=" + indicatorId + " ]";
    }
    
}
