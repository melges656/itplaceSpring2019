$(document).ready(function () {
    $.get( "/rest/summary/1", function( data ) {
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
        $( data.educations ).each(function( index ) {
            $( "#educations" ).append( "<li>" + this.education + "</li>" );
        });
        $( data.skills ).each(function( index ) {
            $( "#skills" ).append( "<li> Опыт работы с " + this.skill +
             " в месяцах: " + this.value + "</li>" );
        });
    });

});