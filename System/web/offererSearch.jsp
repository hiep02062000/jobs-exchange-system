<%@page import="dataAccessObjects.domain.Offerer"%>
<%@page import="dataAccessObjects.domain.OffererFeature"%>
<%@page import="java.util.Stack"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dataAccessObjects.domain.Feature"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% List<Offerer> RESULTS = (ArrayList<Offerer>) session.getAttribute("RESULTS"); %>
<% List<OffererFeature> ITEMS= (ArrayList<OffererFeature>) session.getAttribute("ITEMS");%>
<% Stack<String> PATH = (Stack) session.getAttribute("PATH"); %>

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
            
        <jsp:useBean id="roots" scope="request" type="List<Feature>" class="java.util.ArrayList"/>
        <jsp:useBean id="features" scope="request" type="List<Feature>" class="java.util.ArrayList"/>

        <!--Title for Advanced Search-->
        <h2>Offerers Search</h2> <hr>

        <!--Filters Section-->
        <% if(!ITEMS.isEmpty()){ %> 
            <h4>Filters:</h4>
            
            <button style="margin-left: 40px;" type="button" class="btn btn-sm btn-outline-info" data-toggle="modal" data-target="#exampleModal">
                ADD LEVEL  <!-- Button trigger modal -->
            </button>

            <ul style="display: inline">
                <% for(OffererFeature pf: ITEMS){ %> 
                <li class="path-item" style="display: inline;"> 
                    <a style="color: inherit; text-decoration: none;" title="Remove <%=pf.getFeature().getName()%>" href="\System\filterRem?selectedValue=<%=ITEMS.indexOf(pf)%>"> <%=pf.getFeature().getName()%> </a> &nbsp;
                </li> &nbsp; &nbsp;
                <%}%> 
            </ul> <hr>
        <%}%>
         <!--End Filters Section-->

            <h4>Categories</h4>

            <ul> <!--Root Categories-->
                <% for(Feature f: roots){ %>
                    <li style="list-style:none; font-size: 20px;">
                           <%=f.getName()%> 
                           <% if(f.hasChilds()) { %>
                            <a href="\System\expFilter?selectedValue=<%=f.getName()%>"> <img src="images\expand.png" alt="(+)"> </a>
                           <% } %>
                    </li>
                <% } %>
            </ul> <!--End Root Categories-->


        <!-- #region LIST OF SUBCATEGORIES-->
        <ul><% if(!PATH.isEmpty()) { %>
            <li style="margin-bottom: 10px; list-style:none; font-size: 20px;">
                <a href="\System\contFilter?selectedValue=<%=PATH.peek()%>"> <img src="images\contract.png" alt="(-)"> </a>
                <%= PATH.peek() %>
            </li>
            <% } %>
        <ul>
            <% for(Feature f: features){ %>
            <li style="margin-bottom: 10px; list-style:none; font-size: 20px;">
                    <a style="color: inherit;" href="\System\filterAdd?selectedValue=<%=f.getIdFeature()%>"> <%=f.getName()%> </a>
                    <% if(f.hasChilds()) { %>
                    <a href="\System\expFilter?selectedValue=<%=f.getName()%>"> <img src="images\expand.png" alt="(+)"> </a>
                    <% } %>
            </li>
            <% } %>  
        </ul>
        </ul>
        <!-- #END REGION LIST OF SUBCATEGORIES-->    
           
            <!-- #region RESULTS--> 
             <%if(RESULTS.isEmpty()){ %>
            <br>
            <h3 id="adv">Offerers: [<%=RESULTS.size()%>]</h3>
            <br><br>
            <img src="images\sadface.png" style="margin-left: 470px"><br><br>
            <h4 style="margin-left: 360px">No results match your search criteria</h4>
            <% } %> 
            <%if(!RESULTS.isEmpty()){ %>
            <br>
            <h3 id="adv">Offerers: [<%=RESULTS.size()%>]</h3>
            <hr>
            <% } %> 
            <ol>
            <% for(Offerer p: RESULTS){ %>
            <li><p><b style="cursor: pointer;" onclick="javascript:offerer_view('<%=p.getIdOfferer()%>')"><%=p.getName()+" "+p.getLastName() %></b><br>
            <small>Phone: <%=p.getPhoneNumber() %></small><br>
            <small>Country: <%=p.getOriginCountry()%></small><br></p></li>
            <% } %> <hr>
            </ol>
            <!--END #region RESULTS--> 


            <!-- LEVEL MODAL -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Add the knowledge level to each feature</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                
                <div class="modal-body">
                    <form action="T" method="post">
                        <% int count =0; %>
                        <% for(OffererFeature pf : ITEMS) { %>
                            <label style="margin-left: 20px;"><%=pf.getFeature().getName()%></label> &nbsp;
                                <select name=<%=count%>>
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
                        <% count++; %>           
                        <% } %>
                
                    <button style="margin-left: 20px;" type="submit" class="btn btn-info">Search</button>
                    </form>
                </div>
                </div>
            </div>
            </div>
            <!--END LEVEL MODAL -->
            
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
                        <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="email" type="text" disabled> <br>
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

        </div>  <!--END OF PRINCIPAL CONTAINER-->  
        
        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js\offererSearch.js"></script>
    </body>
</html>
