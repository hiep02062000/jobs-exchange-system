package businessLogic.logic;

import dataAccessObjects.dao.LoginDao;
import dataAccessObjects.domain.Login;
import java.util.List;

public class LoginModel {
    
    private LoginDao dao;
    private static LoginModel uniqueInstance;
     
    public static LoginModel instance(){
        if (uniqueInstance == null){
            uniqueInstance = new LoginModel();
        }
        return uniqueInstance;
    }
    
    private LoginModel(){
        dao = new LoginDao();
    }
   
    public void saveLogin(Login l) throws Exception{
        dao.save(l);
    }
    
    public void deleteLogin(Login l) throws Exception{
        dao.delete(l);
    }
    
    public void updateLogin(Login l)throws Exception{
       dao.update(l);
    }
    
    public List<Login> findAll(){
        return dao.findAll();
    }
    
    public Login findbyId(Integer id){
        return dao.findById(id);
    }
     
    public Login findByCredentials(String email, String password){
        return dao.findByCredentials(email, password);
    }
    
    public List<Login> findCompanyDisables(){
        return dao.findCompanyDisables();
    }
    
    public List<Login> findOffererDisables(){
        return dao.findOffererDisables();
    }
    
    public Login findByEmail(String email){
        return dao.findByEmail(email);
    }
    
}