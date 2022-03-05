<%@page import="dataAccessObjects.domain.Company"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Object user = (Object)session.getAttribute("user");%>

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
        
       <div class="container"> <!--PRINCIPAL CONTAINER-->

           <!-- TITLE -->
            <h1 class="main">Jobs Exchange System</h1>
            
            <!-- NAV BAR-->
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                
                <div class="collapse navbar-collapse" id="navbarSupportedContent"> <!--NAV BAR CONTENT-->
                  <ul class="navbar-nav mr-auto">  
                    <li class="nav-item active">
                      <a class="nav-link" href="\System\preCompanyLogin.jsp"> Profile <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                      <a id="offerers-search" class="nav-link" href="\System\OffererSearch">Looking for Offerers</a>                     
                    </li> 
                  </ul>
                    
                  <div class="nav-item dropdown"> <!--LOGIN DROPDOWN-->
                      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <%=((Company)user).getName()%>   
                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                          <a class="dropdown-item" href="\System\logout" >Sign Out</a>
                      </div>
                  </div> <!--END LOGIN DROPDOWN-->

                </div> <!--END NAV BAR CONTENT-->

            </nav> <!-- END MENU - NAV BAR--> 

       </div> <!--END PRINCIPAL CONTAINER-->

    </body>
</html>
