<%@page import="dataAccessObjects.domain.OffererFeature"%>
<%@page import="java.util.Stack"%>
<%@page import="dataAccessObjects.domain.Feature"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Offerer offerer = (Offerer) session.getAttribute("user"); %>
     
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv='cache-control' content='no-cache'>
        <meta http-equiv='expires' content='0'>
        <meta http-equiv='pragma' content='no-cache'>
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <title>Profile</title>
    </head>
    <body>

       <%@ include file="offererHeader.jsp" %>

       <jsp:useBean id="fea" scope="request" type="List<OffererFeature>" class="java.util.ArrayList"/>

       <div class="container"> <br>    

           <h2>Welcome back <%=offerer.getName()%>!</h2> <hr>
            
           <div class="row"> 
               
            <div class="col-3"> &nbsp; &nbsp;
                <div class="card" style="width: 18rem; margin-left: 5px;">
                    <img class="card-img-top" style="width: 286px; height: 286px;" src="images\<%=offerer.getIdOfferer()%>.png" onerror="this.src='images/userProfile.png';">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><img src="images\user-icon.png">&nbsp;&nbsp;&nbsp;<%=offerer.getName()+ " "+offerer.getLastName() %></li>
                        <li class="list-group-item"><img src="images\Phone-icon.png">&nbsp;&nbsp;&nbsp;<%=offerer.getPhoneNumber()%></li>
                        <li class="list-group-item"><img src="images\email.png">&nbsp;&nbsp;&nbsp;<%=offerer.getLogin().getEmail()%></li>
                    </ul>
                </div>
            </div>
              
            <div class="col-4" style="margin-left: 90px;">
                <h3>Features:</h3>
                <table class="table table-hover">
                      <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Level</th>
                        </tr>
                      </thead>
                    <tbody>
                      <% for(OffererFeature p: fea) { %>
                        <tr class="offererF-row" style="cursor: pointer">
                            <td><%=p.getFeature().getName() %></td>
                            <td id="lev"><%=p.getLevel()%>%</td>
                        </tr>
                      <%}%>   
                    </tbody>
                </table><hr>
                <a href="\System\FeatureRoot" class="btn btn-info">Add Features</a>
            </div> 

            <div class="col-3" style="margin-left: 40px;">
                <h4>Your CV Options:</h4> 
    
                <div id="success-alert" class="form-row alert alert-success show" role="alert"> <br>
                        CV has been updated! &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;
                </div> <hr>
    
                <a class="btn btn-info btn-block" href="documents/<%=offerer.getIdOfferer()%>.pdf" download>&nbsp;&nbsp;Review&nbsp;&nbsp;</a>
                <button type="button" class="btn btn-info btn-block" onclick="javascript:showModalCV();">&nbsp;&nbsp;Edit&nbsp;&nbsp;</button>
            </div>

          </div> <!--END ROW-->
          
        </div> <!--END PRINCIPAL CONTAINER-->   <br> <br>
        
        <!-- #MODAL PDF-->
          <div class="modal fade" id="pdfModal" tabindex="-1" role="dialog" aria-labelledby="pdfModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle" id="img_logo" src="images/PDF.png" style="max-width: 50px; max-height: 50px"> </div>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                </div>
                <div class="modal-body">
                  <h3 style="font-family: 'Mina', sans-serif;" class="modal-title" id="top5ModalLabel"></h3> 
                  <label for="name">Submit your new CV (Format: .pdf): </label>  
                  <div class="form-row col-md-12">
                    <input type="file" name="cv" id="cv" class="hide  filestyle" data-buttonBefore="true" data-text="Find File" accept=".pdf">
                  </div>
                </div> 
                <div class="modal-footer">
                    <button id="modal-accept" type="button" class="btn btn-outline-success" data-dismiss="modal">Accept</button>
                    <button id="modal-cancel" type="button" class="btn btn-outline-danger" data-dismiss="modal">Cancel</button>
                </div>
            </div>
            </div>
        </div>
         <!-- #END PDF-->           
         
         
         <!-- Level update modal -->
        <div class="modal fade" id="ofFeatureModal" tabindex="-1" role="dialog" aria-labelledby="ofFeatureModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                <h5 class="modal-title" id="ofFeatureModalLabel">Please set the new level</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                </div>
                <div class="modal-body">
                    <label for="upLevel">Level </label>
                    <select id="upLevel">
                        <option value="10">10%</option>
                        <option value="20">20%</option>
                        <option value="30">30%</option>
                        <option value="40">40%</option>
                        <option value="50">50%</option>
                        <option value="60">60%</option>
                        <option value="70">70%</option>
                        <option value="80">80%</option>
                        <option value="90">90%</option>
                        <option value="100">100%</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success" id="enable-account"  onclick="javascript:updateLevel()">Update Level</button>
                </div>
            </div>
            </div>
        </div> <!-- END MODAL -->

                    
        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js/bootstrap-filestyle.min.js"> </script>
        <script>
            var idOfferer = '<%=offerer.getIdOfferer()%>';
        </script>
        <script src="js\offererProfile.js"></script>
    </body>
</html>
