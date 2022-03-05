<%@page import="dataAccessObjects.domain.PositionFeature"%>
<%@page import="java.util.Stack"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dataAccessObjects.domain.Position"%>
<%@page import="java.util.List"%>
<%@page import="dataAccessObjects.domain.Feature"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% List<Position> RESULTS = (ArrayList<Position>) session.getAttribute("RESULTS"); %>
<% List<PositionFeature> ITEMS= (ArrayList<PositionFeature>) session.getAttribute("ITEMS");%>
<% Stack<String> PATH = (Stack) session.getAttribute("PATH"); %>

<!DOCTYPE html>

<html>
    <head>
        <title>Advanced Search</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <link rel="stylesheet" href="css\styles.css">
    </head>
    
    <body>

        <%@ include file="header.jsp" %>

        <div class="container"> <!--START OF PRINCIPAL CONTAINER-->  
            
        <jsp:useBean id="roots" scope="request" type="List<Feature>" class="java.util.ArrayList"/>
        <jsp:useBean id="features" scope="request" type="List<Feature>" class="java.util.ArrayList"/>

        <!--Title for Advanced Search-->
            <h2>Advanced Search</h2> <hr>

        <!--Filters Section-->
        <% if(!ITEMS.isEmpty()){ %> 
            <h4>Filters:</h4>
            
            <button style="margin-left: 40px;" type="button" class="btn btn-sm btn-outline-info" data-toggle="modal" data-target="#exampleModal">
                ADD LEVEL  <!-- Button trigger modal -->
            </button>

            <ul style="display: inline">
                <% for(PositionFeature pf: ITEMS){ %> 
                <li class="path-item" style="display: inline;"> 
                    <a style="color: inherit; text-decoration: none;" title="Remove <%=pf.getFeature().getName()%>" href="\System\removeFilter?selectedValue=<%=ITEMS.indexOf(pf)%>"> <%=pf.getFeature().getName()%> </a> &nbsp;
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
                            <a href="\System\expandFilter?selectedValue=<%=f.getName()%>"> <img src="images\expand.png" alt="(+)"> </a>
                           <% } %>
                    </li>
                <% } %>
            </ul> <!--End Root Categories-->


        <!-- #region LIST OF SUBCATEGORIES-->
        <ul><% if(!PATH.isEmpty()) { %>
            <li style="margin-bottom: 10px; list-style:none; font-size: 20px;">
                <a href="\System\contractFilter?selectedValue=<%=PATH.peek()%>"> <img src="images\contract.png" alt="(-)"> </a>
                <%= PATH.peek() %>
            </li>
            <% } %>
        <ul>
            <% for(Feature f: features){ %>
            <li style="margin-bottom: 10px; list-style:none; font-size: 20px;">
                    <a style="color: inherit;" href="\System\addFilter?selectedValue=<%=f.getIdFeature()%>"> <%=f.getName()%> </a>
                    <% if(f.hasChilds()) { %>
                    <a href="\System\expandFilter?selectedValue=<%=f.getName()%>"> <img src="images\expand.png" alt="(+)"> </a>
                    <% } %>
            </li>
            <% } %>  
        </ul>
        </ul>
        <!-- #END REGION LIST OF SUBCATEGORIES-->    
        
           
            <!-- #region RESULTS--> 
            <%if(RESULTS.isEmpty()) { %><br>
                <h3 id="adv">Positions: [<%=RESULTS.size()%>]</h3> <br> <br>
                <img src="images\sadface.png" style="margin-left: 470px"> <br> <br>
                <h4 style="margin-left: 360px">No results match your search criteria</h4>
            <% } %> 

            <%if(!RESULTS.isEmpty()) { %> <br>
                <h3 id="adv">Positions: [<%=RESULTS.size()%>]</h3> <hr>
            <% } %> 
            
            <ol>
                <% for(Position p: RESULTS) { %>
                    <li>
                        <p> <b style="cursor: pointer;" onclick="javascript:position_view('<%=p.getIdPosition()%>')"> <%=p.getName()%> </b> <br>
                        <small>Company: <%=p.getCompany().getName()%></small><br>
                        <small>Published Date: <%=p.getPublishDate()%></small></p>
                    </li>
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
                    <form action="Testing" method="post">
                        <% int count =0; %>
                        <% for(PositionFeature pf : ITEMS) { %>
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
            </div> <!-- END OFFERER'S MODAL-->   
            

        </div>  <!--END OF PRINCIPAL CONTAINER-->  
        
        <script src="js\jquery.js"></script>
        <script src="js\adv_search.js"></script>
        <script src="js\bootstrap.min.js"></script>
    </body>
</html>
