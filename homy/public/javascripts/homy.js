
$(document).ready(function() 
    { 
		//sorts all the table on the page which has tablesorter class
        //$(".tablesorter").tablesorter(); 
        $( "#datepicker" ).datepicker({ dateFormat: 'dd-mm-yy'}).datepicker('setDate', new Date());
        //When textbox is clicked 'add news task' string is removed
        $("#txtTask").focusin(function() {$("#txtTask").val("")});
        //jquery tab view is created as mytasks and alltasks
        $( "#tabs" ).tabs();
        
        
        $("#createTask").click(
        		function() {
        			$.post('../task/createTask', {
        				task : $("#txtTask").val(),
        				recurrence:$('#selectRecurrence :selected').val(),
        				assignee:$('#selectUser :selected').val(),
        				remainingDate:$('#datepicker').val()
        			}, function(task) {
        				$('#allTasks tbody').append('<tr><td>'+task.title+'</td><td>'+task.roomy.firstName+'</td><td><input type="checkbox" id="'+task.id+'"></td><td>'+task.recurrence+'</td><td>'+task.remainingDate+'</td></tr>');
        			}, 'json')
        })
      //change status
        $("#allTasks input[type='checkbox']").click(function() {
        	$.post('../task/changeStatus', {
        		id : $(this).attr('id'),
        		status : $(this).is(":checked")
        	})
        });
    } 
); 

//when done button on task is clicked set as done and remove the box
function setTaskAsDone(boxid,taskID) {
	$.post('../task/changeStatus', {
		id : taskID,
		status : true
	})
	$(boxid).fadeOut("slow");
}


//when pass button on task is clicked assign task to next person
function passTask(boxid,taskID) {
	$.post('../task/passTask', {
		id : taskID
	})
	$(boxid).fadeOut("slow");
}


function diffDate(f,e)
{
	return new Date(f - e);
}
