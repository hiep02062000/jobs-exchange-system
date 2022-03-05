package businessLogic.logic;

import dataAccessObjects.dao.CompanyDao;
import dataAccessObjects.domain.Company;
import dataAccessObjects.domain.Login;
import java.util.List;

public class CompanyModel {
    
    private CompanyDao dao;
    private static CompanyModel uniqueInstance;
     
    public static CompanyModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new CompanyModel();
        }
        return uniqueInstance;
    }
    
    private CompanyModel(){
        dao = new CompanyDao();
    }
     
    public void saveCompany(Company c) throws Exception{
        dao.save(c);
    }
    
    public void deleteCompany(Company c) throws Exception{
        dao.delete(c);
    }
    
    public void updateCompany(Company c)throws Exception{
       dao.merge(c);
    }
    
    public List<Company> findAll(){
        return dao.findAll();
    }
    
    public Company findbyId(Integer id){
        return dao.findById(id);
    }
    
    public Company findByLogin(Login l){
        return dao.findByLogin(l);
    }
    
    public List<Company> findAllEnabled(){
        return dao.findAllEnabled();
    }
    
}