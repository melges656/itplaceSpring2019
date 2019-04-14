$(document).ready(function () {
    var table;
    $.get( "/rest/summary", function( data ) {
        var idColumnIndex = 2;
        $( data ).each(function( index ) {
            var tr = document.createElement( 'tr' );

            var fioColumn = document.createElement( 'td' );
            $( fioColumn ).text( this.fio );
            tr.append( fioColumn );

            var targetColumn = document.createElement( 'td' );
            $( targetColumn ).text( this.target );
            tr.append( targetColumn );

            var idColumn = document.createElement( 'td' );
            $( idColumn ).text( this.id );
            tr.append( idColumn );

            $( "#dataTableBody" ).append( tr );
        });

        table = $('#dataTable').DataTable( {
            "columnDefs": [ { className: "hide", "targets": [ idColumnIndex ] } ]
        });


        $('#dataTable tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass( 'selected' ) ) {
                $(this).removeClass( 'selected' );
                }
            else {
                table.$( 'tr.selected' ).removeClass( 'selected' );
                 $( this ).addClass( 'selected' );
            }
        });

        $( '#buttonDel' ).click( function () {
            var selectedRow = table.row( '.selected' );
            $.ajax({
                url: "/rest/summary/" + selectedRow.data()[ idColumnIndex ],
                type: 'DELETE',
                success: function(result) {
                    selectedRow.remove().draw( false );
                }
            });
        });

        $( '#buttonOpen' ).click( function () {
            var selectedRow = table.row( '.selected' );
            window.location.href = "/summary/" + selectedRow.data()[ idColumnIndex ];
        });

        $( '#buttonEdit' ).click( function () {
            var selectedRow = table.row( '.selected' );
            window.location.href = "/summary/edit/" + selectedRow.data()[ idColumnIndex ];
        });
    });

    $.get( "/rest/tags", function( data ) {
        var word_list = new Array();
        $( data ).each(function( index ) {
            word_list.push({word: this.name, weight: this.weight});
        });
        $( "#wordCloud" ).jQWCloud({
            words: word_list,
            word_click : function(){
                $.get( "/rest/summary/tag/" + $(this).text(), function( newData ) {
                    var newDataArray = new Array();
                    $( newData ).each(function( index ) {
                        var rowArray = new Array(3);
                        rowArray[0] = this.fio;
                        rowArray[1] = this.target;
                        rowArray[2] = this.id;
                        newDataArray.push(rowArray);
                    });
                    table.clear();
                    table.rows.add(newDataArray);
                    table.draw();
                });
            }
        });
    });
});