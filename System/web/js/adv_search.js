function position_view(a) {
    
    $.ajax({
        type: "POST",
        url: "pos_inf_adv",
        data: JSON.stringify(a),
        processData: false,
        contentType: false,
        success: function (response) {
           console.log(response.positionfeatures);
           updateModal(response);
        },
        error: function () { console.log("There was an error..");
        }
    });
}

function updateModal(position) {
    $("#positionAbout").modal('show');
    $("#company").val(position.company.name);
    $("#date").val(position.publishDate);
    $("#position").val(position.name);
    $("#salary").val("$" +position.salary);
    $("#features").html('');

    for (i in position.positionfeatures) {
        var li;
        if (position.positionfeatures[i].level !== undefined) {
            li = '<li>' + position.positionfeatures[i].feature.name + ' ' + position.positionfeatures[i].level + '% </li>';
            $("#features").append(li);
        }
    }
}

