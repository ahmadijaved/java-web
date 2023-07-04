/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import javax.persistence.ManyToOne;
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
@Table(name = "planning")
@NamedQueries({
    @NamedQuery(name = "Planning.findAll", query = "SELECT p FROM Planning p"),
    @NamedQuery(name = "Planning.findByPlanningId", query = "SELECT p FROM Planning p WHERE p.planningId = :planningId"),
    @NamedQuery(name = "Planning.findByRoom", query = "SELECT p FROM Planning p WHERE p.room = :room"),
    @NamedQuery(name = "Planning.findBySeanceDate", query = "SELECT p FROM Planning p WHERE p.seanceDate = :seanceDate"),
    @NamedQuery(name = "Planning.findByStartHour", query = "SELECT p FROM Planning p WHERE p.startHour = :startHour")})
public class Planning implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "planning_id")
    private Integer planningId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "room")
    private String room;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seance_date")
    @Temporal(TemporalType.DATE)
    private Date seanceDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_hour")
    @Temporal(TemporalType.TIME)
    private Date startHour;
    @JoinColumn(name = "organized_ue_id", referencedColumnName = "organized_ue_id")
    @ManyToOne(optional = false)
    private OrganizedUe organizedUeId;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false)
    private Person personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planningId")
    private List<Presence> presenceList;

    public Planning() {
    }

    public Planning(Integer planningId) {
        this.planningId = planningId;
    }

    public Planning(Integer planningId, String room, Date seanceDate, Date startHour) {
        this.planningId = planningId;
        this.room = room;
        this.seanceDate = seanceDate;
        this.startHour = startHour;
    }

    public Integer getPlanningId() {
        return planningId;
    }

    public void setPlanningId(Integer planningId) {
        this.planningId = planningId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getSeanceDate() {
        return seanceDate;
    }

    public void setSeanceDate(Date seanceDate) {
        this.seanceDate = seanceDate;
    }

    public Date getStartHour() {
        return startHour;
    }

    public void setStartHour(Date startHour) {
        this.startHour = startHour;
    }

    public OrganizedUe getOrganizedUeId() {
        return organizedUeId;
    }

    public void setOrganizedUeId(OrganizedUe organizedUeId) {
        this.organizedUeId = organizedUeId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public List<Presence> getPresenceList() {
        return presenceList;
    }

    public void setPresenceList(List<Presence> presenceList) {
        this.presenceList = presenceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planningId != null ? planningId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planning)) {
            return false;
        }
        Planning other = (Planning) object;
        if ((this.planningId == null && other.planningId != null) || (this.planningId != null && !this.planningId.equals(other.planningId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Planning[ planningId=" + planningId + " ]";
    }
    
}
