$(document).ready(function () {
    $.get( "/rsummary/1", function( data ) {
        $( "#FIO" ).text( data.fio );
        $( "#DOB" ).text( data.dob );
        $( "#phone" ).text( data.phone );
        $( "#email" ).text( data.email );
        $( "#skype" ).text( data.skype );
        $( "#avatar" ).attr("src", data.avatar );
        $( "#target" ).text( data.target );
        $( "#experiences" ).text( data.experiences );
        $( "#additionalEducations" ).text( data.additionalEducations );
        $( "#examplesCode" ).html( data.examplesCode );
    });

});