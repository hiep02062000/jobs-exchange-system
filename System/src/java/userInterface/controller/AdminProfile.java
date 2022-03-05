package userInterface.controller;


import businessLogic.logic.FeatureModel;
import businessLogic.logic.LoginModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dataAccessObjects.domain.Feature;
import dataAccessObjects.domain.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "AdminProfile", urlPatterns = {"/AdminProfile","/enableAccount","/ShowFeatures","/addAnotherFeature"
        ,"/selectLevel","/goBack"})
public class AdminProfile extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try (PrintWriter out = response.getWriter()) {
             switch(request.getServletPath()){
                case "/AdminProfile":
                     this.profile(request, response);
                break;
                case "/enableAccount":
                     this.enableAccount(request, response);
                break;
                case "/ShowFeatures":
                    this.showFeature(request, response);
                break;
                case "/addAnotherFeature":
                    this.addAnotherFeature(request, response);
                break;
                case "/selectLevel":
                    this.selectLevel(request, response);
                break;
                case "/goBack":
                    this.goBack(request, response);
                break;
             }
        }
    }
    
private void profile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession s =  request.getSession( true);
        List<Login> loginsCompany = LoginModel.instance().findCompanyDisables();
        List<Login> loginsOfferer = LoginModel.instance().findOffererDisables();
        request.setAttribute("loginsCompany", loginsCompany);
        request.setAttribute("loginsOfferer", loginsOfferer);
        request.getRequestDispatcher("adminProfile.jsp").forward(request,response);
    }

private void enableAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Login loginReq = null;
        Login login =null;
        
            try{
                BufferedReader reader = request.getReader(); 
                Gson gson = new Gson();
                loginReq = gson.fromJson(reader, Login.class);
                LoginModel.instance().updateLogin(loginReq);
                response.setStatus(204); // ok with no content
            }
            catch(Exception e){	
                System.err.println("There was an exception: " + e.getMessage());
                response.setStatus(401); //Bad request
            }	
    }

private void showFeature(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession s =  request.getSession( true);
        List<Feature> features = FeatureModel.instance().findRootFeatures();
        request.setAttribute("fRoots", features);
        request.getRequestDispatcher("featuresMaintenance.jsp").forward(request,response);
    }

    private void addAnotherFeature(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Feature feature;
        String jsonResponse;
        
            try{
                BufferedReader reader = request.getReader(); 
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                Gson gson = new Gson();
                feature = gson.fromJson(reader, Feature.class); 
                FeatureModel.instance().saveFeature(feature);
                feature = FeatureModel.instance().findByNameNoParentJoin(feature.getName());
                
                PrintWriter out = response.getWriter();
                response.setContentType("application/json; charset=UTF-8"); 
                gson = builder.create();
                jsonResponse = gson.toJson(feature);
                out.write(jsonResponse);  
                response.setStatus(200); // ok 
            }
            catch(Exception e){	
                System.err.println("There was an exception: " + e.getMessage());
                response.setStatus(401); //Bad request
            }	
    }
    
    private void selectLevel(HttpServletRequest request, HttpServletResponse response){
        
        int idFeature;
        String jsonResponse;
        List<Feature> subFeatures;
        
        try{
            BufferedReader reader = request.getReader(); 
            GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            idFeature = Integer.parseInt(gson.fromJson(reader, String.class)); 
            subFeatures = FeatureModel.instance().findFeaturesByParentId(idFeature);
            
            PrintWriter out = response.getWriter();
            response.setContentType("application/json; charset=UTF-8"); 
            jsonResponse = gson.toJson(subFeatures);
            out.write(jsonResponse);  
            response.setStatus(200); // ok 
        }
        catch(Exception e){	
            System.err.println("There was an exception: " + e.getMessage());
            response.setStatus(401); //Bad request
        }
        
    }
    
    private void goBack(HttpServletRequest request, HttpServletResponse response){
        int idFeature;
        String jsonResponse;
        Feature current;
        List<Feature> features;
        
        try{
            BufferedReader reader = request.getReader(); 
            GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            idFeature = Integer.parseInt(gson.fromJson(reader, String.class));
            current = FeatureModel.instance().findbyId(idFeature);
            
            if (current.hasParent()) {
                features = FeatureModel.instance().findFeaturesByParentId(current.getparentFeature().getIdFeature());
            } else{
                features = FeatureModel.instance().findRootFeatures();
            }
            
            PrintWriter out = response.getWriter();
            response.setContentType("application/json; charset=UTF-8"); 
            jsonResponse = gson.toJson(features);
            System.out.println(jsonResponse);
            out.write(jsonResponse);  
            response.setStatus(200); // ok 
        }
        catch(Exception e){	
            System.err.println("There was an exception: " + e.getMessage());
            response.setStatus(401); //Bad request
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
