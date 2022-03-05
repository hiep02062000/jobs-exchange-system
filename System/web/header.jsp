<%@page import="dataAccessObjects.domain.Login"%>
<%@page import="dataAccessObjects.domain.Offerer"%>
<%@page import="dataAccessObjects.domain.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object user = (Object)session.getAttribute("user");%>
<% Integer userType = (Integer)session.getAttribute("userType");%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
        <link href="https://fonts.googleapis.com/css?family=Mina" rel="stylesheet">
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <link rel="stylesheet" href="css\styles.css">
    </head>
    
    <body>
        <% String ERROR = (String) session.getAttribute("ERROR");%>
        <% String EMAIL = (String) session.getAttribute("email");%>
        <% String PASSWORD = (String) session.getAttribute("password");%>


        <div class="container"> <!--PRINCIPAL CONTAINER-->
          
            <!-- TITLE -->
            <% if (user==null){%>
            <h1 class="main"><a href="\System\Home">Jobs Exchange System</a></h1>
            <% } else{ %>
            <h1 class="main">Jobs Exchange System</h1>
            <% } %>

            <!-- NAV BAR-->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">

              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
              </button>
                  
              <div class="collapse navbar-collapse" id="navbarSupportedContent"> <!--NAV BAR CONTENT-->

                  <ul class="navbar-nav mr-auto"> 
                      <% if (user==null){%>
                    <li class="nav-item active">
                      <a class="nav-link" href="\System\Home"> Home <span class="sr-only">(current)</span></a>
                    </li>
                    <% } else{ %>
                    <li class="nav-item active">
                      <a class="nav-link" href="\System\OffererHome"> Home <span class="sr-only">(current)</span></a>
                    </li>
                    <% }%>
                    <li class="nav-item active">
                      <a id="advS" class="nav-link" href="\System\AdvancedSearch">Advanced Search</a>                         
                    </li>

                    <li class="nav-item active">
                        <a id="map-search" class="nav-link" href="\System\MapSearch">Map Search</a>                         
                    </li>
                  </ul>

                    
                  <!--LOGIN (COMPANYS, OFFERERS AND ADMIN)-->  
                  <% if (user==null){%>
                  <form class="form-inline my-2 my-lg-0" action="login" method="POST">
                      <% if (EMAIL==null){%>
                      <input type="text" class="form-control" name="email" placeholder="Email"> &nbsp
                      <% }%>
                      <% if (EMAIL!=null){%>
                      <input type="text" class="form-control" name="email" placeholder="Email" value="<%=EMAIL%>"> &nbsp
                      <% }%>
                      <% if (PASSWORD==null){%>
                      <input type="password" class="form-control" name="password" placeholder="Password"> &nbsp
                      <% }%>
                      <% if (PASSWORD!=null){%>
                      <input type="password" class="form-control" name="password" placeholder="Password" value="<%=PASSWORD%>"> &nbsp
                      <% }%>
                      <button type="submit" id="button" class="btn btn-outline-info my-2 my-sm-0">Sign In</button>
                  </form>
                  <% }%> 
                  
                   <% if (user!=null){%>
                <div class="nav-item dropdown"> <!--LOGIN DROPDOWN-->
                
                  <% if (userType == Login.OFFERER){%>
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%=((Offerer)user).getName()%> <%=" "%> <%=((Offerer)user).getLastName()%>
                  </a>
                  <% }%> 
                
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                         <% if (userType == Login.OFFERER){%>
                        <a class="dropdown-item" href="\System\preOffererLogin.jsp">Profile</a>
                         <% }%> 
                      <a class="dropdown-item" href="\System\logout" >Sign Out</a>
                    </div>
                </div> <!--END LOGIN DROPDOWN
                <% }%> <!--END LOGIN (COMPANYS, OFFERERS AND ADMIN)-->

              </div> <!--END NAV BAR CONTENT-->

            </nav> <!-- END MENU - NAV BAR-->     
         
        </div> <!--END PRINCIPAL CONTAINER-->

        
            <% if (ERROR!=null){%>
              <label class="er" name="ERROR" style="margin-left: 580px"><%=ERROR%></label>
              <% ERROR = null; %>
            <% }%>

    </body>
</html>
