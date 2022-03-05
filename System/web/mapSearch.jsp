<%@page import="java.util.List"%>
<%@page import="dataAccessObjects.domain.Position"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Map Search</title>
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <style>
        #map {
            height: 400px;
            width: 100%;
            }
        </style>
    </head>

    <body>

        <div class="container">

            <%@ include file="header.jsp" %> <br>

            <h3>&nbsp; Map Search &nbsp; <small>Powered by GOOGLE ®</small></h3> <hr>

            <div class="row">
                <div class="col-5">
                    <!-- #region RESULTS-->                   
                    <ol id="results">

                    </ol>
                    <!--END #region RESULTS--> 
                </div>

                <div class="form-group col-6" style="display: inline;">
                    <div id="map" style="border: solid; margin-left: 40px; border-width: 1px;"></div>
                </div>
            </div>

        </div>


        <!-- BEGIN POSITION'S MODAL-->
        <div class="modal fade" id="positionAbout" tabindex="-1" role="dialog" aria-labelledby="positionAbout" aria-hidden="true">
            <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                <div class="d-flex justify-content-center" style="width: 90%">  <img class="img-circle" id="img_logo" src="images/worker.png" style="max-width: 50px; max-height: 57px"> </div>
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


        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js\mapSearch.js"></script>
        <script async defer 
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9exI5czKBSQkhitNZaNLFYQpXKM-m4s4&callback=initMap">
        </script>
        <script src="js\adv_search.js"></script>
    </body>

</html>
