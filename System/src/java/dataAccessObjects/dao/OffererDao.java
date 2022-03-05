package dataAccessObjects.dao;

import dataAccessObjects.domain.Login;
import dataAccessObjects.domain.Offerer;
import dataAccessObjects.domain.OffererFeature;
import java.util.List;
import org.hibernate.HibernateException;
import dataAccessObjects.utils.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

public class OffererDao extends HibernateUtil implements IBaseDAO<Offerer, Integer>{

    @Override
    public void save(Offerer o) {
        try{
            startOperation();
            getSesion().save(o);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
    }

    @Override
    public Offerer merge(Offerer o) throws HibernateException{
        try{
            startOperation();
            o=(Offerer)getSesion().merge(o);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        return o;
    }

    @Override
    public void delete(Offerer o) {
         try{
            startOperation();
            getSesion().delete(o);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        
    }

    @Override
    public Offerer findById(Integer o) {
        Offerer offerer = null;
        try{
            startOperation();
            offerer=(Offerer)getSesion().get(Offerer.class, o);
        } finally{
            getSesion().close();
        } return offerer;
    }

    @Override
    public List<Offerer> findAll() {
        List<Offerer> offerersList;
        try{
            startOperation();
            offerersList=getSesion().createQuery("from Offerer").list();
        }finally{
            getSesion().close();
        } return offerersList;
    }
    
    public Offerer findByLogin(Login l){
        Offerer offerer = null;
        try{
            startOperation();
            offerer=(Offerer)getSesion().createQuery("select f from Offerer f join fetch f.login as l where l.email='" 
                    + l.getEmail() + "' and l.password='" + l.getPassword() + "'").uniqueResult();
        } finally{
            getSesion().close();
        } return offerer;
    }
    
    public List<Offerer> listByName(String name) {
      List<Offerer> offerers = new ArrayList<>();
      try{
          startOperation();
          Query query = getSesion().createQuery("from Offerer"+
                  "where name like '%"+name+"%'");
          query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
          offerers = query.list();
       } finally{
           getSesion().close();
       } return offerers;
    }
    
    
    public List<Offerer> findByParameters(List<OffererFeature> list) {
        List<Offerer> offerers = new ArrayList<Offerer>();
        try{
           startOperation();

            String select="select p from Offerer p " +
            "join fetch p.offererfeatures as pf " +
            "inner join pf.feature as f " +
            "where";

            String values ="";

            for(OffererFeature p: list){
                values = values.concat(" (f.name='" + p.getFeature().getName() + "' and pf.level <="+String.valueOf(p.getLevel())+") or");
            } 
            values = values.substring(0, values.length() - 2);
            Query query = getSesion().createQuery(select + values);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            offerers = query.list();

        } finally{
           getSesion().close();
        } 
       return offerers;
    }
    
    
      public List<Offerer> findByParameters2(List<OffererFeature> list) {
        List<Offerer> offerers = new ArrayList<Offerer>();
        try{
           startOperation();

            String select="select p from Offerer p " +
            "join fetch p.offererfeatures as pf " +
            "inner join pf.feature as f " +
            "where";

            String values ="";

            for(OffererFeature p: list){
                values = values.concat(" (f.name='" + p.getFeature().getName() + "' and pf.level >="+String.valueOf(p.getLevel())+") or");
            } 
            values = values.substring(0, values.length() - 2);
            Query query = getSesion().createQuery(select + values);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            offerers = query.list();

        } finally{
           getSesion().close();
        } 
       return offerers;
    }
    
    
}
