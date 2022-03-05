package userInterface.controller;

import businessLogic.logic.CompanyModel;
import businessLogic.logic.LoginModel;
import businessLogic.logic.OffererModel;
import dataAccessObjects.domain.Login;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PortalLogin", urlPatterns = {"/login", "/logout"})
public class PortalLogin extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch(request.getServletPath()){
        case "/login":
            this.doLogin(request,response);
            break;
        case "/logout":
            this.doLogout(request,response);
            break;
        }
    }
    
    protected void doLogin(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, IOException {
        HttpSession s =  request.getSession( true);
      try{    
        String email   = request.getParameter("email");
        String password   = request.getParameter("password");
        
        if(email==null || password==null){
            throw new Exception();
        }
        
        Login login = LoginModel.instance().findByCredentials(email, password);
       
        Integer userType = login.getType();
        Object user = null;
        
        switch(login.getType()){
            case Login.COMPANY:
                user = CompanyModel.instance().findByLogin(login);
                s.setAttribute("user",user);
                s.setAttribute("userType",userType);
                s.setAttribute("ERROR",null);
                request.getRequestDispatcher("ProfileC").forward( request, response);
                break;
            
            case Login.OFFERER:
                user = OffererModel.instance().findByLogin(login);
                s.setAttribute("user",user);
                s.setAttribute("userType",userType);
                s.setAttribute("ERROR",null);
                request.getRequestDispatcher("ProfileO").forward( request, response);
                break;
           
            case Login.ADMINISTRATOR:
                user = LoginModel.instance().findByCredentials(email,password);
                s.setAttribute("user",user);
                s.setAttribute("userType",userType);
                s.setAttribute("ERROR",null);
                request.getRequestDispatcher("AdminProfile").forward( request, response);
                break;
                
            default:
                break;
        }
        
                
      } catch(Exception e){	
        s.setAttribute("email", request.getParameter("email"));
        s.setAttribute("password", request.getParameter("password"));
	s.setAttribute("ERROR", "Sorry, your password or email was incorrect. Please try again");
	request.getRequestDispatcher("Home").forward( request, response);
        s.setAttribute("ERROR", null);
      }		
    }
    
    
    protected void doLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.getSession().invalidate();
            request.getRequestDispatcher("Home").forward( request, response);
        }catch(Exception e){
            
        }
                    
    }  
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
