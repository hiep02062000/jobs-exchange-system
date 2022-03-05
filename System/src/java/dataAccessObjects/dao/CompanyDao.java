package dataAccessObjects.dao;

import dataAccessObjects.domain.Company;
import dataAccessObjects.domain.Login;
import java.util.List;
import org.hibernate.HibernateException;
import dataAccessObjects.utils.HibernateUtil;

public class CompanyDao extends HibernateUtil implements IBaseDAO<Company, Integer>{

    @Override
    public void save(Company c) {
        try{
            startOperation();
            getSesion().save(c);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
    }

    @Override
    public Company merge(Company c) throws HibernateException{
        try{
            startOperation();
            c=(Company)getSesion().merge(c);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        return c;
    }

    @Override
    public void delete(Company c) {
         try{
            startOperation();
            getSesion().delete(c);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        
    }

    @Override
    public Company findById(Integer c) {
        Company company = null;
        try{
            startOperation();
            company=(Company)getSesion().get(Company.class, c);
        } finally{
            getSesion().close();
        } return company;
    }

    @Override
    public List<Company> findAll() {
        List<Company> companiesList;
        try{
            startOperation();
            companiesList=getSesion().createQuery("from Company").list();
        }finally{
            getSesion().close();
        } return companiesList;
    }
    
    public Company findByLogin(Login l){
        Company company = null;
        try{
            startOperation();
            company=(Company)getSesion().createQuery("select c from Company c join fetch c.login as l "
                    + "where l.email='" + l.getEmail() + "' and l.password='" + l.getPassword() + "'").uniqueResult();
        } finally{
            getSesion().close();
        } return company;
    }
    
    public List<Company> findAllEnabled() {
        List<Company> companiesList;
        try{
            startOperation();
            companiesList=getSesion().createQuery("select c from Company c join fetch c.login as l where l.enable=127 and l.type=1").list();
        }finally{
            getSesion().close();
        } return companiesList;
    }
  
}