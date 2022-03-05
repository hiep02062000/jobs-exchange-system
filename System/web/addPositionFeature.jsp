<%@page import="dataAccessObjects.domain.Feature"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dataAccessObjects.domain.PositionFeature"%>
<%@page import="java.util.Stack"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% List<PositionFeature> ITEMS= (ArrayList<PositionFeature>) session.getAttribute("ITEMS");%>
<% Stack<String> PATH = (Stack) session.getAttribute("PATH"); %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <link rel="stylesheet" href="css\styles.css">
        <title>Position Features</title>
    </head>
    <body>
        <%@ include file="companyHeader.jsp" %>
         <jsp:useBean id="roots" scope="request" type="List<Feature>" class="java.util.ArrayList"/>
         <jsp:useBean id="features" scope="request" type="List<Feature>" class="java.util.ArrayList"/>
 
        <div class="container"> <br>  <!--PRINCIPAL CONTAINER-->
            
         <div class="row"> <!--ROW--> <br>
             
          <div class="col-3"> &nbsp; &nbsp;<!-- CARD-->
            <div class="card" style="width: 18rem; margin-left: 20px;"> 
                <img class="card-img-top" src="images\features.png" alt="Card image cap">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item"><strong> Associating features to your position is the easiest way for offerers to find it.</strong></li>
                </ul>
            </div> 
          </div><!--END CARD-->
             
          <div class="col-8" style="margin-left: 90px;"> <br>
          <!--Filters Section-->
        <% if(!ITEMS.isEmpty()){ %> 
            <h4>Filters:</h4>
            
            <button style="margin-left: 40px;" type="button" class="btn btn-sm btn-outline-info" data-toggle="modal" data-target="#exampleModal">
                ADD LEVEL  <!-- Button trigger modal -->
            </button>

            <ul style="display: inline">
                <% for(PositionFeature pf: ITEMS){ %> 
                <li class="path-item" style="display: inline;"> 
                    <a style="color: inherit; text-decoration: none;" title="Remove <%=pf.getFeature().getName()%>" href="\System\doRemoveF?selectedValue=<%=ITEMS.indexOf(pf)%>"> <%=pf.getFeature().getName()%> </a> &nbsp;
                </li> &nbsp; &nbsp;
                <%}%> 
            </ul> <hr>
        <%}%> <!--End Filters Section-->

            <h4>Features</h4>

            <ul> <!--Root Categories-->
                <% for(Feature f: roots){ %>
                    <li style="list-style:none; font-size: 20px;">
                           <%=f.getName()%> 
                           <% if(f.hasChilds()) { %>
                            <a href="\System\doExpand?selectedValue=<%=f.getName()%>"> <img src="images\expand.png" alt="(+)"> </a>
                           <% } %>
                    </li>
                <% } %>
            </ul> <!--End Root Categories-->

        <!-- #region LIST OF SUBCATEGORIES-->
        <ul><% if(!PATH.isEmpty()) { %>
            <li style="margin-bottom: 10px; list-style:none; font-size: 20px;">
                <a href="\System\doContract?selectedValue=<%=PATH.peek()%>"> <img src="images\contract.png" alt="(-)"> </a>
                <%= PATH.peek() %>
            </li>
            <% } %>
            <ul>
                <% for(Feature f: features){ %>
                <li style="margin-bottom: 10px; list-style:none; font-size: 20px;">
                        <a style="color: inherit;" href="\System\doAddF?selectedValue=<%=f.getIdFeature()%>"> <%=f.getName()%> </a>
                        <% if(f.hasChilds()) { %>
                        <a href="\System\doExpand?selectedValue=<%=f.getName()%>"> <img src="images\expand.png" alt="(+)"> </a>
                        <% } %>
                </li>
                <% } %>  
            </ul>
        </ul>
        <!-- #END REGION LIST OF SUBCATEGORIES-->  
        </div>
        </div>
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
                        <form action="doSavePosFeatures" method="post">
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
                    
                        <button style="margin-left: 20px;" type="submit" class="btn btn-info">Add</button>
                        </form>
                    </div>
                    </div>
                </div>
            </div> <!--END LEVEL MODAL -->

        </div>  <!--END OF PRINCIPAL CONTAINER-->  

        
        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>      
    </body>
</html>