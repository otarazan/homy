
$(document).ready(function() 
    { 
		//sorts all the table on the page which has tablesorter class
        $(".tablesorter").tablesorter(); 
        $( "#datepicker" ).datepicker();
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
        				$('#allTasks tbody').append('<tr><td>'+task.title+'</td><td>'+task.assignee+'</td><td><input type="checkbox" id="'+task.id+'"></td><td>'+task.recurrence+'</td><td>'+task.remainingDate+'</td></tr>');
        			}, 'json')
        })
        
		//change status
		$('input checkbox').click(function() {
			$.post('../task/changeStatus', {
				id : $(this).attr('id'),
				status : $(this).val()
			})
		});
		
    } 
); 

//when done button on task is clicked set as done and remove the box
function setTaskAsDone(boxid) {
	$.post('../task/changeStatus', {
		id : 1,
		status : true
	})
	$(boxid).fadeOut("slow");
}

