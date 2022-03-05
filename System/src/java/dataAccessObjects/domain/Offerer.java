package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class Offerer  implements java.io.Serializable {

    @Expose private Integer idOfferer;
    @Expose private Login login;
    @Expose private String name;
    @Expose private String lastName;
    @Expose private String originCountry;
    @Expose private String phoneNumber;

    @Expose private Set<OffererFeature> offererfeatures = new HashSet<OffererFeature>(0);
    
    public Offerer() {
    }
	
    public Offerer(Integer idOfferer, Login login) {
        this.idOfferer = idOfferer;
        this.login = login;
    }
    
    public Offerer(Integer idOfferer, Login login, String name, String lastName, String originCountry, 
                String phoneNumber, Set<OffererFeature> features) {
        
       this.idOfferer = idOfferer;
       this.login = login;
       this.name = name;
       this.lastName = lastName;
       this.originCountry = originCountry;
       this.phoneNumber = phoneNumber;
       this.offererfeatures=features;
    }
   
    public Integer getIdOfferer() {
        return this.idOfferer;
    }
    
    public void setIdOfferer(Integer idOfferer) {
        this.idOfferer = idOfferer;
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
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getOriginCountry() {
        return this.originCountry;
    }
    
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOffererfeatures(Set<OffererFeature> offererfeatures) {
        this.offererfeatures = offererfeatures;
    }

    public Set<OffererFeature> getOffererfeatures() {
        return offererfeatures;
    }

}
