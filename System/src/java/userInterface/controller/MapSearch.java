package userInterface.controller;

import businessLogic.logic.CompanyModel;
import businessLogic.logic.PositionModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dataAccessObjects.domain.Company;
import dataAccessObjects.domain.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MapSearch", urlPatterns = {"/MapSearch","/MapPlaces","/getPositions"})
public class MapSearch extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        switch(request.getServletPath()){
        case "/MapSearch":
            this.renderMap(request,response);
            break;
        case "/MapPlaces":
            this.renderPlaces(request,response);
            break;
        case "/getPositions":
            this.getPositions(request, response);
            break;
        }
        
    }
    
    private void renderMap(HttpServletRequest request, HttpServletResponse response){
        try {
            request.getRequestDispatcher("mapSearch.jsp").forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(MapSearch.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void renderPlaces(HttpServletRequest request, HttpServletResponse response) throws IOException{
             
        String json;
        List<Company> companies;
        
        try{
            GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            PrintWriter out = response.getWriter();

            companies = CompanyModel.instance().findAllEnabled();
            json = gson.toJson(companies);

            response.setContentType("application/json; charset=UTF-8"); 
            out.write(json);  
            response.setStatus(200); // ok 
        } catch(Exception e){
            System.err.print(e.getMessage());
        }
    }
    
    private void getPositions(HttpServletRequest request, HttpServletResponse response){
        String json;
        List<Position> positions = null;
        ArrayList<String> companies;
        HttpSession s =  request.getSession( true);
        
        try{
            BufferedReader reader = request.getReader(); 
            GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            Type stringListType = new TypeToken<ArrayList<String>>(){}.getType();  
            PrintWriter out = response.getWriter();

            companies = gson.fromJson(reader, stringListType);
            if(s.getAttribute("user")==null){
            positions = PositionModel.instance().findPublicPositionsByCompaniesIDs(companies);}
            else{
            positions = PositionModel.instance().findByCompaniesIDs(companies);
            }
            json = gson.toJson(positions);
            response.setContentType("application/json; charset=UTF-8"); 
            out.write(json);  
            response.setStatus(200); // ok 
        } catch(Exception e){
            System.err.print(e.getMessage());
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
