package dataAccessObjects.dao;

import dataAccessObjects.utils.HibernateUtil;
import dataAccessObjects.domain.PositionFeature;
import dataAccessObjects.domain.Position;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.*;

public class PositionDao extends HibernateUtil implements IBaseDAO<Position, Integer>{

    @Override
    public void save(Position p) {
        try{
            startOperation();
            getSesion().save(p);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
    }

    @Override
    public Position merge(Position p) throws HibernateException{
        try{
            startOperation();
            p=(Position)getSesion().merge(p);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        return p;
    }

    @Override
    public void delete(Position p) {
         try{
            startOperation();
            getSesion().delete(p);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
    }

    @Override
    public Position findById(Integer p) {
        Position position;
        try{
            startOperation();
            position=(Position)getSesion().get(Position.class, p);
        } finally{
            getSesion().close();
        } return position;
    }
    
    @Override
    public List<Position> findAll() {
        List<Position> positionsList;
        try{
            startOperation();
            positionsList=getSesion().createQuery("from Position").list();
        }finally{
            getSesion().close();
        } return positionsList;
    }
    
    public List<Position> top5(){
        List<Position> positions = new ArrayList<>();
        try{
            startOperation();
            Query query = getSesion().createQuery("from Position where publicPos=127 order by publishDate desc");
            query.setMaxResults(5);
            positions = query.list();
        } finally{
            getSesion().close();
        } return positions;
    }
    
    public List<Position> top5ForOfferer(){
        List<Position> positions = new ArrayList<>();
        try{
            startOperation();
            Query query = getSesion().createQuery("from Position order by publishDate desc");
            query.setMaxResults(5);
            positions = query.list();
        } finally{
            getSesion().close();
        } return positions;
    }
    
    public void update(Position p){
        try{
          startOperation();
          Query q = getSesion().createQuery("update Position set enable="+p.getEnable()
          +" where idPosition="+p.getIdPosition());
          q.executeUpdate();
          getTransac().commit();
        }catch(HibernateException h){
            System.out.println(h.getMessage());
        }
        finally{
            getSesion().close();
        }
    }
    
    public List<Position> listByName(String name) {
       List<Position> positions = new ArrayList<>();
       try{
           startOperation();
           Query query = getSesion().createQuery("from Position p join fetch "+
                   "p.company where p.name like '%"+name+"%'");
           query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
           positions = query.list();
       } finally{
           getSesion().close();
       } return positions;
    }
    
    public List<Position> findByCompanysName(String cName) {
        List<Position> positions = new ArrayList<Position>();
        try{
           startOperation();
           Query query = getSesion().createQuery("select p from Position p " +
             "join fetch p.company as c " + "where c.name='"+cName+"'");
           positions = query.list();
           } finally{
               getSesion().close();
        } return positions;
    }
    
    public Position findPositionByCompanyName(String cName, String pName) {
        Position positions = null;
        try{
           startOperation();
           Query query = getSesion().createQuery("select p from Position p " +
                   "join fetch p.company as c " + "where c.name='"+cName+"'"+
                   " and p.name='"+pName+"'");
           positions = (Position)query.uniqueResult();
           } finally{
               getSesion().close();
        } return positions;
    }
        
    public List<Position> findByParameters(List<PositionFeature> list) {
        List<Position> positions = new ArrayList<Position>();
        try{
           startOperation();

            String select="select p from Position p " +
            "join fetch p.company as c " +
            "join fetch p.positionfeatures as pf " +
            "inner join pf.feature as f " +
            "where";

            String values ="";

            for(PositionFeature p: list){
                values = values.concat(" (f.name='" + p.getFeature().getName() 
                         + "' and pf.level <="+String.valueOf(p.getLevel())+") or");
            } 
            values = values.substring(0, values.length() - 2);
            Query query = getSesion().createQuery(select + values);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            positions = query.list();

        } finally{
           getSesion().close();
        } 
           return positions;
    }
    
    public List<Position> findPublicPositionsByCompaniesIDs(List<String> list) {
        List<Position> positions = new ArrayList<Position>();
        try{
           startOperation();

            String select="select p from Position p " +
            "join fetch p.company as c " +
            "where";

            String values ="";

            for(String id: list){
                values = values.concat(" (c.idCompany="+ id +" and p.publicPos=127 and p.enable=127) or");
            } 
            values = values.substring(0, values.length() - 2);
            Query query = getSesion().createQuery(select + values);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            positions = query.list();

        } finally{
           getSesion().close();
        } 
           return positions;
    }
    
    public List<Position> findByCompaniesIDs(List<String> list) {
        List<Position> positions = new ArrayList<Position>();
        try{
           startOperation();

            String select="select p from Position p " +
            "join fetch p.company as c " +
            "where";

            String values ="";

            for(String id: list){
                values = values.concat(" c.idCompany="+ id +" or");
            } 
            values = values.substring(0, values.length() - 2);
            Query query = getSesion().createQuery(select + values);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            positions = query.list();

        } finally{
           getSesion().close();
        } 
           return positions;
    }
    
}