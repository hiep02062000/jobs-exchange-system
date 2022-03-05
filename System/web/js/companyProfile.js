var idPosition;

function disableModal(id) {
    $("#disableModal").modal('show');  
    idPosition = id;
}

function enableModal(id) {
    $("#enableModal").modal('show');  
    idPosition = id;
}


 function disablePosition(){
        position={
            idPosition: idPosition,
            enable: -128
        };

    $.ajax({
             type: "POST",
             url: "disablePosition",
             data: JSON.stringify(position),
             processData: false,
             contentType: false,
             success: function (response) {
                 $("#disableModal").modal('hide');
                 $('#btn-' + idPosition).text('Enable');
                 document.getElementById('btn-' + idPosition).classList.remove("btn-danger");
                 document.getElementById('btn-' + idPosition).classList.add("btn-success");
                 document.getElementById('btn-' + idPosition).setAttribute('onclick','javascript:enableModal(' + idPosition + ')');
             },
             error: function (response){
                 window.alert("Was imposible to disable...");
             } 
         });
 }
 
  function enablePosition(){
        position={
            idPosition:idPosition,
            enable: 127
        };

    $.ajax({
             type: "POST",
             url: "disablePosition",
             data: JSON.stringify(position),
             processData: false,
             contentType: false,
             success: function (response) {
                 $("#enableModal").modal('hide');
                 $('#btn-' + idPosition).text('Disable');
                 document.getElementById('btn-' + idPosition).classList.remove("btn-success");
                 document.getElementById('btn-' + idPosition).classList.add("btn-danger");
                 document.getElementById('btn-' + idPosition).setAttribute('onclick','javascript:disableModal(' + idPosition + ')');
             },
             error: function (response){
                 window.alert("Was imposible to enable...");
             } 
         });
 }


