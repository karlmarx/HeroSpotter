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

$(document).ready(function() {
    $('#location-table').DataTable();

} );
