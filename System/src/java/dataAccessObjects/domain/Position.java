package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Position  implements java.io.Serializable {

    @Expose private Integer idPosition;
    @Expose private Company company;
    @Expose private String name;
    @Expose private Date publishDate;
    @Expose private Double salary;
    @Expose private Byte publicPos;
    private byte enable; //127 enable, -218 disable...
    
    @Expose private Set<PositionFeature> positionfeatures = new HashSet<PositionFeature>(0);

    public Position() {
    }
	
    public Position(Company company) {
        this.company = company;
    }
    
    public Position(Company company, String name, Date publishDate, Double salary, Byte publicPos, 
                Set<PositionFeature> positionfeatures) {
        
       this.company = company;
       this.name = name;
       this.publishDate = publishDate;
       this.salary = salary;
       this.publicPos = publicPos;
       this.positionfeatures = positionfeatures;
    }
   
    public Integer getIdPosition() {
        return this.idPosition;
    }
    
    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }
    
    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getPublishDate() {
        return this.publishDate;
    }
    
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    public Double getSalary() {
        return this.salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    public Byte getPublicPos() {
        return this.publicPos;
    }
    
    public void setPublicPos(Byte publicPos) {
        this.publicPos = publicPos;
    }
    
    public Set<PositionFeature> getPositionfeatures() {
        return this.positionfeatures;
    }
    
    public void setPositionfeatures(Set<PositionFeature> positionfeatures) {
        this.positionfeatures = positionfeatures;
    }

    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

}
