package businessLogic.logic;

import dataAccessObjects.dao.PositionDao;
import dataAccessObjects.domain.Position;
import dataAccessObjects.domain.PositionFeature;
import java.util.List;

public class PositionModel {
    
    private PositionDao dao;
    private static PositionModel uniqueInstance;
     
    public static PositionModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new PositionModel();
        }
        return uniqueInstance;
    }
    
    private PositionModel(){
        dao = new PositionDao();
    }
    
    public void savePosition(Position p) throws Exception{
        dao.save(p);
    }
     
    public void deletePosition(Position p) throws Exception{
        dao.delete(p);
    }
    
    public void updatePosition(Position p)throws Exception{
       dao.merge(p);
    }
    
    public List<Position> findAll(){
        return dao.findAll();
    }
    
    public Position findbyId(Integer id){
        return dao.findById(id);
    }
    
    public List<Position> top5(){
        return dao.top5();
    }
    public List<Position> top5ForOfferer(){
        return dao.top5ForOfferer();
    }
    
    public List<Position> listByName(String n){
        return dao.listByName(n);
    }
    
     public List<Position> findByParameters(List<PositionFeature> pf){
        return dao.findByParameters(pf);
    }
    
    public List<Position> findByCompanysName(String n){
        return dao.findByCompanysName(n);
    }
    
    public void update(Position p){
        dao.update(p);
    }
    
    public Position findPosByCompanysName(String cName, String pName){
        return dao.findPositionByCompanyName(cName,pName);
    }
    
    public List<Position> findPublicPositionsByCompaniesIDs(List<String> list) {
        return dao.findPublicPositionsByCompaniesIDs(list);
    }
    public List<Position> findByCompaniesIDs(List<String> list) {
        return dao.findByCompaniesIDs(list);
    }
}