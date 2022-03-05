package dataAccessObjects.dao;

import dataAccessObjects.domain.PositionFeature;
import java.util.List;
import org.hibernate.HibernateException;
import dataAccessObjects.utils.HibernateUtil;

public class PositionFeatureDao  extends HibernateUtil implements IBaseDAO<PositionFeature,Integer>{

    @Override
    public void save(PositionFeature p) {
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
    public PositionFeature merge(PositionFeature p) throws HibernateException{
        try{
            startOperation();
            p = (PositionFeature)getSesion().merge(p);
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
    public void delete(PositionFeature p) {
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
    public PositionFeature findById(Integer p) {
        PositionFeature offererf = null;
        try{
            startOperation();
            offererf=(PositionFeature)getSesion().get(PositionFeature.class, p);
        } finally{
            getSesion().close();
        } return offererf;
    }

    @Override
    public List<PositionFeature> findAll() {
        List<PositionFeature> positionfsList;
        try{
            startOperation();
            positionfsList=getSesion().createQuery("from PositionFeature").list();
        }finally{
            getSesion().close();
        } return positionfsList;
    }
    
    public List<PositionFeature> findAllByPositionId(Integer p) {
        List<PositionFeature> positionfsList;
        try{
            startOperation();
            positionfsList=getSesion().createQuery("select pf from PositionFeature pf join pf.position as p "
                                                 + "join fetch pf.feature where p.idPosition = " + p).list();
        }finally{
            getSesion().close();
        } 
          return positionfsList;
    }
    
}