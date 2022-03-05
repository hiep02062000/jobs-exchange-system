
document.getElementById('indicator-0').classList.toggle('active'); //Carousel
document.getElementById('carouselItem-0').classList.toggle('active'); //Carousel


function showInfo(a) {
    
    $.ajax({
        type: "POST",
        url: "showInfo",
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
    $("#top5Modal").modal('show');
    $("#company-name").val(position.company.name);
    $("#top5ModalLabel").text(position.name);
    $("#date").val(position.publishDate);
    $("#salary").val("$" + position.salary);
    $("#requirements").html('');

    for (i in position.positionfeatures) {
        var li;
        if (position.positionfeatures[i].level !== undefined) {
            li = '<li>' + position.positionfeatures[i].feature.name + ' ' + position.positionfeatures[i].level + '% </li>';
            $("#requirements").append(li);
        }
    }
}

//FEEDBACK:
function sendMail() {
    window.open("mailto:andres030579@gmail.com"
    + "?cc=estefa.mr97@gmail.com" + "&subject=" 
    + escape(document.getElementById('subject-feed').value)
    + "&body=" + document.getElementById('inquiry-feed').value);

    document.getElementById("feedback").reset();
}
