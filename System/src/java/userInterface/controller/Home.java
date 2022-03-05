package userInterface.controller;

import businessLogic.logic.PositionModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import dataAccessObjects.domain.Position;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Home", urlPatterns = {"/Home","/showInfo","/OffererHome"})
public class Home extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            switch(request.getServletPath()){
            case "/Home":
                this.top5Positions(request, response);
                break;
            case "/showInfo":
                this.showInfo(request,response);
                break;   
            case "/OffererHome":
                this.OffererHome(request, response);
                break;
        }
    }

    
    protected void top5Positions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
                List<Position> positionL = PositionModel.instance().top5();
		request.setAttribute("positionL",positionL);
                request.getRequestDispatcher("index.jsp").forward( request, response);
          }
          catch(IOException | ServletException e){
                String error = e.getMessage(); 	
                request.setAttribute("error",error);
          }		
	} 
    
      protected void OffererHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
                List<Position> positionL = PositionModel.instance().top5ForOfferer();
		request.setAttribute("positionL",positionL);
                request.getRequestDispatcher("offererIndex.jsp").forward( request, response);
          }
          catch(IOException | ServletException e){
                String error = e.getMessage(); 	
                request.setAttribute("error",error);
          }		
	}
    
    
    protected void showInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
