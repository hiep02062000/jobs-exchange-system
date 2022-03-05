package userInterface.controller;

import businessLogic.logic.FeatureModel;
import businessLogic.logic.PositionModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dataAccessObjects.domain.Feature;
import dataAccessObjects.domain.Offerer;
import dataAccessObjects.domain.Position;
import dataAccessObjects.domain.PositionFeature;
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


@WebServlet(name = "Search", urlPatterns = 
{"/Search","/AdvancedSearch","/expandFilter","/contractFilter","/addFilter","/advancedResults","/removeFilter",
    "/addLevel","/Testing","/pos_inf_adv"})
public class Search extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        this.init(request);

        switch(request.getServletPath()){
            
        case "/Search":
            this.doSearch(request,response);
            break;
            
        case "/AdvancedSearch":
            this.doAdvancedSearch(request,response);
            break;
            
        case "/expandFilter":
            this.doExpandFilter(request, response);
            break;
            
        case "/contractFilter":
            this.doContractFilter(request, response);
            break;
        
        case "/addFilter":
            this.doAddFilter(request, response);
            break;
            
        case "/removeFilter":
            this.doRemoveFilter(request, response);
            break;
            
        case "/advancedResults":
            this.doAdvancedResults(request, response);
            break;
            
        case "/addLevel":
            this.doAddLevel(request, response);
            break;
            
        case "/Testing" :
            this.doTesting(request, response);
            break;
            
        case "/pos_inf_adv" :
            this.pos_inf_adv(request, response);
            break;
        }
    } 
    
        private static List<PositionFeature> ITEMS = new ArrayList();
        private static List<Position> RESULTS = new ArrayList();
        private static Stack<String> PATH = new Stack(); //Path of the selected features
        private String message=null;
        
    protected void init(HttpServletRequest request){
        HttpSession s =  request.getSession(true);
        s.setAttribute("RESULTS", RESULTS);
        s.setAttribute("ITEMS", ITEMS);
        s.setAttribute("PATH", PATH);
        s.setAttribute("message",message);
    }
        
    
    protected void doSearch(HttpServletRequest request, HttpServletResponse response) //Function for General Search.
        throws ServletException, IOException {
        
        HttpSession s =  request.getSession(true);
        Offerer of = (Offerer)s.getAttribute("user"); 
        try{
            String name = request.getParameter("search"); //Getting the parameter for searching.
            List<Position> positions=PositionModel.instance().listByName(name);
            if(of!=null){
            RESULTS.addAll(positions); //Adding them to the global results list.
            }
            else{
                for(Position p: positions){
                    if((p.getPublicPos()).intValue()!=-128){
                        RESULTS.add(p);
                    }
                }
            }
            if(RESULTS.isEmpty()){
                s.setAttribute("message", "No results match your search criteria, please try again with a broader search!");
            }
            request.getRequestDispatcher("generalSearch.jsp").forward( request, response); 
            RESULTS.clear(); //Clearing the results to not be repeated in the next general search.
            s.setAttribute("message",null);
        }catch(Exception e){
                    String error = e.getMessage(); 	
                    request.setAttribute("Error: ", error);
        }
    }
  
    
    protected void doAdvancedSearch(HttpServletRequest request, HttpServletResponse response) //Function for Advanced Search.
        throws ServletException, IOException {
        System.out.println("TSTING");
        HttpSession s =  request.getSession(true);
        Offerer of = (Offerer)s.getAttribute("user");
        
        try{
            PATH.clear(); ITEMS.clear(); RESULTS.clear();
             List<Position> positions=PositionModel.instance().listByName("");
          //  RESULTS.addAll(PositionModel.instance().listByName(""));
          if(of!=null){
            RESULTS.addAll(positions);
            }
            else{
                for(Position p: positions){
                    if((p.getPublicPos()).intValue()!=-128){
                        RESULTS.add(p);
                    }
                }
            }
            List<Feature> roots = FeatureModel.instance().findRootFeatures();
            request.setAttribute("roots", roots);
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);
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
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);  
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
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);  
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
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);  
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
                List<Feature> features = FeatureModel.instance().findFeaturesByParentName(PATH.peek());
                request.setAttribute("features", features);
                request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);
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
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);  
        } catch(Exception e){
             String error = e.getMessage(); 	
             request.setAttribute("error",error);
        }
    }
    
     protected void doTesting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession s =  request.getSession(true);
         Offerer of = (Offerer)s.getAttribute("user");
        try{
            RESULTS.clear();
            Float level;
            int count=0;
            for(PositionFeature pf : ITEMS){
                level = Float.parseFloat(request.getParameter(String.valueOf(count)));
                pf.setLevel(level);
                count++;
            }
             List<Position> positions=PositionModel.instance().findByParameters(ITEMS);
              if(of!=null){
            RESULTS.addAll(positions);
            }
            else{
                for(Position p: positions){
                    if((p.getPublicPos()).intValue()!=-128){
                        RESULTS.add(p);
                    }
                }
            }
            request.getRequestDispatcher("advancedSearch.jsp").forward(request, response);
            RESULTS.clear(); //Clearing the results to not be repeated in the next general search.
        } catch(Exception e){
            String error = e.getMessage(); 	
            request.setAttribute("error",error);
            
        }
     }
     
     //INFORMACIÓN DE POSICIONES EN LA BÚSQUEDA AVANZADA
      protected void pos_inf_adv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        try{
                BufferedReader reader = request.getReader(); 
                GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                Gson gson = builder.create();
                id = gson.fromJson(reader,int.class);
                
                PrintWriter out = response.getWriter();
                
                Position p = PositionModel.instance().findbyId(id); 
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
