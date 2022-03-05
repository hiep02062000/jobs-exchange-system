function readURL(input) { //To display the image preview...

    if (input.files && input.files[0]) {
      var reader = new FileReader();
  
      reader.onload = function(e) {
        $('#preview').attr('src', e.target.result);
      }
  
      reader.readAsDataURL(input.files[0]);
    }
}

$("#profile-pic").change(function (e) { 
    e.preventDefault();
    console.log("Testing image preview...");
    
    readURL(this);
});

  //END of method for image preview...

function add(){
    
    offerer = {
             idOfferer: document.getElementById("id").value,
             login: {
                  email: document.getElementById("email").value,
                  password: 1234,
                  type: 2,
                  enable: -128
              },
              name: document.getElementById("name").value,
              lastName: document.getElementById("lastname").value,
              originCountry: "Costa Rica",
              phoneNumber:document.getElementById("phone").value,
              offererfeatures: []
          };

        data=new FormData();
        data.append("offerer",JSON.stringify(offerer));
        data.append("cv",$("#cv")[0].files[0]);
        data.append("profile-pic",$("#profile-pic")[0].files[0]);

              $.ajax({
                type: "POST",
                url: "doRegistryProcessOfferer",
                data: data,
                processData: false,
                contentType: false,
                success: function () {
                   $("#modal-success").modal('show');
                   document.getElementById("OffererRegistration").reset();
                   $('#preview').attr('src','images/userProfile.png');
                },
                error: function (){
                   $("#modal-error").modal('show');
                } 
              });
              
    } 