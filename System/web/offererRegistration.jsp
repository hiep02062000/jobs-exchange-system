<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
        <link rel="stylesheet" href="css\bootstrap.min.css">
        <title>Registration</title>
    </head>

    <body>

    <%@ include file="header.jsp" %>
         
    <div class="container"> <br>

        <h2>You're about to join to the best job market place!</h2> <hr> 
      
        <form action="javascript:add();" method="POST" id="OffererRegistration" name="OffererRegistration">
            
            <!-- BASIC INFORMATION -->

            <div class="row">
            <div class="col-7">

                <div class="form-row col-md-12">
                    <div class= "form-group col-md-6">
                        <label for="inputName">Name</label>
                        <input type="text"  id="name" class="form-control" name="name" placeholder="Name" required>    
                    </div>

                    <div class="form-group col-md-6">
                        <label for="inputLast Name">Last Name</label>
                        <input type="text" id="lastname" class="form-control" name="lastname" placeholder="Last Name"  required>
                    </div>
                </div>

                <div class="form-row col-md-12">
                    <div class="form-group col-md-6">
                        <label for="inputId">ID</label>
                        <input type="text" id="id" class="form-control" name="id" placeholder="ID" required>
                    </div>
                        
                    <div class="form-group col-md-6">            
                        <label for="inputPhoneNumber">Phone Number</label>
                        <input type="text" id="phone" class="form-control" name="phone" placeholder="Phone Number" required>         
                    </div>
                </div>

                    <div class="form-group col-md-12">
                        <label for="inputEmail">Email Address <small class="text-muted"> &nbsp; [We'll never share your email with anyone else]</small> </label>
                        <input id="email" type="email" class="form-control" name="email" placeholder="Email" required>           
                    </div>
                
                <div class="form-row col-md-12">
                    <div class="form-group col-md-6">
                        <small>Upload your CV, fotmat: .PDF</small> <br>
                        <input type="file" name="cv" id="cv" class="hide filestyle" accept=".pdf">
                    </div>
                    <div class="form-grouu col-md-6">
                        <small>Upload your Profile Pic, format: .PNG</small> <br>
                        <input type="file" name="profile-pic" id="profile-pic" class="hide filestyle" accept="image/*">
                    </div>
                </div> <hr>

                    <!-- <br> -->
                    
                    <div class="form-row col-md-6" style="margin-left: 140px;">
                        <button type="submit" id="register" class="btn btn-info btn-block"> <b>Sign Up</b> </button>
                    </div>

                </div>

                <div class="col-3" style="margin-left: 80px;">
                    <img class="rcorners" style="border: solid; border-width: 0.1px; width: 256px; height: 256px;" id="preview" src="images/userProfile.png" />
                    <hr> <figcaption> <b> PROFILE PICTURE </b> </figcaption>
                </div>  

            </div> <!--END ROW-->

            </form> <!--END FORM-->

    </div> <!--END PRINCIPAL CONTAINER-->

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
                  <p>There was and error, please validate you are using a correct ID or Email address.</p>
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
                  <p>You have been registered!</p>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-info" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>

    <!--END MODALs-->

        <script src="js\jquery.js"></script>
        <script src="js\bootstrap.min.js"></script>
        <script src="js/bootstrap-filestyle.min.js"> </script>
        <script src="js\offererRegistration.js"></script>

    </body>

</html>