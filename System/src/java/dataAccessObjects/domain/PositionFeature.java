package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;

public class PositionFeature  implements java.io.Serializable {

    private Integer idPositionFeature;
    @Expose private Feature feature;
    private Position position;
    @Expose private Float level;

    public PositionFeature() {
        this.feature = null;
        this.level = null;
    }
	
    public PositionFeature(Feature feature, Position position) {
        this.feature = feature;
        this.position = position;
    }
    
    public PositionFeature(Feature feature, Position position, Float level) {
       this.feature = feature;
       this.position = position;
       this.level = level;
    }
   
    public Integer getIdPositionFeature() {
        return this.idPositionFeature;
    }
    
    public void setIdPositionFeature(Integer idPositionFeature) {
        this.idPositionFeature = idPositionFeature;
    }
    
    public Feature getFeature() {
        return this.feature;
    }
    
    public void setFeature(Feature feature) {
        this.feature = feature;
    }
    
    public Position getPosition() {
        return this.position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public Float getLevel() {
        return this.level;
    }
    
    public void setLevel(Float level) {
        this.level = level;
    }
    
}
