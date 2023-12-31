package be.isl.ue.viewmodel;


public class UeSearchVM {

    private String name;
    private String description;
    private String sectionName;
    private String capacityname;
    private String code;
    private Boolean isDecisive;
    public UeSearchVM() {
    }

    public UeSearchVM(String name, String description, String sectionName, String capacityname, String code, Boolean isDecisive) {
        this.name = name;
        this.description = description;
        this.sectionName = sectionName;
        this.capacityname = capacityname;
        this.code = code;
        this.isDecisive = isDecisive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getCapacityname() {
        return capacityname;
    }

    public void setCapacityname(String capacityname) {
        this.capacityname = capacityname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getIsDecisive() {
       return this.isDecisive;
    }

    public void setIsDecisive(Boolean isDecisive) {
        this.isDecisive = isDecisive;
    }
}
