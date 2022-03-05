<%@page import="dataAccessObjects.domain.Company"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dataAccessObjects.domain.Position"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Company company = (Company) session.getAttribute("user"); %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <title>Profile</title>
    </head>

    <body>

        <%@ include file="companyHeader.jsp" %>

        <jsp:useBean id="companysPos" scope="request" type="List<Position>" class="java.util.ArrayList"/>
        
        <div class="container"> <br>

            <h2>Welcome back <%=company.getName()%> !</h2> <hr>

            <div class="row"> 

                <div class="col-3"> &nbsp; &nbsp;
                    <div class="card" style="width: 18rem; margin-left: 15px;">
                        <img class="card-img-top" src="companyImages/<%=company.getIdCompany()%>.png" onerror="this.src='images/usersProfile.png';">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><img src="images\user-icon.png">&nbsp;&nbsp;&nbsp;<%=company.getName()%></li>
                                <li class="list-group-item"><img src="images\Phone-icon.png">&nbsp;&nbsp;&nbsp;<%=company.getPhoneNumber()%></li>
                                <li class="list-group-item"><img src="images\email.png">&nbsp;&nbsp;&nbsp;<%=company.getLogin().getEmail()%></li>
                                <li class="list-group-item"><%=company.getDescription()%></li>
                            </ul>
                    </div>
                </div>
            

            <div class="col-5" style="margin-left: 90px;"> <br>

                <h3>Positions:</h3>

                <table class="table table-hover">
                  <thead>
                    <tr>
                     <th scope="col">Name</th>
                     <th scope="col">Published Date</th>
                     <th scope="col">Salary</th>
                     <th scope="col">Enable</th>
                     <th scope="col">Offerers</th>
                    </tr>
                  </thead>
                  <tbody>
                  <% for(Position p: companysPos) { %>
                    <tr class="position-row" id='<%=p.getIdPosition()%>'>
                      <td><%= p.getName()%></td>
                        <td><%= p.getPublishDate()%></td>
                        <td><%= p.getSalary()%></td>
                        <%if(p.getEnable()==Byte.MAX_VALUE ){ %>
                        <td><button id="btn-<%=p.getIdPosition()%>" onclick="javascript:disableModal('<%=p.getIdPosition()%>')" class="btn btn-danger">Disable</button></td>
                        <% } else { %>
                        <td><button id="btn-<%=p.getIdPosition()%>" onclick="javascript:enableModal('<%=p.getIdPosition()%>')" class="btn btn-success">Enable</button></td>
                        <% } %>
                        <td><a href="\System\searchOfferers?selectedValue=<%=p.getIdPosition()%>" class="btn btn-info">Search</a></td>
                    </tr>
                    <%}%> 
                  </tbody>
                </table>

            </div>
                  
            <div class="col-2" style="margin-top: 70px; margin-left: 80px;">
               <!-- <a href="\System\Position" class="btn btn-info">Add Position</a>-->
               <button type="button" class="btn btn-info" data-toggle="modal" data-target="#positionModal">
                Add Position <!-- Button trigger modal -->
            </button>
            </div>

            </div> <!-- END ROW-->
            
            
            <!-- Disable Modal -->
            <div class="modal fade" id="disableModal" tabindex="-1" role="dialog" aria-labelledby="disableModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title" id="disableModalLabel">Disable position</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to disable this position?</p>
                        <p><small>Note: Offerers won't be able to look up for this position</small></p>
                    </div>
                    <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" id="enable-account"  onclick="javascript:disablePosition()">Yes</button>
                    </div>
                </div>
                </div>
            </div>
            
            <!-- Enable Modal -->
            <div class="modal fade" id="enableModal" tabindex="-1" role="dialog" aria-labelledby="enableModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title" id="enableModalLabel">Enable position</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <p>Are you sure you want to enable this position?</p>
                        <p><small>Note: Offerers will be able to look up for this position</small></p>
                    </div>
                    <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-success" id="enable-account"  onclick="javascript:enablePosition()">Yes</button>
                    </div>
                </div>
                </div>
            </div>
            
            <!-- ADD POSITION MODAL -->
            <div class="modal fade" id="positionModal" tabindex="-1" role="dialog" aria-labelledby="positionModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="positionModalLabel">New Position</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                
                <div class="modal-body">
                    <form action="doPositionRegistry" method="post">
                        
                        <label for="inputName">Type the position's name</label>
                        <input type="text" class="form-control col-12" name="name" placeholder="Name" required>

                        <label for="inputSalary">Salary</label>
                        <input type="text" class="form-control col-12" name="salary" placeholder="Salary" required><br>

                        <label for="inputPublic">Public Position</label>&nbsp;&nbsp;&nbsp;
                        <input type="radio"  name="mode" value="public" id="public" checked>&nbsp;&nbsp;&nbsp;
                        <label for="inputPrivate">Private Position</label> &nbsp;
                        <input type="radio" name="mode" value="private" id="private"> 
                        <br><br>
                        <button type="submit" class="btn btn-info col-12">Save Changes and Add Position Features</button>
                    </form>
                </div>
                </div>
            </div>
            </div>

            <br> <br>

        </div> <!-- END PRINCIPAL CONTAINER-->

        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js\companyProfile.js"></script>
    </body>
</html>
