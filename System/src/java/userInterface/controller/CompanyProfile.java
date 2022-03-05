/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface.controller;

import businessLogic.logic.FeatureModel;
import businessLogic.logic.OffererModel;
import businessLogic.logic.PositionFeatureModel;
import businessLogic.logic.PositionModel;
import com.google.gson.Gson;
import dataAccessObjects.domain.Company;
import dataAccessObjects.domain.Feature;
import dataAccessObjects.domain.Offerer;
import dataAccessObjects.domain.OffererFeature;
import dataAccessObjects.domain.Position;
import dataAccessObjects.domain.PositionFeature;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Estefania
 */
@WebServlet(name = "CompanyProfile", urlPatterns = {"/ProfileC","/Position","/doPositionRegistry",
    "/disablePosition","/doShowRoots","/doExpand","/doContract","/doAddF","/doRemoveF",
    "/doAdvancedR","/doSavePosFeatures","/searchOfferers"})
public class CompanyProfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static List<PositionFeature> ITEMS = new ArrayList();
    private static Stack<String> PATH = new Stack(); //Path of the selected features
    Position position = new Position();
    private static List<Offerer> offResults = new ArrayList();
    private String message=null;
     protected void init(HttpServletRequest request){
        HttpSession s =  request.getSession(true);
        s.setAttribute("ITEMS", ITEMS);
        s.setAttribute("PATH", PATH);
        s.setAttribute("offResults", offResults);
        s.setAttribute("message", message);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            this.init(request);
            switch(request.getServletPath()){ 
                case "/ProfileC":
                    this.profile(request, response);
                break;
                case "/Position":
                   this.position(request, response);
                break;
                case "/doPositionRegistry":
                    this.doPositionRegistry(request, response);
                break;
                case "/disablePosition":
                  this.disablePosition(request, response);
                break;
                case "/doShowRoots":
                    this.doShowRoots(request, response);
                break;
                case "/doExpand":
                    this.expandFilter(request, response);
                break;
                case "/doContract":
                    this.doContractFilter(request, response);
                break;
                case "/doAddF":
                    this.doAddFilter(request, response);
                break;
                case "/doRemoveF":
                    this.doRemoveFilter(request, response);
                break;
                case "/doAdvancedR":
                    this.doAdvancedResults(request, response);
                break;
                case "/doSavePosFeatures":
                    this.doSaveFeatures(request, response);
                break;
                case "/searchOfferers":
                    this.searchOff(request, response);
                break;
        } }
    }
    
     private void profile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession s =  request.getSession( true);
            Company company = (Company)s.getAttribute("user");
            List<Position> pos = PositionModel.instance().findByCompanysName(company.getName());
            request.setAttribute("companysPos", pos);
        request.getRequestDispatcher("companyProfile.jsp").forward(request,response);
    }
     
    private void position(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("addPosition.jsp").forward(request,response);
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

    private void doPositionRegistry(HttpServletRequest request, HttpServletResponse response) throws IOException {
       HttpSession s =  request.getSession( true);
       try{
            position = new Position();
            Company company = (Company)s.getAttribute("user");
            String name = request.getParameter("name");
            Date publishDate = Calendar.getInstance().getTime();
            String salary = request.getParameter("salary");
            
            if(Double.parseDouble(salary)<0){
                throw new Exception();
            }
            
            String mode =request.getParameter("mode");
            Byte publicPos;
            
            if("public".equals(mode)){
                publicPos=Byte.MAX_VALUE;
            }else{
                publicPos=Byte.MIN_VALUE;
            }
            
            position.setCompany(company);
            position.setName(name);
            position.setPublishDate(publishDate);
            position.setSalary(Double.parseDouble(salary));
            position.setPublicPos(publicPos);
            position.setEnable(Byte.MAX_VALUE);
            
            PositionModel.instance().savePosition(position);
            request.getRequestDispatcher("doShowRoots").forward(request, response);  
           }
       catch(Exception e){
           s.setAttribute("name",request.getParameter("name") );
           s.setAttribute("salary",request.getParameter("salary") );
           PrintWriter out = response.getWriter();  
                    response.setContentType("text/html");
                    out.println("<script type=\"text/javascript\">");  
                    out.println("alert('ERROR!');");
                    out.println("location.href = 'addPosition.jsp'");
                    out.println("</script>");
    
        }
    }
    
    private void disablePosition(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Position pos = null;
        Position aux=null;
        
            try{
                BufferedReader reader = request.getReader(); 
                Gson gson = new Gson();
                aux = gson.fromJson(reader, Position.class); 
                pos = PositionModel.instance().findbyId(aux.getIdPosition());
                pos.setEnable(aux.getEnable());
                PositionModel.instance().update(pos);
                response.setStatus(204); // ok with no content
            }
            catch(Exception e){	
                System.err.println("There was an exception: " + e.getMessage());
                response.setStatus(401); //Bad request
            }	
    }
    
    //For adding features to a position
    protected void doShowRoots(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
         HttpSession s =  request.getSession(true);
        try{
            PATH.clear(); ITEMS.clear();
            List<Feature> roots = FeatureModel.instance().findRootFeatures();
            request.setAttribute("roots", roots);
            request.getRequestDispatcher("addPositionFeature.jsp").forward(request, response);
        }catch(Exception e){
                    String error = e.getMessage(); 	
                    request.setAttribute("Error: ", error);
        }
    }
    
    protected void expandFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
            String feature = request.getParameter("selectedValue");
            this.PATH.push(feature); 
            List<Feature> features = FeatureModel.instance().findFeaturesByParentName(feature);
            request.setAttribute("features", features);
            request.getRequestDispatcher("addPositionFeature.jsp").forward(request, response);  
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
            request.getRequestDispatcher("addPositionFeature.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
     
      protected void doAddFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        PositionFeature pf = new PositionFeature();
        
        try{
            Integer feature = Integer.parseInt(request.getParameter("selectedValue"));
            pf.setFeature(FeatureModel.instance().findbyId(feature));
            ITEMS.add(pf);
            List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
            request.setAttribute("features", features);
            request.getRequestDispatcher("addPositionFeature.jsp").forward(request, response);  
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
            if(ITEMS.isEmpty()){
                this.doShowRoots(request, response);
            }
            else{
                List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
                request.setAttribute("features", features);
                request.getRequestDispatcher("addPositionFeature.jsp").forward(request, response);
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
            request.getRequestDispatcher("addPositionFeature.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
    protected void doSaveFeatures(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession s =  request.getSession(true);
            Company company = (Company) s.getAttribute("user");
            Position pp = PositionModel.instance().findPosByCompanysName(position.getCompany().getName(),position.getName());
            Float level;
            int count=0;
            for(PositionFeature pf : ITEMS){
                level = Float.parseFloat(request.getParameter(String.valueOf(count)));
                pf.setLevel(level);
                pf.setPosition(pp);
                count++;
                PositionFeatureModel.instance().savePositionFeatureModel(pf);
            }
            List<Position> companysPos = PositionModel.instance().findByCompanysName(company.getName());
            request.setAttribute("companysPos", companysPos);
            request.getRequestDispatcher("ProfileC").forward(request, response);
            
        } catch(Exception e){
            String error = e.getMessage(); 	
            request.setAttribute("error",error);
            
        }
     }
    
    private void searchOff(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     String id;
     List<PositionFeature> positionfeatures;
     OffererFeature of=new OffererFeature();
     List<OffererFeature> offFeatures=new ArrayList<>();
        try{
            HttpSession s =  request.getSession(true);
            id = request.getParameter("selectedValue"); 
            
            positionfeatures=PositionFeatureModel.instance().findAllByPositionId(Integer.parseInt(id));
            for(PositionFeature pf : positionfeatures){
                of.setFeature(pf.getFeature());
                of.setLevel(pf.getLevel());
                offFeatures.add(of);
                of = new OffererFeature();
            }
            
            offResults.addAll(OffererModel.instance().findByParameters2(offFeatures));
            if(offResults.isEmpty()){
                s.setAttribute("message", "No results match your search criteria, please try again with a broader search!");
            }
            
            request.getRequestDispatcher("offererResults.jsp").forward(request, response);
            offResults.clear(); //Clearing the results to not be repeated in the next search.
            s.setAttribute("message",null);
            
        } catch(Exception e){
            String error = e.getMessage(); 	
            request.setAttribute("error",error);
            
        }
    }
}
