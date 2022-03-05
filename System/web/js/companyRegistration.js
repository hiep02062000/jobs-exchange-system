
var point;
var marker;

function initMap() {
    point = {lat: 9.929029, lng: -84.092173};
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 12,
      center: point
    });
      marker = new google.maps.Marker({
      position:point,
      draggable:true,
      map: map
    });

    marker.addListener('dragend', function(){
      point.lat = marker.getPosition().lat();
      point.lng = marker.getPosition().lng();
    });
  }


  function add(){
    company = {
              login: {
                      email: document.getElementById("email").value,
                      type: 1,
                      enable: -128
              },
              name:document.getElementById("name").value,
              description: document.getElementById("description").value,
              phoneNumber: document.getElementById("phone").value,
              longitude: point.lng,
              latitude: point.lat,
              positions: []
          };
    data=new FormData();
    data.append("company",JSON.stringify(company));
    data.append("profilePic",$("#profilePic")[0].files[0]);

              $.ajax({
                type: "POST",
                url: "doRegistryProcessCompany",
                data: data,
                processData: false,
                contentType: false,
                success: function (response) {
                   $("#modal-success").modal('show');
                   document.getElementById("CompanyRegistration").reset();
                },
                error: function (response){
                   $("#modal-error").modal('show');
                } 
              });

    } 
    

    function validate(event){
      var name=$("#name");
      var phone=$("#phone");
      var error=false;
      
	    $("#RegistroCompany input").removeClass("invalid");
	      if (name.val().length===0){
		        name.addClass("invalid");
		        error=true;
	      }

	    if (phone.val().length===0){
		      phone.addClass("invalid");
		      error=true;
	    }

	    if (error){
          event.preventDefault();
      }
  }
