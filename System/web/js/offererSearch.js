
function offerer_view(a) {
    
    $.ajax({
        type: "POST",
        url: "of_inf",
        data: JSON.stringify(a),
        processData: false,
        contentType: false,
        success: function (response) {
           console.log(response.offererfeatures);
           updateModal(response);
        },
        error: function () { console.log("There was an error..");
        }
    });
}

function updateModal(offerer) {
    $("#offererAbout").modal('show');
    $("#name").val(offerer.name);
    $("#last-name").val(offerer.lastName);
    $("#country").val(offerer.originCountry);
    $("#phone").val(offerer.phoneNumber);
    $("#email").val(offerer.login.email);
    $("#pdf").attr("href", "documents/"+offerer.idOfferer+".pdf");
    $("#features").html('');

    for (i in offerer.offererfeatures) {
        var li;
        if (offerer.offererfeatures[i].level !== undefined) {
            li = '<li>' + offerer.offererfeatures[i].feature.name + ' ' + offerer.offererfeatures[i].level + '% </li>';
            $("#features").append(li);
        }
    }
}