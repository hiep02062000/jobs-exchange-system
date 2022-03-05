package businessLogic.logic;

import dataAccessObjects.dao.FeatureDao;
import dataAccessObjects.domain.Feature;
import java.util.List;

public class FeatureModel {
    
    private FeatureDao dao;
    private static FeatureModel uniqueInstance;
     
    public static FeatureModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new FeatureModel();
        }
        return uniqueInstance;
    }
    
    private FeatureModel(){
        dao = new FeatureDao();
    }
    
    public void saveFeature(Feature f) throws Exception{
        dao.save(f);
    }
    
    public void deleteFeature(Feature f) throws Exception{
        dao.delete(f);
    }
    
    public void updateFeature(Feature f)throws Exception{
       dao.merge(f);
    }
    
    public List<Feature> findAll(){
        return dao.findAll();
    }
    
    public Feature findbyId(Integer id){
        return dao.findById(id);
    }
    
    public List<Feature> findRootFeatures(){
        return dao.findRootFeatures();
    }
    
    public List<Feature> findFeaturesByParentName(String name){
        return dao.findFeaturesByParentName(name);
    }
    
    public List<Feature> findFeaturesByParentId(Integer id){
        return dao.findFeaturesByParentId(id);
    }
    
    public Feature findByName(String Name){
        return dao.findByName(Name);
    }
    
     public Feature findByNameNoParentJoin(String Name){
        return dao.findByNameNoParentJoin(Name);
    }

}