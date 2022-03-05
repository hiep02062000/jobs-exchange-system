package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;

public class Company  implements java.io.Serializable {

    @Expose private Integer idCompany;
    private Login login;
    @Expose private String name;
    private String description;
    @Expose private String phoneNumber;
    @Expose private Double longitude;
    @Expose private Double latitude;

    public Company() {
    }
	
    public Company(Login login) {
        this.login = login;
    }
    
    public Company(Login login, String name, String description, String phoneNumber, Double longitude, 
                   Double latitude) {
        
       this.login = login;
       this.name = name;
       this.description = description;
       this.phoneNumber = phoneNumber;
       this.longitude = longitude;
       this.latitude = latitude;
    }
   
    public Integer getIdCompany() {
        return this.idCompany;
    }
    
    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }
    
    public Login getLogin() {
        return this.login;
    }
    
    public void setLogin(Login login) {
        this.login = login;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public Double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
