package userInterface.controller;

import businessLogic.logic.CompanyModel;
import businessLogic.logic.LoginModel;
import businessLogic.logic.OffererModel;
import com.google.gson.Gson;
import dataAccessObjects.domain.Company;
import dataAccessObjects.domain.Offerer;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Registration", urlPatterns = {"/Registration","/doRegistryProcessCompany","/doRegistryProcessOfferer"})
@MultipartConfig
public class Registration extends HttpServlet { 
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                switch(request.getServletPath()){
        case "/doRegistryProcessCompany":
            this.doRegistryProcessCompany(request, response);
            break;
        case "/doRegistryProcessOfferer":
            this.doRegistryProcessOfferer(request, response);
            break;   
        }
    }
	
    
    protected void doRegistryProcessCompany(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
     
            Company company = null;
        
            try{
                BufferedReader reader =  new BufferedReader(new InputStreamReader(request.getPart("company").getInputStream())); 
                Gson gson = new Gson();
                company = gson.fromJson(reader, Company.class); 
                LoginModel.instance().saveLogin(company.getLogin());
                CompanyModel.instance().saveCompany(company);
                
                OutputStream profilePic = new FileOutputStream(new File(getServletContext().getRealPath("/")+"companyImages/"+company.getIdCompany()+".png"));
                InputStream profilePicReader = request.getPart("profilePic").getInputStream();
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = profilePicReader.read(bytes)) != -1) {
                    profilePic.write(bytes, 0, read);
                }
                profilePic.close(); 
                response.setStatus(204); // ok with no content
            }
            catch(Exception e){	
                System.err.println("There was an exception: " + e.getMessage());
                response.setStatus(401); //Bad request
            }	
        } 
    
    
        protected void doRegistryProcessOfferer(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            
            Offerer offerer = null;
        
            try{
                Reader offererReader = new BufferedReader(new InputStreamReader(request.getPart("offerer").getInputStream()));
                Gson gson = new Gson();
                offerer = gson.fromJson(offererReader, Offerer.class);
                LoginModel.instance().saveLogin(offerer.getLogin());
                OffererModel.instance().saveOfferer(offerer);
                
                OutputStream cv = new FileOutputStream(new File(getServletContext().getRealPath("/")+"documents/"+offerer.getIdOfferer()+".pdf"));
                InputStream cvReader = request.getPart("cv").getInputStream();
                int readCv = 0;
                final byte[] bytesCv = new byte[1024];
                while ((readCv = cvReader.read(bytesCv)) != -1) {
                    cv.write(bytesCv, 0, readCv);
                }
                cv.close(); 
                
                OutputStream profilePic = new FileOutputStream(new File(getServletContext().getRealPath("/")+"images/"+offerer.getIdOfferer()+".png"));
                InputStream pictureReader = request.getPart("profile-pic").getInputStream();
                int readPicture = 0;
                final byte[] bytesPicture = new byte[1024];
                while ((readPicture = pictureReader.read(bytesPicture)) != -1) {
                    profilePic.write(bytesPicture, 0, readPicture);
                }
                profilePic.close(); 

                response.setContentType("application/json; charset=UTF-8");    
                response.setStatus(204); // ok with content no content
                
            }
            
            catch(Exception e){	
                System.err.println("Error:" + e.getMessage());
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
