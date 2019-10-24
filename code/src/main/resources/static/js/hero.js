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
function openAddLocation() {
    $('#addLocation').modal("show");
}
function closeAddSightingOpenAddLocation() {
    $('#addSighting').modal("hide");
    openAddLocation();
}
function closeAddSightingOpenAddSuper () {
    $('#addSighting').modal("hide");
    openAddSuper();
}
function openAddSuper() {
    $("#addSuper").modal("show");
}
function form_submit() {
    document.getElementById("add-location-form").submit();
}

function confirm(heading, question, cancelButtonTxt, okButtonTxt, callback) {

    var confirmModal =
        $('<div class="modal hide fade">' +
            '<div class="modal-header">' +
            '<a class="close" data-dismiss="modal" >&times;</a>' +
            '<h3>' + heading +'</h3>' +
            '</div>' +

            '<div class="modal-body">' +
            '<p>' + question + '</p>' +
            '</div>' +

            '<div class="modal-footer">' +
            '<a href="#" class="btn" data-dismiss="modal">' +
            cancelButtonTxt +
            '</a>' +
            '<a href="#" id="okButton" class="btn btn-primary">' +
            okButtonTxt +
            '</a>' +
            '</div>' +
            '</div>');

    confirmModal.find('#okButton').click(function(event) {
        callback();
        confirmModal.modal('hide');
    });

    confirmModal.modal('show');
};

$("i#deleteTransaction").live("click", function(event) {
    // get txn id from current table row
    var id = $(this).data('id');

    var heading = 'Confirm Transaction Delete';
    var question = 'Please confirm that you wish to delete transaction ' + id + '.';
    var cancelButtonTxt = 'Cancel';
    var okButtonTxt = 'Confirm';

    var callback = function() {
        alert('delete confirmed ' + id);
    };

    confirm(heading, question, cancelButtonTxt, okButtonTxt, callback);

});


