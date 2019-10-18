$(document).ready(function() {
    $('#sightings-table').DataTable( {
        destroy: true,
        paging: false,
        initComplete: function () {
            this.api().columns(0).every( function () {
                var column = this;
                var select = $('<select><option value="">Select Date</option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );

                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
            this.api().columns(1).every( function () {
                var column = this;
                var select = $('<select><option value="">Select Superhero</option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );

                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        }
    } );
    $('#location-table').DataTable();
    $('#organization-table').DataTable();

    $('#power-table').DataTable();
    $('#super-table').DataTable();

} );
function openPowerModal(id) {
    $.ajax({
        url:"/power/" + id,
        success: function(data) {
            $("#powerModalHolder").html(data);
            $("#powerModal").modal("show");
        }
    })
}

function openSuperModal(id) {
    $.ajax({
        url:"/super/" + id,
        success: function(data) {
            $("#superModalHolder").html(data);
            $("#superModal").modal("show");
        }
    })
}
function openSightingModal(id) {
    $.ajax({
        url:"/sighting/" + id,
        success: function(data) {
            $("#sightingModalHolder").html(data);
            $("#sightingModal").modal("show");
        }
    })
}
function openLocationModal(id) {
    $.ajax({
        url:"/location/" + id,
        success: function(data) {
            $("#locationModalHolder").html(data);
            $("#locationModal").modal("show");
        }
    })
}
function openOrganizationModal(id) {
    $.ajax({
        url:"/organization/" + id,
        success: function(data) {
            $("#organizationModalHolder").html(data);
            $("#organizationModal").modal("show");
        }
    })
}
function form_submit() {
    document.getElementById("add-location-form").submit();
}
var placeSearch, autocomplete, place;

var componentForm = {
    street_number: 'short_name',
    route: 'long_name',
    locality: 'long_name',
    administrative_area_level_1: 'short_name',
    country: 'long_name',
    postal_code: 'short_name'
};

function initAutocomplete() {
    // Create the autocomplete object, restricting the search predictions to
    // geographical location types.
    autocomplete = new google.maps.places.Autocomplete(
        document.getElementById('autocomplete'), {types: ['geocode']});

    // Avoid paying for data that you don't need by restricting the set of
    // place fields that are returned to just the address components.
    autocomplete.setFields(['address_component', 'place_id', 'geometry']);

    // When the user selects an address from the drop-down, populate the
    // address fields in the form.
    autocomplete.addListener('place_changed', fillInAddress);
}

function fillInAddress() {
    // Get the place details from the autocomplete object.
    place = autocomplete.getPlace();
    document.getElementById('placeID').value = place.place_id;
    document.getElementById('latitude').value =  place.geometry.location.lat();
    document.getElementById('longitude').value =  place.geometry.location.lng();



    for (var component in componentForm) {
        document.getElementById(component).value = '';
        document.getElementById(component).disabled = false;
    }

    // Get each component of the address from the place details,
    // and then fill-in the corresponding field on the form.
    for (var i = 0; i < place.address_components.length; i++) {
        var addressType = place.address_components[i].types[0];
        if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
        }
    }
}
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
function geolocate() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var geolocation = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            var circle = new google.maps.Circle(
                {center: geolocation, radius: position.coords.accuracy});
            autocomplete.setBounds(circle.getBounds());
        });
    }
}
// function initMap() {
//     var myLatLng = {lat: -25.363, lng: 131.044};
//
//     var map = new google.maps.Map(document.getElementById('map'), {
//         zoom: 4,
//         center: myLatLng
//     });
//
//     var marker = new google.maps.Marker({
//         position: myLatLng,
//         map: map,
//         title: 'Hello World!'
//     });
// }

