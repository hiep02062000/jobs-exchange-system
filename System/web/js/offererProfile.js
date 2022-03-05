var tr;

(function() { //When DOM is ready
    $("#success-alert").hide();
 })();

$(".offererF-row").click(function (event) { 
    tr = $(this);
    $("#ofFeatureModal").modal('show');
});

 function updateLevel(){
        var td = $(tr).children('td');
        var name = td[0].innerText;
        var lev= parseFloat($('#upLevel').val());
        Offererfeature={
            feature:{
                name: name
            },
            level: lev
        };

    $.ajax({
             type: "POST",
             url: "updateLevel",
             data: JSON.stringify(Offererfeature),
             processData: false,
             contentType: false,
             success: function (response) {
                 $("#ofFeatureModal").modal('hide');
                 td[1].innerText = parseFloat($('#upLevel').val()) + '.0%';
             },
             error: function (response){
                 console.log("its NOT working");
             } 
         });
}

function showModalCV() {
    $("#pdfModal").modal('show');
}

$("#modal-accept").click(function (e) { 
    e.preventDefault();

    data=new FormData();
        data.append("id",JSON.stringify(idOfferer));
        data.append("cv",$("#cv")[0].files[0]);   

    $.ajax({
        type: "POST",
        url: "updateCV",
        data: data,
        processData: false,
        contentType: false,
        success: function (response) {
            $("#success-alert").fadeIn();
            
            setTimeout(() => {
                $("#success-alert").slideToggle();
            }, 3000);
            document.getElementById("cv").value = '';
        },
        error: function (response) { 
            console.log("Error...");
         }
    });

});