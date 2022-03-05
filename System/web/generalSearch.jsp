<%@page import="java.util.ArrayList"%>
<%@page import="dataAccessObjects.domain.Position"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% List<Position> RESULTS = (ArrayList<Position>) session.getAttribute("RESULTS"); %>
<% String message = (String) session.getAttribute("message"); %>

<!DOCTYPE html>
<html>
    <head>
        <title>Jobs Exchange System</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <link rel="stylesheet" href="css\styles.css">
    </head>
    <body>
        
        <%@ include file="header.jsp" %>

        <div class="container"> <!--START OF PRINCIPAL CONTAINER-->

            
            <!-- #region RESULTS-->
            <%if (message!=null) { %>
            <br><br>
            <img src="images\sadface.png" style="margin-left: 470px">
            <br><br>
            <h3>&nbsp;<%=message%></h3>
            <% }%> 
            <%if(!RESULTS.isEmpty()){ %>
            <br>
            <h3 id="adv">Results: (<%=RESULTS.size()%>)</h3>
            <hr>
            <% } %> 
            <ol>
            <% for(Position p: RESULTS){ %>
            <li><p><b style="cursor: pointer;" onclick="javascript:position_view('<%=p.getIdPosition()%>')"><%=p.getName()%></b><br>
            <small>Company: <%=p.getCompany().getName()%></small><br>
            <small>Published Date: <%=p.getPublishDate()%></small></p></li>
            <% } %> <hr>
            </ol>

        </div>  <!--END OF PRINCIPAL CONTAINER-->  
        
                     <!-- BEGIN POSITION'S MODAL-->
            <div class="modal fade" id="positionAbout" tabindex="-1" role="dialog" aria-labelledby="positionAbout" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle" id="img_logo" src="images/maleta.png" style="max-width: 50px; max-height: 57px"> </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h3 style="font-family: 'Mina', sans-serif;" class="modal-title" id="offererAboutH"></h3> <br>
                        <label for="company">Company: </label> 
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="company" type="text" disabled>
                        <label for="position">Position: </label>  
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="position" type="text" disabled>
                        <label for="date">Published Date: </label>  
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="date" type="text" disabled>
                        <label for="salary">Salary: </label>  
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="salary" type="text" disabled>                
                        <label for="features">Requirements: </label>
                        <ul id="features"></ul>
                    </div>
                    <div class="modal-footer">
                        <button id="modal-close" type="button" class="btn btn-outline-info" data-dismiss="modal">Close</button>
                    </div>
                </div>
                </div>
            </div> <!-- END POSITION'S MODAL--> 
        <script src="js\adv_search.js"></script>
        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
    </body>
</html>
