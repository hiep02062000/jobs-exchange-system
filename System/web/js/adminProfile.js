var idObject;

function setPasswordModal(id) {
    $("#loginModal").modal('show');  
    idObject = id;
} 

function enableAccount(){

        login={
            idLogin:idObject,
            password: $('#login-password').val(),
            enable: 127
        };

    $.ajax({
             type: "POST",
             url: "enableAccount",
             data: JSON.stringify(login),
             processData: false,
             contentType: false,
             success: function (response) {
                 $("#loginModal").modal('hide');
                 document.getElementById(idObject).remove();
             },
             error: function (response){
                 console.log("its NOT working");
             } 
         });
}