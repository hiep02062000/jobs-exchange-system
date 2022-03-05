<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <title>Registration</title>
        <style>
        #map {
            height: 400px;
            width: 100%;
            }
        </style>
    </head>

    <body>
        
        <%@ include file="header.jsp" %>

        <div class="container"> <!--Principal Container-->
            <h3>You're about to join to the best job market place!</h3> <hr>
                    
            <form action="javascript:add();" method="post" name="CompanyRegistration" id="CompanyRegistration" style="display: inline;">


            <!-- BASIC INFORMATION -->
            <div class="row">
            <div class="col-5">
            <div class="form-group" style="display: inline;">
                <label for="inputCompany">Company Name</label>
                <input type="text" id="name" class="form-control" name="name" placeholder="Your Company's Name" required> 
            </div> <br>
            <div class="form-group" style="display: inline;">
                <label for="inputPhoneNumber">Telephone Number</label>
                <input type="text" id="phone" class="form-control" name="phone" placeholder="Ex: 2298-60-00" required>
            </div> <br>
            <div class="form-group" style="display: inline;">
                <label for="inputEmail">Email Address <small class="form-text text-muted">[We'll never share your email with anyone else]</small></label>
                <input type="email" id="email" class="form-control" name="email" placeholder="Ex: company@outlook.com" required> 
            </div> <br>
            <div class="form-group" style="display: inline;">  
                <label for="formText">Description</label> 
                <input id="description" class="form-control" name="description" placeholder="What's your company about?" required>
            </div> <br>
            <div class="form-group" style="display: inline;">
                <label for="formText">Submit Your Company's Picture</label> 
                <input style="margin-left: 9px;" type="file" name="profilePic" id="profilePic" class="hide filestyle" data-buttonBefore="true" data-text="Company Picture" accept="image/png">
            </div> <br>
            </div>
            <div class="form-group col-6" style="display: inline;">
                <label for="formText">Place your company's location: <small class="text-muted">[Drag and Drop the Marker]</small></label> 
                <div id="map" style="border: solid; margin-left: 40px; border-width: 1px;"></div>
            </div>
            </div> <hr>
                
            </form> <br> <br>

            <div id="paypal-button-container" style="bottom: 50px; position:relative; left: 370px;"></div>
            
            
            
            <!--MODALs-->
    
    <!--ERROR MODAL-->
    <div class="modal fade" id="modal-error" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" style="color: red;">ERROR!</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <p>There was an error, please validate you are using a correct Email address.</p>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-danger" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>
    
    <!--SUCCESS MODAL-->
        <div class="modal fade" id="modal-success" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Success!</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <p>Subscription completed, company has been registered!</p>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-info" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>

    <!--END MODALs-->

           
        </div> <!--End of principal container-->
           
            <script src="js\companyRegistration.js"></script>
            <script async defer 
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC9exI5czKBSQkhitNZaNLFYQpXKM-m4s4&callback=initMap">
            </script>
            <script src="js\jquery.js"></script>
            <script src="js\bootstrap.min.js"></script>
            <script src="js/bootstrap-filestyle.min.js"> </script>
            <script src="https://www.paypalobjects.com/api/checkout.js"></script>
            <script src="js\paypal.js"></script>

    </body>
</html>