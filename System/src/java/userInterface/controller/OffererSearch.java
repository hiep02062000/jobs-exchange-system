package userInterface.controller;

import businessLogic.logic.FeatureModel;
import businessLogic.logic.OffererModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dataAccessObjects.domain.Feature;
import dataAccessObjects.domain.Offerer;
import dataAccessObjects.domain.OffererFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "OffererSearch", urlPatterns = {"/OffererSearch","/expFilter",
    "/contFilter","/filterAdd","/adResults","/filterRem","/levelA","/T","/of_inf"})
public class OffererSearch extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        this.init(request);

        switch(request.getServletPath()){

        case "/OffererSearch":
            this.doAdvancedSearch(request,response);
            break;
            
        case "/expFilter":
            this.doExpandFilter(request, response);
            break;
            
        case "/contFilter":
            this.doContractFilter(request, response);
            break;
        
        case "/filterAdd":
            this.doAddFilter(request, response);
            break;
            
        case "/filterRem":
            this.doRemoveFilter(request, response);
            break;
            
        case "/adResults":
            this.doAdvancedResults(request, response);
            break;
            
        case "/levelA":
            this.doAddLevel(request, response);
            break;
        case "/T" :
            this.doTesting(request, response);
            break;
            
        case "/of_inf":
            this.of_inf(request,response);
            break;
        }
    } 
    
        private static List<OffererFeature> ITEMS = new ArrayList();
        private static List<Offerer> RESULTS = new ArrayList();
        private static Stack<String> PATH = new Stack(); //Path of the selected features
        private String message=null;
        
    protected void init(HttpServletRequest request){
        HttpSession s =  request.getSession(true);
        s.setAttribute("RESULTS", RESULTS);
        s.setAttribute("ITEMS", ITEMS);
        s.setAttribute("PATH", PATH);
        s.setAttribute("message",message);
    }

    
    protected void doAdvancedSearch(HttpServletRequest request, HttpServletResponse response) //Function for Advanced Search.
        throws ServletException, IOException {
        
        HttpSession s =  request.getSession(true);
        
        try{
            PATH.clear(); ITEMS.clear(); RESULTS.clear();
            RESULTS.addAll(OffererModel.instance().findAll());
            List<Feature> roots = FeatureModel.instance().findRootFeatures();
            request.setAttribute("roots", roots);
            request.getRequestDispatcher("offererSearch.jsp").forward(request, response);
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
            request.getRequestDispatcher("offererSearch.jsp").forward(request, response);  
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
            request.getRequestDispatcher("offererSearch.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
    
    protected void doAddFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        OffererFeature pf = new OffererFeature();
        
        try{
            Integer feature = Integer.parseInt(request.getParameter("selectedValue"));
            pf.setFeature(FeatureModel.instance().findbyId(feature));
            ITEMS.add(pf);
            List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
            request.setAttribute("features", features);
            request.getRequestDispatcher("offererSearch.jsp").forward(request, response);  
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
            HttpSession s =  request.getSession(true); RESULTS.clear();
            if(ITEMS.isEmpty()){
                this.doAdvancedSearch(request, response);
            }
            else{
                RESULTS.clear(); 
            //  RESULTS.addAll(PositionModel.instance().findByFeatures(ITEMS));
                List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
                request.setAttribute("features", features);
                request.getRequestDispatcher("offererSearch.jsp").forward(request, response);
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
            request.getRequestDispatcher("offererSearch.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
     protected void doTesting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            RESULTS.clear();
            Float level;
            int count=0;
            for(OffererFeature pf : ITEMS){
                level = Float.parseFloat(request.getParameter(String.valueOf(count)));
                pf.setLevel(level);
                count++;
            }
            RESULTS.addAll(OffererModel.instance().findByParameters(ITEMS));
            request.getRequestDispatcher("offererSearch.jsp").forward(request, response);
            
        } catch(Exception e){
            String error = e.getMessage(); 	
            request.setAttribute("error",error);
            
        }
        
     }
    
        protected void of_inf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try{
                BufferedReader reader = request.getReader(); 
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                Gson gson = builder.create();
                id = gson.fromJson(reader,int.class);
                
                PrintWriter out = response.getWriter();
                
                Offerer p = OffererModel.instance().findbyId(id); 
                response.setContentType("application/json; charset=UTF-8");  
                String json = gson.toJson(p);
                
                out.write(json);  
                response.setStatus(200); // ok   
        }
        catch(JsonIOException | JsonSyntaxException | IOException e){
                System.err.println("There was an exception: " + e.getMessage());
                response.setStatus(401401); //Bad request
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
