function initPage(){
	$(".image-box").mouseover(function() {
		 
	    $(".expand-box").show();
	     
	}).mouseout(function() {
	 
	    $(".expand-box").hide();
	});	
}


//sorts all the table on the page which has tablesorter class

$(document).ready(function() 
    { 
        $(".tablesorter").tablesorter(); 
        $( "#datepicker" ).datepicker();
        $("#txtTask").focusin(function() {$("#txtTask").val("")});
        $( "#tabs" ).tabs();
    } 
); 
