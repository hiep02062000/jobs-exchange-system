package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;

public class OffererFeature  implements java.io.Serializable {

    private Integer idOffererFeature;
    @Expose private Feature feature;
    private Offerer offerer;
    @Expose private Float level;

    public OffererFeature() {
        this.feature = null;
        this.level = null;
    }

    public OffererFeature(Feature feature, Offerer offerer) {
        this.feature = feature;
        this.offerer = offerer;
    }
    
    public OffererFeature(Feature feature, Offerer offerer, Float level) {
       this.feature = feature;
       this.offerer = offerer;
       this.level = level;
    }
   
    public Integer getIdOffererFeature() {
        return this.idOffererFeature;
    }
    
    public void setIdOffererFeature(Integer idOffererFeature) {
        this.idOffererFeature = idOffererFeature;
    }
    
    public Feature getFeature() {
        return this.feature;
    }
    
    public void setFeature(Feature feature) {
        this.feature = feature;
    }
    
    public Offerer getOfferer() {
        return this.offerer;
    }
    
    public void setOfferer(Offerer offerer) {
        this.offerer = offerer;
    }
    
    public Float getLevel() {
        return this.level;
    }
    
    public void setLevel(Float level) {
        this.level = level;
    }

}
