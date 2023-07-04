
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
<<<<<<< OURS
 * @author ahmad
=======
 * @author ahmadi
>>>>>>> THEIRS
 */
@Entity
@Table(name = "capacity")
@NamedQueries({
    @NamedQuery(name = "Capacity.findAll", query = "SELECT c FROM Capacity c"),
    @NamedQuery(name = "Capacity.findByCapacityId", query = "SELECT c FROM Capacity c WHERE c.capacityId = :capacityId"),
    @NamedQuery(name = "Capacity.findByDescription", query = "SELECT c FROM Capacity c WHERE c.description = :description"),
    @NamedQuery(name = "Capacity.findByIsThresholdOfSuccess", query = "SELECT c FROM Capacity c WHERE c.isThresholdOfSuccess = :isThresholdOfSuccess"),
    @NamedQuery(name = "Capacity.findByName", query = "SELECT c FROM Capacity c WHERE c.name = :name")})
public class Capacity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "capacity_id")
    private Integer capacityId;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "is_threshold_of_success")
    private Boolean isThresholdOfSuccess;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "ue_capacity", joinColumns = {
        @JoinColumn(name = "capacities_capacity_id", referencedColumnName = "capacity_id")}, inverseJoinColumns = {
        @JoinColumn(name = "ue_ue_id", referencedColumnName = "ue_id")})
    @ManyToMany
    private List<Ue> ueList;
    @OneToMany(mappedBy = "capacityId")
    private List<Indicator> indicatorList;
    @JoinColumn(name = "ue_id", referencedColumnName = "ue_id")
    @ManyToOne(optional = false)
    private Ue ueId;

    public Capacity() {
    }

    public Capacity(Integer capacityId) {
        this.capacityId = capacityId;
    }

    public Capacity(Integer capacityId, String name) {
        this.capacityId = capacityId;
        this.name = name;
    }

    public Integer getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(Integer capacityId) {
        this.capacityId = capacityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsThresholdOfSuccess() {
        return isThresholdOfSuccess;
    }

    public void setIsThresholdOfSuccess(Boolean isThresholdOfSuccess) {
        this.isThresholdOfSuccess = isThresholdOfSuccess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ue> getUeList() {
        return ueList;
    }

    public void setUeList(List<Ue> ueList) {
        this.ueList = ueList;
    }

    public List<Indicator> getIndicatorList() {
        return indicatorList;
    }

    public void setIndicatorList(List<Indicator> indicatorList) {
        this.indicatorList = indicatorList;
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
        hash += (capacityId != null ? capacityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacity)) {
            return false;
        }
        Capacity other = (Capacity) object;
        if ((this.capacityId == null && other.capacityId != null) || (this.capacityId != null && !this.capacityId.equals(other.capacityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Capacity[ capacityId=" + capacityId + " ]";
    }
    
}
