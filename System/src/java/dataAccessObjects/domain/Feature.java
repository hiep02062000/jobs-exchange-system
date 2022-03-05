package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class Feature  implements java.io.Serializable {

    @Expose private Integer idFeature;
    private Feature parentFeature;
    @Expose private String name;
    private Set<Feature> features = new HashSet<Feature>(0);

    public Feature() {
    }

    public Feature(Feature parentFeature, String name, Set<OffererFeature> offererfeatures) {
       this.parentFeature = parentFeature;
       this.name = name;
       this.features = features;
    }
   
    public Integer getIdFeature() {
        return this.idFeature;
    }
    
    public void setIdFeature(Integer idFeature) {
        this.idFeature = idFeature;
    }
    
    public Feature getparentFeature() {
        return this.parentFeature;
    }
    
    public void setparentFeature(Feature feature) {
        this.parentFeature = feature;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Feature> getFeatures() {
        return this.features;
    }
    
    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    public boolean hasChilds(){
        if(features.isEmpty()){
            return false;
        }
        return true;
    }
    
    public boolean hasParent(){
        if(this.parentFeature == null){
            return false;
        }
        return true;
    }
}
