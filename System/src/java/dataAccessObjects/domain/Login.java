package dataAccessObjects.domain;

import com.google.gson.annotations.Expose;
import java.util.HashSet;
import java.util.Set;

public class Login  implements java.io.Serializable {

    private Integer idLogin;
    @Expose private String email;
    private String password;
    private Integer type;
    private byte enable; 
    
    //Types:
    public static final int COMPANY = 1;
    public static final int OFFERER = 2;
    public static final int ADMINISTRATOR = 3;

    public Login() {
    }
    
    public Login(String email, String password, Integer type, byte enable) {
       this.email = email;
       this.password = password;
       this.type = type;
       this.enable = enable;
    }

    public Login(String email, String password, Integer type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
   
    public Integer getIdLogin() {
        return this.idLogin;
    }
    
    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public byte getEnable() {
        return this.enable;
    }
    
    public void setEnable(byte enable) {
        this.enable = enable;
    }
    
    public String getTypesName(){
        String name = null;
        switch(this.type){
            case COMPANY: name= "Company";
            break;
            case OFFERER: name="Offerer"; 
            break;
        }
        return name;
    }

}
