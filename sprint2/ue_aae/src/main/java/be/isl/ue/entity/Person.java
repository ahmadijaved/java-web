
package be.isl.ue.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.Entity;

import javax.persistence.Table;

import javax.persistence.NamedQuery;



@Entity
@Table(name = "person")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId"),
    @NamedQuery(name = "Person.findByAddress", query = "SELECT p FROM Person p WHERE p.address = :address"),
    @NamedQuery(name = "Person.findByCity", query = "SELECT p FROM Person p WHERE p.city = :city"),
    @NamedQuery(name = "Person.findByCountry", query = "SELECT p FROM Person p WHERE p.country = :country"),
    @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
    @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Person.findByIsJuryMember", query = "SELECT p FROM Person p WHERE p.isJuryMember = :isJuryMember"),
    @NamedQuery(name = "Person.findByIsTeacher", query = "SELECT p FROM Person p WHERE p.isTeacher = :isTeacher"),
    @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Person.findByMobile", query = "SELECT p FROM Person p WHERE p.mobile = :mobile"),
    @NamedQuery(name = "Person.findByPostalCode", query = "SELECT p FROM Person p WHERE p.postalCode = :postalCode")})
public class Person extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 255)
    @Column(name = "city")
    private String city;
    @Size(max = 255)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "is_jury_member")
    private Boolean isJuryMember;
    @Column(name = "is_teacher")
    private Boolean isTeacher;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 255)
    @Column(name = "mobile")
    private String mobile;
    @Size(max = 255)
    @Column(name = "postal_code")
    private String postalCode;
    @ManyToMany(mappedBy = "personList")
    private List<OrganizedUe> organizedUeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<StudentOrganizedUe> studentOrganizedUeList;
    @OneToMany(mappedBy = "personId")
    private List<Section> sectionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private List<Planning> planningList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private List<Presence> presenceList;
    @OneToMany(mappedBy = "personId")
    private List<PersonSecurity> personSecurityList;

    public Person() {
    }

    public Person(Integer personId) {
        this.personId = personId;
    }

    public Person(Integer personId, Date dateOfBirth, String firstName, String lastName) {
        this.personId = personId;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getIsJuryMember() {
        return isJuryMember;
    }

    public void setIsJuryMember(Boolean isJuryMember) {
        this.isJuryMember = isJuryMember;
    }

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<OrganizedUe> getOrganizedUeList() {
        return organizedUeList;
    }

    public void setOrganizedUeList(List<OrganizedUe> organizedUeList) {
        this.organizedUeList = organizedUeList;
    }

    public List<StudentOrganizedUe> getStudentOrganizedUeList() {
        return studentOrganizedUeList;
    }

    public void setStudentOrganizedUeList(List<StudentOrganizedUe> studentOrganizedUeList) {
        this.studentOrganizedUeList = studentOrganizedUeList;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public List<Planning> getPlanningList() {
        return planningList;
    }

    public void setPlanningList(List<Planning> planningList) {
        this.planningList = planningList;
    }

    public List<Presence> getPresenceList() {
        return presenceList;
    }

    public void setPresenceList(List<Presence> presenceList) {
        this.presenceList = presenceList;
    }

    public List<PersonSecurity> getPersonSecurityList() {
        return personSecurityList;
    }

    public void setPersonSecurityList(List<PersonSecurity> personSecurityList) {
        this.personSecurityList = personSecurityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.isl.ue.entity.Person[ personId=" + personId + " ]";
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
