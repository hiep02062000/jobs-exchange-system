<%@page import="dataAccessObjects.domain.Position"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Jobs Exchange System</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
        <link href="https://fonts.googleapis.com/css?family=Mina" rel="stylesheet">
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <link rel="stylesheet" href="css\styles.css">
    </head>

    <body>
        
        <div class="container"> <!--PRINCIPAL CONTAINER-->
          
            <%@ include file="header.jsp" %>

            <br>
            <form id="general-search" action="Search">
                <div class="form-row">
                  <div class="col-7">
                    <input type="search" class="form-control search" name="search" placeholder="Ex: Developer, Engineer">
                  </div>
                  <div class="col-1">
                    <button class="btn btn-info" id="general-search-bt" type="submit">SEARCH</button>
                  </div>
                </div>
            </form> <br> <br>

            <!-- CAROUSEL and SIGN UP-->
            <jsp:useBean id="positionL" scope="request" type="List<Position>" class="java.util.ArrayList"/>

            <div class="row"> <!--HERE STARTS THE CAROUSEL AND SIGN UP'S ROW...-->

                <!--SIGN UP OPTIONS-->
                <div class="card rcorners" id="sign-up" style="width: 18rem;">
                    <img class="card-img-top" style="border-top-left-radius: 20px; border-top-right-radius: 20px;" src="images\peopleWorking.png">
                    <div class="card-body">
                        <h5 class="card-title">SIGN UP</h5>
                        <p class="card-text">Register your professional profile and start looking for your dreamed job now!</p>
                        <a href="\System\offererRegistration.jsp" class="btn btn-info">Offerer</a>
                        <a href="\System\companyRegistration.jsp" class="btn btn-info">Company</a>
                    </div>
                </div>
                <!--END OF SIGN UP OPTIONS-->

            
                <div id="myCarousel" class="carousel slide col-8" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                        <% for(int i=0; i<positionL.size(); i++) { %>
                          <li id="indicator-<%=i%>" data-target="#myCarousel" data-slide-to="<%=i%>"></li>
                        <% } %>
                        </ol>
                    
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                    
                        <% for(Position p: positionL){ %>
                          <div class="carousel-item" id="carouselItem-<%=positionL.lastIndexOf(p)%>">
                            <img src="companyImages\<%=p.getCompany().getIdCompany()%>.png" style="width:100%; height: 375px;">
                            <div class="carousel-caption">
                              <h2 class="carousel-title" onclick="javascript:showInfo('<%=p.getIdPosition()%>');" > <b> <%=p.getName()%> </b> </h2>
                              <p>Click for More Info!</p>
                            </div>
                          </div>
                        <% } %>

                        </div> 
                        <!-- #endregion CAROUSEL INNER-->
                    
                        <!-- Left and right controls -->
                        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>.
                            <span class="sr-only">Next</span>
                        </a>
                </div>

            </div>  <!--HERE ENDS THE CAROUSEL AND SIGN UP'S ROW...--> <br>

            <div class="row" style="margin-bottom: -20px; "> 
                <!-- #JUMBOTRON-->
                <div class="jumbotron col-8 rcorners" style="margin-left: 40px;">
                        <h1 class="display-4">Why to apply online?!</h1>
                        <p class="lead">The internet is an essential employment resource for many of today’s job seekers, being used for the 80% of the people looking for a job.</p>
                </div>
                <!-- # END JUMBOTRON-->
                
                <!-- #region FEEDBACK-->
                <form style="margin-left: 50px" class="form-group" id="feedback" action="">
                    <h4 style="text-decoration-line: underline;">Feedback:</h4>
                    <label for="subject-feed">Subject:</label>
                    <input class="form-control" type="text" id="subject-feed">
                    <label for="inquiry-feed">Inquiry: </label>  
                    <textarea id="inquiry-feed" class="form-control" rows="2"> </textarea>
                    <button onclick="sendMail(); return false" style="margin-top: 15px;" type="submit" class="btn btn-outline-info">Send</button>
                </form>
                <!-- #endregion FEEDBACK-->
            </div> 
              
            <!--Footer-->
            <footer class="page-footer font-small blue pt-4 mt-4" id="footer">

            <!--Footer Links-->
            <div class="container-fluid text-center text-md-left">
                <div class="row">

                    <!--First column-->
                    <div class="col-md-6">
                        <h5 class="text-uppercase" style="text-decoration: underline">About Us</h5>
                        <p>Jobs Exchange System is a project elaborated by 3 systems engineering students from Costa Rica.</p>
                    </div>
                    <!--/.First column-->

                    <!--Second column-->
                    <div class="col-md-6">
                        <h5 class="text-uppercase" style="text-decoration: underline">Links</h5>
                        <ul class="list-unstyled">
                            <li>
                                <a href="http://www.una.ac.cr/" target="_blank"><p style="color:black;">Universidad Nacional Costa Rica</p></a>
                            </li>
                            <li>
                                <a href="http://www.escinf.una.ac.cr/" target="_blank"><p style="color:black;">Escuela de Informatica</p></a>
                            </li>
                        </ul>
                    </div>
                    <!--/.Second column-->
                </div>
            </div>
            <!--/.Footer Links-->

            <!--Copyright-->
            <div class="footer-copyright py-3 text-center" id="copyright" style="color: white;">
               GitHub:
               <a href="https://github.com/ANDRESROMEROH/Jobs-Exchange" target="_blank" style="color: white; text-decoration: underline;"> Jobs-Exchange-System </a>
            </div>
            <!--/.Copyright-->

            </footer>
            <!--/.Footer-->

            </div> <!--END OF PRINCIPAL CONTAINER-->


            <!-- #MODAL TOP 5 Information-->
            <div class="modal fade" id="top5Modal" tabindex="-1" role="dialog" aria-labelledby="top5ModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle" id="img_logo" src="images/worker.png" style="max-width: 50px; max-height: 50px"> </div>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    </div>
                    <div class="modal-body">
                      <h3 style="font-family: 'Mina', sans-serif;" class="modal-title" id="top5ModalLabel"></h3> <br>
                      <label for="name">Company: </label>  
                      <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="company-name" type="text" disabled>
                      <label for="publish-date">Published On: </label>  
                      <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="date" type="text" disabled>
                      <label for="publish-date">Salary: </label>  
                      <input style="border-color: rgb(36, 135, 201);" class="form-control col-12" id="salary" type="text" disabled>
                      <label for="requirements">Requirements: </label>
                      <ul id="requirements"></ul>
                    </div>
                    <div class="modal-footer">
                    <button id="modal-close" type="button" class="btn btn-outline-info" data-dismiss="modal">Close</button>
                    </div>
                </div>
                </div>
            </div>
             <!-- #END MODAL TOP 5 Information-->             
                  
        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js\index.js"></script>
    </body>
</html>