//Script to render map and places from the Back-End
var map;
var centralPoint;
var markers = [];
var placesID = [];


function initMap() {
    centralPoint = {lat: 9.929029, lng: -84.092173};

    map = new google.maps.Map(document.getElementById('map'), {
      zoom:12,
      center: centralPoint
    });
    
    $.ajax({
        type: "POST",
        url: "MapPlaces",
        processData: false,
        contentType: false,
        success: function (places) {
            renderMarkers(places);
            // Fired when the map becomes idle after panning or zooming.
            google.maps.event.addListener(map, 'idle', function() {
                findVisibleMarkers();
            });
            findVisibleMarkers();
        },
        error: function () { 
            console.log('There was an error...');    
        }
    });

  }


function renderMarkers(places) {
    
    places.forEach(place => {
        var point = {lat: place.latitude , lng:  place.longitude};

        var contentString = '<div id="content">'+
            '<h6 id="firstHeading" class="firstHeading">' + place.name +'</h6>'+
            '</div>';

            var infowindow = new google.maps.InfoWindow({
                content: contentString
              });
              
            var marker = new google.maps.Marker({
                position: point,
                label: place.name[0],
                map: map,
                id: parseInt(place.idCompany)
            })

            marker.addListener('mouseover', function() {
                infowindow.open(map, this);
                setTimeout(function () { infowindow.close(); }, 2000);
            }); 

            // Keep marker instances in a global array
            markers.push(marker);
    }); 
}

function findVisibleMarkers() {
    var bounds = map.getBounds();
                                   
    for (var i = 0; i < markers.length; i++) {
        var marker = markers[i];
                                           
        if(bounds.contains(marker.getPosition())===true) {
            placesID.push(parseInt(marker.id));
        }
    }
    searchPositions();
}

function searchPositions() {    
    $.ajax({
        type: "POST",
        url: "getPositions",
        data: JSON.stringify(placesID),
        processData: false,
        contentType: false,
        success: function (response) {
            $("#results").fadeOut();
            showPositions(response);
        },
        error: function () { 
            console.log('There was an error...');    
        }
    });
}

function showPositions(positions) {

    placesID = [];
    $("#results").html('');
    
    if(positions.length > 0) {
        positions.forEach(element => {
            var li = document.createElement('li');
            li.setAttribute('style','cursor: pointer;');

            var p = document.createElement('p');
            p.innerHTML = '<b style="cursor: pointer;" onclick="javascript:position_view(' + element.idPosition +')">' 
            + element.name + '</b> <br> <small>Company: ' + element.company.name + '</small> <br> <hr>';

            li.appendChild(p);
            $("#results").append(li);

            //Effects: 
            $("#results").hide();
            $("#results").fadeIn();
        });
    }
}

