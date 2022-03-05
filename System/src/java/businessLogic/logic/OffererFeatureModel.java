package businessLogic.logic;

import dataAccessObjects.dao.OffererFeatureDao;
import dataAccessObjects.domain.OffererFeature;
import java.util.List;

public class OffererFeatureModel {
      
    private OffererFeatureDao dao;
    private static OffererFeatureModel uniqueInstance;
     
    public static OffererFeatureModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new OffererFeatureModel();
        }
        return uniqueInstance;
    }
    
    private OffererFeatureModel(){
        dao = new OffererFeatureDao();
    }
    
    public void saveOffererFeature(OffererFeature o) throws Exception{
        dao.save(o);
    }
    
    public void deleteOffererFeature(OffererFeature o) throws Exception{
        dao.delete(o);
    }
    
    public void updateOffererFeature(OffererFeature o)throws Exception{
       dao.merge(o);
    }
    
    public List<OffererFeature> findAll(){
        return dao.findAll();
    }
    
    public OffererFeature findbyId(Integer id){
        return dao.findById(id);
    }
    
    public List<OffererFeature> findbyName(String n){
        return dao.findByName(n);
    }
    
    public List<OffererFeature> findAllByOffererId(Integer id) {
        return dao.findAllByOffererId(id);
    }
    
    public OffererFeature findByOffererIdAndFeatureName(Integer id, String n){
        return dao.findByOffererIdAndFeatureName(id, n);
    }
    
    public void update(OffererFeature f){
        dao.update(f);
    }
    
}
