var currentLevelId = null;
var path = [];

(function() {
    // your page initialization code here
    // the DOM will be available here
    $("#table-back").click(function (e) { 
        e.preventDefault();
        goBack();
    });

    $("#table-add").click(function (e) { 
        e.preventDefault();
        openModal();
    });

 })();

function selectLevel(idFeature) {

    path.push(idFeature);
    currentLevelId = idFeature;

    console.log("Current Level " + currentLevelId);
    console.log("Path: " + path);
    
    
    $.ajax({
        type: "POST",
        url: "selectLevel",
        data: JSON.stringify(idFeature),
        processData: false,
        contentType: false,
        success: function (response) {
            showFeatures(response);
        }
    });
}

function showFeatures(featuresList) {

    var tr;
    var td;

    $("#features-tbody").html('');

    featuresList.forEach(element => {
        tr = document.createElement('tr');
        tr.setAttribute('onclick', "javascript:selectLevel('" + element.idFeature + "');");
        td = document.createElement('td');
        td.innerText = element.name;
        tr.appendChild(td);

        $("#features-tbody").append(tr);
    });
    
    
}

function goBack() {
    
    $.ajax({
        type: "POST",
        url: "goBack",
        data: JSON.stringify(currentLevelId),
        processData: false,
        contentType: false,
        success: function (response) {
            path.pop();

            if (path.length == 0) {
                currentLevelId = null;
            } else {
                currentLevelId = path[path.length - 1];
            }

            console.log("Current Level " + currentLevelId);
            console.log("Path: " + path);

            showFeatures(response);
        }
    });
}


function openModal(){
    $("#fModal").modal('show');
}

 function addFeature(){
        var name= $('#feature-name').val();
        var feature;

        if (currentLevelId == null) {
            
            feature={
                parentFeature: null,
                name: name,
                features: []
            };
        } else {
            
            feature={
                parentFeature: {
                    idFeature: currentLevelId,
                    name: ''
                },
                name: name,
                features: []
            };
        }

    $.ajax({
             type: "POST",
             url: "addAnotherFeature",
             data: JSON.stringify(feature),
             processData: false,
             contentType: false,
             success: function (response) {
                 $("#fModal").modal('hide');
                 updateTable(response);
             },
             error: function (response){
                 console.log("its NOT working");
             } 
         });
 }

function updateTable(feature) {
    tr = document.createElement('tr');
    tr.setAttribute('onclick', "javascript:selectLevel('" + feature.idFeature + "');");
    td = document.createElement('td');
    td.innerText = feature.name;
    tr.appendChild(td);

    $("#features-tbody").append(tr);
}