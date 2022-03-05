package businessLogic.logic;

import dataAccessObjects.dao.PositionFeatureDao;
import dataAccessObjects.domain.PositionFeature;
import java.util.List;

public class PositionFeatureModel {
       
    private PositionFeatureDao dao;
    private static PositionFeatureModel uniqueInstance;
     
    public static PositionFeatureModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new PositionFeatureModel();
        }
        return uniqueInstance;
    }
    
    private PositionFeatureModel(){
        dao = new PositionFeatureDao();
    }
     
    public void savePositionFeatureModel(PositionFeature p) throws Exception{
        dao.save(p);
    }
    
    public void deletePositionFeatureModel(PositionFeature p) throws Exception{
         dao.delete(p);
    }
     
    public void updatePositionFeatureModel(PositionFeature p)throws Exception{
       dao.merge(p);
    }
     
    public List<PositionFeature> findAll(){
        return dao.findAll();
    }
     
    public PositionFeature findbyId(Integer id){
        return dao.findById(id);
    }
    
    public List<PositionFeature> findAllByPositionId(Integer id){
        return dao.findAllByPositionId(id);
    }
     
}