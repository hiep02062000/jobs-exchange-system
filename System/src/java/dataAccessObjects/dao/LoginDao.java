package dataAccessObjects.dao;

import dataAccessObjects.domain.Login;
import java.util.List;
import org.hibernate.HibernateException;
import dataAccessObjects.utils.HibernateUtil;
import org.hibernate.Query;

public class LoginDao  extends HibernateUtil implements IBaseDAO<Login, Integer>{

    @Override
    public void save(Login l) {
        try{
            startOperation();
            getSesion().save(l);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
    }

    @Override
    public Login merge(Login l) throws HibernateException{
        try{
            startOperation();
            l = (Login)getSesion().merge(l);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        return l;
    }

    @Override
    public void delete(Login l) {
         try{
            startOperation();
            getSesion().delete(l);
            getTransac().commit();
        }catch(HibernateException he){
            handleException(he);
            throw he;
        }finally{
            getSesion().close();
        }
        
    }
    
    public void update(Login l){
        try{
            startOperation();
            Query q = getSesion().createQuery("update Login set password='"+l.getPassword()+"', enable="+l.getEnable()
            +" where idLogin=" + l.getIdLogin());
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
    public Login findById(Integer l) {
        Login login = null;
        try{
            startOperation();
            login=(Login)getSesion().get(Login.class, l);
        } finally{
            getSesion().close();
        } return login;
    }

    @Override
    public List<Login> findAll() {
        List<Login> loginsList;
        try{
            startOperation();
            loginsList=getSesion().createQuery("from Login").list();
        }finally{
            getSesion().close();
        } return loginsList;
    }
    
    public Login findByCredentials(String email, String password){
        Login login = null;
        try{
            startOperation();
            login=(Login)getSesion().createQuery("from Login where email=" + "'" + email + "'" + " and password='" + password + "'" ).uniqueResult();
        } finally{
            getSesion().close();
        } return login;
    }
    
    public List<Login> findCompanyDisables() {
        List<Login> loginsList;
        try{
            startOperation();
            loginsList=getSesion().createQuery("from Login where enable=-128 and type=1").list();
        }finally{
            getSesion().close();
        } return loginsList;
    }
    
    public List<Login> findOffererDisables() {
        List<Login> loginsList;
        try{
            startOperation();
            loginsList=getSesion().createQuery("from Login where enable=-128 and type=2").list();
        }finally{
            getSesion().close();
        } return loginsList;
    }
    
    public Login findByEmail(String email){
        Login login=null;
        try{
            startOperation();
            login=(Login)getSesion().createQuery("from Login where email=" + "'" +email+"'").uniqueResult();
        } finally{
            getSesion().close();
        } return login;
    }
}