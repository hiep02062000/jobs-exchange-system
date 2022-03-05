<%@page import="dataAccessObjects.domain.Login"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dataAccessObjects.domain.Position"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <title>Profile</title>
    </head>
    <body>

        <%@ include file="adminHeader.jsp" %>

        <jsp:useBean id="loginsCompany" scope="request" type="List<Login>" class="java.util.ArrayList"/>
        <jsp:useBean id="loginsOfferer" scope="request" type="List<Login>" class="java.util.ArrayList"/>
        
        <div class="container"> <br>

            <h2>Administrator</h2> <hr>

            <div class="row"> 

                <div class="col-3"> &nbsp; &nbsp;
                    <div class="card" style="width: 18rem; margin-left: 5px;">
                            <img class="card-img-top" src="images\usersProfile.png" alt="Card image cap">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><img src="images\email.png">&nbsp;&nbsp;&nbsp;admin@jobssystem.com</li>
                            </ul>
                    </div>
                </div>
            
                <div class="col-3" style="margin-left: 80px;"> <br>
                    <h3>Companies' Request</h3>
                <table class="table table-striped" id="login-table" style="display: inline;">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Email</th>
                            <th scope="col">Activation</th>
                        </tr>
                        </thead>
                        <tbody id="login-data">
                            <% if(!loginsCompany.isEmpty()) { %>
                                <% for(Login l: loginsCompany) { %>
                                    <tr id="<%=l.getIdLogin()%>">
                                        <th scope="row"><%= loginsCompany.indexOf(l) + 1 %></th>
                                        <td><%= l.getEmail()%></td>
                                        <td><button onclick="javascript:setPasswordModal('<%=l.getIdLogin()%>')" class="btn btn-success enable-btn">Enable</button></td>
                                    </tr>
                                <%}%>   
                            <% } else { %>
                                <tr>
                                    <th scope="row"> Empty </th>
                                    <td> Empty </td>
                                    <td> Empty </td>
                                </tr>
                            <% } %>
                        </tbody> 
                </table>
                </div> <!--END COMPAMY LOGINS TABLE-->


                <div class="col-3" style="margin-left: 90px;"> <br>
                    <h3>Offerers' Request</h3>

                <table class="table table-striped" id="login-table" style="display: inline;">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Email</th>
                            <th scope="col">Activation</th>
                        </tr>
                    </thead>
                        <tbody>
                            <% if(!loginsOfferer.isEmpty()) { %>
                                <% for(Login l: loginsOfferer) { %>
                                    <tr id="<%=l.getIdLogin()%>">
                                        <th scope="row"><%= loginsOfferer.indexOf(l) + 1 %></th>
                                        <td><%= l.getEmail()%></td>
                                        <td><button onclick="javascript:setPasswordModal('<%=l.getIdLogin()%>')" class="btn btn-success enable-btn">Enable</button></td>
                                    </tr>
                                <%}%>   
                            <% } else { %>
                                <tr>
                                    <th scope="row"> Empty </th>
                                    <td> Empty </td>
                                    <td> Empty </td>
                                </tr>
                            <% } %>
                        </tbody>
                </table>

                </div> <!--END COMPAMY LOGINS TABLE-->


            </div> <!--END DIV ROW-->
            
            <!-- Modal -->
            <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <h5 class="modal-title" id="loginModalLabel">Please set the password:</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                        <label for="login-password">Password: </label>
                        <input class="form-control" type="password" name="login-password" id="login-password">
                    </div>
                    <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success" id="enable-account"  onclick="javascript:enableAccount()">Enable Account</button>
                    </div>
                </div>
                </div>
            </div>

        </div> <!--END PRINCIPAL CONTAINER-->

        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>

        <script src="js\adminProfile.js"></script>
    </body>
</html>
