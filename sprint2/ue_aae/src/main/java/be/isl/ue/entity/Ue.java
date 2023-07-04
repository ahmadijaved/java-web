/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.isl.ue.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author ahmad
 */
@Entity
@Table(name = "ue")
@NamedQueries({
    @NamedQuery(name = "Ue.findAll", query = "SELECT u FROM Ue u"),
    @NamedQuery(name = "Ue.findByUeId", query = "SELECT u FROM Ue u WHERE u.ueId = :ueId"),
    @NamedQuery(name = "Ue.findByCode", query = "SELECT u FROM Ue u WHERE u.code = :code"),
    @NamedQuery(name = "Ue.findByDescription", query = "SELECT u FROM Ue u WHERE u.description = :description"),
    @NamedQuery(name = "Ue.findByIsDecisive", query = "SELECT u FROM Ue u WHERE u.isDecisive = :isDecisive"),
    @NamedQuery(name = "Ue.findByName", query = "SELECT u FROM Ue u WHERE u.name = :name"),
    @NamedQuery(name = "Ue.findByNumOfPeriods", query = "SELECT u FROM Ue u WHERE u.numOfPeriods = :numOfPeriods")})
public class Ue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ue_id")
    private Integer ueId;
    @Size(max = 255)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "is_decisive")
    private Boolean isDecisive;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "num_of_periods")
    private Integer numOfPeriods;
    @ManyToMany(mappedBy = "ueList")
    private List<OrganizedUe> organizedUeList;
    @ManyToMany(mappedBy = "ueList")
    private List<Capacity> capacityList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ueId")
    private List<Capacity> capacityList1;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    @ManyToOne
    private Section sectionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ueId")
    private List<OrganizedUe> organizedUeList1;

    public Ue() {
    }

    public Ue(Integer ueId) {
        this.ueId = ueId;
    }

    public Ue(Integer ueId, String name) {
        this.ueId = ueId;
        this.name = name;
    }

    public Integer getUeId() {
        return ueId;
    }

    public void setUeId(Integer ueId) {
        this.ueId = ueId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDecisive() {
        return isDecisive;
    }

    public void setIsDecisive(Boolean isDecisive) {
        this.isDecisive = isDecisive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumOfPeriods() {
        return numOfPeriods;
    }

    public void setNumOfPeriods(Integer numOfPeriods) {
        this.numOfPeriods = numOfPeriods;
    }

    public List<OrganizedUe> getOrganizedUeList() {
        return organizedUeList;
    }

    public void setOrganizedUeList(List<OrganizedUe> organizedUeList) {
        this.organizedUeList = organizedUeList;
    }

    public List<Capacity> getCapacityList() {
        return capacityList;
    }

    public void setCapacityList(List<Capacity> capacityList) {
        this.capacityList = capacityList;
    }

    public List<Capacity> getCapacityList1() {
        return capacityList1;
    }

    public void setCapacityList1(List<Capacity> capacityList1) {
        this.capacityList1 = capacityList1;
    }

    public Section getSectionId() {
        return sectionId;
    }

    public void setSectionId(Section sectionId) {
        this.sectionId = sectionId;
    }

    public List<OrganizedUe> getOrganizedUeList1() {
        return organizedUeList1;
    }

    public void setOrganizedUeList1(List<OrganizedUe> organizedUeList1) {
        this.organizedUeList1 = organizedUeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ueId != null ? ueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ue)) {
            return false;
        }
        Ue other = (Ue) object;
        if ((this.ueId == null && other.ueId != null) || (this.ueId != null && !this.ueId.equals(other.ueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Ue[ ueId=" + ueId + " ]";
    }
    
}
