package dataAccessObjects.dao;

import dataAccessObjects.domain.Feature;
import java.util.List;
import org.hibernate.HibernateException;
import dataAccessObjects.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;

public class FeatureDao extends HibernateUtil implements IBaseDAO<Feature, Integer>{

    @Override
    public void save(Feature f) {
        try{
            startOperation();
            getSesion().save(f);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
    }

    @Override
    public Feature merge(Feature f) throws HibernateException{
        try{
            startOperation();
            f = (Feature)getSesion().merge(f);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        return f;
    }

    @Override
    public void delete(Feature f) {
         try{
            startOperation();
            getSesion().delete(f);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        
    }

    @Override
    public Feature findById(Integer id) {
        Feature feature = null;
        try{
            startOperation();
            feature=(Feature)getSesion().get(Feature.class, id);
        } finally{
            getSesion().close();
        } return feature;
    }
    
    public Feature findByName(String name) {
        Feature feature = null;
        try{
            startOperation();
            feature=(Feature)getSesion().createQuery("select f from Feature f inner join f.parentFeature where f.name='" 
                    + name + "'").uniqueResult();
            if (feature != null) { //If the feature is a root it will return null
                Hibernate.initialize(feature.getFeatures());
                Hibernate.initialize(feature.getparentFeature());
            }
        } finally{
            getSesion().close();
        } return feature;
    }
    
    public Feature findByNameNoParentJoin(String name) {
        Feature feature = null;
        try{
            startOperation();
            feature=(Feature)getSesion().createQuery("from Feature f where f.name='" 
                    + name + "'").uniqueResult();
            Hibernate.initialize(feature.getFeatures());
        } finally{
            getSesion().close();
        } return feature;
    }

    @Override
    public List<Feature> findAll() {
        List<Feature> featuresList;
        try{
            startOperation();
            featuresList=getSesion().createQuery("from Feature").list();
            for(Feature f: featuresList){
               Hibernate.initialize(f.getFeatures());
            }
        }finally{
            getSesion().close();
        } return featuresList;
    }
    
    public List<Feature> findRootFeatures(){ //Finds the "Root" features.
       List<Feature> featuresList;
       try{
           startOperation();
           featuresList=getSesion().createQuery("from Feature where parentFeature is null order by name").list();
           
           for(Feature f: featuresList){
               Hibernate.initialize(f.getFeatures());
           }
       
        } finally{
            getSesion().close();
        } return featuresList;
    }
    
    public List<Feature> findFeaturesByParentName(String feature){ //Find features by parent name
       List<Feature> featuresList;
       try{
           startOperation();
           Query query = getSesion().createQuery("select f from Feature f " +
           "inner join f.parentFeature as fc " +
           "where fc.name= '"+feature+"' order by f.name");
           featuresList = query.list();
           
           for(Feature f: featuresList){
               Hibernate.initialize(f.getFeatures());
           }
           
       } finally{
            getSesion().close();
       } return featuresList;
   }
    
    public List<Feature> findFeaturesByParentId(Integer id){ //Find features by parent ID
       List<Feature> featuresList;
       try{
           startOperation();
           Query query = getSesion().createQuery("select f from Feature f " +
           "join fetch f.parentFeature as pf " +
           "where pf.idFeature= "+id+" order by f.name");
           featuresList = query.list();
           
           for(Feature f: featuresList){
               Hibernate.initialize(f.getFeatures());
           }
           
       } finally{
            getSesion().close();
       } return featuresList;
   }
   
}