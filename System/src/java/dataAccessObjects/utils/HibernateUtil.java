package dataAccessObjects.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 */

public class HibernateUtil {
    
    private Transaction transac;
    private Session sesion;

    public Transaction getTransac() {
        return transac;
    }

    public Session getSesion() {
        return sesion;
    }

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) { // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public void startOperation() throws HibernateException{
        this.sesion=HibernateUtil.getSessionFactory().openSession();
        this.transac=sesion.beginTransaction();
    }

    public void handleException(HibernateException he)throws HibernateException{
        transac.rollback();
        throw new HibernateException("There was an error in the data access layer...", he);
    }
    
}