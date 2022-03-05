package dataAccessObjects.dao;

import dataAccessObjects.domain.OffererFeature;
import java.util.List;
import org.hibernate.HibernateException;
import dataAccessObjects.utils.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Query;

public class OffererFeatureDao  extends HibernateUtil implements IBaseDAO<OffererFeature,Integer>{

    @Override
    public void save(OffererFeature o) {
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
    public OffererFeature merge(OffererFeature o) throws HibernateException{
        try{
            startOperation();
            o=(OffererFeature)getSesion().merge(o);
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
    public void delete(OffererFeature o) {
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
    
    public void update(OffererFeature f){
        try{
          startOperation();
          Query q = getSesion().createQuery("update OffererFeature set level=" + f.getLevel() +
                  " where idOffererFeature="+f.getIdOffererFeature());
          q.executeUpdate();
          getTransac().commit();
        }catch(HibernateException h){
        System.out.println(h.getMessage());
        }
        finally{
            getSesion().close();
        }
    }

    @Override
    public OffererFeature findById(Integer o) {
        OffererFeature offererf = null;
        try{
            startOperation();
            offererf=(OffererFeature)getSesion().get(OffererFeature.class, o);
        } finally{
            getSesion().close();
        } return offererf;
    }

    @Override
    public List<OffererFeature> findAll() {
        List<OffererFeature> offererfsList;
        try{
            startOperation();
            offererfsList=getSesion().createQuery("from OffererFeature").list();
        }finally{
            getSesion().close();
        } return offererfsList;
    }
    
   
    public List<OffererFeature> findByName(String name) {
        List<OffererFeature> of = new ArrayList<OffererFeature>();
        try{
           startOperation();
           Query query = getSesion().createQuery
           (
                "select o from OffererFeature o " +
                "join fetch o.offerer as f " +
                "where f.name = '" + name + "'"
           );
           
           of = query.list();
           
        } finally{
           getSesion().close();
        } return of;
    }
    
    public List<OffererFeature> findAllByOffererId(Integer p) {
        List<OffererFeature> offererfsList;
        try{
            startOperation();
            Query q = getSesion().createQuery("select off from OffererFeature off join off.offerer as o "
                    + "join fetch off.feature where o.idOfferer="+p);
           q.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
           offererfsList=q.list();
        }finally{
            getSesion().close();
        } 
          return offererfsList;
    }
    
    public OffererFeature findByOffererIdAndFeatureName(Integer o, String f) {
        OffererFeature offererf;
        try{
            startOperation();
            offererf=(OffererFeature)getSesion().createQuery("select off from OffererFeature off join off.offerer as o "
                    + "join fetch off.feature as f where o.idOfferer="+o+" and"
                    + " f.name='"+f+"'").uniqueResult(); 
        }finally{
            getSesion().close();
        } 
          return offererf;
    }
     
}