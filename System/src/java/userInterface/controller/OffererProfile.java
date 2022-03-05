package userInterface.controller;

import businessLogic.logic.FeatureModel;
import businessLogic.logic.OffererFeatureModel;
import com.google.gson.Gson;
import dataAccessObjects.domain.Feature;
import dataAccessObjects.domain.Offerer;
import dataAccessObjects.domain.OffererFeature;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "OffererProfile", urlPatterns = {"/ProfileO","/FeatureRoot","/xFilter", "/cFilter",
"/dFilter","/rFilter","/aResults","/aLevel","/testOf","/updateLevel","/updateCV"})
@MultipartConfig
public class OffererProfile extends HttpServlet {

  
   private static List<OffererFeature> ITEMS = new ArrayList();
   private static Stack<String> PATH = new Stack(); //Path of the selected features
        
   
     protected void init(HttpServletRequest request){
        HttpSession s =  request.getSession(true);
        s.setAttribute("ITEMS", ITEMS);
        s.setAttribute("PATH", PATH);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            this.init(request);
             switch(request.getServletPath()){ 
         case "/ProfileO":
                    this.profile(request, response);
                break;
         case "/FeatureRoot":
            this.doShowRoots(request,response);
            break;
            
        case "/xFilter":
            this.doExpandFilter(request, response);
            break;
            
        case "/cFilter":
            this.doContractFilter(request, response);
            break;
        
        case "/dFilter":
            this.doAddFilter(request, response);
            break;
            
        case "/rFilter":
            this.doRemoveFilter(request, response);
            break;
            
        case "/aResults":
            this.doAdvancedResults(request, response);
            break;
            
        case "/aLevel":
            this.doAddLevel(request, response);
            break;
            
        case "/testOf" :
            this.doTesting(request, response);
            break;
            
        case "/updateLevel":
            this.updateLevel(request, response);
            break;
            
        case "/updateCV":
            this.updateCV(request, response);
            break;
            }
        }
        
    }
    
    private void profile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession s =  request.getSession( true);
        Offerer of = (Offerer)s.getAttribute("user");
        List<OffererFeature> offFeatures = OffererFeatureModel.instance().findAllByOffererId(of.getIdOfferer());
        request.setAttribute("fea", offFeatures);
        request.getRequestDispatcher("offererProfile.jsp").forward(request,response);
    }

    protected void doShowRoots(HttpServletRequest request, HttpServletResponse response) //Function for Advanced Search.
        throws ServletException, IOException {
        
         HttpSession s =  request.getSession(true);
        
        try{
            PATH.clear(); ITEMS.clear();
            List<Feature> roots = FeatureModel.instance().findRootFeatures();
            request.setAttribute("roots", roots);
            request.getRequestDispatcher("addFeature.jsp").forward(request, response);
        }catch(Exception e){
                    String error = e.getMessage(); 	
                    request.setAttribute("Error: ", error);
        }
    }
    
    
    protected void doExpandFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
            String feature = request.getParameter("selectedValue");
            this.PATH.push(feature); 
            List<Feature> features = FeatureModel.instance().findFeaturesByParentName(feature);
            request.setAttribute("features", features);
            request.getRequestDispatcher("addFeature.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
    
    protected void doContractFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String name = request.getParameter("selectedValue"); //First we store the value and then we reject it
            this.PATH.pop(); 
            
            List<Feature> features;
            Feature feature = FeatureModel.instance().findByName(name);
            
            if(feature == null){ //If returns null it is a root
                features = FeatureModel.instance().findRootFeatures();
            } else { //Else it is a sub feature
                features = FeatureModel.instance().findFeaturesByParentName(feature.getparentFeature().getName());
            }
            
            request.setAttribute("features", features);
            request.getRequestDispatcher("addFeature.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
    
    protected void doAddFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        OffererFeature of = new OffererFeature();
        
        try{
            Integer feature = Integer.parseInt(request.getParameter("selectedValue"));
            of.setFeature(FeatureModel.instance().findbyId(feature));
            ITEMS.add(of);
            List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
            request.setAttribute("features", features);
            request.getRequestDispatcher("addFeature.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
    
    protected void doRemoveFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
         try{
            ITEMS.remove(Integer.parseInt(request.getParameter("selectedValue")));
            doAdvancedResults(request, response);
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
    
    protected void doAdvancedResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession s =  request.getSession(true);
            if(ITEMS.isEmpty()){
                this.doShowRoots(request, response);
            }
            else{
                List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
                request.setAttribute("features", features);
                request.getRequestDispatcher("addFeature.jsp").forward(request, response);
            }
        } catch(Exception e){
            String error = e.getMessage(); 	
            request.setAttribute("error",error);
        }
    }
    
    protected void doAddLevel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        Integer feature = Integer.parseInt(request.getParameter("feature"));
        Float level = Float.parseFloat(request.getParameter("level"));
        try{
            ITEMS.get(feature).setLevel(level);
            List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
            request.setAttribute("features", features);
            request.getRequestDispatcher("addFeature.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
     protected void doTesting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession s =  request.getSession(true);
            Offerer offerer = (Offerer)s.getAttribute("user");
            Float level;
            int count=0;
            for(OffererFeature of : ITEMS){
              if(OffererFeatureModel.instance().findByOffererIdAndFeatureName(offerer.getIdOfferer(), of.getFeature().getName())==null){
                level = Float.parseFloat(request.getParameter(String.valueOf(count)));
                of.setLevel(level);
                of.setOfferer(offerer);
                count++;
                OffererFeatureModel.instance().saveOffererFeature(of);
                }
            }
           
            System.out.println("offerer: "+offerer.getIdOfferer());
            List<OffererFeature> offFeatures = OffererFeatureModel.instance().findAllByOffererId(offerer.getIdOfferer());
            request.setAttribute("fea", offFeatures);
            request.getRequestDispatcher("ProfileO").forward(request, response);
            
        } catch(Exception e){
            String error = e.getMessage(); 	
            request.setAttribute("error",error);
            
        }
     }
     
     private void updateLevel(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OffererFeature of1 = null;
        OffererFeature of2 = null;
        
            try{
                HttpSession s =  request.getSession(true);
                Offerer offerer = (Offerer)s.getAttribute("user");
                BufferedReader reader = request.getReader(); 
                Gson gson = new Gson();
                of1 = gson.fromJson(reader, OffererFeature.class); 
                System.out.println("Estoy llegando");
                of2=  OffererFeatureModel.instance().findByOffererIdAndFeatureName(offerer.getIdOfferer(),of1.getFeature().getName());
                of2.setLevel(of1.getLevel());
                OffererFeatureModel.instance().update(of2);
                response.setStatus(204); // ok with no content
            }
            catch(Exception e){	
                System.err.println("There was an exception: " + e.getMessage());
                response.setStatus(401); //Bad request
            }	
}
     
     
     protected void updateCV(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            
            String idOfferer;
        
            try{
                Reader idReader = new BufferedReader(new InputStreamReader(request.getPart("id").getInputStream()));
                Gson gson = new Gson();
                idOfferer = gson.fromJson(idReader, String.class);
                PrintWriter out = response.getWriter();
                
                OutputStream cv = new FileOutputStream(new File(getServletContext().getRealPath("/")+"documents/"+idOfferer+".pdf"));
                InputStream cvReader = request.getPart("cv").getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = cvReader.read(bytes)) != -1) {
                    cv.write(bytes, 0, read);
                }
                cv.close(); 

                response.setContentType("application/json; charset=UTF-8");    
                response.setStatus(204); // ok with content no content
                
            }
            
            catch(Exception e){	
                System.err.println("Error: " + e.getMessage());
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
