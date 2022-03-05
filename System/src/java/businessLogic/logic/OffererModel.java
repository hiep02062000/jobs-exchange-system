package businessLogic.logic;

import dataAccessObjects.dao.OffererDao;
import dataAccessObjects.domain.Login;
import dataAccessObjects.domain.Offerer;
import dataAccessObjects.domain.OffererFeature;
import java.util.List;

public class OffererModel {
    
    private OffererDao dao;
    private static OffererModel uniqueInstance;
     
    public static OffererModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new OffererModel();
        }
        return uniqueInstance;
    }
    
    private OffererModel(){
        dao = new OffererDao();
    }
    
    public void saveOfferer(Offerer o) throws Exception{
         dao.save(o);
    }
    
    public void deleteOfferer(Offerer o) throws Exception{
         dao.delete(o);
    }
    
    public void updateOfferer(Offerer o)throws Exception{
       dao.merge(o);
    }
    
    public List<Offerer> findAll(){
        return dao.findAll();
    }
    
    public Offerer findbyId(Integer id){
        return dao.findById(id);
    }
    
    public Offerer findByLogin(Login l){
        return dao.findByLogin(l);
    }
    
     public List<Offerer> listByName(String n){
        return dao.listByName(n);
    }
    
    
     public List<Offerer> findByParameters(List<OffererFeature> parameters){
        return dao.findByParameters(parameters);
    }
    
      public List<Offerer> findByParameters2(List<OffererFeature> parameters){
        return dao.findByParameters2(parameters);
    }
}