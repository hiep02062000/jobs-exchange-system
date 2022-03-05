<%@page import="dataAccessObjects.domain.Feature"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Mina" rel="stylesheet">
        <title>Features Maintenance</title>
    </head>
    
    <body>

      <!--HEADERS AND BEANS-->
      <%@ include file="adminHeader.jsp" %>
      <jsp:useBean id="fRoots" scope="request" type="List<Feature>" class="java.util.ArrayList"/>
      <!--END HEADER AND BEANS-->

      <div class="container"> <!--PRINCIPAL CONATINER-->

          <h1>Features Maintenance</h1> <hr>

          <div class="row"> <!--ROW--> <br>

          <div class="col-3"> &nbsp; &nbsp;
              <div class="card" style="width: 18rem; margin-left: 20px;"> <!-- CARD-->
                <img class="card-img-top" src="images\maintenance.png" alt="Card image cap">
                  <ul class="list-group list-group-flush">
                      <li class="list-group-item"><strong>Add a new Category:</strong><small> Click on the "Add Feature" button.</small></li>
                      <li class="list-group-item"><strong>Add a Sub-Category:</strong><small> Select one of the levels, then click the "Add Feature" button.</small></li>
                  </ul>
              </div> <!--END CARD-->
          </div>

          <!-- FEATURES TABLE -->
                
          <div class="col-4" style="margin-left: 90px;"> <br>
            <table class="table table-hover" id="features-table">
              <thead>
                <tr>
                  <th scope="col" id="th-feature" style="font-size: 20px;">Categories</th>
                </tr>
              </thead>
              <tbody id="features-tbody">  
                <% for(Feature f: fRoots){ %>
                <tr class="feature-row" onclick="javascript:selectLevel('<%=f.getIdFeature()%>');">
                  <td><%=f.getName()%></td>
                </tr>
                <% } %>
              </tbody>
            </table> 
          </div>

          <div class="col-3" style="margin-left: 40px; margin-top: 30px;"> <br>
            <button id="table-back" type="button" class="btn btn-info btn-block">Back</button>
            <button id="table-add" type="button" class="btn btn-info btn-block">Add feature</button>
          </div>

          </div> <!-- END ROW-->


          <!-- MODALS -->
          <div class="modal fade" id="fModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="fModalLabel">Add a feature</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                      <label for="feature-name">Name </label>
                      <input class="form-control" type="text" name="feature-name" id="feature-name">
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="javascript:addFeature()">Save feature</button>
                  </div>
                </div>
              </div>
          </div>
        
      </div> <!--END PRINCIPAL CONTAINER-->


        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js\featuresMaintenance.js"></script>
    </body>
</html>