<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dataAccessObjects.domain.Offerer"%>
<% String message = (String) session.getAttribute("message"); %>
<% List<Offerer> offResults = (ArrayList<Offerer>) session.getAttribute("offResults"); %>
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
        
            <%@ include file="companyHeader.jsp" %>
            <div class="container"> <!--START OF PRINCIPAL CONTAINER--> 
                <h2>Offerers Search</h2> <hr>
            <!-- #region RESULTS-->
                    <%if (message!=null) { %>
                    <br><br>
                        <img src="images\sadface.png" style="margin-left: 470px">
                    <br><br>
                    <h3>&nbsp;<%=message%></h3>
                    <% }%> 

                    <%if(!offResults.isEmpty()){ %>
                    <br>
                    <h3 id="adv">Results: (<%=offResults.size()%>)</h3>
                    <hr>
                    <% } %>

                    <ol>
                    <% for(Offerer p: offResults){ %>
                    <li><p><b  onclick="javascript:offerer_view('<%=p.getIdOfferer()%>')" style="cursor: pointer;"><%=p.getName()+" "+p.getLastName() %></b><br>
                        <small>Phone: <%=p.getPhoneNumber() %></small><br>
                        <small>Country: <%=p.getOriginCountry()%></small><br></p></li>
                    <% } %> <hr>
                    </ol>
            </div> <!--END OF PRINCIPAL CONTAINER-->
            
             <!-- BEGIN OFFERER'S MODAL-->
            <div class="modal fade" id="offererAbout" tabindex="-1" role="dialog" aria-labelledby="offererAbout" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle" id="img_logo" src="images/worker.png" style="max-width: 50px; max-height: 50px"> </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h3 style="font-family: 'Mina', sans-serif;" class="modal-title" id="offererAboutH"></h3> <br>
                        <label for="name">Name: </label>  
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="name" type="text" disabled>
                        <label for="last-name">Last Name: </label>  
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="last-name" type="text" disabled>
                        <label for="country">Country: </label>  
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="country" type="text" disabled>
                        <label for="phone">Phone: </label>
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="phone" type="text" disabled>
                        <label for="email">Email </label>
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="email" type="text" disabled>
                        <label for="features">Features: </label>
                        <ul id="features"></ul>
                        <a class="btn btn-info btn-block" id="pdf" href="#" download>&nbsp;&nbsp;Download CV&nbsp;&nbsp;</a>
                    </div>
                    <div class="modal-footer">
                        <button id="modal-close" type="button" class="btn btn-outline-info" data-dismiss="modal">Close</button>
                    </div>
                </div>
                </div>
            </div> <!-- END OFFERER'S MODAL-->   
            
        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js\offererSearch.js"></script>
        </body>
</html>